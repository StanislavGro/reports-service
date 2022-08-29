package ru.isands.reportsservice.service;

import org.springframework.stereotype.Service;
import ru.isands.reportsservice.entity.servregdb.Services;

import java.util.List;

public interface ServicesService {

    List<Services> getAll();

}
