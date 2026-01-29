package com.example.sportsclub.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "sports")
public class Sport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private boolean teamSport;

    public Sport() {
    }

    public Sport(String name, boolean teamSport) {
        this.name = name;
        this.teamSport = teamSport;
    }

    public void renameTo(String newName) {
        if (newName == null || newName.isBlank()) {
            throw new IllegalArgumentException("Sport name must not be blank");
        }
        this.name = newName.trim();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isTeamSport() {
        return teamSport;
    }

    public void setTeamSport(boolean teamSport) {
        this.teamSport = teamSport;
    }

    @Override
    public String toString() {
        return "Sport{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", teamSport=" + teamSport +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sport sport)) return false;
        return teamSport == sport.teamSport &&
                Objects.equals(name, sport.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, teamSport);
    }
}

