package com.dkoper.orion.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comparison_element")
public class ComparisonElement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "times")
    private String times;

    @Column(name = "altitude")
    private String altitude;

    @Column(name = "accuracy")
    private String accuracy;

    @JoinColumn(name = "comparison", referencedColumnName = "id")
    @ManyToOne(targetEntity = Comparison.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Comparison comparison;

}


