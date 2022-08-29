package ru.isands.reportsservice.entity.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.isands.reportsservice.entity.reportsdb.Statistics;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder(setterPrefix = "with")
public class ReportDto {

    @NotNull
    private UUID serviceId;

    @NotNull
    private boolean availability;

    @NotNull
    private int numberRequests;

    @NotNull
    @NotEmpty
    private List<StatisticsDto> statisticsDto = new ArrayList<>();

}
