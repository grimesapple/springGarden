package com.djq.springGarden.controller;

import java.util.List;

import com.djq.springGarden.entity.Review;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import com.djq.springGarden.service.ReviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.Map;

import com.github.pagehelper.PageHelper;
import com.djq.springGarden.vo.ResultVO;

import javax.annotation.Resource;


/**
 * 评论Controller
 *
 * @author duanjiaqi
 */
@Api(tags = "评论控制器" )
@RestController
@RequestMapping("/system/review" )
public class ReviewController {
    @Resource
    private ReviewService reviewService;

    /**
     * 查询评论列表
     */
    @GetMapping("/list" )
    @ApiOperation("查询评论列表" )
    public ResultVO<Map<String,Object>> list(Review review,
                                             @RequestParam(value = "pageNum" , required = false, defaultValue = "1" ) Integer pageNum,
                                             @RequestParam(value = "pageSize" , required = false, defaultValue = "10" ) Integer pageSize) {
        HashMap<String, Object> map = new HashMap<>();
        PageHelper.startPage(pageNum, pageSize);
        List<Review> list = reviewService.select(review);
        map.put("total" , list.size());
        map.put("list" , list);
        return ResultVO.ok(map,"查询成功");
    }

    /**
     * 获取评论详细信息
     */
    @ApiOperation("获取评论详细信息" )
    @GetMapping(value = "/searchOne" )
    public ResultVO<Review> searchOne(Review review) {
        return ResultVO.ok(reviewService.selectOne(review));
    }

    /**
     * 新增评论
     */
    @ApiOperation("新增评论" )
    @PostMapping("/add" )
    public ResultVO<Review> add(Review review) {
        return reviewService.insertReview(review) > 0 ? ResultVO.ok("新增成功" ) : ResultVO.error("新增失败" );
    }

    /**
     * 修改评论
     */
    @ApiOperation("修改评论" )
    @PostMapping("/update" )
    public ResultVO<Review> edit(Review review) {
        return reviewService.updateReview(review) > 0 ? ResultVO.ok("更新成功" ) : ResultVO.error("更新失败" );
    }

    /**
     * 删除评论
     */
    @ApiOperation("删除评论" )
    @PostMapping("/delete" )
    public ResultVO<Review> remove(@RequestParam(value = "id" ) Integer id) {
        return reviewService.deleteReviewById(id) > 0 ? ResultVO.ok("删除成功" ) : ResultVO.error("删除失败" );
    }
}
