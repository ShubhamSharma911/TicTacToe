import controller.GameController;
import exceptions.InvalidBotCountException;
import exceptions.InvalidInputException;
import exceptions.InvalidNameException;
import exceptions.InvalidSymbolException;
import model.*;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) throws InvalidBotCountException, InterruptedException {
        GameController gameController = new GameController();
        Scanner scanner = new Scanner(System.in);

        List<Player> playerList = new ArrayList<Player>();

        HashSet<String> nameSet = new HashSet<>();
        HashSet<Character> symbolSet = new HashSet<>();
        char isBotPlayer;
        boolean bot = false;
        while (true) {
            System.out.println("Are bots going to play? (Y/N) ");
            isBotPlayer = scanner.next().charAt(0);
            if (isBotPlayer == 'Y' || isBotPlayer == 'N') {
                break; // Valid input, exit loop
            }
            System.out.println("Invalid Input! Please enter 'Y' or 'N'.");
        }
        BotLevel botLevel = BotLevel.EASY;
        if (isBotPlayer == 'Y') {
            bot = true;
            char botLevelchar;
            while (true) {
                System.out.println("Enter Bot level (E/M/H): ");
                botLevelchar = scanner.next().charAt(0);
                if (botLevelchar == 'E' || botLevelchar == 'M' || botLevelchar == 'H') {
                    break; // Valid input, exit loop
                }
                System.out.println("Invalid Input! Please enter 'E', 'M', or 'H'.");
            }

            if (botLevelchar == 'M') {
                botLevel = BotLevel.MEDIUM;
            } else if (botLevelchar == 'H') {
                botLevel = BotLevel.HARD;
            }

            playerList.add(new Bot('B', "Bot", botLevel));
            nameSet.add("Bot");
            symbolSet.add('B');

        }
        System.out.println("Enter the number of HUMAN players: ");
        int n = scanner.nextInt();
        int numberOfPlayers = n;
        if(bot){
            numberOfPlayers--;
        }

        for( int i=0; i<=numberOfPlayers; i++ ) {
            try{
                System.out.println("Enter Player Name ");
                String playerName = scanner.next();

                if(nameSet.contains(playerName)){
                    throw new InvalidNameException("Name "+ playerName+ " already exists. ");
                }


                System.out.println("Enter Symbol:  ");
                char  symbol = scanner.next().charAt(0);

                if(symbolSet.contains(symbol)){
                    throw new InvalidSymbolException("Symbol " + symbol + " already exists. ");
                }

                nameSet.add(playerName);
                symbolSet.add(symbol);
                Player humanPlayer = new HumanPlayer(playerName, symbol);
                playerList.add(humanPlayer);
                i++;
            }
            catch(InvalidNameException e){
                System.out.println("Error: " + e.getMessage());
            }
            catch(InvalidSymbolException e){
                System.out.println("Error: " + e.getMessage());
            }

        }

        Game game = gameController.createGame(playerList);
        gameController.printBoard(game);
        System.out.println("!Board!");
        while(gameController.getGameStatus(game) == GameStatus.InProgress){
            gameController.makeMove(game);
        }
        if(gameController.getGameStatus(game) == GameStatus.WON){
            Player winner = gameController.getWinner(game);
            System.out.println("Winner is " + winner.getName()+ " with symbol " + winner.getSymbol() + " has won.");
            gameController.printBoard(game);
        }
        else System.out.println("game has drawn");

        boolean isReplay = false;
        while (true) {
            System.out.println("Do you want a REPLAY? (Y/N) ");
            char replay = scanner.next().charAt(0);
            if (replay == 'Y' || replay == 'N') {
                if(replay == 'Y'){
                    isReplay = true;
                }

                break; // Valid input, exit loop
            }
            System.out.println("Invalid Input! Please enter 'Y' or 'N'.");
        }

        if(isReplay){
            gameController.clearBoard(game);

            gameController.replay(game);
        }

    }
}
