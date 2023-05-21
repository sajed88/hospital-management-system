package com.example.demo.service.impl;


import com.example.demo.dto.DrugsDto;
import com.example.demo.entity.Drugs;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DrugsRepository;
import com.example.demo.service.DrugsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DrugsServiceImpl implements DrugsService {

    @Autowired
    private DrugsRepository drugsRepository;
    public  DrugsServiceImpl(DrugsRepository drugsRepository){this.drugsRepository = drugsRepository;}

    @Override
    public DrugsDto createDrugs(DrugsDto drugsDto) {
        Drugs drugs = mapToEntity(drugsDto);
        Drugs newDrugs = drugsRepository.save(drugs);

        DrugsDto drugsResponse = mapToDTO(newDrugs);
        return  drugsResponse;
    }

    @Override
    public List<DrugsDto> getAllDrugs() {
        List<Drugs> drugs = drugsRepository.findAll();
        return drugs.stream().map(drug -> mapToDTO(drug))
                .collect(Collectors.toList());
    }

    @Override
    public DrugsDto getDrugsById(long id) {
        Drugs drugs = drugsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("drugs", "id", id));
        return mapToDTO(drugs);
    }

    @Override
    public DrugsDto updateDrugs(DrugsDto drugsDto, long id) {
        Drugs drugs = drugsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("drugs", "id", id));

        drugs.setDrug_id(drugsDto.getDrug_id());
        drugs.setDrug_name(drugsDto.getDrug_name());
        drugs.setDrug_company(drugsDto.getDrug_company());
        drugs.setDrug_description(drugsDto.getDrug_description());
        drugs.setPrice(drugsDto.getPrice());
        drugs.setReport_id(drugsDto.getReport_id());


        Drugs updatedDrugs = drugsRepository.save(drugs);
        return mapToDTO(updatedDrugs);
    }

    @Override
    public void deleteDrugsById(long id) {
        Drugs drugs = drugsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("drugs", "id", id));
        drugsRepository.delete(drugs);

    }


    private DrugsDto mapToDTO(Drugs drugs){
        DrugsDto drugsDto = new DrugsDto();
        drugsDto.setDrug_id(drugs.getDrug_id());
        drugsDto.setDrug_name(drugs.getDrug_name());
        drugsDto.setDrug_company(drugs.getDrug_company());
        drugsDto.setDrug_description(drugs.getDrug_description());
        drugsDto.setPrice(drugs.getPrice());
        drugsDto.setReport_id(drugs.getReport_id());

        return  drugsDto;
    }

    private Drugs mapToEntity(DrugsDto drugsDto){
        Drugs drugs = new Drugs();
        drugs.setDrug_id(drugsDto.getDrug_id());
        drugs.setDrug_name(drugsDto.getDrug_name());
        drugs.setDrug_company(drugsDto.getDrug_company());
        drugs.setDrug_description(drugsDto.getDrug_description());
        drugs.setPrice(drugsDto.getPrice());
        drugs.setReport_id(drugsDto.getReport_id());
        return drugs;
    }

}
