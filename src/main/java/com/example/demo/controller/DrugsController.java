package com.example.demo.controller;

import com.example.demo.dto.DrugsDto;
import com.example.demo.exception.BadRequestException;
import com.example.demo.service.DrugsService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Drugs")
public class DrugsController {
    private final Logger log = LoggerFactory.getLogger(DrugsController.class);

    private DrugsService drugsService;

    public DrugsController(DrugsService drugsService) {
        this.drugsService = drugsService;
    }

    @GetMapping
    public ResponseEntity<List<DrugsDto>> getAllDrugs() {
        return ResponseEntity.ok().body(drugsService.getAllDrugs());
    }


    @GetMapping("/{id}")
    public ResponseEntity<DrugsDto> getDrugsById(
            @PathVariable(name = "id") long id) {
        return ResponseEntity.ok(drugsService.getDrugsById(id));
    }



    @PostMapping
    public ResponseEntity<DrugsDto> createDrugs
            (@Valid @RequestBody DrugsDto drugsDto) {
        if (drugsDto.getDrug_id() != null) {
            log.error("Cannot have an ID {}", drugsDto);
            throw new BadRequestException(DrugsController.class.getSimpleName(),
                    "Id");
        }
        return new ResponseEntity(drugsService.createDrugs(drugsDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DrugsDto> updateDrugs
            (@Valid @RequestBody DrugsDto drugsDto
                    , @PathVariable(name = "id") long id) {
        return new ResponseEntity<>(drugsService.updateDrugs(drugsDto, id), HttpStatus.OK);
    }

    //    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDrugs(@PathVariable(name = "id") long id) {
        drugsService.deleteDrugsById(id);
//        return ResponseEntity.ok().headers(<add warnings....>).build();
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }
}
