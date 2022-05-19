package com.hhy.coursemanage.controller;

import com.hhy.coursemanage.entity.Student;
import com.hhy.coursemanage.mapper.StudentMapper;
import com.hhy.coursemanage.service.StudentService;
import com.hhy.coursemanage.utils.CommunityUtil;
import com.hhy.coursemanage.utils.HostHolder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

/**
 * @program: CourseManage
 * @description: 学生控制器（其实是课程）
 * @author: hhy
 * @create: 2022-05-01 14:45
 **/
@RestController
@RequestMapping("/student")
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StudentService studentService;

    @Autowired
    private HostHolder hostHolder;

    @Value("${student.path.upload}")
    private String uploadPath;

    @Value("${student.path.domain}")
    private String domain;

    /**
     * 获取课程信息，用于显示到表中
     * @return 用户信息list
     */
    @GetMapping("/findall")
    public List<Student> getAllStudentInfo() {
        List<Student> list = studentMapper.findAllStudent();
        return list;
    }

    /**
     * 新增Student（课程） / 更新
     * @param student
     * @return
     */
    @PostMapping
    public Integer insertNewStudent(@RequestBody Student student) {
        return studentService.save(student);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Integer deleteStu(@PathVariable Integer id) {
        System.out.println(id);
        return studentMapper.delete(id);
    }

    /**
     * 头像上传并更新
     * @param mFile
     * @return
     */
    @RequestMapping(value = "/upload" , method = RequestMethod.POST)
    public String uploadHeaderImg(MultipartFile mFile) {
        // 定义存储图片的地址
        String folder = "D:/Learn/在校课程/高级WEB/data/upload";
        // 文件夹
        File imgFolder = new File(folder);
        // 获取文件名
        String fname = mFile.getOriginalFilename();
        // 获取文件后缀
        String ext = "." + fname.substring(fname.lastIndexOf(".")+1);
        // 获取时间字符串
        String dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now());
        // 生成新的文件名
        String newFileName = dateTimeFormatter + UUID.randomUUID().toString().replaceAll("-","") + ext;
        // 文件在本机的全路径
        File filePath = new File(imgFolder, newFileName);
        if (!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdirs();
        }
        try{
            mFile.transferTo(filePath);
            // 返回文件名
            return filePath.getName();
        }catch (IOException e){
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 从本机读取头像
     * @param fileName
     * @param response
     */
    @RequestMapping(path = "/head/{fileName}", method = RequestMethod.GET)
    public void getHeadImage(@PathVariable("fileName") String fileName, HttpServletResponse response) {
        fileName = uploadPath + "/" + fileName;

        String suffix = fileName.substring(fileName.lastIndexOf("."));
        //响应图片
        response.setContentType("image/" + suffix);
        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
            //输出流，因为图片是二进制流
            OutputStream os = response.getOutputStream();
            byte[] buffer = new byte[1024];
            int b = 0;
            while ((b = fileInputStream.read(buffer)) != -1) {
                os.write(buffer, 0, b);
            }

        } catch (IOException e) {
            logger.error("读取头像失败"+ e.getMessage());
        }
    }

    /**
     * 更新头像
     * @param student
     * @return 重定向至用户设置界面
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateSelfInformation(Student student){
        Student localUser = hostHolder.getStudent();
        student.setId(localUser.getId());
        studentService.save(student);

        return "redirect:/";
    }


}
