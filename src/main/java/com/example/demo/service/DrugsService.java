package com.example.demo.service;

import com.example.demo.dto.DoctorsDto;
import com.example.demo.dto.DrugsDto;

import java.util.List;

public interface DrugsService {
    DrugsDto createDrugs(DrugsDto drugs);

    List<DrugsDto> getAllDrugs();

    DrugsDto getDrugsById(long id);
    DrugsDto updateDrugs(DrugsDto drugs, long id);
    void deleteDrugsById(long id);
}
