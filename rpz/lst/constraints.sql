ALTER TABLE jobs 
    ALTER COLUMN title SET
NOT NULL,
    ALTER COLUMN salary SET
NOT NULL,
    ADD CONSTRAINT check_title CHECK (
    title <> ''
),
    ADD CONSTRAINT check_salary CHECK (
    salary > 0
);

ALTER TABLE employees 
    ALTER COLUMN first_name SET
NOT NULL,
    ALTER COLUMN last_name SET
NOT NULL,
    ALTER COLUMN jobFID SET
NOT NULL,
    ALTER COLUMN parkingFID SET
NOT NULL,
    ADD CONSTRAINT check_name CHECK (
    first_name <> ''
    AND
    last_name <> ''
),
    ADD CONSTRAINT FK_job FOREIGN KEY (jobFID) REFERENCES jobs (jobID),
    ADD CONSTRAINT FK_parking FOREIGN KEY (parkingFID) REFERENCES parkings (parkingID);

ALTER TABLE parking_meters 
    ALTER COLUMN gate_number SET
NOT NULL,
    ALTER COLUMN parkingFID SET
NOT NULL,
    ADD CONSTRAINT check_gate_number CHECK (
    gate_number > 0
),
    ADD CONSTRAINT FK_parking FOREIGN KEY (parkingFID) REFERENCES parkings (parkingID);

ALTER TABLE tariffs 
    ALTER COLUMN title SET
NOT NULL,
    ALTER COLUMN cost_per_hour SET
NOT NULL,
    ALTER COLUMN cost_per_day SET
NOT NULL,
    ADD CONSTRAINT check_cost CHECK (
    cost_per_hour >= 0
    AND cost_per_day >= 0
);

ALTER TABLE parkings 
    ALTER COLUMN address SET
NOT NULL,
    ALTER COLUMN count_total_places SET
NOT NULL,
    ALTER COLUMN count_free_places SET
NOT NULL,
    ALTER COLUMN tariffFID SET
NOT NULL,
    ADD CONSTRAINT check_count_places CHECK (
    count_total_places > 0
    AND count_free_places > 0
    AND count_free_places <= count_total_places
),
    ADD CONSTRAINT FK_tariff FOREIGN KEY (tariffFID) REFERENCES tariffs (tariffID);

ALTER TABLE tickets 
    ALTER COLUMN start_date SET
NOT NULL,
    ALTER COLUMN finish_date SET
NOT NULL,
    ALTER COLUMN is_active SET
NOT NULL,
    ALTER COLUMN parkingfid SET
NOT NULL,
    ALTER COLUMN auto_ownerfid SET
NOT NULL,
    ADD CONSTRAINT check_date CHECK (
    start_date < finish_date
),
    ADD CONSTRAINT FK_parking FOREIGN KEY (parkingFID) REFERENCES parkings (parkingID),
    ADD CONSTRAINT FK_auto_owner FOREIGN KEY (auto_ownerFID) REFERENCES auto_owners (auto_ownerID);

ALTER TABLE bookings
    ALTER COLUMN start_date SET
NOT NULL,
    ALTER COLUMN finish_date SET
NOT NULL,
    ALTER COLUMN is_active SET
NOT NULL,
    ALTER COLUMN parkingfid SET
NOT NULL,
    ALTER COLUMN auto_ownerfid SET
NOT NULL,
    ADD CONSTRAINT check_date CHECK (
    start_date < finish_date
),
    ADD CONSTRAINT FK_parking FOREIGN KEY (parkingFID) REFERENCES parkings (parkingID),
    ADD CONSTRAINT FK_auto_owner FOREIGN KEY (auto_ownerFID) REFERENCES auto_owners (auto_ownerID);

ALTER TABLE auto_owners 
    ALTER COLUMN first_name SET
NOT NULL,
    ALTER COLUMN last_name SET
NOT NULL,
    ALTER COLUMN account SET
NOT NULL,
	ALTER COLUMN login SET
NOT NULL,
	ALTER COLUMN password_ SET
NOT NULL,
	ALTER COLUMN date_subscription_expire SET
NOT NULL,
    ALTER COLUMN subscriptionFID SET
NOT NULL,
    ADD CONSTRAINT check_name CHECK (
    first_name <> ''
    AND
    last_name <> ''
),
    ADD CONSTRAINT check_account CHECK (
    account >= 0
),
    ADD CONSTRAINT FK_subscription FOREIGN KEY (subscriptionFID) REFERENCES subscriptions (subscriptionID);

ALTER TABLE subscriptions 
    ALTER COLUMN title SET
NOT NULL,
    ALTER COLUMN cost_ SET
NOT NULL,
    ALTER COLUMN discount SET
NOT NULL,
    ALTER COLUMN period_ SET
NOT NULL,
    ALTER COLUMN grace_hours SET
NOT NULL,
    ADD CONSTRAINT check_title CHECK (
    title <> ''
),
    ADD CONSTRAINT check_cost_ CHECK (
    cost_ >= 0
),
    ADD CONSTRAINT check_discount CHECK (
    discount >= 0
    AND discount <= 1
),
    ADD CONSTRAINT check_grace_hours CHECK (
    grace_hours >= 0
);

ALTER TABLE cars 
    ALTER COLUMN model SET
NOT NULL,
    ADD CONSTRAINT check_model CHECK (
    model <> ''
);

ALTER TABLE auto_owner_cars 
    ALTER COLUMN auto_ownerFID SET
NOT NULL,
    ALTER COLUMN car_number SET
NOT NULL,
    ADD CONSTRAINT FK_auto_owner FOREIGN KEY (auto_ownerFID) REFERENCES auto_owners (auto_ownerID),
    ADD CONSTRAINT FK_car_number FOREIGN KEY (car_number) REFERENCES cars (car_number);
