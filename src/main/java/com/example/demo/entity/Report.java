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
public class Report {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private  Long report_id;

    @Column(name = "name")
    private  String report_name;

    @Column(name = "patient_id")
    private  Long patient_id ;

    @Column(name = "doctors_id")
    private  Long doctors_id;

    @Column(name = "drugs_id")
    private  Long drugs_id;

}
