# Nebula Rift — Space Shooter (Spring Boot + JSP + Servlet)

A compact gaming website built with **Spring Boot**, **JSP**, and a **custom servlet**. It showcases a space‑shooter command deck with pilots, ships, missions, enemy waves, and a playable canvas mini‑game.

## Concepts used (as requested)
- **Collection Frameworks & Data Structures**: `ArrayList`, `LinkedHashMap`, `LinkedHashSet`, `ArrayDeque` (queue + stack), `PriorityQueue`, `EnumMap`, `EnumSet`, `TreeSet` in `GameService`.
- **Enums**: `Difficulty`, `PilotRank`, `ShipClass`, `EnemyType`, `WeaponType`.
- **Servlets**: `GameStateServlet` is registered manually and serves JSON at `/servlet/state`.
- **JSP**: `index.jsp` and `hangar.jsp` render the UI with JSTL.
- **Spring Boot**: Main app, controllers, servlet registration, and configuration.

## Features
- Command Deck data (pilots, missions, next‑wave intel, weapon stock, leaderboard) exposed via JSON endpoints.
- Hangar page with ship roles, stats, and upgrades.
- Real‑time mini arcade game (WASD/Arrow keys + Space).
- API state endpoint (`/api/state`) and servlet endpoint (`/servlet/state`).

## Command deck data (moved from UI)
The detailed Command Deck readouts (pilots, mission board, next‑wave intel, weapon stock, and leaderboard) are still available in the backend payloads. Use the JSON endpoints to view or reuse them:
- **Spring API**: `/api/state`
- **Servlet JSON**: `/servlet/state`

## Project structure
```
src/
  main/
    java/com/spaceshooter/
      SpaceShooterApplication.java
      config/ServletRegistrationConfig.java
      controller/GameController.java
      model/ (enums + data classes)
      service/GameService.java
      servlet/GameStateServlet.java
    resources/
      application.properties
      static/css/styles.css
      static/js/game.js
    webapp/WEB-INF/jsp/
      index.jsp
      hangar.jsp
```

## How to run
### Requirements
- **Java 17**
- **Maven**

### Run locally
```bash
mvn spring-boot:run
```

### Build a WAR
```bash
mvn -DskipTests package
```

Then open:
- **Home**: http://localhost:8080/
- **Hangar**: http://localhost:8080/hangar
- **API JSON**: http://localhost:8080/api/state
- **Servlet JSON**: http://localhost:8080/servlet/state

## Login
Use the demo credentials below to access the game UI:
- **Username**: `dhyan`
- **Password**: `1234`

## Game controls
- **Move**: WASD or Arrow Keys
- **Fire**: Space
