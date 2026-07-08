CREATE TABLE IF NOT EXISTS movie (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    rating DOUBLE,
    image VARCHAR(255),
    imdb_url VARCHAR(255),
    description VARCHAR(1000)
);

CREATE TABLE IF NOT EXISTS booking (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(255),
    movie_title VARCHAR(255),
    seats VARCHAR(255),
    total_price DOUBLE
);
