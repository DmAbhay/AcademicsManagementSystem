package in.myedbiz.service;

import in.myedbiz.entity.Course;
import in.myedbiz.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;


    public String createCourse(Course course){
        courseRepository.save(course);
        return "course created successfully";
    }

    public String updateCourse(Course course){
        courseRepository.save(course);
        return "course created successfully";
    }

    public List<Course> getAllCourse(){
        return courseRepository.findAll();
    }

    public Course getCourseById(String courseCode) {
        return courseRepository.findById(courseCode)
                .orElseThrow(() -> new RuntimeException("Department not found with code: " + courseCode));
    }

    public String deleteById(String courseCode){
        courseRepository.deleteById(courseCode);
        return "course deleted successfully";
    }

}

