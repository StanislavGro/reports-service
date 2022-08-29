package ru.isands.reportsservice.service;

import ru.isands.reportsservice.entity.dto.ReportDto;
import ru.isands.reportsservice.entity.reportsdb.Report;

import java.util.List;

public interface ReportService {

    void createReport(ReportDto reportDto);

    void createRandomReport();

    List<Report> getAll();

}
