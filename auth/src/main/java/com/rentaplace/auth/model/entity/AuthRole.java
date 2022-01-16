package com.rentaplace.auth.model.entity;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Column;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "AUTH_ROLE", schema = "RENTAPLACE")
public class AuthRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private UUID id;

    @Column(name = "ROLE")
    private String role;
}
