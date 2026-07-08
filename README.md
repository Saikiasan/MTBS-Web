# Movie Ticket Booking System (MTBS-Web)

## Overview
MTBS-Web is a Java-based web application for booking movie tickets. It fetches an external API to display available movies and allows customers to select seats, calculate prices, and confirm bookings.

## Technologies Used
- **Language**: Java 17
- **Framework**: Spring Boot 4.1.0 (Spring WebMVC)
- **View Template**: Thymeleaf
- **Database / ORM**: Spring Data JPA with MySQL Connector (MySQL Database)
- **Build Tool**: Maven (`pom.xml`)

## Features
- **Movie Listing**: Fetches movie data (title, rating, image, IMDB URL) from an external dummy API (`https://dummyapi.online/api/movies`) and displays them on the homepage.
- **Ticket Booking**: Users can click on a movie to book tickets, selecting seats and entering their name.
- **Booking Confirmation**: Confirmed bookings are stored in a database via JPA repository (`BookingRepository`), and the user receives a confirmation message.

## Project Structure
```text
src/main/java/com/example/mtbsweb/
├── controller/
│   └── MovieController.java     # Handles web requests ("/" for home, "/book/{id}" for booking, "/book/confirm" for confirmation)
├── model/
│   ├── Booking.java             # JPA Entity representing a customer's booking
│   └── Movie.java               # POJO representing movie data fetched from the external API
├── repository/
│   └── BookingRepository.java   # Spring Data JPA interface for saving and managing Booking entities
└── service/
    └── MovieService.java        # Service class that fetches movies from the external API using RestTemplate
```

## How to Run
1. Configure your MySQL database settings in `src/main/resources/application.properties` (or `.yml`).
2. Run the application using the Maven wrapper:
   ```bash
   ./mvnw spring-boot:run
   ```
3. Access the application in your browser at `http://localhost:8080/`.

## Endpoints Summary
- `GET /` - Displays the list of available movies.
- `GET /book/{id}` - Displays the booking form for a specific movie.
- `POST /book/confirm` - Processes the booking and redirects back to the home page with a confirmation message.
