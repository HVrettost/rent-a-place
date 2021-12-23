package com.blueground.test.repository;

import com.blueground.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsersTestRepository extends JpaRepository<User, UUID> {
}
