package in.myedbiz.controller;

import in.myedbiz.entity.Department;
import in.myedbiz.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class DepartmentController {


    private final DepartmentService departmentService;

    // Create department
    @PostMapping("/department")
    public ResponseEntity<String> createDepartment(@RequestBody Department department) {
        String message = departmentService.createDepart(department);
        return ResponseEntity.ok(message);
    }

    // Update department
    @PutMapping("/department")
    public ResponseEntity<String> updateDepartment(@RequestBody Department department) {
        String message = departmentService.updateDepartment(department);
        return ResponseEntity.ok(message);
    }

    // Get all departments
    @GetMapping("/department")
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartment();
        return ResponseEntity.ok(departments);
    }

    // Get department by ID
    @GetMapping("/department/{departmentCode}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable String departmentCode) {
        Department department = departmentService.getDepartmentById(departmentCode);
        return ResponseEntity.ok(department);
    }

    // Delete department by ID
    @DeleteMapping("/department/{departmentCode}")
    public ResponseEntity<String> deleteDepartment(@PathVariable String departmentCode) {
        String message = departmentService.deleteById(departmentCode);
        return ResponseEntity.ok(message);
    }


}
