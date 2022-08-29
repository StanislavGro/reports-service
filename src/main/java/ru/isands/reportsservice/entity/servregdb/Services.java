package ru.isands.reportsservice.entity.servregdb;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class Services {

    @Id
    private UUID id;

    private String name;

}
