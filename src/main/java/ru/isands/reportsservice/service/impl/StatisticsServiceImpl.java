package ru.isands.reportsservice.service.impl;

import org.springframework.stereotype.Service;
import ru.isands.reportsservice.entity.reportsdb.Statistics;
import ru.isands.reportsservice.repository.reportsdb.StatisticsRepository;
import ru.isands.reportsservice.service.StatisticsService;
import ru.isands.reportsservice.utill.RandomStatisticValues;

import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    private final StatisticsRepository statisticsRepository;

    public StatisticsServiceImpl(StatisticsRepository statisticsRepository) {
        this.statisticsRepository = statisticsRepository;
    }

    @Override
    public void create(Statistics statistics) {
        statisticsRepository.save(statistics);
    }

    @Override
    public void createRandom() {
        RandomStatisticValues randomValues = new RandomStatisticValues();

        Statistics statistics = Statistics.builder()
                .withName(randomValues.getRandomName())
                .withValue(randomValues.getRandomValue())
                .withNumeric(randomValues.isNumeric())
                .build();

        statisticsRepository.save(statistics);
    }

    @Override
    public List<Statistics> getAll() {
        return statisticsRepository.findAll();
    }
}
