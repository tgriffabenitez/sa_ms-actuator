package com.sistemasactivos.msactuator.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "metric_id")
    private List<Measurement> measurements;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "metric_id")
    private List<AvailableTag> availableTags;

}
