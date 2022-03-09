package com.djq.springGarden.controller;

import java.util.List;

import com.djq.springGarden.entity.Property;
import org.springframework.web.bind.annotation.*;
import com.djq.springGarden.service.PropertyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.Map;

import com.github.pagehelper.PageHelper;
import com.djq.springGarden.vo.ResultVO;

import javax.annotation.Resource;


/**
 * 房间属性字典Controller
 *
 * @author duanjiaqi
 */
@Api(tags = "房间属性字典控制器" )
@RestController
@RequestMapping("/property" )
public class PropertyController {
    @Resource
    private PropertyService propertyService;

    /**
     * 查询房间属性字典列表
     */
    @GetMapping("/list" )
    @ApiOperation("查询房间属性字典列表" )
    public ResultVO<Map<String,Object>> list(Property property,
                                             @RequestParam(value = "pageNum" , required = false, defaultValue = "1" ) Integer pageNum,
                                             @RequestParam(value = "pageSize" , required = false, defaultValue = "10" ) Integer pageSize) {
        HashMap<String, Object> map = new HashMap<>();
        PageHelper.startPage(pageNum, pageSize);
        List<Property> list = propertyService.select(property);
        map.put("total" , list.size());
        map.put("list" , list);
        return ResultVO.ok(map,"查询成功");
    }

    /**
     * 获取房间属性字典详细信息
     */
    @ApiOperation("获取房间属性字典详细信息" )
    @GetMapping(value = "/searchOne" )
    public ResultVO<Property> searchOne(Property property) {
        return ResultVO.ok(propertyService.selectOne(property));
    }

    /**
     * 新增房间属性字典
     */
    @ApiOperation("新增房间属性字典" )
    @PostMapping("/add" )
    public ResultVO<Property> add(@RequestBody Property property) {
        return propertyService.insertProperty(property) > 0 ? ResultVO.ok("新增成功" ) : ResultVO.error("新增失败" );
    }

    /**
     * 修改房间属性字典
     */
    @ApiOperation("修改房间属性字典" )
    @PostMapping("/update" )
    public ResultVO<Property> edit(@RequestBody Property property) {
        return propertyService.updateProperty(property) > 0 ? ResultVO.ok("更新成功" ) : ResultVO.error("更新失败" );
    }

    /**
     * 删除房间属性字典
     */
    @ApiOperation("删除房间属性字典" )
    @GetMapping("/delete" )
    public ResultVO<Property> remove(@RequestParam(value = "id") Integer id) {
        return propertyService.deletePropertyById(id) > 0 ? ResultVO.ok("删除成功" ) : ResultVO.error("删除失败" );
    }
}
