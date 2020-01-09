/**
 * 公司：DLUT
 * 文件名：UserServiceImpl
 * 作者：haibing
 * 时间：2020/1/4 12:37
 * 描述：
 */

package com.hundsun.xone.ssm.service.impl;

import com.hundsun.xone.ssm.dao.impl.mysql.UserDAOImpl;
import com.hundsun.xone.ssm.dao.support.ResultInfo;
import com.hundsun.xone.ssm.entity.User;
import com.hundsun.xone.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAOImpl userDAO;

    @Override
    public User queryUserById(String userId) {
        return userDAO.selectUserByUserId(userId);
    }

    @Override
    public User queryUserByName(String username) {
        return userDAO.selectUserByUserName(username);
    }

    @Override
    public ResultInfo addUser(User user) {
        return userDAO.insertUser(user);
    }

    @Override
    public boolean existingUser(String userId) {
        return userDAO.existingUser(userId);
    }

    @Override
    public ResultInfo updateUser(User user) {
        return userDAO.updateUser(user);
    }
}
