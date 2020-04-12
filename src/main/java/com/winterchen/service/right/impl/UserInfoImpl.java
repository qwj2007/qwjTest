package com.winterchen.service.right.impl;


import com.winterchen.dao.right.UserinfoMapper;
import com.winterchen.model.Userinfo;
import com.winterchen.service.right.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 作者：齐文杰
 * 时间：2018/9/16
 */
@Component
public class UserInfoImpl implements IUserInfoService {

    @Autowired
    private UserinfoMapper userinfoMapper;
    @Override
    public int addUserInfo(Userinfo userinfo) {
      return userinfoMapper.insert(userinfo);
    }
}
