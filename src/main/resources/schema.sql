CREATE TABLE config_constants (
    id SERIAL PRIMARY KEY,
    constant_name VARCHAR(255) NOT NULL,
    constant_value TEXT,
    environment VARCHAR(100) NOT NULL,
    description TEXT,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    CONSTRAINT unique_name_env UNIQUE (constant_name, environment)
);
