package com.example.demo.service;

import com.example.demo.controller.DoctorsController;
import com.example.demo.dto.DoctorsDto;
import com.example.demo.entity.Doctors;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public interface DoctorsService {

    DoctorsDto createDoctors(DoctorsDto doctors);

    List<DoctorsDto> getAllDoctors();

    DoctorsDto getDoctorsById(long id);
    DoctorsDto updateDoctors(DoctorsDto doctors, long id);
    void deleteDoctorsById(long id);

}
