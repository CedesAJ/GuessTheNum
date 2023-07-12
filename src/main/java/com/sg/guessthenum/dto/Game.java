package com.sg.guessthenum.dto;

public class Game {
    int gameId;
    String answer;
    boolean finished;

    public Game() {
    }

    public Game(String answer, boolean finished) {
        this.answer = answer;
        this.finished = finished;
    }

    public Game(int gameId, String answer, boolean finished) {
        this.gameId = gameId;
        this.answer = answer;
        this.finished = finished;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public String getStatus() {
        return "Game{" + "gameId=" + gameId + ", answer=" + answer + ", finished=" + finished + '}';
    }
}