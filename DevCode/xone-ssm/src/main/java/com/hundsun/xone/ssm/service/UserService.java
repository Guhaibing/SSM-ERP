package com.hundsun.xone.ssm.service;

import com.hundsun.xone.ssm.entity.User;

public interface UserService {

    User queryUserById(String userId);

    User queryUserByName(String username);
}
