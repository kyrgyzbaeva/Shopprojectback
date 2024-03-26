CREATE TABLE bags (
                      id SERIAL PRIMARY KEY,
                      brand VARCHAR(255),
                      price DECIMAL(10, 2),
                      production VARCHAR(255),
                      material VARCHAR(255)
);

CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL
);
