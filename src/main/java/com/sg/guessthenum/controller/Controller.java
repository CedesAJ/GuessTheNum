package com.sg.guessthenum.controller;

import java.util.List;
import com.sg.guessthenum.dto.Game;
import com.sg.guessthenum.dto.Round;
import com.sg.guessthenum.service.GuessTheNumServiceLayerImpl;
import com.sg.guessthenum.dao.InvalidGameException;
import com.sg.guessthenum.dao.NoRoundsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class Controller {
    @Autowired
    GuessTheNumServiceLayerImpl service;

    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public int begin() {
        int gameId = service.beginGame();
        return gameId;
    }

    @PostMapping("/guess")
    public Round guess(@RequestBody Round round) throws InvalidGameException {
        Round aRound = service.getPlayerGuess(round);
        return aRound;
    }

    @GetMapping("/game")
    public List<Game> getAllGames() {
        return service.getAllGames();
    }

    @GetMapping("/game/{gameId}")
    public Game getGameById(@PathVariable("gameId") int gameId) throws InvalidGameException {
        return service.getGameById(gameId);

    }

    @GetMapping("/rounds/{gameId}")
    public List<Round> getGameRounds(@PathVariable("gameId") int gameId) throws InvalidGameException, NoRoundsException {
        return service.getGameRounds(gameId);
    }

}

