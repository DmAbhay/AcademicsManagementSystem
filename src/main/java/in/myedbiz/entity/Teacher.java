package in.myedbiz.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "teacher")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {

    @Id
    private String teacherId;
    private String teacherName;
    private String email;
    private String phone;
    private String departmentCode;



}

