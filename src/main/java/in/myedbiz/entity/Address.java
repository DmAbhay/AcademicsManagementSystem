package in.myedbiz.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

    @Id
    private String email;

    private String street;

    @Column(name = "city_code")
    private String cityCode;

    @Column(name = "state_code")
    private String stateCode;

    @Column(name = "postal_code")
    private String postalCode;

    private String country;

}
