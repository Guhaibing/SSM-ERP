package com.hundsun.xone.ssm.service;

import com.hundsun.xone.ssm.dao.support.ResultInfo;
import com.hundsun.xone.ssm.entity.User;

public interface UserService {

    User queryUserById(String userId);

    User queryUserByName(String username);

    ResultInfo addUser(User user);

    boolean existingUser(String userId);

    ResultInfo updateUser(User user);
}
