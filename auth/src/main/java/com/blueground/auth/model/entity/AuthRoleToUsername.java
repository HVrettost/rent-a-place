package com.blueground.auth.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "AUTH_ROLE_TO_USERNAME", schema = "MARSRENTAL")
public class AuthRoleToUsername {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private UUID id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "ROLE")
    private String role;

    @Column(name = "ROLE_ID")
    private UUID roleId;
}
