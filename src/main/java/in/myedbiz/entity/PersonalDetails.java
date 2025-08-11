package in.myedbiz.entity;

import in.myedbiz.enums.Gender;
import in.myedbiz.enums.RelationShip;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "personal_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalDetails {

    @Id
    private String email;

    @Column(name = "relationship")
    @Enumerated(EnumType.STRING)
    private RelationShip relationship;

    @Column(name = "parents")
    private String parents;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String dob;

    @Enumerated(EnumType.STRING)
    private Gender gender;
}
