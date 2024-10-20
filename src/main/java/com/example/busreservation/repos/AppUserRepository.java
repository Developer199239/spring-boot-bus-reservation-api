package com.example.busreservation.repos;

import com.example.busreservation.entities.AppUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUsers, Long> {
    Optional<AppUsers> findByUserName(String userName);

    Optional<AppUsers> findByMobileOrEmail(String mobile, String email);

    Optional<AppUsers> findByMobile(String mobile);

    Optional<AppUsers> findByEmail(String email);

    Boolean existsByMobile(String mobile);

    Boolean existsByEmail(String email);

    Boolean existsByMobileOrEmail(String mobile, String email);
}

