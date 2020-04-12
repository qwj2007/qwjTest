package com.winterchen.service.right;

import com.winterchen.model.Userinfo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 作者：齐文杰
 * 时间：2018/9/16
 */
@Component
@Service
public interface IUserInfoService {
    int addUserInfo(Userinfo userinfo);
}
