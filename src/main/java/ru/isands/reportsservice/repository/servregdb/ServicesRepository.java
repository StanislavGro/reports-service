package ru.isands.reportsservice.repository.servregdb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.isands.reportsservice.entity.servregdb.Services;

import java.util.UUID;

@Repository
public interface ServicesRepository extends JpaRepository<Services, UUID> {
}
