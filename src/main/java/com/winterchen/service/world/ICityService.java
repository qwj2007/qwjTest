package com.winterchen.service.world;

import com.winterchen.model.City;
import org.springframework.stereotype.Component;

@Component
public interface ICityService {
    int insert(City city);
}
