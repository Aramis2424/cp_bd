select * from bookings;

-- Изучение индексов
CREATE INDEX is_active_index ON bookings (is_active);
drop index is_active_index;

CREATE INDEX is_active_index ON bookings (is_active) WHERE is_active;
drop index is_active_index;

EXPLAIN ANALYZE SELECT * FROM bookings WHERE is_active = true;
SELECT * FROM bookings WHERE is_active = true;

EXPLAIN ANALYZE SELECT * FROM bookings WHERE bookingid < 100;
SELECT * FROM bookings WHERE bookingid < 100;
