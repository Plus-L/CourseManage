package com.hhy.coursemanage.utils;

import com.hhy.coursemanage.entity.Student;
import org.springframework.stereotype.Component;

/**
 * @program: kci_onlinesys
 * @description: 持有用户信息，用于代替session对象
 * @author: PlusL
 * @create: 2022-03-20 15:09
 **/
@Component
public class HostHolder {

    private ThreadLocal<Student> students = new ThreadLocal<>();

    public void setStudent(Student user){
        students.set(user);
    }

    public Student getStudent(){
        return students.get();
    }

    public void clear(){
        students.remove();
    }

}
