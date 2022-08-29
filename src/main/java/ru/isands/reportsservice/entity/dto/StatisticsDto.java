package ru.isands.reportsservice.entity.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder(setterPrefix = "with")
public class StatisticsDto {

    @NotBlank
    private String name;

    @NotBlank
    private String value;

    @NotBlank
    private boolean numeric;

}
