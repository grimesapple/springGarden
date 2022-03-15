package com.djq.springGarden.controller;

import java.util.List;

import com.djq.springGarden.entity.Orderitem;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import com.djq.springGarden.service.OrderitemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.Map;

import com.github.pagehelper.PageHelper;
import com.djq.springGarden.vo.ResultVO;

import javax.annotation.Resource;


/**
 * 订单详情-入住人信息Controller
 *
 * @author duanjiaqi
 */
@Api(tags = "订单详情-入住人信息控制器" )
@RestController
@RequestMapping("/orderitem" )
public class OrderitemController {
    @Resource
    private OrderitemService orderitemService;

    /**
     * 查询订单详情-入住人信息列表
     */
    @GetMapping("/list" )
    @ApiOperation("查询订单详情-入住人信息列表" )
    public ResultVO<Map<String,Object>> list(Orderitem orderitem,
                                             @RequestParam(value = "pageNum" , required = false, defaultValue = "1" ) Integer pageNum,
                                             @RequestParam(value = "pageSize" , required = false, defaultValue = "10" ) Integer pageSize) {
        HashMap<String, Object> map = new HashMap<>();
        PageHelper.startPage(pageNum, pageSize);
        List<Orderitem> list = orderitemService.select(orderitem);
        map.put("total" , list.size());
        map.put("list" , list);
        return ResultVO.ok(map,"查询成功");
    }

    /**
     * 获取订单详情-入住人信息详细信息
     */
    @ApiOperation("获取订单详情-入住人信息详细信息" )
    @GetMapping(value = "/searchOne" )
    public ResultVO<Orderitem> searchOne(Orderitem orderitem) {
        return ResultVO.ok(orderitemService.selectOne(orderitem));
    }

    /**
     * 新增订单详情-入住人信息
     */
    @ApiOperation("新增订单详情-入住人信息" )
    @PostMapping("/add" )
    public ResultVO<Orderitem> add(Orderitem orderitem) {
        return orderitemService.insertOrderitem(orderitem) > 0 ? ResultVO.ok("新增成功" ) : ResultVO.error("新增失败" );
    }

    /**
     * 修改订单详情-入住人信息
     */
    @ApiOperation("修改订单详情-入住人信息" )
    @PostMapping("/update" )
    public ResultVO<Orderitem> edit(Orderitem orderitem) {
        return orderitemService.updateOrderitem(orderitem) > 0 ? ResultVO.ok("更新成功" ) : ResultVO.error("更新失败" );
    }

    /**
     * 删除订单详情-入住人信息
     */
    @ApiOperation("删除订单详情-入住人信息" )
    @PostMapping("/delete" )
    public ResultVO<Orderitem> remove(@RequestParam(value = "id" ) Integer id) {
        return orderitemService.deleteOrderitemById(id) > 0 ? ResultVO.ok("删除成功" ) : ResultVO.error("删除失败" );
    }
}
