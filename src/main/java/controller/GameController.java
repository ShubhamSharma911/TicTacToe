package controller;

import exceptions.InvalidBotCountException;
import model.Game;
import model.GameStatus;
import model.Player;

import java.util.List;

public class GameController {

    public Game createGame(List<Player> playerList) throws InvalidBotCountException {
        Game game = Game.getBuilder().setPlayers(playerList).build();
        return game;
    }

    public GameStatus getGameStatus(Game game){
        return game.getGameStatus();
    }

    public void printBoard(Game game){
        game.printBoard();
    }

    public void makeMove(Game game){
        game.makeMove();
    }

    public Player getWinner(Game game){
        return game.getWinner();
    }

    public void clearBoard(Game game){
        game.clearBoard();
    }

    public void replay(Game game) throws InterruptedException {
        game.replay();
    }
}
