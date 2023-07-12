package com.sg.guessthenum.dao;

import com.sg.guessthenum.dto.Game;

import java.util.List;

public interface GuessTheNumGameDao {
    Game addGame(Game game);
    Game getGameById(int gameId);
    List<Game> getAllGames();
    void updateGame(Game round);
}
