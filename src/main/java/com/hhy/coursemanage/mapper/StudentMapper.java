package com.hhy.coursemanage.mapper;

import com.hhy.coursemanage.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: CourseManage
 * @description: 课程Mapper（我也不想命名成student，但老师给的建表代码就是student）
 * @author: hhy
 * @create: 2022-04-30 21:41
 **/
@Repository
@Mapper
public interface StudentMapper {

    Student selectById(@Param("id") int id);

    List<Student> selectStudentBySid(@Param("sid") int sid);

    int updateHours(Student student);

    int insertStudent(Student student);

    List<Student> findAllStudent();

    int update(Student student);

    int delete(@Param("id") Integer id);

    /**
     * 更新头像
     * @param studentId
     * @param headurl
     * @return
     */
    int updateHeadUrl(@Param("id") int studentId, @Param("headurl") String headurl);

}
