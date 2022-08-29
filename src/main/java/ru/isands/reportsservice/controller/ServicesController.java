package ru.isands.reportsservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.isands.reportsservice.entity.servregdb.Services;
import ru.isands.reportsservice.service.ServicesService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/services")
@AllArgsConstructor
@Tag(name = "Services", description = "Получение инфомации о сервисах")
public class ServicesController {

    private final ServicesService servicesService;

    @GetMapping
    @ResponseBody
    @Operation(summary = "Получение актуальной инфомации о доступных сервисах")
    public ResponseEntity<List<Services>> getAll(){
        return ResponseEntity.ok(servicesService.getAll());
    }

}
