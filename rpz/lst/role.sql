CREATE ROLE guest login;
GRANT SELECT ON auto_owners, parkings, tariffs, subscriptions TO guest;

CREATE ROLE user_ login;
GRANT SELECT ON auto_owners, parkings, tariffs, subscriptions,
cars, auto_owner_cars, bookings, tickets TO user_;
GRANT UPDATE, INSERT, DELETE ON  cars, auto_owner_cars TO user_;

CREATE ROLE parking_meter login;
GRANT SELECT ON auto_owners, parkings, tariffs, subscriptions,
cars, auto_owner_cars, bookings, tickets TO parking_meter;
GRANT UPDATE, INSERT, DELETE ON  cars, auto_owner_cars TO parking_meter;
GRANT UPDATE, INSERT ON bookings, tickets TO parking_meter;

CREATE ROLE admin_ login superuser;