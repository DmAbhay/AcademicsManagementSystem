package in.myedbiz.controller;

import in.myedbiz.entity.Course;
import in.myedbiz.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class CourseController {


    private final CourseService courseService;

    // Create department
    @PostMapping("/course")
    public ResponseEntity<String> createDepartment(@RequestBody Course course) {
        System.out.println(course);
        String message = courseService.createCourse(course);
        return ResponseEntity.ok(message);
    }

    // Update department
    @PutMapping("/course")
    public ResponseEntity<String> updateDepartment(@RequestBody Course course) {
        String message = courseService.updateCourse(course);
        return ResponseEntity.ok(message);
    }

    // Get all departments
    @GetMapping("/course")
    public ResponseEntity<List<Course>> getAllDepartments() {
        List<Course> course = courseService.getAllCourse();
        return ResponseEntity.ok(course);
    }

    // Get department by ID
    @GetMapping("/course/{courseCode}")
    public ResponseEntity<Course> getDepartmentById(@PathVariable String courseCode) {
        Course course = courseService.getCourseById(courseCode);
        return ResponseEntity.ok(course);
    }

    // Delete department by ID
    @DeleteMapping("/course/{courseCode}")
    public ResponseEntity<String> deleteDepartment(@PathVariable String courseCode) {
        String message = courseService.deleteById(courseCode);
        return ResponseEntity.ok(message);
    }

}
