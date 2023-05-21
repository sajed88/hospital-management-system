package com.example.demo.controller;
import com.example.demo.dto.ReportDto;
import com.example.demo.exception.BadRequestException;
import com.example.demo.service.ReportService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Report")
public class ReportController {
    private final Logger log = LoggerFactory.getLogger(ReportController.class);

    private ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    public ResponseEntity<List<ReportDto>> getAllReport() {
        return ResponseEntity.ok().body(reportService.getAllReport());
    }


    @GetMapping("/{id}")
    public ResponseEntity<ReportDto> getReportById(
            @PathVariable(name = "id") long id) {
        return ResponseEntity.ok(reportService.getReportById(id));
    }



    @PostMapping
    public ResponseEntity<ReportDto> createReport
            (@Valid @RequestBody ReportDto reportDto) {
        if (reportDto.getReport_id() != null) {
            log.error("Cannot have an ID {}", reportDto);
            throw new BadRequestException(ReportController.class.getSimpleName(),
                    "Id");
        }
        return new ResponseEntity(reportService.createReport(reportDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReportDto> updateReport
            (@Valid @RequestBody ReportDto reportDto
                    , @PathVariable(name = "id") long id) {
        return new ResponseEntity<>(reportService.updateReport(reportDto, id), HttpStatus.OK);
    }

    //    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReport(@PathVariable(name = "id") long id) {
        reportService.deleteReportById(id);
//        return ResponseEntity.ok().headers(<add warnings....>).build();
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }
}
