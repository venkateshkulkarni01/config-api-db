CREATE TABLE config_constants (
    id BIGINT PRIMARY KEY,
    constant_name VARCHAR(255) NOT NULL,
    constant_value VARCHAR(255) NOT NULL,
    environment VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    CONSTRAINT uq_constant_env UNIQUE (constant_name, environment)
);
