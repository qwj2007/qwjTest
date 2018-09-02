package com.winterchen.service.world.impl;

import com.winterchen.dao.world.CityMapper;
import com.winterchen.model.City;
import com.winterchen.service.world.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityServiceImpl implements ICityService {
    @Autowired
    private CityMapper mapper;
    @Override
    public int insert(City city) {
        return mapper.insert(city);
    }
}
