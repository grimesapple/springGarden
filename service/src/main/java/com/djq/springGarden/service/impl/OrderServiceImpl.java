package com.djq.springGarden.service.impl;

import java.util.*;

import javax.annotation.Resource;

import com.djq.springGarden.entity.OrderT;
import com.djq.springGarden.entity.Orderitem;
import com.djq.springGarden.entity.Product;
import com.djq.springGarden.entity.User;
import com.djq.springGarden.mapper.OrderitemMapper;
import com.djq.springGarden.mapper.ProductMapper;
import com.djq.springGarden.mapper.UserMapper;
import com.djq.springGarden.util.RandomUtils;
import com.djq.springGarden.vo.OrderTConditionVo;
import com.djq.springGarden.vo.OrderSearchVo;
import lombok.Data;
import org.springframework.stereotype.Service;
import com.djq.springGarden.mapper.OrderMapper;
import com.djq.springGarden.service.OrderService;
import tk.mybatis.mapper.entity.Example;


/**
 * 订单Service业务层处理
 *
 * @author duanjiaqi
 * @date 2022-02-02
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderitemMapper orderitemMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private ProductMapper productMapper;

    /**
     * 条件查询订单列表
     *
     * @param orderT 订单
     * @return 订单
     */
    @Override
    public List<OrderT> select(OrderT orderT) {
        return orderMapper.select(orderT);
    }

    /**
     * 条件查询订单单个
     *
     * @param orderT 订单
     * @return 订单
     */
    @Override
    public OrderT selectOne(OrderT orderT) {
        return orderMapper.selectOne(orderT);
    }

    /**
     * 订单列表详情
     *
     * @return 完成信息
     */
    public List<OrderT> list(OrderTConditionVo orderConditionVo) {

        //根据手机尾号
        Integer number = orderConditionVo.getNumber();
        //用户账号姓名
        String username = orderConditionVo.getUsername();
        Example userExample = new Example(User.class);
        Example.Criteria criteria4User = userExample.createCriteria();
        criteria4User.andLike("name","%"+username+"%");
        List<User> users = userMapper.selectByExample(userExample);
        ArrayList<String> userIdList = new ArrayList<>();
        for (User user : users) {
            //封装id
            Integer id = user.getId();
            userIdList.add(id.toString());
        }

        //客房名字
        String productName = orderConditionVo.getProductName();
        Example productExample = new Example(Product.class);
        Example.Criteria criteria4product = productExample.createCriteria();
        criteria4product.andLike("name","%"+productName+"%");
        List<Product> products = productMapper.selectByExample(productExample);
        ArrayList<String> productIdList = new ArrayList<>();
        for (Product product : products) {
            //封装id
            Integer id = product.getId();
            productIdList.add(id.toString());
        }

        //时间
        Data firstTime = orderConditionVo.getFirstTime();
        Data lastTime = orderConditionVo.getLastTime();

        //拼接条件
        Example example = new Example(OrderT.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("number", number + "%");
        if (firstTime != null && lastTime != null) {
            criteria.andBetween("startTime", firstTime, lastTime);
        }
        if (userIdList.size() != 0) {
            criteria.andIn("userId",userIdList);
        }
        if (productIdList.size() != 0) {
            criteria.andIn("userId",productIdList);
        }

        List<OrderT> orderTList = orderMapper.selectByExample(example);

        //封装订单信息

        return orderTList;
    }


    /**
     * 新增订单；（预定）
     *
     * @param orderSearchVo 订单
     * @return 结果
     */
    @Override
    public int insertOrder(OrderSearchVo orderSearchVo) {
        //插入订单
        OrderT orderT = orderSearchVo.getOrderT();
        //预定或者入住的结束时间
        Date startTime = new Date();
        //预定或者入住的开始时间
        Date endTime = new Date();
        //校验对应房间是否已经被预定
        if (!check(startTime,endTime,orderT.getProductId())) {
            //有冲突，不能通过预定
            return 0;
        }
        //初始化订单编号
        String businessNum = RandomUtils.businessNum();
        orderT.setOrderCode(businessNum);

        //订单插入
        int orderNum = orderMapper.insert(orderT);

        //设置入住人信息
        int itemNum = insertBatchOrderItem(orderSearchVo.getOrderitems());
        if (orderNum == 0) {
            return 0;
        }
        if (itemNum == 0) {
            //表示没有出入住户信息
            return 1;
        }
        return 2;
    }

    /**
     * 办理入住
     *
     * @param orderSearchVo 对应订单
     * @return 返回结果
     */
    public int intoHouse(OrderSearchVo orderSearchVo) {
        //对应订单:设置对应订单状态：
        OrderT orderT = orderSearchVo.getOrderT();
        orderT.setStatus(2);
        int orderNum = orderMapper.updateByPrimaryKey(orderT);

        //办理入住：核对结果，添加住户信息,点击确认，办理入住,
        int orderItemNum = insertBatchOrderItem(orderSearchVo.getOrderitems());
        if (orderNum == 0) {
            return 0;
        }
        if (orderItemNum == 0) {
            return 1;
        }
        return 2;
    }


    /**
     * 新增订单
     *
     * @return 返回结果
     */
    public int addOrder(OrderT orderT) {
        //新增订单
        return orderMapper.insert(orderT);
    }


    /**
     * 批量新增住户信息。
     *
     * @param orderitems 住户信息列表
     * @return 插入行数
     */
    public int insertBatchOrderItem(List<Orderitem> orderitems) {
        int orderItemNum = 0;
        if (orderitems != null && !orderitems.isEmpty() && orderitems.get(0) != null) {
            for (Orderitem orderitem : orderitems) {
                orderItemNum += orderitemMapper.insert(orderitem);
            }
        }
        return orderItemNum;
    }


    /**
     * 判断当前选定时间是否可以入住是可预定
     *
     * @param endTime 预定或者入住的结束时间
     * @param startTime 预定或者入住的开始时间
     * @param productId 房间id
     * @return true:无冲突，可以预定.false：有冲突。
     */
    @Override
    public boolean check(Date startTime,Date endTime,Integer productId) {
        //查询对应的房间列表：预定
        OrderT orderTForSearch = new OrderT();
        orderTForSearch.setProductId(productId);
        orderTForSearch.setStatus(1);
        List<OrderT> orderTList = select(orderTForSearch);
        //查询对应的房间列表：已入住
        orderTForSearch.setStatus(2);
        List<OrderT> list2 = select(orderTForSearch);
        //两种订单合并
        orderTList.addAll(list2);
        //订单需要根据时间进行排序：前一个订单和后一个订单的时间不会有交叉，最多是相等。(升序)
        orderTList.sort(Comparator.comparing(OrderT::getStartTime));

        //遍历订单在选定时间中是否有冲突
        boolean flag = true;
        for (int i = orderTList.size() - 1; i > 0; i++) {
            //当前订单
            OrderT firstOrderT = orderTList.get(i);
            //下一时间段订单
            OrderT secondOrderT = orderTList.get(--i);

            //判断选定时间是否在这两个订单之间
            if (firstOrderT.getEndTime().before(startTime) && secondOrderT.getStartTime().after(endTime)) {
                //订单不冲突
                flag = true;
                break;
            }
            flag = false;
        }
        return flag;
    }

    /**
     * 修改订单
     *
     * @param orderT 订单
     * @return 结果
     */
    @Override
    public int updateOrder(OrderT orderT) {
        return orderMapper.updateByPrimaryKey(orderT);
    }

    /**
     * 删除订单信息
     *
     * @param id 订单主键
     * @return 结果
     */
    @Override
    public int deleteOrderById(Integer id) {

        OrderT orderT = new OrderT();
        orderT.setId(id);
        return orderMapper.delete(orderT);
    }

}
