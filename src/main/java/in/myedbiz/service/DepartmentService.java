package in.myedbiz.service;

import in.myedbiz.entity.Department;
import in.myedbiz.entity.Student;
import in.myedbiz.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public String createDepart(Department department){
        departmentRepository.save(department);
        return "department created successfully";
    }

    public String updateDepartment(Department department){
        departmentRepository.save(department);
        return "department created successfully";
    }

    public List<Department> getAllDepartment(){
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(String departmentCode) {
        return departmentRepository.findById(departmentCode)
                .orElseThrow(() -> new RuntimeException("Department not found with code: " + departmentCode));
    }

    public String deleteById(String departmentCode){
        departmentRepository.deleteById(departmentCode);
        return "department deleted successfully";
    }

}
