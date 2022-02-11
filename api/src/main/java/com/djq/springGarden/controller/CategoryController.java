package com.djq.springGarden.controller;

import java.util.List;

import com.djq.springGarden.entity.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import com.djq.springGarden.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.Map;

import com.github.pagehelper.PageHelper;
import com.djq.springGarden.vo.ResultVO;

import javax.annotation.Resource;


/**
 * 房间类型Controller
 *
 * @author duanjiaqi
 */
@Api(tags = "房间类型控制器" )
@RestController
@RequestMapping("/system/category" )
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    /**
     * 查询房间类型列表
     */
    @GetMapping("/list" )
    @ApiOperation("查询房间类型列表" )
    public ResultVO<Map<String,Object>> list(Category category,
                                             @RequestParam(value = "pageNum" , required = false, defaultValue = "1" ) Integer pageNum,
                                             @RequestParam(value = "pageSize" , required = false, defaultValue = "10" ) Integer pageSize) {
        HashMap<String, Object> map = new HashMap<>();
        PageHelper.startPage(pageNum, pageSize);
        List<Category> list = categoryService.select(category);
        map.put("total" , list.size());
        map.put("list" , list);
        return ResultVO.ok(map,"查询成功");
    }

    /**
     * 获取房间类型详细信息
     */
    @ApiOperation("获取房间类型详细信息" )
    @GetMapping(value = "/searchOne" )
    public ResultVO<Category> searchOne(Category category) {
        return ResultVO.ok(categoryService.selectOne(category));
    }

    /**
     * 新增房间类型
     */
    @ApiOperation("新增房间类型" )
    @PostMapping("/add" )
    public ResultVO<Category> add(Category category) {
        return categoryService.insertCategory(category) > 0 ? ResultVO.ok("新增成功" ) : ResultVO.error("新增失败" );
    }

    /**
     * 修改房间类型
     */
    @ApiOperation("修改房间类型" )
    @PostMapping("/update" )
    public ResultVO<Category> edit(Category category) {
        return categoryService.updateCategory(category) > 0 ? ResultVO.ok("更新成功" ) : ResultVO.error("更新失败" );
    }

    /**
     * 删除房间类型
     */
    @ApiOperation("删除房间类型" )
    @PostMapping("/delete" )
    public ResultVO<Category> remove(@RequestParam(value = "id" ) Integer id) {
        return categoryService.deleteCategoryById(id) > 0 ? ResultVO.ok("删除成功" ) : ResultVO.error("删除失败" );
    }
}
