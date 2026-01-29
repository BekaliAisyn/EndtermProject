# Sports Club Management (Endterm Project)

Subject area: **Sports Club Management**  
Entities: **Sport**, **Player**, **SportsClub**

This project is made to satisfy:
- **Assignment 1**: classes + constructors + attributes + methods + getters/setters + instances in `main` (done via Spring Boot runner) + console output + object comparison (`equals`)
- **Assignment 2**: data abstraction + data pool + filtering/searching/sorting (see `PlayerPool`)
- **Assignment 3**: PostgreSQL DB connection + tables + CRUD (create/read/update/delete) via Spring Data JPA on startup (see `DemoRunner`)

## Tech
- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven

## How to run (PostgreSQL)
1. Create DB (in pgAdmin or psql):

```sql
CREATE DATABASE sportsclub_db;
```

2. Update credentials in `src/main/resources/application.properties` if needed:
- `spring.datasource.username`
- `spring.datasource.password`
- `spring.datasource.url`

3. Run the app:

```bash
mvn spring-boot:run
```

On first run, Hibernate will create/update tables:
- `sports`
- `sports_clubs`
- `players`

The console will show:
- object creation + printing + comparisons
- filtering/search/sorting from an in-memory pool
- DB CRUD demo (insert/select/update/delete)

## Where the required code is
- Entities:
  - `src/main/java/com/example/sportsclub/domain/Sport.java`
  - `src/main/java/com/example/sportsclub/domain/Player.java`
  - `src/main/java/com/example/sportsclub/domain/SportsClub.java`
- Data pool (Assignment 2):
  - `src/main/java/com/example/sportsclub/service/PlayerPool.java`
- DB CRUD demo (Assignment 3):
  - `src/main/java/com/example/sportsclub/runner/DemoRunner.java`

## Push to GitHub (what to submit)
In your project folder:

```bash
git init
git add .
git commit -m "Sports Club Management assignments (Spring Boot + Postgres)"
git branch -M main
git remote add origin https://github.com/<YOUR_USERNAME>/<YOUR_REPO>.git
git push -u origin main
```

Then copy the repo link (example):
`https://github.com/<YOUR_USERNAME>/<YOUR_REPO>`

Paste that link into the assignment “result/text field”.

