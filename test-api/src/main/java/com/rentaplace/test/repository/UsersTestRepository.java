package com.rentaplace.test.repository;

import com.rentaplace.users.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsersTestRepository extends JpaRepository<User, UUID> {
}
