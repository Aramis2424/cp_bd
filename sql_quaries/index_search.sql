select * from bookings;

-- Изучение индексов
CREATE INDEX is_active_index ON bookings (is_active);
drop index is_active_index;

CREATE INDEX is_active_index_partly ON bookings (is_active) WHERE is_active;
drop index is_active_index_partly;

CREATE INDEX is_active_hash_idx ON bookings USING hash (is_active);
DROP INDEX is_active_hash_idx;

EXPLAIN ANALYZE SELECT * FROM bookings WHERE is_active = true;
SELECT * FROM bookings WHERE is_active = true;

EXPLAIN ANALYZE SELECT * FROM bookings WHERE is_active = FALSE;

EXPLAIN ANALYZE SELECT * FROM bookings WHERE bookingid < 100;
SELECT * FROM bookings WHERE bookingid < 100;

-- Получение размера индекса is_active_btree_idx
SELECT
    indexname,
    pg_size_pretty(pg_relation_size(indexrelid)) AS index_size
FROM
    (
        SELECT
            indexrelid,
            indexname
        FROM
            pg_index
        JOIN
            pg_class ON pg_class.oid = pg_index.indexrelid
        JOIN
            pg_namespace ON pg_namespace.oid = pg_class.relnamespace
        JOIN
            pg_indexes ON pg_indexes.indexname = pg_class.relname
        WHERE
            pg_indexes.tablename = 'bookings'
    ) AS subquery;

SELECT bookingid FROM bookings b ;
DELETE FROM bookings WHERE bookingid > 45000;
TRUNCATE TABLE bookings RESTART IDENTITY;

