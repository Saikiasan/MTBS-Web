import random

theaters = [
    ("PVR IMAX", "Orion Mall, Dr Rajkumar Rd", "Bangalore", "999-000-1111", "https://images.unsplash.com/photo-1517604931442-7e0c8ed2963c?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=80", "IMAX, 4DX, Food Court, Recliner Seats, Valet Parking"),
    ("INOX Lido", "Lido Mall, Swami Vivekananda Rd", "Bangalore", "999-000-2222", "https://images.unsplash.com/photo-1595769816263-9b910be24d5f?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=80", "Standard, Gold Class, Dolby Atmos, Wheelchair Access"),
    ("Cinépolis Forum", "Forum Shantiniketan, Whitefield", "Bangalore", "999-000-3333", "https://images.unsplash.com/photo-1489599849927-2ee91cede3ba?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=80", "4DX, VIP Lounge, Gourmet Food, Arcade"),
    ("AMC Premium", "Times Square", "New York", "555-111-4444", "https://images.unsplash.com/photo-1440404653325-ab127d49abc1?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=80", "Dolby Cinema, Dine-In, Bar, IMAX"),
    ("Regal Cinemas", "L.A. Live", "Los Angeles", "555-222-5555", "https://images.unsplash.com/photo-1507924538820-ede94a04019d?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=80", "RPX, ScreenX, VIP Seating, Validation Parking")
]

halls = [
    ("Screen 1", 200, "STANDARD"),
    ("Screen 2", 150, "STANDARD"),
    ("Gold Class", 50, "GOLD"),
    ("IMAX 3D", 300, "IMAX"),
    ("4DX Arena", 100, "4DX")
]

sql = "\n\n-- Seeding Theaters\nINSERT INTO theater (name, address, city, phone, image_url, facilities) VALUES \n"
theater_inserts = []
for t in theaters:
    theater_inserts.append(f"('{t[0]}', '{t[1]}', '{t[2]}', '{t[3]}', '{t[4]}', '{t[5]}')")
sql += ",\n".join(theater_inserts) + ";\n\n"

sql += "-- Seeding Halls\nINSERT INTO hall (name, capacity, hall_type, theater_id) VALUES \n"
hall_inserts = []
hall_id_counter = 1
for t_idx in range(1, len(theaters)+1):
    num_halls = random.randint(3, 5)
    selected_halls = random.sample(halls, num_halls)
    for h in selected_halls:
        hall_inserts.append(f"('{h[0]}', {h[1]}, '{h[2]}', {t_idx})")
        hall_id_counter += 1
sql += ",\n".join(hall_inserts) + ";\n\n"

total_halls = hall_id_counter - 1

sql += "-- Seeding Showtimes\nINSERT INTO showtime (movie_id, hall_id, show_date, show_time, price) VALUES \n"
showtime_inserts = []
# Assuming 58 movies currently in data.sql (IDs 1 to 58)
# Let's generate a few showtimes for each movie
from datetime import date, timedelta
today = date.today()
dates = [today, today + timedelta(days=1), today + timedelta(days=2)]
times = ['10:00:00', '13:30:00', '17:00:00', '20:30:00', '22:45:00']

for movie_id in range(1, 59):
    num_showtimes = random.randint(5, 12)
    for _ in range(num_showtimes):
        h_id = random.randint(1, total_halls)
        d = random.choice(dates)
        t = random.choice(times)
        price = random.choice([12.50, 15.00, 18.00, 22.50, 25.00])
        showtime_inserts.append(f"({movie_id}, {h_id}, '{d.isoformat()}', '{t}', {price})")

sql += ",\n".join(showtime_inserts) + ";\n\n"

with open('d:/Codes/java/MTBS-Web/src/main/resources/data.sql', 'a', encoding='utf-8') as f:
    f.write(sql)

print(f"Generated {len(theaters)} theaters, {total_halls} halls, and {len(showtime_inserts)} showtimes!")
