package com.blueground.auth.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "REFRESH_TOKEN_WHITELIST", schema = "MARSRENTAL")
public class RefreshTokenWhitelist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private UUID id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "USER_AGENT")
    private String userAgent;

    @Column(name = "REFRESH_TOKEN")
    private String refreshToken;
}
