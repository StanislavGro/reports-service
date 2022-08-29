package ru.isands.reportsservice.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.isands.reportsservice.entity.servregdb.Services;
import ru.isands.reportsservice.repository.servregdb.ServicesRepository;
import ru.isands.reportsservice.service.ServicesService;

import java.util.List;

@Service
@AllArgsConstructor
public class ServicesServiceImpl implements ServicesService {

    private final ServicesRepository servicesRepository;

    @Override
    public List<Services> getAll() {
        return servicesRepository.findAll();
    }
}
