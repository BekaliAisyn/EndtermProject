package com.example.sportsclub.runner;

import com.example.sportsclub.domain.Player;
import com.example.sportsclub.domain.Sport;
import com.example.sportsclub.domain.SportsClub;
import com.example.sportsclub.repo.PlayerRepository;
import com.example.sportsclub.repo.SportRepository;
import com.example.sportsclub.repo.SportsClubRepository;
import com.example.sportsclub.service.PlayerPool;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DemoRunner implements CommandLineRunner {

    private final SportRepository sportRepository;
    private final SportsClubRepository sportsClubRepository;
    private final PlayerRepository playerRepository;

    public DemoRunner(SportRepository sportRepository,
                      SportsClubRepository sportsClubRepository,
                      PlayerRepository playerRepository) {
        this.sportRepository = sportRepository;
        this.sportsClubRepository = sportsClubRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public void run(String... args) {
        System.out.println("=== Sports Club Management Demo ===");

        if (playerRepository.count() > 0) {
            System.out.println("DB already seeded, skip demo inserts");
            return;
        }

        Sport football = new Sport("Football", true);
        SportsClub kairat = new SportsClub("Kairat FC", "Almaty");

        Player p1 = new Player("Dastan", "Satpayev", 17, LocalDate.of(2026, 1, 19), football, kairat);
        Player p2 = new Player("Temirlan", "Anarbekov", 22, LocalDate.of(2026, 1, 19), football, kairat);
        Player p3 = new Player("Erkin", "Tapalov", 32, LocalDate.of(2026, 1, 19), football, kairat);

        System.out.println("\n--- Assignment 2: PlayerPool (filter/search/sort) ---");
        PlayerPool pool = new PlayerPool();
        pool.add(p1);
        pool.add(p2);
        pool.add(p3);

        System.out.println("\nAll players:");
        pool.getAll().forEach(System.out::println);

        System.out.println("\nPlayers over 18:");
        pool.filterAdults().forEach(System.out::println);

        System.out.println("\nSearch by full name: Temirlan Anarbekov");
        System.out.println(pool.searchByFullName("Temirlan Anarbekov").orElse(null));

        System.out.println("\nSorted by age (ASC):");
        pool.sortByAgeAsc().forEach(pp -> System.out.println(pp.getFullName() + " age=" + pp.getAge()));

        // ---------------------------
        // Assignment 3

        System.out.println("\n=== DB CRUD Demo (PostgreSQL via Spring Data JPA) ===");

        Sport footballDb = sportRepository.findByNameIgnoreCase("Football")
                .orElseGet(() -> sportRepository.save(new Sport("Football", true)));

        SportsClub kairatDb = sportsClubRepository.findAll().stream()
                .filter(c -> c.getName() != null && c.getName().equalsIgnoreCase("Kairat FC"))
                .findFirst()
                .orElseGet(() -> sportsClubRepository.save(new SportsClub("Kairat FC", "Almaty")));

        Player db1 = playerRepository.save(new Player("Dastan", "Satpayev", 17, LocalDate.now(), footballDb, kairatDb));
        Player db2 = playerRepository.save(new Player("Temirlan", "Anarbekov", 22, LocalDate.now(), footballDb, kairatDb));
        Player db3 = playerRepository.save(new Player("Erkin", "Tapalov", 32, LocalDate.now(), footballDb, kairatDb));

        Player bekali = playerRepository.save(new Player("Bekali", "Test", 20, LocalDate.now(), footballDb, kairatDb));

        System.out.println("Created players IDs: " + List.of(db1.getId(), db2.getId(), db3.getId(), bekali.getId()));

        System.out.println("\nRead all players from DB:");
        playerRepository.findAll().forEach(System.out::println);

        System.out.println("\nUpdate club city: Kairat FC -> Astana");
        kairatDb.setCity("Astana");
        sportsClubRepository.save(kairatDb);

        System.out.println("Update player age: Bekali Test -> 21");
        bekali.setAge(21);
        playerRepository.save(bekali);

        System.out.println("\nAfter UPDATE, players from DB:");
        playerRepository.findAll().forEach(System.out::println);

        System.out.println("\nDelete: remove Dastan Satpayev");
        playerRepository.deleteById(db1.getId());
        System.out.println("After delete, players count=" + playerRepository.count());
    }
}
