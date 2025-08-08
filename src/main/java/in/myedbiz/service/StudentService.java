package in.myedbiz.service;

import in.myedbiz.entity.Student;
import in.myedbiz.repository.StudentRepository;
import in.myedbiz.util.Util;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private Util util;


    @Transactional
    public String createStudent(Student student){
        String enrollmentNo = util.generateEnrollmentNo(student.getDepartmentCode());
        student.setEnrollmentNo(enrollmentNo);
        studentRepository.save(student);
        return "student added successfully";
    }



    public String updateStudent(Student student){
        studentRepository.save(student);
        return "student updated successfully";
    }


    public List<Student> getAllStudent(){
        return studentRepository.findAll();
    }

    public Student getCourseById(String enrollmentNo) {
        return studentRepository.findById(enrollmentNo)
                .orElseThrow(() -> new RuntimeException("Department not found with code: " + enrollmentNo));
    }

    public String deleteById(String enrollmentNo){
        studentRepository.deleteById(enrollmentNo);
        return "course deleted successfully";
    }

}
