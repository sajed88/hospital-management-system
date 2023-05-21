package com.example.demo.service.impl;

import com.example.demo.dto.DoctorsDto;
import com.example.demo.dto.PatientDto;
import com.example.demo.entity.Doctors;
import com.example.demo.entity.Patient;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DoctorsRepository;
import com.example.demo.repository.PatientRepository;
import com.example.demo.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepository;
    public  PatientServiceImpl(PatientRepository patientRepository){this.patientRepository = patientRepository;}

    @Override
    public PatientDto createPatient(PatientDto patientDto) {
        Patient patient = mapToEntity(patientDto);
        Patient newPatient = patientRepository.save(patient);

        PatientDto patientResponse = mapToDTO(newPatient);
        return  patientResponse;
    }

    @Override
    public List<PatientDto> getAllPatient() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(patient -> mapToDTO(patient))
                .collect(Collectors.toList());
    }

    @Override
    public PatientDto getPatientById(long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("patient", "id", id));
        return mapToDTO(patient);
    }

    @Override
    public PatientDto updatePatient(PatientDto patientDto, long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("patient", "id", id));

        patient.setPatient_id(patientDto.getPatient_id());
        patient.setPatient_name(patientDto.getPatient_name());
        patient.setPatient_mobile(patientDto.getPatient_mobile());
        patient.setPatient_address(patientDto.getPatient_address());

        Patient updatedPatient = patientRepository.save(patient);
        return mapToDTO(updatedPatient);
    }

    @Override
    public void deletePatientById(long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient", "id", id));
        patientRepository.delete(patient);

    }


    private PatientDto mapToDTO(Patient patient){
        PatientDto patientDto = new PatientDto();
        patientDto.setPatient_id(patient.getPatient_id());
        patientDto.setPatient_name(patient.getPatient_name());
        patientDto.setPatient_address(patient.getPatient_address());
        patientDto.setPatient_mobile(patient.getPatient_mobile());

        return  patientDto;
    }

    private Patient mapToEntity(PatientDto patientDto){
        Patient patient = new Patient();
        patient.setPatient_id(patientDto.getPatient_id());
        patient.setPatient_name(patientDto.getPatient_name());
        patient.setPatient_mobile(patientDto.getPatient_mobile());
        patient.setPatient_address(patientDto.getPatient_address());
        return patient;
    }
}
