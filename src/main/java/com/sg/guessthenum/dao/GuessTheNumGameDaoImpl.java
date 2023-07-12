package com.sg.guessthenum.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.sg.guessthenum.dto.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class GuessTheNumGameDaoImpl implements GuessTheNumGameDao {
    @Autowired
    JdbcTemplate jdbc;

    @Override
    @Transactional
    public Game addGame(Game game) {
        String statement = "INSERT INTO game(number) VALUES (?)";
        jdbc.update(statement, game.getAnswer());

        int newGameId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        game.setGameId(newGameId);
        return game;
    }

    @Override
    public Game getGameById(int gameId) {
        try {
            String statement = "SELECT * FROM game WHERE gameId = ?";
            return jdbc.queryForObject(statement, new GameMapper(), gameId);
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Game> getAllGames() {
        String statement = "SELECT * FROM game";
        return jdbc.query(statement, new GameMapper());
    }

    @Override
    public void updateGame(Game game) {
        String statement = "UPDATE game SET isFinished = ? WHERE gameId = ?";
        jdbc.update(statement, game.isFinished(), game.getGameId());
    }

    public static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet row, int index) throws SQLException {
            Game game = new Game();
            game.setGameId(row.getInt("gameId"));
            game.setAnswer(row.getString("number"));
            game.setFinished(row.getBoolean("isFinished"));
            return game;
        }
    }
}
