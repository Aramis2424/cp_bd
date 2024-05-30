CREATE TABLE IF NOT EXISTS jobs
(
    jobID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    title VARCHAR(64),
    salary int
);

CREATE TABLE IF NOT EXISTS employees
(
    employeeID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    first_name VARCHAR(16),
    last_name VARCHAR(16),
    jobFID INT,
    parkingFID INT
);

CREATE TABLE if NOT EXISTS parking_meters
(
    parking_meterID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    gate_number INT,
    parkingFID INT
);

CREATE TABLE if NOT EXISTS tariffs
(
    tariffID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    title VARCHAR(32),
    cost_per_hour INT,
    cost_per_day INT
);

CREATE TABLE if NOT EXISTS parkings
(
    parkingID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    address VARCHAR(64),
    count_total_places INT,
    count_free_places INT,
    tariffFID INT
);

CREATE TABLE IF NOT EXISTS tickets
(
    ticketID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    start_date TIMESTAMP,
    finish_date TIMESTAMP,
    is_active BOOLEAN,
    parkingFID INT,
    auto_ownerFID INT
);

CREATE TABLE IF NOT EXISTS bookings
(
    bookingID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    start_date TIMESTAMP,
    finish_date TIMESTAMP,
    is_active BOOLEAN,
    parkingFID INT,
    auto_ownerFID INT
);

CREATE TABLE IF not EXISTS auto_owners
(
    auto_ownerID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    first_name VARCHAR(16),
    last_name VARCHAR(16),
    account INT,
	login VARCHAR(32),
	password_ VARCHAR(32),
	date_subscription_expire TIMESTAMP,
    subscriptionFID INT
);

CREATE TABLE IF NOT EXISTS subscriptions
(
    subscriptionID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    title VARCHAR(32),
    cost_ INT,
    discount DOUBLE PRECISION,
    period_ INTERVAL,
    grace_hours INT
);

CREATE TABLE IF NOT EXISTS auto_owner_cars
(
    auto_ownerFID INT,
    car_number VARCHAR(16)
);

CREATE TABLE IF NOT EXISTS cars
(
    car_number VARCHAR(16) PRIMARY KEY,
    model VARCHAR(32)
);
