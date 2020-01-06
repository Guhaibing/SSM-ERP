package com.hundsun.xone.ssm.dao;

import com.hundsun.xone.ssm.entity.User;

public interface UserDAO {

    User selectUserByUserId(String userId);

    User selectUserByUserName(String username);
}
