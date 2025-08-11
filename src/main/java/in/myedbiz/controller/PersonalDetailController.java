package in.myedbiz.controller;

import in.myedbiz.dto.PersonalDetailsDTO;
import in.myedbiz.service.PersonalDetailService;
import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN') or hasRole('STUDENT')")
public class PersonalDetailController {


    private final PersonalDetailService personalDetailService;

    // Create department
    @PostMapping("/personal-details")
    public ResponseEntity<String> addPersonalDetails(@RequestBody PersonalDetailsDTO personalDetailsDTO) {

        System.out.println(personalDetailsDTO);
        String message = personalDetailService.createPersonalDetails(personalDetailsDTO);
        return ResponseEntity.ok(message);
    }

    // Update department
    @PutMapping("/personal-details")
    public ResponseEntity<String> updatePersonalDetails(@RequestBody PersonalDetailsDTO personalDetailsDTO) {
        String message = personalDetailService.updatePersonalDetails(personalDetailsDTO);
        return ResponseEntity.ok(message);
    }

    // Get all departments
    @GetMapping("/personal-details")
    public ResponseEntity<List<PersonalDetailsDTO>> getAllPersonalDetails() {
        List<PersonalDetailsDTO> teacher = personalDetailService.getAllPersonalDetails();
        return ResponseEntity.ok(teacher);
    }

    // Get department by ID
    @GetMapping("/personal-details/{email}")
    public ResponseEntity<PersonalDetailsDTO> getPersonalDetailsById(@PathVariable String email) {
        PersonalDetailsDTO teacher = personalDetailService.getPersonalDetailsById(email);
        return ResponseEntity.ok(teacher);
    }

    // Delete department by ID
    @DeleteMapping("/personal-details/{email}")
    public ResponseEntity<String> deletePersonalDetails(@PathVariable String email) {
        String message = personalDetailService.deletePesonalDetailsById(email);
        return ResponseEntity.ok(message);
    }

}
