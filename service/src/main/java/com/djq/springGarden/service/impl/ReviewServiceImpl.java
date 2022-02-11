package com.djq.springGarden.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.djq.springGarden.vo.ResultVO;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Service;
import com.djq.springGarden.mapper.ReviewMapper;
import com.djq.springGarden.entity.Review;
import com.djq.springGarden.service.ReviewService;


import javax.annotation.Resource;


/**
 * 评论Service业务层处理
 *
 * @author duanjiaqi
 * @date 2022-02-02
 */
@Service
public class ReviewServiceImpl implements ReviewService {
    @Resource
    private ReviewMapper reviewMapper;

    /**
     * 条件查询评论列表
     *
     * @param review  评论
     * @return 评论
     */
    @Override
    public List<Review> select(Review review) {
        return reviewMapper.select(review);
    }

    /**
     * 条件查询评论单个
     *
     * @param review 评论
     * @return 评论
     */
    @Override
    public Review selectOne(Review review) {
        return reviewMapper.selectOne(review);
    }

    /**
     * 新增评论
     *
     * @param review 评论
     * @return 结果
     */
    @Override
    public int insertReview(Review review) {
        return reviewMapper.insert(review);
    }

    /**
     * 修改评论
     *
     * @param review 评论
     * @return 结果
     */
    @Override
    public int updateReview(Review review) {
        return reviewMapper.updateByPrimaryKey(review);
    }

    /**
     * 删除评论信息
     *
     * @param id 评论主键
     * @return 结果
     */
    @Override
    public int deleteReviewById(Integer id) {

        Review review = new Review();
        review.setId(id);
        return reviewMapper.delete(review);
    }

}
