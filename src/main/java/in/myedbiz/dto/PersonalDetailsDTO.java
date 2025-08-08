package in.myedbiz.dto;

import in.myedbiz.enums.Gender;
import in.myedbiz.enums.RelationShip;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalDetailsDTO {

    // ===== Personal Details Fields =====
    private Long id;
    private String email;
    private RelationShip relationship;
    private String parents;
    private String firstName;
    private String lastName;
    private String dob; // keep as String if format varies, else use LocalDate
    private Gender gender;

    // ===== Address Fields =====
    private String street;
    private String cityCode;
    private String stateCode;
    private String postalCode;
    private String country;
}
