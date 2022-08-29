package ru.isands.reportsservice.service.impl;

import org.springframework.stereotype.Service;
import ru.isands.reportsservice.entity.dto.ReportDto;
import ru.isands.reportsservice.entity.dto.StatisticsDto;
import ru.isands.reportsservice.entity.enums.Names;
import ru.isands.reportsservice.entity.reportsdb.Report;
import ru.isands.reportsservice.entity.reportsdb.Statistics;
import ru.isands.reportsservice.entity.servregdb.Services;
import ru.isands.reportsservice.repository.reportsdb.ReportRepository;
import ru.isands.reportsservice.repository.reportsdb.StatisticsRepository;
import ru.isands.reportsservice.repository.servregdb.ServicesRepository;
import ru.isands.reportsservice.service.ReportService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static ru.isands.reportsservice.controller.StatisticsController.statisticConverter;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final ServicesRepository servicesRepository;

    private final StatisticsRepository statisticsRepository;

    public ReportServiceImpl(ReportRepository repository, ServicesRepository servicesRepository, StatisticsRepository statisticsRepository) {
        this.reportRepository = repository;
        this.servicesRepository = servicesRepository;
        this.statisticsRepository = statisticsRepository;
    }


    @Override
    public void createReport(ReportDto reportDto) {

        Report report = reportConverter(reportDto);

        for (StatisticsDto statisticsDto : reportDto.getStatisticsDto()) {
//            if (statistics.getId() != null) {
//                Optional<Statistics> findStatistic = statisticsRepository.findById(statistics.getId());
//                findStatistic.ifPresent(report::addStatistics);
//            } else
//                report.getStatistics().add(statistics);
            report.addStatistics(statisticConverter(statisticsDto));
        }

        reportRepository.save(report);
    }

    @Override
    public void createRandomReport() {

//        Report report = Report
//                .builder()
//                .withServiceId(getRandomServiceId())
//                .withAvailability(getRandomAvailability())
//                .withNumberRequests(getRandomNumberRequests())
//                .withStatistics(getRandomStatisticsArrayList())
//                .build();

        reportRepository.save(randomReport());

    }

    @Override
    public List<Report> getAll() {
        return reportRepository.findAll();
    }

    public UUID getRandomServiceId() {
        List<Services> services = servicesRepository.findAll();
        int randomServiceId = (int) (Math.random() * services.size());
        return services.get(randomServiceId).getId();
    }

    public boolean getRandomAvailability() {
        return (int) (Math.random() * 2) == 0;
    }

    public int getRandomNumberRequests() {
        return (int) (Math.random() * 101);
    }

    public ArrayList<Statistics> getRandomStatisticsArrayList() {

        List<Statistics> statisticsList = statisticsRepository.findAll();

        int randomStatisticSize = (int) (Math.random() * statisticsList.size());

        ArrayList<Statistics> randomStatisticsArrayList = new ArrayList<>(randomStatisticSize);

        HashMap<Integer, String> hashMap = new HashMap<>();

        for (int i = 0; i < randomStatisticSize + 1; i++) {
            int randomIndex = (int) (Math.random() * statisticsList.size());
            while (hashMap.containsKey(randomIndex))
                randomIndex = (int) (Math.random() * statisticsList.size());
            hashMap.put(randomIndex, "");
            randomStatisticsArrayList.add(statisticsList.get(randomIndex));
        }

        return randomStatisticsArrayList;
    }

    public ArrayList<StatisticsDto> getRandomStatisticsDto() {

        List<Statistics> statisticsList = statisticsRepository.findAll();

        int randomStatisticSize = (int) (Math.random() * statisticsList.size());

        HashMap<Integer, StatisticsDto> statisticsDtoHashMap = new HashMap<>();

        for (int i = 0; i < randomStatisticSize + 1; i++) {

            createHashMap(statisticsList, statisticsDtoHashMap);

        }

        return new ArrayList<>(statisticsDtoHashMap.values());
    }


    public ArrayList<StatisticsDto> getRandomScheduledStatistics() {

        List<Statistics> statisticsList = statisticsRepository.findAll();

        HashMap<Integer, StatisticsDto> statisticsDtoHashMap = new HashMap<>();

        int randomIndex = (int) (Math.random() * statisticsList.size());

        while (!statisticsList.get(randomIndex).getName().equals(Names.NUMBER.getValue())) {
            randomIndex = (int) (Math.random() * statisticsList.size());
        }

        Statistics statistics = statisticsList.get(randomIndex);

        StatisticsDto statisticsDto = StatisticsDto
                .builder()
                .withName(statistics.getName())
                .withValue(statistics.getValue())
                .withNumeric(statistics.isNumeric())
                .build();

        statisticsDtoHashMap.put(randomIndex, statisticsDto);

        while (statisticsDtoHashMap.containsKey(randomIndex) && !statisticsList.get(randomIndex).getName().equals(Names.STRING.getValue())) {
            randomIndex = (int) (Math.random() * statisticsList.size());
        }

        statistics = statisticsList.get(randomIndex);

        statisticsDto = StatisticsDto
                .builder()
                .withName(statistics.getName())
                .withValue(statistics.getValue())
                .withNumeric(statistics.isNumeric())
                .build();

        statisticsDtoHashMap.put(randomIndex, statisticsDto);

        return new ArrayList<>(statisticsDtoHashMap.values());
    }

    private void createHashMap(List<Statistics> statisticsList, HashMap<Integer, StatisticsDto> statisticsDtoHashMap) {

        int randomIndex = (int) (Math.random() * statisticsList.size());

        while (statisticsDtoHashMap.containsKey(randomIndex))
            randomIndex = (int) (Math.random() * statisticsList.size());

        Statistics statistics = statisticsList.get(randomIndex);

        StatisticsDto statisticsDto = StatisticsDto
                .builder()
                .withName(statistics.getName())
                .withValue(statistics.getValue())
                .withNumeric(statistics.isNumeric())
                .build();

        statisticsDtoHashMap.put(randomIndex, statisticsDto);

    }

    private Report reportConverter(ReportDto reportDto) {

        return Report
                .builder()
                .withServiceId(reportDto.getServiceId())
                .withAvailability(reportDto.isAvailability())
                .withNumberRequests(reportDto.getNumberRequests())
                .withStatistics(new ArrayList<>())
                .build();
    }

    public Report randomReport() {
        return Report
                .builder()
                .withServiceId(getRandomServiceId())
                .withAvailability(getRandomAvailability())
                .withNumberRequests(getRandomNumberRequests())
                .withStatistics(getRandomStatisticsArrayList())
                .build();
    }

}
