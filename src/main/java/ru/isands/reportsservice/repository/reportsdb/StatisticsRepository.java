package ru.isands.reportsservice.repository.reportsdb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.isands.reportsservice.entity.reportsdb.Statistics;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistics, Long> {

}
