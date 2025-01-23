package model;

import exceptions.InvalidBotCountException;

import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private int currentPlayer;
    private GameStatus gameStatus;
    private List<Move> moves;

    static class GameBuilder{

        private List<Player> players;

        public GameBuilder setPlayers(List<Player> players){
            this.players = players;
            return this;
        }
        public Game build() throws InvalidBotCountException {
            int botCount = 0;
            for(Player player : players){
                if(player instanceof Bot){
                    botCount++;
                }
                if(botCount >1){
                    throw new InvalidBotCountException("At max 1 bot is allowed");
                }
            }
            return new Game(this);
        }

    }
}
