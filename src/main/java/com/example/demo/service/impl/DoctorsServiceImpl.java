package com.example.demo.service.impl;

import com.example.demo.controller.DoctorsController;
import com.example.demo.dto.DoctorsDto;
import com.example.demo.dto.ReportDto;
import com.example.demo.entity.Doctors;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DoctorsRepository;
import com.example.demo.service.DoctorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorsServiceImpl implements DoctorsService {
    @Autowired
    private DoctorsRepository doctorsRepository;
    public  DoctorsServiceImpl(DoctorsRepository doctorsRepository){this.doctorsRepository = doctorsRepository;}

    @Override
    public DoctorsDto createDoctors(DoctorsDto doctorsDto) {
        Doctors doctors = mapToEntity(doctorsDto);
        Doctors newdoctors = doctorsRepository.save(doctors);

        DoctorsDto doctorsResponse = mapToDTO(newdoctors);
        return  doctorsResponse;
    }

    @Override
    public List<DoctorsDto> getAllDoctors() {
        List<Doctors> doctors = doctorsRepository.findAll();
        return doctors.stream().map(doctor -> mapToDTO(doctor))
                .collect(Collectors.toList());
    }

    @Override
    public DoctorsDto getDoctorsById(long id) {
       Doctors doctors = doctorsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("doctors", "id", id));
        return mapToDTO(doctors);
    }

    @Override
    public DoctorsDto updateDoctors(DoctorsDto doctorsDto, long id) {
        Doctors doctors = doctorsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("doctors", "id", id));

        doctors.setDoctors_id(doctorsDto.getDoctors_id());
        doctors.setDoctors_name(doctorsDto.getDoctors_name());
        doctors.setDoctors_mobile(doctorsDto.getDoctors_mobile());
        doctors.setDoctors_address(doctorsDto.getDoctors_address());

        Doctors updatedDoctors = doctorsRepository.save(doctors);
        return mapToDTO(updatedDoctors);
    }

    @Override
    public void deleteDoctorsById(long id) {
        Doctors doctors = doctorsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("doctors", "id", id));
        doctorsRepository.delete(doctors);

    }


    private DoctorsDto mapToDTO(Doctors doctors){
   DoctorsDto doctorsDto = new DoctorsDto();
   doctorsDto.setDoctors_id(doctors.getDoctors_id());
   doctorsDto.setDoctors_name(doctors.getDoctors_name());
   doctorsDto.setDoctors_mobile(doctors.getDoctors_mobile());
   doctorsDto.setDoctors_address(doctors.getDoctors_address());
   return  doctorsDto;
    }

    private Doctors mapToEntity(DoctorsDto doctorsDto){
        Doctors doctors = new Doctors();
        doctors.setDoctors_id(doctorsDto.getDoctors_id());
        doctors.setDoctors_name(doctorsDto.getDoctors_name());
        doctors.setDoctors_mobile(doctorsDto.getDoctors_mobile());
        doctors.setDoctors_address(doctorsDto.getDoctors_address());
        return doctors;
    }
}
