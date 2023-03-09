package com.ljc.crm.settings.service;

import com.ljc.crm.settings.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    User queryUserByLoginActAndPwd(Map<String,Object> map);

    List<User> queryAllUsers();
}
