package ru.isands.reportsservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;
import ru.isands.reportsservice.service.MonitoringService;

@RestController
@RequestMapping("/api/v1/monitoring")
@Tag(name = "Monitoring", description = "Контроллер для взаимодействия модуля мониторинга с МФО")
public class MonitoringController {
    private final MonitoringService monitoringService;

    public MonitoringController(MonitoringService monitoringService) {
        this.monitoringService = monitoringService;
    }

//    @GetMapping(produces = "application/json")
//    @ResponseBody
//    @Operation(summary = "Получение общего количества отчетов")
//    public ResponseEntity<Mono<Integer>> getServiceNumbers() {
//        return ResponseEntity.ok(monitoringService.getServiceNumbers());
//    }

    @PostMapping(value = "/randreports", produces = "application/json")
    @ResponseBody
    @Operation(summary = "Генерация рандомного отчета и его отправка")
    public ResponseEntity<HttpStatus> createAndSendRandomReport() {
        try {
            monitoringService.createAndSendRandomReport();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
