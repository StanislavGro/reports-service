package ru.isands.reportsservice.repository.reportsdb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.isands.reportsservice.entity.reportsdb.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

}
