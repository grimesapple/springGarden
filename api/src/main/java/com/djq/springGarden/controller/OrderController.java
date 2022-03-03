package com.djq.springGarden.controller;

import java.util.List;

import com.djq.springGarden.entity.Order;
import com.djq.springGarden.vo.OrderSearchVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import com.djq.springGarden.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.Map;

import com.github.pagehelper.PageHelper;
import com.djq.springGarden.vo.ResultVO;

import javax.annotation.Resource;


/**
 * 订单Controller
 *
 * @author duanjiaqi
 */
@Api(tags = "订单控制器" )
@RestController
@RequestMapping("/system/order" )
public class OrderController {
    @Resource
    private OrderService orderService;

    /**
     * 查询订单列表
     */
    @GetMapping("/list" )
    @ApiOperation("查询订单列表" )
    public ResultVO<Map<String,Object>> list(Order order,
                                             @RequestParam(value = "pageNum" , required = false, defaultValue = "1" ) Integer pageNum,
                                             @RequestParam(value = "pageSize" , required = false, defaultValue = "10" ) Integer pageSize) {
        HashMap<String, Object> map = new HashMap<>();
        PageHelper.startPage(pageNum, pageSize);
        List<Order> list = orderService.select(order);
        map.put("total" , list.size());
        map.put("list" , list);
        return ResultVO.ok(map,"查询成功");
    }

    /**
     * 获取订单详细信息
     */
    @ApiOperation("获取订单详细信息" )
    @GetMapping(value = "/searchOne" )
    public ResultVO<Order> searchOne(Order order) {
        return ResultVO.ok(orderService.selectOne(order));
    }

    /**
     * 新增订单
     */
    @ApiOperation("新增订单" )
    @PostMapping("/add" )
    public ResultVO<Order> add(OrderSearchVo orderSearchVo) {
        return orderService.insertOrder(orderSearchVo) > 0 ? ResultVO.ok("新增成功" ) : ResultVO.error("新增失败" );
    }

    /**
     * 修改订单
     */
    @ApiOperation("修改订单" )
    @PostMapping("/update" )
    public ResultVO<Order> edit(Order order) {
        return orderService.updateOrder(order) > 0 ? ResultVO.ok("更新成功" ) : ResultVO.error("更新失败" );
    }

    /**
     * 删除订单
     */
    @ApiOperation("删除订单" )
    @PostMapping("/delete" )
    public ResultVO<Order> remove(@RequestParam(value = "id" ) Integer id) {
        return orderService.deleteOrderById(id) > 0 ? ResultVO.ok("删除成功" ) : ResultVO.error("删除失败" );
    }
}
