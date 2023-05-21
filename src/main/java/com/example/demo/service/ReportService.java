package com.example.demo.service;

import com.example.demo.dto.DoctorsDto;
import com.example.demo.dto.ReportDto;

import java.util.List;

public interface ReportService {
    ReportDto createReport(ReportDto report);

    List<ReportDto> getAllReport();

    ReportDto getReportById(long id);
    ReportDto updateReport(ReportDto report, long id);
    void deleteReportById(long id);
}
