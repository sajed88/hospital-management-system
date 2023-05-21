package com.example.demo.service;

import com.example.demo.dto.DrugsDto;
import com.example.demo.dto.PatientDto;

import java.util.List;

public interface PatientService {
    PatientDto createPatient(PatientDto patient);
    List<PatientDto> getAllPatient();
    PatientDto getPatientById(long id);
    PatientDto updatePatient(PatientDto drugs, long id);
    void deletePatientById(long id);
}
