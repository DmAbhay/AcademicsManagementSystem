package in.myedbiz.controller;


import in.myedbiz.entity.Teacher;
import in.myedbiz.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class TeacherController {


    private final TeacherService teacherService;

    // Create department
    @PostMapping("/teacher")
    public ResponseEntity<String> createDepartment(@RequestBody Teacher teacher) {
        System.out.println(teacher);
        String message = teacherService.createTeacher(teacher);
        return ResponseEntity.ok(message);
    }

    // Update department
    @PutMapping("/teacher")
    public ResponseEntity<String> updateDepartment(@RequestBody Teacher teacher) {
        String message = teacherService.updateTeacher(teacher);
        return ResponseEntity.ok(message);
    }

    // Get all departments
    @GetMapping("/teacher")
    public ResponseEntity<List<Teacher>> getAllDepartments() {
        List<Teacher> teacher = teacherService.getAllTeacher();
        return ResponseEntity.ok(teacher);
    }

    // Get department by ID
    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<Teacher> getDepartmentById(@PathVariable String teacherId) {
        Teacher teacher = teacherService.getTeacherById(teacherId);
        return ResponseEntity.ok(teacher);
    }

    // Delete department by ID
    @DeleteMapping("/teacher/{teacherId}")
    public ResponseEntity<String> deleteDepartment(@PathVariable String teacherId) {
        String message = teacherService.deleteById(teacherId);
        return ResponseEntity.ok(message);
    }

}
