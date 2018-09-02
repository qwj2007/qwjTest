package com.winterchen.service.teacher;
import com.winterchen.model.Teachers;
import org.springframework.stereotype.Component;
@Component
public interface ITeacherService {
    int addTeacher(Teachers user);
}
