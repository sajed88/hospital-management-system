package com.example.demo.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class DrugsDto {
    private  Long drug_id;
    private  String drug_name;
    private  int price;
    private  String drug_description;
    private String drug_company;
    private Long report_id;
}
