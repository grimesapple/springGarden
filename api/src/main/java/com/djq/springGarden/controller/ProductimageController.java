package com.djq.springGarden.controller;

import java.util.List;

import com.djq.springGarden.entity.Productimage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import com.djq.springGarden.service.ProductimageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.Map;

import com.github.pagehelper.PageHelper;
import com.djq.springGarden.vo.ResultVO;

import javax.annotation.Resource;


/**
 * 客房图片Controller
 *
 * @author duanjiaqi
 */
@Api(tags = "客房图片控制器" )
@RestController
@RequestMapping("/system/productimage" )
public class ProductimageController {
    @Resource
    private ProductimageService productimageService;

    /**
     * 查询客房图片列表
     */
    @GetMapping("/list" )
    @ApiOperation("查询客房图片列表" )
    public ResultVO<Map<String,Object>> list(Productimage productimage,
                                             @RequestParam(value = "pageNum" , required = false, defaultValue = "1" ) Integer pageNum,
                                             @RequestParam(value = "pageSize" , required = false, defaultValue = "10" ) Integer pageSize) {
        HashMap<String, Object> map = new HashMap<>();
        PageHelper.startPage(pageNum, pageSize);
        List<Productimage> list = productimageService.select(productimage);
        map.put("total" , list.size());
        map.put("list" , list);
        return ResultVO.ok(map,"查询成功");
    }

    /**
     * 获取客房图片详细信息
     */
    @ApiOperation("获取客房图片详细信息" )
    @GetMapping(value = "/searchOne" )
    public ResultVO<Productimage> searchOne(Productimage productimage) {
        return ResultVO.ok(productimageService.selectOne(productimage));
    }

    /**
     * 新增客房图片
     */
    @ApiOperation("新增客房图片" )
    @PostMapping("/add" )
    public ResultVO<Productimage> add(Productimage productimage) {
        return productimageService.insertProductimage(productimage) > 0 ? ResultVO.ok("新增成功" ) : ResultVO.error("新增失败" );
    }

    /**
     * 修改客房图片
     */
    @ApiOperation("修改客房图片" )
    @PostMapping("/update" )
    public ResultVO<Productimage> edit(Productimage productimage) {
        return productimageService.updateProductimage(productimage) > 0 ? ResultVO.ok("更新成功" ) : ResultVO.error("更新失败" );
    }

    /**
     * 删除客房图片
     */
    @ApiOperation("删除客房图片" )
    @PostMapping("/delete" )
    public ResultVO<Productimage> remove(@RequestParam(value = "id" ) Integer id) {
        return productimageService.deleteProductimageById(id) > 0 ? ResultVO.ok("删除成功" ) : ResultVO.error("删除失败" );
    }
}
