package in.myedbiz.entity;



import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "student")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @Column(name  = "enrollmentNo")
    private String enrollmentNo;

    @Column(name  = "studentName")
    private String studentName;

    @Column(name  = "email")
    private String email;

    @Column(name  = "phone")
    private String phone;

    @Column(name  = "courseCode")
    private String courseCode;

    @Column(name  = "departmentCode")
    private String departmentCode;

    @Column(name  = "academicYear")
    private Integer academicYear;


}

