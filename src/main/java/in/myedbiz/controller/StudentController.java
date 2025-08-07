package in.myedbiz.controller;


import in.myedbiz.entity.Student;
import in.myedbiz.service.StudentService;
import in.myedbiz.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class StudentController {


    private final StudentService studentService;

    // Create department
    @PostMapping("/student")
    public ResponseEntity<String> createStudent(@RequestBody Student student) {
        System.out.println(student);
        String message = studentService.createStudent(student);
        return ResponseEntity.ok(message);
    }

    // Update department
    @PutMapping("/student")
    public ResponseEntity<String> updateStudent(@RequestBody Student student) {
        String message = studentService.updateStudent(student);
        return ResponseEntity.ok(message);
    }

    // Get all departments
    @GetMapping("/student")
    public ResponseEntity<List<Student>> getAllStudent() {
        List<Student> course = studentService.getAllStudent();
        return ResponseEntity.ok(course);
    }

    // Get department by ID
    @GetMapping("/student/{enrollmentNo}")
    public ResponseEntity<Student> getStudentById(@PathVariable String enrollmentNo) {
        Student student = studentService.getCourseById(enrollmentNo);
        return ResponseEntity.ok(student);
    }

    // Delete department by ID
    @DeleteMapping("/student/{enrollmentNo}")
    public ResponseEntity<String> deleteStudent(@PathVariable String enrollmentNo) {
        String message = studentService.deleteById(enrollmentNo);
        return ResponseEntity.ok(message);
    }

}
