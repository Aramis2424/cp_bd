from datetime import date, timedelta, datetime
from faker import Faker
from random import randint, choice
from copy import deepcopy


class DB_generator:
    BOOKINGS_FILE = "./data_csv/bookings_"
    faker = Faker()

    start_date = datetime(2020, 1, 1)
    end_date = datetime(2024, 5, 31)

    booking_prob = 10

    def get_bookings_filename(self, N):
        return self.BOOKINGS_FILE + str(N) + '.csv'

    def __init__(self, max_count=1000):
        print("Generator is ready\n")
        self.MAX_COUNT = max_count

    def generate_DB(self):
        print("Start generation\n")
        self.generate_bookings()
        print("Generations successfull\n")

    def generate_bookings(self):
        with open(self.get_bookings_filename(self.MAX_COUNT), "w") as file:
            file.write(self.gen_header_booking())
            for i in range(1, self.MAX_COUNT + 1):
                start_date = self.gen_datetime()
                finish_date = self.gen_datetime(start_date)

                parking_id = self.gen_random_int(1, 100)
                user_id = self.gen_random_int(1, 100)
                booking_id = i

                status = self.gen_booking_status()

                line = f"{start_date};{finish_date};" + \
                    f"{status};{parking_id};{user_id}\n"

                file.write(line)

    def gen_datetime(self, left_limit=None):
        left_date = self.start_date if left_limit is None else left_limit
        right_date = self.end_date
        random_date_time = self.faker.date_time_between(
            start_date=left_date, end_date=right_date)
        return random_date_time

    def gen_random_int(self, left, right):
        return randint(left, right)

    def gen_booking_status(self):
        num = self.gen_random_int(1, 100)
        if num <= self.booking_prob: # 10% шанс
            return True
        return False

    def gen_header_booking(self):
        return "start_date;finish_date;" + \
                "is_active;parkingfid;auto_ownerfid\n"


def main():
    generator = DB_generator(50000)
    generator.generate_DB()


if __name__ == "__main__":
    main()