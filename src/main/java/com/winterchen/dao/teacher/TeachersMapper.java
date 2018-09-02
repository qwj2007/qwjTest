package com.winterchen.dao.teacher;

import com.winterchen.config.dbconfig.UseDatasourceRead;
import com.winterchen.model.Teachers;


@UseDatasourceRead
public interface TeachersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Teachers record);

    int insertSelective(Teachers record);

    Teachers selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Teachers record);

    int updateByPrimaryKey(Teachers record);
}