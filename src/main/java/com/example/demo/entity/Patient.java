package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Patient {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private  Long patient_id;

    @Column(name = "name")
    private  String patient_name;

    @Column(name = "mobile")
    private  String patient_mobile;

    @Column(name = "address")
    private  String patient_address;
}
