package ru.isands.reportsservice.service;

import reactor.core.publisher.Mono;
import ru.isands.reportsservice.entity.reportsdb.Report;

public interface MonitoringService {

    Mono<Integer> getServiceNumbers();

    void createAndSendRandomReport();

}
