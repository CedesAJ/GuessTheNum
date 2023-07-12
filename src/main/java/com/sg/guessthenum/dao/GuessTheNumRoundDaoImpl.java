package com.sg.guessthenum.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import com.sg.guessthenum.dto.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class GuessTheNumRoundDaoImpl implements GuessTheNumRoundDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    @Transactional
    public Round addRound(Round round) {
        String statement = "INSERT INTO round(gameId, guess, result) VALUES(?,?,?)";
        jdbc.update(statement, round.getGameId(), round.getGuess(), round.getResult());

        int newRoundId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        round.setRoundId(newRoundId);
        return getRoundById(newRoundId);

    }

    @Override
    public Round getRoundById(int roundId) {
        try {
            String statement = "SELECT * FROM round WHERE roundId = ?";
            return jdbc.queryForObject(statement, new RoundMapper(), roundId);
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Round> getAllRoundsByGameId(int gameId) {
        try {
            String statement = "SELECT * FROM round "
                    + "WHERE gameId = ? ORDER BY time";
            List<Round> rounds = jdbc.query(statement, new RoundMapper(), gameId);
            return rounds;
        } catch(DataAccessException ex) {
            return null;
        }
    }


    public static final class RoundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet row, int index) throws SQLException {
            Round round = new Round();
            round.setRoundId(row.getInt("roundId"));
            round.setGameId(row.getInt("gameId"));
            round.setGuess(row.getString("guess"));

            Timestamp timestamp = row.getTimestamp("time");
            round.setGuessTime(timestamp.toLocalDateTime());

            round.setResult(row.getString("result"));
            return round;
        }
    }

}
