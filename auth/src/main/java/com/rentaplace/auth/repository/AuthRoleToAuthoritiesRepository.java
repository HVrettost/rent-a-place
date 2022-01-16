package com.rentaplace.auth.repository;

import com.rentaplace.auth.model.entity.AuthRoleToAuthorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthRoleToAuthoritiesRepository extends JpaRepository<AuthRoleToAuthorities, UUID> {

    Optional<AuthRoleToAuthorities> findByRole(@Param("role") String authRole);
}
