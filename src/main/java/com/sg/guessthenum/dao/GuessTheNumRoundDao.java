package com.sg.guessthenum.dao;

import com.sg.guessthenum.dto.Round;

import java.util.List;

public interface GuessTheNumRoundDao {
    Round addRound(Round round);
    Round getRoundById(int roundId);
    List<Round> getAllRoundsByGameId(int gameId);
}

