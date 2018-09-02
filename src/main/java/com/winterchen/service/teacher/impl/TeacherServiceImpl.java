package com.winterchen.service.teacher.impl;

import com.winterchen.dao.teacher.TeachersMapper;
import com.winterchen.model.Teachers;
import com.winterchen.service.teacher.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeacherServiceImpl implements ITeacherService {
    @Autowired
    private TeachersMapper teachersMapper;
   @Override
    public int addTeacher(Teachers user) {
        return teachersMapper.insert(user);
    }
}
