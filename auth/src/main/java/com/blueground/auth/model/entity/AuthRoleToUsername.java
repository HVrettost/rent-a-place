package com.blueground.auth.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "AUTH_ROLE_TO_USERNAME", schema = "RENTAPLACE")
public class AuthRoleToUsername {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private UUID id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "USER_ID")
    private UUID userId;

    @Column(name = "ROLE")
    private String role;

    @Column(name = "ROLE_ID")
    private UUID roleId;
}
