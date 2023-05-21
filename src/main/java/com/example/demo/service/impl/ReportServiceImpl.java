package com.example.demo.service.impl;

import com.example.demo.dto.DoctorsDto;
import com.example.demo.dto.ReportDto;
import com.example.demo.entity.Doctors;
import com.example.demo.entity.Report;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DoctorsRepository;
import com.example.demo.repository.ReportRepository;
import com.example.demo.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportRepository reportRepository;
    public  ReportServiceImpl(ReportRepository reportRepository){this.reportRepository = reportRepository;}

    @Override
    public ReportDto createReport(ReportDto reportDto) {
        Report report = mapToEntity(reportDto);
        Report newReport = reportRepository.save(report);

        ReportDto reportResponse = mapToDTO(newReport);
        return  reportResponse;
    }

    @Override
    public List<ReportDto> getAllReport() {
        List<Report> reports = reportRepository.findAll();
        return reports.stream().map(report -> mapToDTO(report))
                .collect(Collectors.toList());
    }

    @Override
    public ReportDto getReportById(long id) {
        Report reports = reportRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("reports", "id", id));
        return mapToDTO(reports);
    }

    @Override
    public ReportDto updateReport(ReportDto reportDto, long id) {
        Report reports = reportRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("reports", "id", id));

        reports.setReport_id(reportDto.getReport_id());
        reports.setReport_name(reportDto.getReport_name());
        reports.setDoctors_id(reportDto.getDoctors_id());
        reports.setDrugs_id(reportDto.getDrugs_id());
        reports.setPatient_id(reportDto.getPatient_id());

        Report updatedReport = reportRepository.save(reports);
        return mapToDTO(updatedReport);
    }

    @Override
    public void deleteReportById(long id) {
        Report reports = reportRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Report", "id", id));
        reportRepository.delete(reports);

    }


    private ReportDto mapToDTO(Report reports){
        ReportDto reportDto = new ReportDto();
        reportDto.setReport_id(reports.getReport_id());
        reportDto.setReport_name(reports.getReport_name());
        reportDto.setDoctors_id(reports.getDoctors_id());
        reportDto.setDrugs_id(reports.getDrugs_id());
        reportDto.setPatient_id(reports.getPatient_id());
        return  reportDto;
    }

    private Report mapToEntity(ReportDto reportDto){
        Report reports = new Report();
        reports.setReport_id(reportDto.getReport_id());
        reports.setReport_name(reportDto.getReport_name());
        reports.setDoctors_id(reportDto.getDoctors_id());
        reports.setDrugs_id(reportDto.getDrugs_id());
        reports.setPatient_id(reportDto.getPatient_id());
        return reports;
    }
}
