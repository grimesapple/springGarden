package com.djq.springGarden.controller;

import java.util.List;

import com.djq.springGarden.entity.Propertyvalue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import com.djq.springGarden.service.PropertyvalueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.Map;

import com.github.pagehelper.PageHelper;
import com.djq.springGarden.vo.ResultVO;

import javax.annotation.Resource;


/**
 * 房间属性情况Controller
 *
 * @author duanjiaqi
 */
@Api(tags = "房间属性情况控制器" )
@RestController
@RequestMapping("/propertyvalue" )
public class PropertyvalueController {
    @Resource
    private PropertyvalueService propertyvalueService;

    /**
     * 查询房间属性情况列表
     */
    @GetMapping("/list" )
    @ApiOperation("查询房间属性情况列表" )
    public ResultVO<Map<String,Object>> list(Propertyvalue propertyvalue,
                                             @RequestParam(value = "pageNum" , required = false, defaultValue = "1" ) Integer pageNum,
                                             @RequestParam(value = "pageSize" , required = false, defaultValue = "10" ) Integer pageSize) {
        HashMap<String, Object> map = new HashMap<>();
        PageHelper.startPage(pageNum, pageSize);
        List<Propertyvalue> list = propertyvalueService.select(propertyvalue);
        map.put("total" , list.size());
        map.put("list" , list);
        return ResultVO.ok(map,"查询成功");
    }

    /**
     * 获取房间属性情况详细信息
     */
    @ApiOperation("获取房间属性情况详细信息" )
    @GetMapping(value = "/searchOne" )
    public ResultVO<Propertyvalue> searchOne(Propertyvalue propertyvalue) {
        return ResultVO.ok(propertyvalueService.selectOne(propertyvalue));
    }

    /**
     * 新增房间属性情况
     */
    @ApiOperation("新增房间属性情况" )
    @PostMapping("/add" )
    public ResultVO<Propertyvalue> add(Propertyvalue propertyvalue) {
        return propertyvalueService.insertPropertyvalue(propertyvalue) > 0 ? ResultVO.ok("新增成功" ) : ResultVO.error("新增失败" );
    }

    /**
     * 修改房间属性情况
     */
    @ApiOperation("修改房间属性情况" )
    @PostMapping("/update" )
    public ResultVO<Propertyvalue> edit(Propertyvalue propertyvalue) {
        return propertyvalueService.updatePropertyvalue(propertyvalue) > 0 ? ResultVO.ok("更新成功" ) : ResultVO.error("更新失败" );
    }

    /**
     * 删除房间属性情况
     */
    @ApiOperation("删除房间属性情况" )
    @PostMapping("/delete" )
    public ResultVO<Propertyvalue> remove(@RequestParam(value = "id" ) Integer id) {
        return propertyvalueService.deletePropertyvalueById(id) > 0 ? ResultVO.ok("删除成功" ) : ResultVO.error("删除失败" );
    }
}
