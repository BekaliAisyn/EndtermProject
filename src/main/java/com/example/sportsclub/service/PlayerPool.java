package com.example.sportsclub.service;

import com.example.sportsclub.domain.Player;
import com.example.sportsclub.domain.Sport;
import com.example.sportsclub.domain.SportsClub;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


public class PlayerPool {
    private final List<Player> players = new ArrayList<>();

    public void add(Player player) {
        Objects.requireNonNull(player, "player");
        players.add(player);
    }

    public List<Player> getAll() {
        return List.copyOf(players);
    }

    public List<Player> filterAdults() {
        return players.stream()
                .filter(Player::isAdult)
                .toList();
    }

    public List<Player> filterBySportName(String sportName) {
        if (sportName == null) return List.of();
        String target = sportName.trim().toLowerCase();
        return players.stream()
                .filter(p -> {
                    Sport s = p.getSport();
                    return s != null && s.getName() != null && s.getName().trim().toLowerCase().equals(target);
                })
                .toList();
    }

    public List<Player> filterByClubCity(String city) {
        if (city == null) return List.of();
        String target = city.trim().toLowerCase();
        return players.stream()
                .filter(p -> {
                    SportsClub c = p.getClub();
                    return c != null && c.getCity() != null && c.getCity().trim().toLowerCase().equals(target);
                })
                .toList();
    }

    public Optional<Player> searchByFullName(String fullName) {
        if (fullName == null) return Optional.empty();
        String target = fullName.trim().toLowerCase();
        return players.stream()
                .filter(p -> p.getFullName().trim().toLowerCase().equals(target))
                .findFirst();
    }

    public List<Player> sortByAgeAsc() {
        return players.stream()
                .sorted(Comparator.comparingInt(Player::getAge))
                .collect(Collectors.toList());
    }

    public List<Player> sortByJoinedAtDesc() {
        return players.stream()
                .sorted(Comparator.comparing(Player::getJoinedAt).reversed())
                .toList();
    }
}

