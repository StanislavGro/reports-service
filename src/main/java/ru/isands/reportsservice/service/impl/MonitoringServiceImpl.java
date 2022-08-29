package ru.isands.reportsservice.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.isands.reportsservice.entity.dto.ReportDto;
import ru.isands.reportsservice.entity.reportsdb.Report;
import ru.isands.reportsservice.entity.servregdb.Services;
import ru.isands.reportsservice.repository.servregdb.ServicesRepository;
import ru.isands.reportsservice.service.MonitoringService;

import java.util.List;

@Service
@Slf4j
public class MonitoringServiceImpl implements MonitoringService {

    private final ReportServiceImpl reportService;
    private final ServicesRepository servicesRepository;
    private static final String MONITORING_URL_TEMPLATE = "/numbers";
    private final WebClient webClient;

    public MonitoringServiceImpl(ReportServiceImpl reportService, ServicesRepository servicesRepository, WebClient webClient) {
        this.reportService = reportService;
        this.servicesRepository = servicesRepository;
        this.webClient = webClient;
    }

    @Override
    public Mono<Integer> getServiceNumbers() {
        return webClient
                .get()
                .uri(MONITORING_URL_TEMPLATE)
                .retrieve()
                .bodyToMono(Integer.class);
    }

    @Override
    @Scheduled(initialDelay = 2000, fixedRate = 7200000)
//    @Async
    public void createAndSendRandomReport() {

        log.info("Creating and sending a report");

        List<Services> servicesList = servicesRepository.findAll();

        for (Services services : servicesList) {

            ReportDto reportDto = ReportDto
                    .builder()
                    .withServiceId(services.getId())
                    .withAvailability(reportService.getRandomAvailability())
                    .withNumberRequests(reportService.getRandomNumberRequests())
                    .withStatisticsDto(reportService.getRandomScheduledStatistics())
                    .build();

            webClient
                    .post()
                    .uri("")
                    .body(Mono.just(reportDto), Report.class)
                    .retrieve()
                    .bodyToMono(Report.class)
                    .block();
        }

        log.info("Successfully");
    }

//    @Override
//    @Scheduled(initialDelay = 2000, fixedRate = 2000)
////    @Async
//    public void createAndSendRandomReport() {
//            ReportDto reportDto = ReportDto
//                    .builder()
//                    .withServiceId(reportService.getRandomId())
//                    .withAvailability(reportService.getRandomAvailability())
//                    .withNumberRequests(reportService.getRandomNumberRequests())
//                    .withStatisticsDto(reportService.getRandomStatisticsDto())
//                    .build();
//
//            webClient
//                    .post()
//                    .uri("")
//                    .body(Mono.just(reportDto), Report.class)
//                    .retrieve()
//                    .bodyToMono(Report.class)
//                    .block()
//            ;
//        }
//    }

}
