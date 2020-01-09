package com.hundsun.xone.ssm.dao;

import com.hundsun.xone.ssm.dao.support.ResultInfo;
import com.hundsun.xone.ssm.entity.User;

public interface UserDAO {

    User selectUserByUserId(String userId);

    User selectUserByUserName(String username);

    ResultInfo insertUser(User user);

    boolean existingUser(String userId);

    ResultInfo updateUser(User user);
}
