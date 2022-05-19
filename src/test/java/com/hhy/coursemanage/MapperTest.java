package com.hhy.coursemanage;

import com.hhy.coursemanage.entity.School;
import com.hhy.coursemanage.entity.Student;
import com.hhy.coursemanage.mapper.SchoolMapper;
import com.hhy.coursemanage.mapper.StudentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

/**
 * @program: CourseManage
 * @description: 数据库操作测试
 * @author: hhy
 * @create: 2022-04-30 20:29
 **/
@SpringBootTest
@ContextConfiguration(classes = CourseManageApplication.class)
public class MapperTest {

    @Autowired
    private SchoolMapper schoolMapper;

    @Autowired
    private StudentMapper studentMapper;

    /**
     * 查询 id=2 的课程信息；
     */
    @Test
    public void testSelectByID() {
        System.out.println(studentMapper.selectById(2));
    }

    /**
     * 查询出所有计算机学院开设的课程信息
     */
    @Test
    public void testSelectBySID() {
        System.out.println(studentMapper.selectStudentBySid(1));
    }

    /**
     * 将 id=4 这⻔课程的课时数修改为 32+8=40
     */
    @Test
    public void testUpdateStudentHours() {
        Student student = studentMapper.selectById(4);
        System.out.println(student);
        student.setHours(40);
        studentMapper.updateHours(student);
        System.out.println(student);
    }

    /**
     * 插⼊⼀条新的课程记录： names=”⼤数据存储“， hours=32， schools =1
     */
    @Test
    public void testStudentInsert() {
        Student student = new Student();
        student.setName("大数据存储");
        student.setHours(32);
        student.setSid(1);
        System.out.println(studentMapper.findAllStudent());
        studentMapper.insertStudent(student);
        System.out.println(studentMapper.findAllStudent());
    }

    /**
     * 输出所有的学院开设的课程信息
     */
    @Test
    public void testSelectAllStudent() {
        System.out.println(studentMapper.findAllStudent());
    }


    /**
     * 测试删除
     */
    @Test
    public void testDelete() {
        System.out.println(studentMapper.delete(4));
    }


    //分界线，上方为实验要求完成的5个子任务，下方是针对School的增删改查


    @Test
    public void testSchoolInsert() {
        School school = new School();
        school.setSchoolName("计算机科学与工程学院");
        schoolMapper.addSchool(school);
    }

    @Test
    public void testSchoolDelete() {
        schoolMapper.deleteSchool(4);
    }

    @Test
    public void testSchoolSelect() {
        School school = schoolMapper.findSchoolById(1);
        System.out.println(school);
    }

    @Test
    public void testSchoolUpdate() {
        School school = schoolMapper.findSchoolById(3);
        school.setSchoolName("信息与技术学院");
        schoolMapper.updateSchool(school);
    }

    @Test
    public void testSelectAllSchool() {
        List<School> list = schoolMapper.findAllSchools();
        System.out.println(list);
    }

    @Test
    public void testUpload() {

    }


}
