package com.djq.springGarden.service;

import java.util.List;

import com.djq.springGarden.entity.User;

/**
 * 用户Service接口
 *
 * @author duanjiaqi
 * @date 2022-02-02
 */
public interface UserService {
    /**
     * 条件查询用户列表
     *
     * @param user 用户
     * @return 用户集合
     */
    List<User> select(User user);

    /**
     * 条件查询用户:查询单个
     *
     * @param  user 用户
     * @return 用户
     */
     User selectOne(User user);


    /**
     * 新增用户
     *
     * @param user 用户
     * @return 结果
     */
    int insertUser(User user);

    /**
     * 修改用户
     *
     * @param user 用户
     * @return 结果
     */
    int updateUser(User user);

    /**
     * 删除用户信息
     *
     * @param id 用户主键
     * @return 结果
     */
    int deleteUserById(Integer id);

    /**
     * 用户登录
     * @param user 封装条件
     * @return 返回结果
     */
    boolean login(User user);

    /**
     * 用户注册
     * @param user 封装条件
     * @return 插入数量
     */
    int register(User user);

}
