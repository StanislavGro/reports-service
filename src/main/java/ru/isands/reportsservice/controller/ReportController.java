package ru.isands.reportsservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.isands.reportsservice.entity.dto.ReportDto;
import ru.isands.reportsservice.entity.reportsdb.Report;
import ru.isands.reportsservice.service.ReportService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/report")
@Tag(name = "Reports", description = "Генерация отчетов о работе сервиса")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping(value = "/reports", produces = "application/json")
    @ResponseBody
    @Operation(summary = "Получение всех отчетов о работах сервисах")
    public ResponseEntity<List<Report>> getAll() {

        List<Report> responseEntity = null;

        try {
             responseEntity = reportService.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(responseEntity);

    }

    @PostMapping(produces = "application/json")
    @ResponseBody
    @Operation(summary = "Ручное создание отчета о работе сервиса")
    public ResponseEntity createReport(@RequestBody ReportDto reportDto) {
        try {
            reportService.createReport(reportDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping(value = "/random", produces = "application/json")
    @ResponseBody
    @Operation(summary = "Автоматическое создание отчета о работе сервиса")
    public ResponseEntity createRandomReport() {
        try {
            reportService.createRandomReport();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }


}
//{
//        "serviceId": "00000000-0000-0444-4444-000000000002",
//        "availability": true,
//        "numberRequests": 15,
//        "statistics": [
//        {
//        "name": "string",
//        "value": "string",
//        "numeric": true
//        }
//        ]
//        }
