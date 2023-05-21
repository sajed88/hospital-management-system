package com.example.demo.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DoctorsDto {
    private  Long doctors_id;
    private  String doctors_name;
    private  String doctors_mobile;
    private  String doctors_address;
}
