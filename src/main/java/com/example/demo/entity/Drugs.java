package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Drugs implements Serializable {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private  Long drug_id;

    @Column(name = "name")
    private  String drug_name;

    @Column(name = "price")
    private  int price;

    @Column(name = "description")
    private  String drug_description;

    @Column(name = "company")
    private String drug_company;
    @Column(name = "report_id")
    private Long report_id;


}
