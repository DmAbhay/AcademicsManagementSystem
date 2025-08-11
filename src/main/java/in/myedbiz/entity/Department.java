package in.myedbiz.entity;



import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "department")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @Id
    private String departmentCode;

    private String departmentName;

    @Column(name = "NOfBatches")
    private Integer numberOfBatch;
}

