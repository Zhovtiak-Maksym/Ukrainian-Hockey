DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS teams CASCADE;
DROP TABLE IF EXISTS matches CASCADE;
DROP TABLE IF EXISTS match_events CASCADE;

CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(50) UNIQUE NOT NULL,
                       password_hash VARCHAR(255) NOT NULL,
                       role VARCHAR(15) NOT NULL CHECK (role IN ('ADMIN', 'REFEREE', 'USER'))
);

CREATE TABLE teams (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(100) NOT NULL UNIQUE,
                       city VARCHAR(100),
                       games_played INT DEFAULT 0,
                       wins INT DEFAULT 0,
                       losses INT DEFAULT 0,
                       ot_wins INT DEFAULT 0,
                       ot_losses INT DEFAULT 0,
                       goals_scored INT DEFAULT 0,
                       goals_conceded INT DEFAULT 0,
                       points INT DEFAULT 0
);

CREATE TABLE matches (
                         id SERIAL PRIMARY KEY,
                         home_team_id INT REFERENCES teams(id) ON DELETE CASCADE,
                         away_team_id INT REFERENCES teams(id) ON DELETE CASCADE,
                         home_score INT DEFAULT 0,
                         away_score INT DEFAULT 0,
                         match_date TIMESTAMP NOT NULL,
                         status VARCHAR(15) DEFAULT 'PLANNED' CHECK (status IN ('PLANNED', 'IN_PROGRESS', 'FINISHED'))
);

CREATE TABLE match_events (
                              id SERIAL PRIMARY KEY,
                              match_id INT REFERENCES matches(id) ON DELETE CASCADE,
                              team_id INT REFERENCES teams(id) ON DELETE CASCADE,
                              player_name VARCHAR(100) NOT NULL,
                              event_minute INT NOT NULL,
                              event_type VARCHAR(15) DEFAULT 'GOAL' CHECK (event_type IN ('GOAL', 'PENALTY'))
);