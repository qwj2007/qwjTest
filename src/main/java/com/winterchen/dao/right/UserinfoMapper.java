package com.winterchen.dao.right;

import com.winterchen.config.dbconfig.rightDb;
import com.winterchen.model.Userinfo;
@rightDb
public interface UserinfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Userinfo record);

    int insertSelective(Userinfo record);

    Userinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Userinfo record);

    int updateByPrimaryKey(Userinfo record);
}