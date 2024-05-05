create database sampleusers;
use sampleusers;

CREATE TABLE metrics_data_1 (
    id INT AUTO_INCREMENT PRIMARY KEY,
    timestamp TIMESTAMP,
    osName VARCHAR(255),
    osVersion VARCHAR(255),
    hostName VARCHAR(255),
    macAddress VARCHAR(255),
    systemManufacturer VARCHAR(255),
    systemModel VARCHAR(255),
    systemType VARCHAR(255),
    totalPhysicalMemory BIGINT
);

CREATE TABLE process_metrics_data (
    id INT AUTO_INCREMENT PRIMARY KEY,
    timestamp timestamp,
    process_id VARCHAR(255),
    name VARCHAR(255),
    session_id VARCHAR(255),
    memory_usage DOUBLE,
    cpu_time DOUBLE,
    start_time DATETIME,
    responding BOOLEAN
);

CREATE TABLE network_metrics (
    id INT AUTO_INCREMENT PRIMARY KEY,
    timestamp timestamp,
    connection_name VARCHAR(255) NOT NULL,
    received_bytes BIGINT NOT NULL,
    unicast_packets BIGINT NOT NULL,
    sent_bytes BIGINT NOT NULL,
    sent_unicast_packets BIGINT NOT NULL
);

CREATE TABLE disk_metrics (
    id INT AUTO_INCREMENT PRIMARY KEY,
    timestamp TIMESTAMP,
    DeviceID VARCHAR(255),
    FreeSpace BIGINT,
    Size BIGINT,
    VolumeName VARCHAR(255)
);

CREATE TABLE memory_metrics (
    id INT AUTO_INCREMENT PRIMARY KEY,
    timestamp TIMESTAMP,
    FreePhysicalMemory BIGINT(255),
    TotalVisibleMemorySize BIGINT(255),
    SystemDirectory VARCHAR(255),
    BuildNumber VARCHAR(255)
);

CREATE TABLE CPU_metrics (
    id INT AUTO_INCREMENT PRIMARY KEY,
    timestamp TIMESTAMP,
    CPU_Load INT
);