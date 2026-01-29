package com.example.sportsclub.repo;

import com.example.sportsclub.domain.SportsClub;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SportsClubRepository extends JpaRepository<SportsClub, Long> {
}

