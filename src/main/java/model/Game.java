package model;

import exceptions.InvalidBotCountException;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private int currentPlayerIdx;
    private GameStatus gameStatus;
    private List<Move> moves;

    private Game (GameBuilder gameBuilder) {
        this.players = gameBuilder.players;
        int n = players.size();
        this.board = new Board(n+1);
        this.currentPlayerIdx = 0;
        this.moves = new ArrayList<>();
        this.gameStatus = GameStatus.InProgress;
    }

    public static GameBuilder getBuilder(){
        return new GameBuilder();
    }

    public Board getBoard() {
        return board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getCurrentPlayerIdx() {
        return currentPlayerIdx;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void printBoard() {
        this.board.printBoard();
    }

    public void makeMove() {
        Player player = players.get(currentPlayerIdx);
        Pair<Integer, Integer > pair = null;
        Cell targetCell = null;

        while(true){
           try{
               pair = player.makeMove();
               targetCell = board.getCell(pair.getX(), pair.getY());
               if(targetCell.getPlayer() == null){
                   break;
               }
               else{
                   System.out.println("Cell is already occupied");
               }
           }
           catch(IndexOutOfBoundsException e){
               int maxIdx = board.getGrid().size()-1;

                System.out.println("Invalid coordinates . please enter values between 0 and " + maxIdx);
           }

        }

        this.board.setPlayer(pair.getX(), pair.getY(), player);

        Move move = new Move(player, this.board.getCell(pair.getX(), pair.getY()));
        this.moves.add(move);
        if(checkIfWon()) { // check if someone has won

            this.gameStatus = GameStatus.WON;
            return;
        }


        this.currentPlayerIdx = (this.currentPlayerIdx+1) % this.players.size();


    }

    private boolean checkIfWon() {
        return true;
    }

    public static class GameBuilder{

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
