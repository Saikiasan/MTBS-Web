# Cine Book (Movie Ticket Booking System)

A comprehensive, industry-grade Cine Book developed for a Master's Level Academic Project. The system allows users to browse movies, discover theaters, select specific showtimes, and book tickets through a modern, responsive UI. It also features a robust Admin Dashboard with visual analytics, data management, and booking tracking.

## Table of Contents
1. [Project Purpose](#project-purpose)
2. [Key Features](#key-features)
3. [Architecture & Data Model](#architecture--data-model)
4. [Technology Stack](#technology-stack)
5. [Setup & Installation Guide](#setup--installation-guide)
6. [Usage Guide](#usage-guide)

---

## Project Purpose
This project is designed to simulate a real-world, scalable ticket booking platform. It demonstrates proficiency in full-stack Java development, relational database design, MVC architectural patterns, and frontend UI/UX design.

---

## Key Features

### 🎬 Client-Facing (End User)
* **Movie Discovery:** Browse an extensive list of movies dynamically fetched with high-quality posters, descriptions, and IMDB ratings.
* **Theater Selection:** View a directory of available cinema theaters, categorized by location and facilities (IMAX, 4DX, VIP).
* **Showtime Selection:** Movies are mapped to specific halls within theaters. Users can select specific dates and time slots.
* **Interactive Seat Booking:** Interactive, click-to-select seat map during the checkout process.
* **Dynamic Pricing:** Prices are dynamically calculated based on the specific showtime and the number of seats selected.
* **Booking Receipts:** Upon successful checkout, users receive a printable Booking Confirmation ticket featuring a Barcode and Reference ID.

### 📊 Administrator Dashboard
* **KPI Analytics:** High-level metrics tracking total revenue, active bookings, movie catalog size, and theater count.
* **Visual Charts:** Integrated `Chart.js` rendering dynamic Doughnut and Line charts to track revenue trends and theater popularity.
* **Data Management:** Full CRUD (Create, Read, Update, Delete) capability for the Movie catalog.
* **Booking Tracking:** A dedicated view for administrators to track and search customer bookings.

---

## Architecture & Data Model

The application follows the **Spring MVC** (Model-View-Controller) architecture.

### Relational Entities:
1. **Movie**: Represents the film (Title, Rating, Description, Poster).
2. **Theater**: Represents the physical cinema location (Name, Address, Facilities).
3. **Hall**: Represents a specific screen inside a theater (Capacity, Type: STANDARD/IMAX).
4. **Showtime**: The core associative entity. Links a `Movie` to a `Hall` at a specific `Date` and `Time`, and dictates the `Price`.
5. **Booking**: Stores the transaction details, linked to a `Showtime`, containing the Customer's Name, Contact details, selected seats, and Total Price.

---

## Technology Stack
* **Backend:** Java 17, Spring Boot (Web, Data JPA)
* **Database:** H2 In-Memory Database (automatically seeded on startup)
* **Frontend Template Engine:** Thymeleaf
* **Styling & UI:** Vanilla CSS with custom modern variables, Flexbox/Grid layouts, Inter Font.
* **Analytics:** Chart.js
* **Build Tool:** Maven

---

## Setup & Installation Guide

This project is built to run out-of-the-box with minimal configuration required. The database is in-memory and automatically seeds itself with movies, theaters, halls, and showtimes upon startup.

### Prerequisites
* Java Development Kit (JDK) 17 or higher installed.
* Git installed.

### 1. Clone the Repository
```bash
git clone <your-github-repo-url>
cd MTBS-Web
```

### 2. Run the Application
You don't need to manually install Maven; the project includes a Maven Wrapper (`mvnw`).

**For Windows:**
```bash
.\mvnw.cmd spring-boot:run
```

**For Mac/Linux:**
```bash
./mvnw spring-boot:run
```

### 3. Common Terminal Commands

For ease of use, here is a quick reference for all the terminal commands you might need when working on this project. 

**Windows Commands (`cmd` or `PowerShell`):**
* **Run the application:** `.\mvnw.cmd spring-boot:run`
* **Build the project (creates a runnable .jar):** `.\mvnw.cmd clean package`
* **Run tests:** `.\mvnw.cmd test`
* **Clean the target directory:** `.\mvnw.cmd clean`
* **Stop the running server:** Press `Ctrl + C` in the terminal where it's running.

**Mac/Linux Commands (Bash/Zsh):**
* **Run the application:** `./mvnw spring-boot:run`
* **Build the project:** `./mvnw clean package`
* **Run tests:** `./mvnw test`
* **Clean the target directory:** `./mvnw clean`
* **Make the wrapper executable (if permission denied):** `chmod +x mvnw`

### 4. Access the Application
* **Client Portal:** Open your browser and navigate to `http://localhost:8080`
* **Admin Dashboard:** Navigate to `http://localhost:8080/admin`
* **Database Console:** Navigate to `http://localhost:8080/h2-console`
  * **JDBC URL:** `jdbc:h2:mem:testdb`
  * **Username:** `sa`
  * **Password:** *(Leave blank)*

---

## Usage Guide

### As a Customer:
1. Navigate to the Home Page (`/`) to see the latest movies.
2. Click **Book Tickets** on a movie card to view its details and available showtimes.
3. Select a time slot associated with a specific theater.
4. On the checkout page, select your preferred seats from the interactive map.
5. Fill in your Name, Email, and Phone number, and click **Confirm Booking**.
6. Print or save the resulting Ticket Receipt.

### As an Administrator:
1. Navigate to the Admin Dashboard (`/admin`).
2. Review the top-level KPIs and Charts to gauge platform performance.
3. Use the top navigation bar to manage **Movies**, **Theaters**, and **Showtimes**.
4. Click on **Bookings** to see a master list of all customer transactions.
