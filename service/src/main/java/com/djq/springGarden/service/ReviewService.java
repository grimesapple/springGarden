package com.djq.springGarden.service;

import java.util.List;

import com.djq.springGarden.entity.Review;

/**
 * 评论Service接口
 *
 * @author duanjiaqi
 * @date 2022-02-02
 */
public interface ReviewService {
    /**
     * 条件查询评论列表
     *
     * @param review 评论
     * @return 评论集合
     */
    List<Review> select(Review review);

    /**
     * 条件查询评论:查询单个
     *
     * @param  review 评论
     * @return 评论
     */
     Review selectOne(Review review);


    /**
     * 新增评论
     *
     * @param review 评论
     * @return 结果
     */
    int insertReview(Review review);

    /**
     * 修改评论
     *
     * @param review 评论
     * @return 结果
     */
    int updateReview(Review review);

    /**
     * 删除评论信息
     *
     * @param id 评论主键
     * @return 结果
     */
    int deleteReviewById(Integer id);
}
