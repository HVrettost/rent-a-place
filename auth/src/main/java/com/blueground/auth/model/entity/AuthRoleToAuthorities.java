package com.blueground.auth.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "AUTH_ROLE_TO_AUTHORITIES", schema = "MARSRENTAL")
public class AuthRoleToAuthorities {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private UUID id;

    @Column(name = "ROLE")
    private String role;

    @Column(name = "ROLE_ID")
    private String roleId;

    @Column(name = "AUTHORITIES")
    private String authorities;
}
