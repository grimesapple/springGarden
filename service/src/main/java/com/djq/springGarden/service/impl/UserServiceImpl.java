package com.djq.springGarden.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.djq.springGarden.vo.UserVo;
import com.mysql.cj.util.StringUtils;
import org.springframework.stereotype.Service;
import com.djq.springGarden.mapper.UserMapper;
import com.djq.springGarden.entity.User;
import com.djq.springGarden.service.UserService;
import tk.mybatis.mapper.entity.Example;

/**
 * 用户Service业务层处理
 *
 * @author duanjiaqi
 * @date 2022-02-02
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    /**
     * 条件查询用户列表
     *
     * @param userVo  用户
     * @return 用户
     */
    @Override
    public List<User> select(UserVo userVo) {
        String phoneTail = userVo.getPhoneTail();
        String name = userVo.getName();
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isNullOrEmpty(phoneTail)) {
            criteria.andLike("telphone","%"+phoneTail);
        }
        if (!StringUtils.isNullOrEmpty(name)) {
            criteria.andLike("name","%"+name+"%");
        }
        return userMapper.selectByExample(example);
    }

    /**
     * 条件查询用户单个
     *
     * @param user 用户
     * @return 用户
     */
    @Override
    public User selectOne(User user) {
        return userMapper.selectOne(user);
    }

    /**
     * 新增用户
     *
     * @param user 用户
     * @return 结果
     */
    @Override
    public int insertUser(User user) {
        return userMapper.insert(user);
    }

    /**
     * 修改用户
     *
     * @param user 用户
     * @return 结果
     */
    @Override
    public int updateUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 删除用户信息
     *
     * @param id 用户主键
     * @return 结果
     */
    @Override
    public int deleteUserById(Integer id) {

        User user = new User();
        user.setId(id);
        return userMapper.delete(user);
    }

    @Override
    public int register(User user) {
        return userMapper.insertSelective(user);
    }

    /**
     * 用户登录
     * @param user 封装条件
     * @return 返回结果
     */
    @Override
    public User login(User user) {
        List<User> users = userMapper.select(user);
        if (users == null || users.size() == 0) {
            return null;
        }
        User userNew = users.get(0);
        return  userNew;
    }

    /**
     * 用户退出登录
     * @param user 对应账号
     */
    @Override
    public int loginOut(User user){
        //将token失效，发现不需要
        return 0;
    }
}
