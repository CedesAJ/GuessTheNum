DROP DATABASE IF EXISTS GuessTheNumberDB;

CREATE DATABASE GuessTheNumberDB;

USE GuessTheNumberDB;

CREATE TABLE game (
	gameId INT AUTO_INCREMENT,
    `number` CHAR(4),
    isFinished BOOLEAN DEFAULT false,
    CONSTRAINT pk_game
		PRIMARY KEY (gameId)
    );
    

CREATE TABLE round (
	roundId INT AUTO_INCREMENT,
    gameid INT NOT NULL,
    `time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    guess CHAR(4),
    result CHAR(7),
    CONSTRAINT pk_round
		PRIMARY KEY (roundId),
	CONSTRAINT fk_round_game
		FOREIGN KEY (gameId) 
        REFERENCES game(gameId)
    );
    

    
    