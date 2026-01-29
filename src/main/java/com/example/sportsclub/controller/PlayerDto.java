package com.example.sportsclub.controller;

import java.time.LocalDate;

public class PlayerDto {
    public Long id;
    public String firstName;
    public String lastName;
    public int age;
    public LocalDate joinedAt;
    public String sportName;
    public String clubName;

    public PlayerDto(Long id, String firstName, String lastName, int age,
                     LocalDate joinedAt, String sportName, String clubName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.joinedAt = joinedAt;
        this.sportName = sportName;
        this.clubName = clubName;
    }
}
