package ru.isands.reportsservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.isands.reportsservice.entity.dto.StatisticsDto;
import ru.isands.reportsservice.entity.reportsdb.Statistics;
import ru.isands.reportsservice.service.StatisticsService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/statistics")
@Tag(name = "Statistics", description = "Взаимодействие со статистикой внутри отчета")
public class StatisticsController {

    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping(value = "/statistics", produces = "application/json")
    @ResponseBody
    @Operation(summary = "Получение всех экземпляров")
    public ResponseEntity<List<Statistics>> getAll() {
        return ResponseEntity.ok(statisticsService.getAll());
    }

    @PostMapping(produces = "application/json")
    @ResponseBody
    @Operation(summary = "Создание экземпляра статистики")
    public ResponseEntity createStatistic(@RequestBody StatisticsDto statisticsDto) {
        try {
            statisticsService.create(statisticConverter(statisticsDto));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping(value = "/random", produces = "application/json")
    @ResponseBody
    @Operation(summary = "Создание рандомного экземпляра статистики")
    public ResponseEntity createRandomStatistic() {
        try {
            statisticsService.createRandom();
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

    public static Statistics statisticConverter(StatisticsDto statisticsDto){
        return Statistics
                .builder()
                .withName(statisticsDto.getName())
                .withNumeric(statisticsDto.isNumeric())
                .withValue(statisticsDto.getValue())
                .build();
    }
}

