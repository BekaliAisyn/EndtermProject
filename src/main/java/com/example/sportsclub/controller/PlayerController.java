package com.example.sportsclub.controller;

import com.example.sportsclub.domain.Player;
import com.example.sportsclub.repo.PlayerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlayerController {

    private final PlayerRepository repo;

    public PlayerController(PlayerRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/players")
    public List<PlayerDto> players() {
        return repo.findAll().stream()
                .map(p -> new PlayerDto(
                        p.getId(),
                        p.getFirstName(),
                        p.getLastName(),
                        p.getAge(),
                        p.getJoinedAt(),
                        p.getSport() != null ? p.getSport().getName() : null,
                        p.getClub() != null ? p.getClub().getName() : null
                ))
                .toList();
    }
}
