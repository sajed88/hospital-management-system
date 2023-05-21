package com.example.demo.controller;

import com.example.demo.dto.DoctorsDto;
import com.example.demo.exception.BadRequestException;
import com.example.demo.service.DoctorsService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/Doctors")
public class DoctorsController {
    private final Logger log = LoggerFactory.getLogger(DoctorsController.class);

    private DoctorsService doctorsService;

    public DoctorsController(DoctorsService doctorsService) {
        this.doctorsService = doctorsService;
    }

    @GetMapping
    public ResponseEntity<List<DoctorsDto>> getAllDoctors() {
        return ResponseEntity.ok().body(doctorsService.getAllDoctors());
    }


    @GetMapping("/{id}")
    public ResponseEntity<DoctorsDto> getDoctorsById(
            @PathVariable(name = "id") long id) {
        return ResponseEntity.ok(doctorsService.getDoctorsById(id));
    }



    @PostMapping
    public ResponseEntity<DoctorsDto> createDoctors
    (@Valid @RequestBody DoctorsDto doctorsDto) {
        if (doctorsDto.getDoctors_id() != null) {
            log.error("Cannot have an ID {}", doctorsDto);
            throw new BadRequestException(DoctorsController.class.getSimpleName(),
                    "Id");
        }
        return new ResponseEntity(doctorsService.createDoctors(doctorsDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorsDto> updateDoctors
            (@Valid @RequestBody DoctorsDto doctorsDto
                    , @PathVariable(name = "id") long id) {
        return new ResponseEntity<>(doctorsService.updateDoctors(doctorsDto, id), HttpStatus.OK);
    }

    //    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDoctors(@PathVariable(name = "id") long id) {
        doctorsService.deleteDoctorsById(id);
//        return ResponseEntity.ok().headers(<add warnings....>).build();
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }

}
