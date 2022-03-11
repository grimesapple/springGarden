package com.djq.springGarden.controller;

import java.util.List;

import com.djq.springGarden.entity.Product;
import com.djq.springGarden.vo.OrderSearchVo;
import com.djq.springGarden.vo.ProductSearchVo;
import com.djq.springGarden.vo.ProductVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import com.djq.springGarden.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.Map;

import com.github.pagehelper.PageHelper;
import com.djq.springGarden.vo.ResultVO;

import javax.annotation.Resource;


/**
 * 客房;商品信息相关：分类，商品图片，商品规格，商品参数Controller
 *
 * @author duanjiaqi
 */
@Api(tags = "客房;商品信息相关：分类，商品图片，商品规格，商品参数控制器")
@RestController
@RequestMapping("/product")
public class ProductController {
    @Resource
    private ProductService productService;

    /**
     * 查询客房:在客房查询页面根据条件筛选客房
     */
    @GetMapping("/list")
    @ApiOperation("查询客房:在客房查询页面根据条件筛选客房")
    public ResultVO<Map<String, Object>> list(ProductSearchVo productSearchVo,
                                              @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                              @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        HashMap<String, Object> map = new HashMap<>();
        PageHelper.startPage(pageNum, pageSize);
        List<Map<String, Object>> list = productService.search(productSearchVo);
        PageInfo<Map<String, Object>> info = new PageInfo<>(list);
        map.put("total", info.getTotal());
        map.put("list", list);
        return ResultVO.ok(map, "查询成功");
    }

    /**
     * 管理员查询所有房屋列表
     *
     * @return 返回结果
     */
    @GetMapping("/listByAdmin")
    @ApiOperation("管理员查询客房")
    public ResultVO<Map<String, Object>> listByAdmin(ProductSearchVo productSearchVo,
                                                     @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                                     @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        Page<Object> info = PageHelper.startPage(pageNum, pageSize);
        List<Map<String, Object>> list = productService.search(productSearchVo);
        map.put("total", info.getTotal());
        map.put("list", list);
        return ResultVO.ok(map, "查询成功");
    }


    /**
     * 管理员查询所有房屋列表
     *
     * @return 返回结果
     */
    @GetMapping("/searchForPage")
    @ApiOperation("房屋信息列表查询")
    public ResultVO<Map<String, Object>> searchForPage(ProductSearchVo productSearchVo,
                                                       @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                                       @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> list = productService.search(productSearchVo);
        map.put("total", list.size());
        map.put("list", list);
        return ResultVO.ok(map, "查询成功");
    }




    /**
     * 获取客房;商品信息相关：分类，商品图片，商品规格，商品参数详细信息
     */
    @ApiOperation("获取客房;")
    @GetMapping(value = "/searchOne")
    public ResultVO<Product> searchOne(Product product) {
        return ResultVO.ok(productService.selectOne(product));
    }

    /**
     * 新增客房;商品信息相关：分类，商品图片，商品规格，商品参数
     */
    @ApiOperation("新增客房;")
    @PostMapping("/add")
    //@ApiParam("图片名字列表")  List<String> fileList
    public ResultVO<Product> add(@RequestBody ProductVO productVO) {
        return productService.insertProduct(productVO) > 0 ? ResultVO.ok("新增成功") : ResultVO.error("新增失败");
    }

    /**
     * 修改客房;商品信息相关：分类，商品图片，商品规格，商品参数
     */
    @ApiOperation("修改客房;")
    @PostMapping("/update")
    public ResultVO<Product> edit(@RequestBody ProductVO productVO) {
        return productService.updateProduct(productVO) > 0 ? ResultVO.ok("更新成功") : ResultVO.error("更新失败");
    }

    /**
     * 删除客房;商品信息相关：分类，商品图片，商品规格，商品参数
     */
    @ApiOperation("删除客房;")
    @GetMapping("/delete")
    public ResultVO<Product> remove(@RequestParam(value = "id") Integer id) {
        return productService.deleteProductById(id) > 0 ? ResultVO.ok("删除成功") : ResultVO.error("删除失败");
    }
}
