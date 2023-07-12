package com.sg.guessthenum.service;

import com.sg.guessthenum.dao.InvalidGameException;
import com.sg.guessthenum.dao.NoRoundsException;
import com.sg.guessthenum.dto.Game;
import com.sg.guessthenum.dto.Round;

import java.util.List;

public interface GuessTheNumServiceLayer {
    int beginGame();

    Round getPlayerGuess(Round round) throws InvalidGameException;

    List<Game> getAllGames();

    List<Round> getGameRounds(int gameId) throws InvalidGameException, NoRoundsException;

    Game getGameById(int gameId) throws InvalidGameException;

    String matchDigits(String guess, String answer);
}