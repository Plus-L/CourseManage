package com.hhy.coursemanage.service;

import com.hhy.coursemanage.entity.Student;
import com.hhy.coursemanage.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: CourseManage
 * @description: 课程业务处理
 * @author: PlusL
 * @create: 2022-05-02 16:07
 **/
@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    public int save(Student student) {
        if(student.getId() != null) {
            return studentMapper.update(student);
        } else {
            return studentMapper.insertStudent(student);
        }

    }

}
