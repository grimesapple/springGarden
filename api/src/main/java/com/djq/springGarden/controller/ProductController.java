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
     * 查询客房;商品信息相关：分类，商品图片，商品规格，商品参数列表
     */
    @GetMapping("/list")
    @ApiOperation("查询客房;商品信息相关：分类，商品图片，商品规格，商品参数列表")
    public ResultVO<Map<String, Object>> list(Product product,
                                              @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                              @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        HashMap<String, Object> map = new HashMap<>();
        PageHelper.startPage(pageNum, pageSize);
        List<Product> list = productService.select(product);
        map.put("total", list.size());
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
    public ResultVO<Map<String, Object>> listByAdmin(Product product,
                                                     @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                                     @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        HashMap<String, Object> map = new HashMap<>();
        Page page = PageHelper.startPage(pageNum, pageSize);
        List<ProductVO> list = productService.listByAdmin(product);
        PageInfo info = new PageInfo<>(page.getResult());
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
    public ResultVO<Map<String, Object>> searchForPage(ProductSearchVo ProductSearchVo
//                                                       @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
//                                                       @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
                                                       ) {
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> list = productService.search(ProductSearchVo);
        map.put("total", list.size());
        map.put("list", list);
        return ResultVO.ok(map, "查询成功");
    }




    /**
     * 获取客房;商品信息相关：分类，商品图片，商品规格，商品参数详细信息
     */
    @ApiOperation("获取客房;商品信息相关：分类，商品图片，商品规格，商品参数详细信息")
    @GetMapping(value = "/searchOne")
    public ResultVO<Product> searchOne(Product product) {
        return ResultVO.ok(productService.selectOne(product));
    }

    /**
     * 新增客房;商品信息相关：分类，商品图片，商品规格，商品参数
     */
    @ApiOperation("新增客房;商品信息相关：分类，商品图片，商品规格，商品参数")
    @PostMapping("/add/{id}")
    //@ApiParam("图片名字列表")  List<String> fileList
    public ResultVO<Product> add(@RequestBody Product product, @PathVariable(value = "id") Integer id) {
        System.out.println(id);
        List<String> fileList = product.getFileList();
        System.out.println("房间名"+fileList);
        return productService.insertProduct(product) > 0 ? ResultVO.ok("新增成功") : ResultVO.error("新增失败");
    }

    /**
     * 修改客房;商品信息相关：分类，商品图片，商品规格，商品参数
     */
    @ApiOperation("修改客房;商品信息相关：分类，商品图片，商品规格，商品参数")
    @PostMapping("/update")
    public ResultVO<Product> edit(Product product) {
        return productService.updateProduct(product) > 0 ? ResultVO.ok("更新成功") : ResultVO.error("更新失败");
    }

    /**
     * 删除客房;商品信息相关：分类，商品图片，商品规格，商品参数
     */
    @ApiOperation("删除客房;商品信息相关：分类，商品图片，商品规格，商品参数")
    @PostMapping("/delete")
    public ResultVO<Product> remove(@RequestParam(value = "id") Integer id) {
        return productService.deleteProductById(id) > 0 ? ResultVO.ok("删除成功") : ResultVO.error("删除失败");
    }
}
