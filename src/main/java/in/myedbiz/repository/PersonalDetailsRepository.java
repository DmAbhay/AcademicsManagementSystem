package in.myedbiz.repository;

import in.myedbiz.entity.PersonalDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalDetailsRepository extends JpaRepository<PersonalDetails, String> {
}
