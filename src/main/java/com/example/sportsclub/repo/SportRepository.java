package com.example.sportsclub.repo;

import com.example.sportsclub.domain.Sport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SportRepository extends JpaRepository<Sport, Long> {
    Optional<Sport> findByNameIgnoreCase(String name);
}

