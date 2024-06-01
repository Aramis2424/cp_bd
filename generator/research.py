import psycopg2
import time
import pandas as pd

def get_conn():
    conn = psycopg2.connect(
        dbname="parkings",
        user="roman2424",
        password="1380",
        host="localhost",
        port="5432"
    )
    conn.autocommit = True
    cursor = conn.cursor()

    return conn, cursor

def close_conn(conn, cursor):
    cursor.close()
    conn.close()

def insert_data(cur, N):
    file_name = '../generator/data_csv/bookings_' + str(N) + '.csv';
    data = pd.read_csv(file_name, sep=';')
    data['start_date'] = pd.to_datetime(data['start_date'])
    data['finish_date'] = pd.to_datetime(data['finish_date'])

    insert_query = """
        INSERT INTO bookings (start_date, finish_date,
                            is_active, parkingfid, auto_ownerfid)
        VALUES %s
    """

    values = [tuple(row) for row in data.to_numpy()]
    values_str = ','.join(cur.mogrify("(%s,%s,%s,%s,%s)", val)
                .decode('utf-8') for val in values)
    cur.execute(insert_query % values_str)

##    for index, row in data.iterrows():
##        cur.execute(insert_query,
##            (row['start_date'], row['finish_date'],
##             row['is_active'], row['parkingfid'], row['auto_ownerfid']))

def prepeare_db(cur, N):
    query = "DELETE FROM bookings;"
    cur.execute(query)
    insert_data(cur, N)

def measure_query_time(cursor, query, repetitions=10, drop_caches=False):
    cursor.execute("PREPARE my_query AS " + query)
    times = []

    for _ in range(repetitions):
        if drop_caches:
            cursor.execute("DISCARD ALL;")
            # Повторно подготавливаем запрос
            cursor.execute("PREPARE my_query AS " + query)
        start_time = time.perf_counter()
        cursor.execute("EXECUTE my_query;")
        end_time = time.perf_counter()
        times.append(end_time - start_time)

    cursor.execute("DEALLOCATE my_query;")

    average_time = sum(times) / repetitions
    return average_time, times


query = "SELECT * FROM bookings WHERE is_active;"
data_volumes = [1000, 5000, 10000, 25000, 50000]

conn, cur = get_conn()

for volume in data_volumes:
    prepeare_db(cur, volume)

    # Измерение времени выполнения с кэшированием
    average_time_cached, times_cached = measure_query_time(cur, query,
                repetitions=10, drop_caches=False)
    print(f"{volume}  {average_time_cached}")

##    # Измерение времени выполнения без кэширования
##    average_time_uncached, times_uncached = measure_query_time(cur, query,
##                    repetitions=10, drop_caches=True)
##    print(f"Average time (uncached); N={volume}: {average_time_uncached} secs")
