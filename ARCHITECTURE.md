# Cine Book Web Architecture & Technical Specifications

This document outlines the internal architecture, design patterns, and entity relationships of the Cine Book. It is intended for developers, evaluators, and academic reviewers.

## 1. High-Level Architecture

The system is built on a standard **N-Tier (Multi-Tier) Architecture** utilizing the **Model-View-Controller (MVC)** design pattern, implemented via the Spring Boot framework.

```text
[ Client (Browser) ]
        │ (HTTP/HTTPS)
        ▼
[ Controller Layer ] <---> [ Thymeleaf View Engine ]
        │
        ▼
[ Service Layer ]
        │
        ▼
[ Repository Layer (Spring Data JPA) ]
        │
        ▼
[ Database (H2 In-Memory) ]
```

### Components:
* **Presentation Tier (Views):** HTML templates rendered server-side using Thymeleaf. This tier is responsible for the UI/UX, utilizing HTML5, CSS3 (Flexbox/Grid), and vanilla JavaScript for interactivity (e.g., interactive seat selection map).
* **Controller Tier:** Spring `@Controller` classes route incoming HTTP requests (`GET`, `POST`), process input parameters, delegate business logic to the Service tier, and return the appropriate View template with injected Model attributes.
* **Business Logic Tier (Services):** Spring `@Service` classes encapsulate complex business operations, such as grouping showtimes by date or aggregating data for the Admin Dashboard.
* **Data Access Tier (Repositories):** Spring Data JPA interfaces (`@Repository`) extend `JpaRepository` to provide out-of-the-box CRUD operations and custom query methods (e.g., `findByMovieId`) without writing boilerplate SQL.
* **Database:** An H2 in-memory relational database, strictly configured by `schema.sql` and populated by `data.sql` upon server startup.

---

## 2. Entity-Relationship Model (ERD)

The database schema is highly normalized to represent a real-world ticketing scenario.

### Entities and Relationships:

1. **Movie**
   - Core attributes: `id`, `title`, `rating`, `image`, `description`.
   - Has a *One-to-Many* relationship with `Showtime`.

2. **Theater**
   - Core attributes: `id`, `name`, `city`, `facilities`.
   - Has a *One-to-Many* relationship with `Hall`.

3. **Hall**
   - Core attributes: `id`, `name`, `capacity`, `hall_type` (e.g., IMAX, 4DX).
   - Has a *Many-to-One* relationship with `Theater`.
   - Has a *One-to-Many* relationship with `Showtime`.

4. **Showtime (Associative Entity)**
   - Core attributes: `id`, `show_date`, `show_time`, `price`.
   - Acts as the junction between `Movie` and `Hall`.
   - A single showtime represents one specific screening of a movie in a specific hall at a specific time.

5. **Booking**
   - Core attributes: `id`, `booking_ref`, `customer_name`, `seats`, `total_price`.
   - Has a *Many-to-One* relationship with `Showtime`.
   - Stores historical snapshot data (e.g., `theater_name`, `movie_title`) defensively, ensuring that if a showtime or movie is deleted, the booking receipt remains intact.

---

## 3. Key Design Decisions

### Dynamic Seed Data Strategy
Instead of relying on a static, tiny dataset, a Python script (`seed_theaters.py`) was used during development to automatically generate hundreds of dynamic `Showtime` relationships between the scraped OMDB Movies and the mock Theaters/Halls. This simulates a high-volume, production-ready database environment necessary for a Master's project demonstration.

### Defensive Booking Model
While `Booking` references `Showtime` via a Foreign Key (`showtime_id`), the `Booking` table intentionally replicates fields like `movie_title` and `theater_name`. This denormalization is an industry-standard accounting practice; historical receipts must not change even if the underlying `Movie` or `Theater` entity is renamed or deleted.

### Client-Side vs Server-Side Rendering
To maximize SEO capabilities and guarantee fast initial page loads, **Server-Side Rendering (SSR)** via Thymeleaf was chosen over a Single-Page Application (SPA) architecture like React. However, targeted **Client-Side JavaScript** is utilized specifically where interactivity is paramount—namely, the interactive seat selection grid on the checkout page, which dynamically calculates totals before submission.

### Admin Analytics Integration
The Admin Dashboard utilizes **Chart.js** via a CDN. The backend Controller calculates aggregate statistics (Total Revenue, Total Bookings) and passes them to the model, while the frontend JavaScript takes these injected variables to render responsive Doughnut and Line charts, providing immediate business intelligence.
