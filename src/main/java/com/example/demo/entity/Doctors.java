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
public class Doctors{
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private  Long doctors_id;

    @Column(name = "name")
    private  String doctors_name;

    @Column(name = "mobile")
    private  String doctors_mobile;

    @Column(name = "address")
    private  String doctors_address;


}
