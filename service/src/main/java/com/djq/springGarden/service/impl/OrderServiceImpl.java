package com.djq.springGarden.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import com.djq.springGarden.vo.OrderTVo;
import com.github.pagehelper.util.StringUtil;
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
     * @param orderTVo 查询条件
     * @return 订单
     */
    @Override
    public List<Map<String, Object>> select(OrderTVo orderTVo) {
        //查询条件，筛选入住时间，状态，支付时间，手机尾号，账号，订单编号。
        //展示信息：订单编号，房间的信息(房间号)，预订人信息（账号名称，姓名，手机号码），预定时间（创建时间），选定时间:(入住时间，离开时间)，状态，总价格，备注，操作（入住，退房）
        List<Map<String, Object>> mapList = new ArrayList<>();
        //部分条件筛选
        Example example = new Example(OrderT.class);
        Example.Criteria criteria = example.createCriteria();
        //状态：
        Integer status = orderTVo.getStatus();
        if (status != null) {
            criteria.andEqualTo("status", status);
        }
        //订单编号
        String orderCode = orderTVo.getOrderCode();
        if (StringUtil.isNotEmpty(orderCode)) {
            criteria.andEqualTo("orderCode", orderCode);
        }
        //入住时间筛选
        Date startTimeVo = orderTVo.getStartTimeVo();
        Date endTimeVO = orderTVo.getEntTimeVo();
        if (startTimeVo != null && endTimeVO != null) {
            criteria.andBetween("startTime", startTimeVo, endTimeVO);
        }
        //支付时间筛选
        Date startTimePay = orderTVo.getStartTimePay();
        Date endTimePay = orderTVo.getEndTimePay();
        if (startTimePay != null && endTimePay != null) {
            criteria.andBetween("payDate", startTimePay, endTimePay);
        }
        //按时间顺序排列
        example.orderBy("createTime").desc();
        //查询
        List<OrderT> orderTList = orderMapper.selectByExample(example);
        //遍历结果继续筛选和封装数据
        for (OrderT t : orderTList) {
            //各项数据查询
            //房间的数据
            Integer productId = t.getProductId();
            Product product = new Product();
            product.setId(productId);
            Product productResult = productMapper.selectOne(product);

            //账号信息
            User user;
            User userResult = null;
            if (t.getUserId() != null) {
                user = new User();
                user.setId(t.getUserId());
                userResult = userMapper.selectOne(user);
            }

            //入住人信息
            Orderitem orderitem = new Orderitem();
            orderitem.setId(t.getResidentId());
            Orderitem orderitemReuslt = orderitemMapper.selectOne(orderitem);

            //条件筛选
            //手机尾号(四位)
            String phone = orderTVo.getPhone();
            if (StringUtil.isNotEmpty(phone)) {
                String mobile = orderitemReuslt.getMobile();
                String substring = mobile.substring(mobile.length() - 4, mobile.length() - 1);
                if (!phone.equals(substring)) {
                    continue;
                }
            }
            //账号名称
            String username = orderTVo.getUsername();
            if (username != null && userResult != null) {
                String name = userResult.getName();
                if (!username.equals(name)) {
                    continue;
                }
            }


            //封装数据
            Map<String, Object> result = new HashMap<>();
            result.put("orderId", t.getId());
            result.put("orderCode", t.getOrderCode());
            result.put("createTime", dateformat(t.getCreateTime()));
            result.put("startTime", dateformat(t.getStartTime()));
            result.put("endTime", dateformat(t.getEndTime()));
            result.put("status", t.getStatus());
            result.put("totalCost", t.getTotal());
            result.put("remark", t.getUserMessage());
            result.put("number", t.getNumber());
            //房间的数据
            result.put("productId", productResult == null ? null : productResult.getId());
            result.put("productName", productResult == null ? null : productResult.getName());
            // 账号信息
            result.put("userId", userResult == null ? null : userResult.getId());
            result.put("username", userResult == null ? null : userResult.getName());
            //入住人信息
            result.put("realName", orderitemReuslt == null ? null : orderitemReuslt.getReceiver());
            result.put("phone", orderitemReuslt == null ? null : orderitemReuslt.getMobile());
            mapList.add(result);
        }

        return mapList;
    }

    private String dateformat(Date time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(time);
    }


    /**
     * 简单条件查询列表
     *
     * @param orderT
     * @return
     */
    @Override
    public List<OrderT> search(OrderT orderT) {
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
        criteria4User.andLike("name", "%" + username + "%");
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
        criteria4product.andLike("name", "%" + productName + "%");
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
            criteria.andIn("userId", userIdList);
        }
        if (productIdList.size() != 0) {
            criteria.andIn("userId", productIdList);
        }

        //封装订单信息

        return orderMapper.selectByExample(example);
    }


    /**
     * 新增订单；（预定）
     *
     * @param orderSearchVo 订单
     * @return 结果
     */
    @Override
    public int insertOrder(OrderSearchVo orderSearchVo) {
        //插入内容：预定时间，入住时间，离开时间，入住人数，房间id，预定人信息（姓名，联系方式，身份证）,账号
        //校验订单
        //预定或者入住的结束时间
        Date startTime = orderSearchVo.getStartTime();
        //预定或者入住的开始时间
        Date endTime = orderSearchVo.getEndTime();
        //校验对应房间是否已经被预定
        if (!check(startTime, endTime, orderSearchVo.getProductId())) {
            //有冲突，不能通过预定
            return -1;
        }
        //初始化订单编号
        String businessNum = RandomUtils.businessNum();
        orderSearchVo.setOrderCode(businessNum);
        //时间初始化
        orderSearchVo.setCreateTime(new Date());
        //计算价格
        long time = endTime.getTime() - startTime.getTime();
        long day = time / 1000 / 3600 / 24;
        //获取房屋的价格
        Product product = new Product();
        product.setId(orderSearchVo.getProductId());
        product = productMapper.selectOne(product);
        BigDecimal promotePrice = product.getPromotePrice();
        BigDecimal price = promotePrice.multiply(new BigDecimal(day));
        orderSearchVo.setTotal(price);
        //预定信息
        orderSearchVo.setStatus(1);
        //订单插入
        int orderNum = orderMapper.insertUseGeneratedKeys(orderSearchVo);

        //入住人信息插入
        Integer id = orderSearchVo.getId();
        List<Orderitem> orderitems = orderSearchVo.getOrderitems();
        for (Orderitem orderitem : orderitems) {
            orderitem.setOrderId(id);
        }
        Orderitem orderitem = orderitems.get(0);
        int itemNum = orderitemMapper.insertUseGeneratedKeys(orderitem);

        //查询入住人id,更新订单id
        orderSearchVo.setResidentId(orderitem.getId());

        //如果是入住则调用入住接口
        if (orderSearchVo.getStatus() == 2) {
            intoHouse(orderSearchVo);
        } else {
            orderMapper.updateByPrimaryKeySelective(orderSearchVo);
        }
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
    @Override
    public int intoHouse(OrderSearchVo orderSearchVo) {
        //对应订单:设置对应订单状态：
        orderSearchVo.setStatus(2);
        int orderNum = orderMapper.updateByPrimaryKeySelective(orderSearchVo);

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
     * 办理退房
     *
     * @param orderSearchVo 对应订单
     * @return 返回结果
     */
    @Override
    public int stayOUt(OrderSearchVo orderSearchVo) {
        //对应订单:设置对应订单状态：
        orderSearchVo.setStatus(0);
        return orderMapper.updateByPrimaryKeySelective(orderSearchVo);
    }

    /**
     * 取消预订
     *
     * @param orderSearchVo 对应订单
     * @return 返回结果
     */
    @Override
    public int cancelPre(OrderSearchVo orderSearchVo) {
        //对应订单:设置对应订单状态：
        orderSearchVo.setStatus(3);
        return orderMapper.updateByPrimaryKeySelective(orderSearchVo);
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
            for (int i = 0; i < orderitems.size(); i++) {
                Orderitem orderitem = orderitems.get(i);
                if (i == 0) {
                    //更新
                    orderItemNum = orderitemMapper.updateByPrimaryKey(orderitem);
                } else {
                    //插入
                    orderItemNum += orderitemMapper.insert(orderitem);
                }
            }
        }
        return orderItemNum;
    }


    /**
     * 判断当前选定时间是否可以入住是可预定
     *
     * @param endTime   预定或者入住的结束时间
     * @param startTime 预定或者入住的开始时间
     * @param productId 房间id
     * @return true:无冲突，可以预定.false：有冲突。
     */
    @Override
    public boolean check(Date startTime, Date endTime, Integer productId) {
//        //查询对应的房间列表：预定
//        OrderT orderTForSearch = new OrderT();
//        orderTForSearch.setProductId(productId);
//        orderTForSearch.setStatus(1);
//        List<OrderT> orderTList = orderMapper.select(orderTForSearch);
//        //查询对应的房间列表：已入住
//        orderTForSearch.setStatus(2);
        Example example = new Example(OrderT.class);
        Example.Criteria criteria = example.createCriteria();
        Example.Criteria criteriaAnd = example.and();
        Example.Criteria criteriaAnd2 = example.and();
        criteria.andEqualTo("productId", productId);
        criteriaAnd.andEqualTo("status", 1);
        criteriaAnd.orEqualTo("status", 2);
        criteriaAnd2.andBetween("startTime", startTime, endTime);
        criteriaAnd2.orBetween("endTime", startTime, endTime);
        List<OrderT> list2 = orderMapper.selectByExample(example);

        if (list2.size() == 0) {
            return true;
        } else if (list2.size() == 1) {
            //判断订单时间是否是筛选时间的边缘
            OrderT orderT = list2.get(0);
            Date orderStartTime1 = orderT.getStartTime();
            Date orderEndTime1 = orderT.getEndTime();
            //第一个订单是否在边缘，是为true，否为FALSE
            boolean a = orderEndTime1.equals(startTime) || orderStartTime1.equals(endTime);
            if (list2.size() == 1) {
                //只有一个订单，判断订单是否在时间段边缘
                return a;
            } else {
                //有两个订单,判断是否两个都在时间段边缘
                OrderT orderT1 = list2.get(1);
                Date orderStartTime2 = orderT1.getStartTime();
                Date orderEndTime2 = orderT1.getEndTime();
                //第二个订单是否在边缘，是为true，否为FALSE
                boolean b = orderEndTime2.equals(startTime) || orderStartTime2.equals(endTime);
                return a && b;
            }
        }


        return false;
//        //两种订单合并
//        orderTList.addAll(list2);
//        //订单需要根据时间进行排序：前一个订单和后一个订单的时间不会有交叉，最多是相等。(降序)
//        orderTList.sort(Comparator.comparing(OrderT::getStartTime).reversed());

        //遍历订单在选定时间中是否有冲突
/*        boolean flag = true;
        for (OrderT orderT : list2) {
            return true;
        }*/


       /* if (orderTList.size() == 1) {
            OrderT orderT = orderTList.get(0);
            long startTime1 = orderT.getStartTime().getTime();
            long endTime1 = orderT.getEndTime().getTime();
            //大于0 小于l 订单包含开始时间,大于l 在后面，小于0在前面
            long l = endTime1 - startTime1;
            long i1 = l - startTime.getTime() - startTime1;
            //大于0 小于l 订单包含结束时间,大于l 在后面，小于0在前面
            long i2 = l - endTime.getTime() - startTime1;
            if ((i1 > 0 && i1 < l) || (i2 > 0 && i2 < l)) {
                return true;
            }
        }

        for (int i = 0; i < orderTList.size()-1; i++) {
            //当前订单
            OrderT firstOrderT = orderTList.get(i);
            //前一段订单
            OrderT secondOrderT = orderTList.get(i+1);
            //判断选择的时间在所有订单之后
            if (i == 0) {
                if (firstOrderT.getEndTime().before(startTime)) {
                    return true;
                }
            }
            //判断选择的时间在所有订单之前
            if (i == orderTList.size()-2) {
                if (secondOrderT.getStartTime().after(startTime)) {
                    return true;
                }
            }


            //判断选定时间是否在这两个订单之间
            if (secondOrderT.getEndTime().before(startTime) && firstOrderT.getStartTime().after(endTime)) {
                //订单不冲突
                flag = true;
                break;
            }
            flag = false;
        }*/
//        return flag;
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
