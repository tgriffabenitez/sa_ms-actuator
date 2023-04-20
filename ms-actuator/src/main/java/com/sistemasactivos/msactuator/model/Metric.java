package com.sistemasactivos.msactuator.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "metric")
public class Metric extends Base {

    private String name;

    private String description;

    private String baseUnit;

    private String ms;

    private String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "metric_id")
    private List<Measurement> measurements;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "metric_id")
    private List<AvailableTag> availableTags;

}
