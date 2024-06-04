CREATE OR REPLACE PROCEDURE insert_car(auto_ownerid INT, car_number VARCHAR(16), model VARCHAR(16))
AS $$
DECLARE
    car_id INT;
BEGIN
	INSERT INTO cars (model, car_number) VALUES (model, car_number);

	INSERT INTO auto_owner_cars VALUES (car_number, auto_ownerid);
END;
$$ LANGUAGE plpgsql;