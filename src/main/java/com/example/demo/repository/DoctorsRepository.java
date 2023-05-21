package com.example.demo.repository;

import com.example.demo.entity.Doctors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorsRepository extends JpaRepository<Doctors, Long> {
}
