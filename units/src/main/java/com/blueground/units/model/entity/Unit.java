package com.blueground.units.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "UNITS")
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "UNIT_ID")
    private UUID unitId;

    @Column(name = "IMAGE_URL")
    private String imageUrl;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "REGION")
    private String region;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CANCELLATION_POLICY")
    private Boolean cancellationPolicy;

    @Column(name = "PRICE")
    private BigDecimal price;
}
