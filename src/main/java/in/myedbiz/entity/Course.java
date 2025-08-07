package in.myedbiz.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "course")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    private String courseCode;
    private String courseName;
    private String description;

    @Column(name = "NOfBatches")
    private Integer numberOfBatch;
    private String departmentCode;


}

