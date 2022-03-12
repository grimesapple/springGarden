package com.djq.springGarden.controller;

import java.util.List;

import com.djq.springGarden.entity.OrderT;
import com.djq.springGarden.vo.OrderSearchVo;
import com.djq.springGarden.vo.OrderTVo;
import com.github.pagehelper.Page;
import org.springframework.web.bind.annotation.*;
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
@RequestMapping("/order" )
public class OrderController {
    @Resource
    private OrderService orderService;

    /**
     * 查询订单列表
     */
    @GetMapping("/search" )
    @ApiOperation("查询订单列表" )
    public ResultVO<Map<String,Object>> list(OrderT orderT,
                                             @RequestParam(value = "pageNum" , required = false, defaultValue = "1" ) Integer pageNum,
                                             @RequestParam(value = "pageSize" , required = false, defaultValue = "10" ) Integer pageSize) {
        HashMap<String, Object> map = new HashMap<>();
        Page<Object> info = PageHelper.startPage(pageNum, pageSize);
        List<OrderT> list = orderService.search(orderT);
        map.put("total" , info.getTotal());
        map.put("list" , list);
        return ResultVO.ok(map,"查询成功");
    }

    /**
     * 条件查询订单列表
     */
    @GetMapping("/list" )
    @ApiOperation("条件查询订单列表" )
    public ResultVO<Map<String,Object>> list(OrderTVo orderTVo,
                                             @RequestParam(value = "pageNum" , required = false, defaultValue = "1" ) Integer pageNum,
                                             @RequestParam(value = "pageSize" , required = false, defaultValue = "10" ) Integer pageSize) {
        HashMap<String, Object> map = new HashMap<>();
        Page<Object> info = PageHelper.startPage(pageNum, pageSize);
        List<Map<String, Object>> list = orderService.select(orderTVo);
        map.put("total" , info.getTotal());
        map.put("list" , list);
        return ResultVO.ok(map,"查询成功");
    }


    /**
     * 获取订单详细信息
     */
    @ApiOperation("获取订单详细信息" )
    @GetMapping(value = "/searchOne" )
    public ResultVO<OrderT> searchOne(OrderT orderT) {
        return ResultVO.ok(orderService.selectOne(orderT));
    }

    /**
     * 新增订单
     */
    @ApiOperation("新增订单" )
    @PostMapping("/add" )
    public ResultVO<OrderT> add(@RequestBody OrderSearchVo orderSearchVo) {
        int i = orderService.insertOrder(orderSearchVo);
        if (i == -1) {
            return ResultVO.error("房间已被预定,请更换时间段");
        } else if (i == 0) {
            return ResultVO.error("订单插入失败");
        } else if (i == 1) {
            return ResultVO.error("入住信息插入失败");
        } else {
            return ResultVO.ok("预定成功");
        }
    }

    /**
     * 修改订单
     */
    @ApiOperation("修改订单" )
    @PostMapping("/update" )
    public ResultVO<OrderT> edit(OrderT orderT) {
        return orderService.updateOrder(orderT) > 0 ? ResultVO.ok("更新成功" ) : ResultVO.error("更新失败" );
    }

    /**
     * 删除订单
     */
    @ApiOperation("删除订单" )
    @PostMapping("/delete" )
    public ResultVO<OrderT> remove(@RequestParam(value = "id" ) Integer id) {
        return orderService.deleteOrderById(id) > 0 ? ResultVO.ok("删除成功" ) : ResultVO.error("删除失败" );
    }
}
