package com.example.demo.repository;

import com.example.demo.entity.Doctors;
import com.example.demo.entity.Drugs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugsRepository extends JpaRepository<Drugs, Long> {

}
