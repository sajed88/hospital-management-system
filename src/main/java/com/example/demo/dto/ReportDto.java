package com.example.demo.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ReportDto {
    private  Long report_id;
    private  String report_name;
    private  Long patient_id ;
    private  Long doctors_id;
    private  Long drugs_id;
}
