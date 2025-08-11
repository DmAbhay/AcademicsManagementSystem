package in.myedbiz.repository;

import in.myedbiz.entity.PersonalDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonalDetailsRepository extends JpaRepository<PersonalDetails, String> {




}
