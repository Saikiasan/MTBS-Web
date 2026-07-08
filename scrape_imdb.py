import urllib.request
import urllib.parse
import json

movie_titles = [
    "Inception", "The Matrix", "Interstellar", "The Dark Knight", 
    "Alpha", "The Wrecking Crew", "Obsession", "Gladiator", 
    "The Godfather", "Pulp Fiction", "The Lord of the Rings", 
    "Fight Club", "Forrest Gump", "Star Wars", "Goodfellas", 
    "Se7en", "City of God", "The Silence of the Lambs", 
    "Schindler's List", "Whiplash", "The Prestige",
    
    # New ones
    "The Avengers", "Iron Man", "The Lion King", "Toy Story", 
    "Jurassic Park", "Titanic", "Avatar", "The Terminator", 
    "Back to the Future", "Indiana Jones and the Raiders of the Lost Ark",
    "The Shining", "Alien", "Die Hard", "The Truman Show", 
    "Catch Me If You Can", "The Wolf of Wall Street", "Shutter Island",
    "Joker", "Parasite", "Avengers: Endgame", "Black Panther", 
    "Mad Max: Fury Road", "Logan", "Deadpool", "The Batman", 
    "Dune", "Oppenheimer", "Barbie", "Spider-Man: Into the Spider-Verse",
    "Coco", "Up", "WALL-E", "Inside Out", "Finding Nemo",
    "The Incredibles", "Monsters, Inc.", "Ratatouille", 
    "Harry Potter and the Sorcerer's Stone", "Pirates of the Caribbean: The Curse of the Black Pearl"
]

api_key = "869cf1a3"

sql_statements = ["INSERT INTO movie (title, rating, image, imdb_url, description) VALUES "]
values = []

for title in movie_titles:
    print(f"Fetching OMDb data for {title}...")
    safe_title = urllib.parse.quote(title)
    req_url = f"http://www.omdbapi.com/?apikey={api_key}&t={safe_title}"
    
    try:
        req = urllib.request.Request(req_url)
        response = urllib.request.urlopen(req, timeout=5).read().decode('utf-8')
        data = json.loads(response)
        
        if data.get("Response") == "True":
            poster = data.get("Poster", "")
            if poster == "N/A": poster = ""
            
            rating = data.get("imdbRating", "0.0")
            if rating == "N/A": rating = "0.0"
            
            plot = data.get("Plot", "")
            if plot == "N/A": plot = ""
            plot = plot.replace("'", "''") # escape for sql
            
            imdb_id = data.get("imdbID", "")
            imdb_url = f"https://www.imdb.com/title/{imdb_id}/" if imdb_id else ""
            
            sql_title = title.replace("'", "''")
            
            val = f"('{sql_title}', {rating}, '{poster}', '{imdb_url}', '{plot}')"
            values.append(val)
            print(f"  -> Success! Rating: {rating}")
        else:
            print(f"  -> Movie not found on OMDb: {data.get('Error')}")
            
    except Exception as e:
        print(f"  -> Failed to fetch for {title}: {e}")

final_sql = sql_statements[0] + "\n" + ",\n".join(values) + ";"

with open('d:/Codes/java/MTBS-Web/src/main/resources/data.sql', 'w', encoding='utf-8') as f:
    f.write(final_sql)

print("Finished generating data.sql with ~50 movies!")
