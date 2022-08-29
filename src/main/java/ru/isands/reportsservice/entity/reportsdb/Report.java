package ru.isands.reportsservice.entity.reportsdb;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
@Getter
@Setter
public class Report implements Cloneable {

    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID serviceId;

    private boolean availability;

    private int numberRequests;

    // Merge - чтобы по id подтягивать данные в statistics,
    // Eager - чтобы при выводе или поиске можно было также доставвать из дочерних таблиц всю нужную информацию (statistics)
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "report_statistics", joinColumns = @JoinColumn(name = "report_id"), inverseJoinColumns = @JoinColumn(name = "statistics_id"))
    private List<Statistics> statistics = new ArrayList<>();

    public void addStatistics(Statistics statistic){
        statistics.add(statistic);
    }

    @Override
    public Object clone() {

        Report o = null;

        try {
            o = (Report) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }

        return o;
    }
}
