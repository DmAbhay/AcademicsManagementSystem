package in.myedbiz.service;


import in.myedbiz.entity.Teacher;

import in.myedbiz.repository.TeacherRepository;
import in.myedbiz.util.Util;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private Util util;

    @Transactional
    public String createTeacher(Teacher teacher){
        String teacherId = util.generateUniqueId("TEACH");
        teacher.setTeacherId(teacherId);
        teacherRepository.save(teacher);
        return "student added successfully";
    }

    public String updateTeacher(Teacher teacher){
        teacherRepository.save(teacher);
        return "student updated successfully";
    }


    public List<Teacher> getAllTeacher(){
        return teacherRepository.findAll();
    }

    public Teacher getTeacherById(String teacherId) {
        return teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Department not found with code: " + teacherId));
    }

    public String deleteById(String teacherId){
        teacherRepository.deleteById(teacherId);
        return "course deleted successfully";
    }

}
