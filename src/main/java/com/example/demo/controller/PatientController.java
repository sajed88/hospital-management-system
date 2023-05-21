package com.example.demo.controller;


import com.example.demo.dto.PatientDto;
import com.example.demo.exception.BadRequestException;
import com.example.demo.service.PatientService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Patient")
public class PatientController {
    private final Logger log = LoggerFactory.getLogger(PatientController.class);

    private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<PatientDto>> getAllPatient() {
        return ResponseEntity.ok().body(patientService.getAllPatient());
    }


    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> getPatientById(
            @PathVariable(name = "id") long id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }



    @PostMapping
    public ResponseEntity<PatientDto> createPatient
            (@Valid @RequestBody PatientDto patientDto) {
        if (patientDto.getPatient_id() != null) {
            log.error("Cannot have an ID {}", patientDto);
            throw new BadRequestException(PatientController.class.getSimpleName(),
                    "Id");
        }
        return new ResponseEntity(patientService.createPatient(patientDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDto> updatePatient
            (@Valid @RequestBody PatientDto patientDto
                    , @PathVariable(name = "id") long id) {
        return new ResponseEntity<>(patientService.updatePatient(patientDto, id), HttpStatus.OK);
    }

    //    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable(name = "id") long id) {
        patientService.deletePatientById(id);
//        return ResponseEntity.ok().headers(<add warnings....>).build();
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }
}
