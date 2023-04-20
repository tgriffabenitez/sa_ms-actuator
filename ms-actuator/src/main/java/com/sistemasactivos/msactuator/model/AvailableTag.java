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
@Table(name = "available_tag")
public class AvailableTag extends Base {

    private String tag;

    @ElementCollection
    @CollectionTable(name ="available_tag_values", joinColumns = @JoinColumn(name = "available_tag_id"))
    @Column(name = "value")
    private List<String> values;
}