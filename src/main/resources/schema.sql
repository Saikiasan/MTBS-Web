DROP TABLE IF EXISTS booking;
DROP TABLE IF EXISTS showtime;
DROP TABLE IF EXISTS hall;
DROP TABLE IF EXISTS theater;
DROP TABLE IF EXISTS movie;

CREATE TABLE movie (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    rating DOUBLE,
    image VARCHAR(1000),
    imdb_url VARCHAR(255),
    description VARCHAR(2000)
);

CREATE TABLE theater (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    address VARCHAR(255),
    city VARCHAR(255),
    phone VARCHAR(255),
    image_url VARCHAR(1000),
    facilities VARCHAR(1000)
);

CREATE TABLE hall (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    capacity INT,
    hall_type VARCHAR(255),
    theater_id BIGINT,
    FOREIGN KEY (theater_id) REFERENCES theater(id)
);

CREATE TABLE showtime (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    movie_id BIGINT,
    hall_id BIGINT,
    show_date DATE,
    show_time TIME,
    price DOUBLE,
    FOREIGN KEY (movie_id) REFERENCES movie(id),
    FOREIGN KEY (hall_id) REFERENCES hall(id)
);

CREATE TABLE booking (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    booking_ref VARCHAR(255),
    showtime_id BIGINT,
    customer_name VARCHAR(255),
    email VARCHAR(255),
    phone VARCHAR(255),
    movie_title VARCHAR(255),
    theater_name VARCHAR(255),
    hall_name VARCHAR(255),
    seats VARCHAR(255),
    total_price DOUBLE,
    booking_date TIMESTAMP,
    FOREIGN KEY (showtime_id) REFERENCES showtime(id)
);
