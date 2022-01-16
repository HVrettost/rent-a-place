package com.rentaplace.units.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Column;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "UNITS", schema = "RENTAPLACE")
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

    @ColumnDefault(value = "FALSE")
    @Column(name = "CANCELLATION_POLICY")
    private Boolean cancellationPolicy;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "AVERAGE_SCORE")
    private Integer averageScore;
}
