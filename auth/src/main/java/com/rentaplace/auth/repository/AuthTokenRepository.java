package com.rentaplace.auth.repository;

import com.rentaplace.auth.model.entity.RefreshTokenWhitelist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthTokenRepository extends JpaRepository<RefreshTokenWhitelist, String> {

    @Modifying
    void deleteByUsernameAndUserAgent(@Param("username") String username,
                                      @Param("userAgent") String userAgent);

    @Modifying
    void deleteAllByUsername(@Param("username") String username);

    boolean existsByUsernameAndUserAgent(@Param("username") String username,
                                         @Param("userAgent") String userAgent);

    boolean existsByUsername(@Param("username") String username);
}
