package com.example.demo.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class PatientDto {
    private  Long patient_id;
    private  String patient_name;
    private  String patient_mobile;
    private  String patient_address;
}
