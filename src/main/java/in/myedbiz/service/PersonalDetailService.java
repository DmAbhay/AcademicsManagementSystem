package in.myedbiz.service;

import in.myedbiz.dto.PersonalDetailsDTO;
import in.myedbiz.entity.Address;
import in.myedbiz.entity.PersonalDetails;
import in.myedbiz.exception.ResourceAlreadyExistsException;
import in.myedbiz.repository.AddressRepository;
import in.myedbiz.repository.PersonalDetailsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PersonalDetailService {

    @Autowired
    private PersonalDetailsRepository personalDetailsRepository;

    @Autowired
    private AddressRepository addressRepository;


    @Transactional
    public String createPersonalDetails(PersonalDetailsDTO personalDetailsDTO){

        try {
            PersonalDetails personalDetails = getPersonalDetails(personalDetailsDTO);
            Address address = getAddress(personalDetailsDTO);

            personalDetailsRepository.save(personalDetails);
            addressRepository.save(address);

            return "Personal details saved successfully.";
        } catch (DataIntegrityViolationException ex) {
            throw new ResourceAlreadyExistsException("Duplicate entry or data integrity violation.");
        } catch (Exception ex) {
            throw new RuntimeException("Failed to save personal details: " + ex.getMessage());
        }

    }

    public String updatePersonalDetails(PersonalDetailsDTO personalDetailsDTO){
        PersonalDetails personalDetails = getPersonalDetails(personalDetailsDTO);
        Address address = getAddress(personalDetailsDTO);
        personalDetailsRepository.save(personalDetails);
        addressRepository.save(address);

        return "personal details updated successfully.";
    }

    public List<PersonalDetailsDTO> getAllPersonalDetails(){

        List<PersonalDetailsDTO> personalDetailsDTOList = new ArrayList<>();
        List<PersonalDetails> personalDetailsList = personalDetailsRepository.findAll();
        List<Address> addressesList = addressRepository.findAll();

        for(int i = 0;i<addressesList.size();i++){

            PersonalDetailsDTO personalDetailsDTO = new PersonalDetailsDTO();

            personalDetailsDTO.setId(personalDetailsList.get(i).getId());
            personalDetailsDTO.setFirstName(personalDetailsList.get(i).getFirstName());
            personalDetailsDTO.setLastName(personalDetailsList.get(i).getLastName());
            personalDetailsDTO.setDob(personalDetailsList.get(i).getDob());
            personalDetailsDTO.setEmail(personalDetailsList.get(i).getEmail());
            personalDetailsDTO.setGender(personalDetailsList.get(i).getGender());
            personalDetailsDTO.setParents(personalDetailsList.get(i).getParents());
            personalDetailsDTO.setRelationship(personalDetailsList.get(i).getRelationship());

            personalDetailsDTO.setCountry(addressesList.get(i).getCountry());
            personalDetailsDTO.setId(addressesList.get(i).getId());
            personalDetailsDTO.setCityCode(addressesList.get(i).getCityCode());
            personalDetailsDTO.setStreet(addressesList.get(i).getStreet());
            personalDetailsDTO.setEmail(addressesList.get(i).getEmail());
            personalDetailsDTO.setPostalCode(addressesList.get(i).getPostalCode());
            personalDetailsDTO.setStateCode(addressesList.get(i).getStateCode());

            personalDetailsDTOList.add(personalDetailsDTO);

        }
        return personalDetailsDTOList;
    }

    public PersonalDetailsDTO getPersonalDetailsById(String email) {

        Optional<PersonalDetails> personalDetailsOptional = personalDetailsRepository.findById(email);
        Optional<Address> addressOptional = addressRepository.findById(email);

        PersonalDetailsDTO personalDetailsDTO = new PersonalDetailsDTO();

        if(personalDetailsOptional.isPresent() && addressOptional.isPresent()){


            personalDetailsDTO.setId(personalDetailsOptional.get().getId());
            personalDetailsDTO.setFirstName(personalDetailsOptional.get().getFirstName());
            personalDetailsDTO.setLastName(personalDetailsOptional.get().getLastName());
            personalDetailsDTO.setDob(personalDetailsOptional.get().getDob());
            personalDetailsDTO.setEmail(personalDetailsOptional.get().getEmail());
            personalDetailsDTO.setGender(personalDetailsOptional.get().getGender());
            personalDetailsDTO.setParents(personalDetailsOptional.get().getParents());
            personalDetailsDTO.setRelationship(personalDetailsOptional.get().getRelationship());

            personalDetailsDTO.setCountry(addressOptional.get().getCountry());
            personalDetailsDTO.setId(addressOptional.get().getId());
            personalDetailsDTO.setCityCode(addressOptional.get().getCityCode());
            personalDetailsDTO.setStreet(addressOptional.get().getStreet());
            personalDetailsDTO.setEmail(addressOptional.get().getEmail());
            personalDetailsDTO.setPostalCode(addressOptional.get().getPostalCode());
            personalDetailsDTO.setStateCode(addressOptional.get().getStateCode());

        }
        return personalDetailsDTO;

    }

    public String deletePesonalDetailsById(String email){
        personalDetailsRepository.deleteById(email);
        addressRepository.deleteById(email);
        
        return "course deleted successfully";
    }


    private Address getAddress(PersonalDetailsDTO personalDetailsDTO) {
        Address address = new Address();
        address.setCountry(personalDetailsDTO.getCountry());
        address.setId(personalDetailsDTO.getId());
        address.setCityCode(personalDetailsDTO.getCityCode());
        address.setStreet(personalDetailsDTO.getStreet());
        address.setEmail(personalDetailsDTO.getEmail());
        address.setPostalCode(personalDetailsDTO.getPostalCode());
        address.setStateCode(personalDetailsDTO.getStateCode());
        return address;
    }

    private PersonalDetails getPersonalDetails(PersonalDetailsDTO personalDetailsDTO) {
        PersonalDetails personalDetails = new PersonalDetails();
        personalDetails.setId(personalDetailsDTO.getId());
        personalDetails.setFirstName(personalDetailsDTO.getFirstName());
        personalDetails.setLastName(personalDetailsDTO.getLastName());
        personalDetails.setDob(personalDetailsDTO.getDob());
        personalDetails.setEmail(personalDetailsDTO.getEmail());
        personalDetails.setGender(personalDetailsDTO.getGender());
        personalDetails.setParents(personalDetailsDTO.getParents());
        personalDetails.setRelationship(personalDetailsDTO.getRelationship());
        return personalDetails;
    }
}

