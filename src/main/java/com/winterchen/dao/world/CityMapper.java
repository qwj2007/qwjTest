package com.winterchen.dao.world;

import com.winterchen.config.dbconfig.WorldDatasource;
import com.winterchen.model.City;
@WorldDatasource
public interface CityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(City record);

    int insertSelective(City record);

    City selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(City record);

    int updateByPrimaryKey(City record);
}