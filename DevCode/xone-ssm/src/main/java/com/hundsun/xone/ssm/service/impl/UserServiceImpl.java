/**
 * 公司：DLUT
 * 文件名：UserServiceImpl
 * 作者：haibing
 * 时间：2020/1/4 12:37
 * 描述：
 */

package com.hundsun.xone.ssm.service.impl;

import com.hundsun.xone.ssm.dao.UserDAO;
import com.hundsun.xone.ssm.dao.impl.UserDAOImpl;
import com.hundsun.xone.ssm.dao.impl.mysql.UserDAOImplMy;
import com.hundsun.xone.ssm.entity.User;
import com.hundsun.xone.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAOImplMy userDAO;

    @Override
    public User queryUserById(String userId) {
        return userDAO.selectUserByUserId(userId);
    }

    @Override
    public User queryUserByName(String username) {
        return userDAO.selectUserByUserName(username);
    }
}
