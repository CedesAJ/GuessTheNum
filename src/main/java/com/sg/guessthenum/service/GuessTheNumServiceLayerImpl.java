package com.sg.guessthenum.service;

import com.sg.guessthenum.dao.GuessTheNumGameDao;
import com.sg.guessthenum.dao.GuessTheNumRoundDao;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

import com.sg.guessthenum.dao.InvalidGameException;
import com.sg.guessthenum.dao.NoRoundsException;
import com.sg.guessthenum.dto.Game;
import com.sg.guessthenum.dto.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuessTheNumServiceLayerImpl implements GuessTheNumServiceLayer{

    @Autowired
    GuessTheNumGameDao gameDao;

    @Autowired
    GuessTheNumRoundDao roundDao;

    public int beginGame() {
        Game game = new Game();
        Random random = new Random();
        StringBuilder answer = new StringBuilder();
        HashSet<Integer>generateUniqueDigits = new HashSet<>();
        for (int i = 0; i < 4; i++){
            int digit = random.nextInt(10);
            while (generateUniqueDigits.contains(digit)){
                digit = random.nextInt(10);
            }
            generateUniqueDigits.add(digit);
            answer.append(digit);
        }
        game.setAnswer(String.valueOf(answer));
        game = gameDao.addGame(game);

        return game.getGameId();
    }

    public Round getPlayerGuess(Round round) throws InvalidGameException {
        String answer = gameDao.getGameById(round.getGameId()).getAnswer();
        String guess = round.getGuess();
        String result = matchDigits(guess, answer);
        round.setResult(result);

        if (guess.equals(answer)) {
            Game game = getGameById(round.getGameId());
            game.setFinished(true);
            gameDao.updateGame(game);
        }
        return roundDao.addRound(round);
    }
    public String matchDigits(String guess, String answer) {
        int exactMatchCount = 0;
        int partialMatchCount = 0;
        for (int i = 0; i < guess.length(); i++) {
            if (answer.charAt(i) == guess.charAt(i)) {
                exactMatchCount++;
            } else {
                for(int j = 0; j < guess.length(); j++){
                    if (guess.charAt(j) == answer.charAt(i)){
                        partialMatchCount++;
                    }
                }
            }
        }

        String result = "e:" + exactMatchCount + ":p:" + partialMatchCount;

        return result;
    }

    public List<Game> getAllGames() {
        List<Game> games = gameDao.getAllGames();
        for (Game game : games) {
            if (!game.isFinished()) {
                game.setAnswer("****");
            }
        }
        return games;
    }

    public Game getGameById(int gameId) throws InvalidGameException {
        Game game = gameDao.getGameById(gameId);
        if(game == null){
            throw new
                    InvalidGameException("Game in invalid. Try another ID");
        } else if (!game.isFinished()) {
            game.setAnswer("****");
        }
        return game;
    }

    public List<Round> getGameRounds(int gameId) throws InvalidGameException, NoRoundsException {
        List<Round>gameIdRounds = roundDao.getAllRoundsByGameId(gameId);
        if(gameIdRounds.isEmpty()){
            throw new
                    NoRoundsException("No rounds are available for this ID");
        } else {
            return gameIdRounds;
        }
    }


}
