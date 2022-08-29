package ru.isands.reportsservice.service;

import ru.isands.reportsservice.entity.reportsdb.Statistics;

import java.util.List;

public interface StatisticsService {

    void create(Statistics statistics);

    void createRandom();

    List<Statistics> getAll();

}
