CREATE DATABASE IF NOT EXISTS tmms_database CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE USER IF NOT EXISTS 'tmms_app'@'localhost' IDENTIFIED BY 'tmms123';
GRANT ALL PRIVILEGES ON tmms_database.* TO 'tmms_app'@'localhost';
FLUSH PRIVILEGES;

USE tmms_database;

CREATE TABLE IF NOT EXISTS system_users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    role VARCHAR(30) NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT TRUE,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS junctions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(80) NOT NULL UNIQUE,
    display_order INT NOT NULL,
    latitude DOUBLE,
    longitude DOUBLE,
    congestion VARCHAR(20) NOT NULL DEFAULT 'GREEN'
);

CREATE TABLE IF NOT EXISTS traffic_observations (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    junction_id BIGINT NOT NULL,
    observed_at DATETIME NOT NULL,
    vehicle_count INT NOT NULL,
    speed_kmh DOUBLE NOT NULL,
    occupancy_percent DOUBLE NOT NULL,
    queue_length INT NOT NULL,
    road_condition VARCHAR(40) NOT NULL,
    congestion VARCHAR(20) NOT NULL,
    green_seconds INT NOT NULL,
    CONSTRAINT fk_observation_junction FOREIGN KEY (junction_id) REFERENCES junctions(id)
);

CREATE TABLE IF NOT EXISTS incidents (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    junction_id BIGINT NOT NULL,
    type VARCHAR(60) NOT NULL,
    description VARCHAR(500) NOT NULL,
    status VARCHAR(30) NOT NULL,
    source VARCHAR(30) NOT NULL,
    reported_at DATETIME NOT NULL,
    resolved_at DATETIME NULL,
    CONSTRAINT fk_incident_junction FOREIGN KEY (junction_id) REFERENCES junctions(id)
);

CREATE TABLE IF NOT EXISTS alerts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    message VARCHAR(600) NOT NULL,
    target_role VARCHAR(30) NOT NULL,
    created_at DATETIME NOT NULL,
    acknowledged BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS traffic_control_logs (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    junction_id BIGINT NOT NULL,
    action_type VARCHAR(60) NOT NULL,
    details VARCHAR(600) NOT NULL,
    performed_by VARCHAR(150) NOT NULL,
    created_at DATETIME NOT NULL,
    CONSTRAINT fk_control_junction FOREIGN KEY (junction_id) REFERENCES junctions(id)
);

CREATE TABLE IF NOT EXISTS traffic_devices (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    junction_id BIGINT NOT NULL,
    device_name VARCHAR(120) NOT NULL,
    device_type VARCHAR(60) NOT NULL,
    status VARCHAR(30) NOT NULL,
    last_seen DATETIME NOT NULL,
    CONSTRAINT fk_device_junction FOREIGN KEY (junction_id) REFERENCES junctions(id)
);

INSERT INTO junctions(name, display_order, latitude, longitude, congestion)
SELECT 'Nausori', 1, -18.0248, 178.5454, 'GREEN' WHERE NOT EXISTS (SELECT 1 FROM junctions WHERE name='Nausori');
INSERT INTO junctions(name, display_order, latitude, longitude, congestion)
SELECT 'Nakasi', 2, -18.0718, 178.5192, 'GREEN' WHERE NOT EXISTS (SELECT 1 FROM junctions WHERE name='Nakasi');
INSERT INTO junctions(name, display_order, latitude, longitude, congestion)
SELECT 'Nasinu', 3, -18.0740, 178.4936, 'GREEN' WHERE NOT EXISTS (SELECT 1 FROM junctions WHERE name='Nasinu');
INSERT INTO junctions(name, display_order, latitude, longitude, congestion)
SELECT 'Valelevu', 4, -18.0913, 178.4680, 'GREEN' WHERE NOT EXISTS (SELECT 1 FROM junctions WHERE name='Valelevu');
