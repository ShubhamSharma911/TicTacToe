import controller.GameController;
import exceptions.InvalidBotCountException;
import exceptions.InvalidInputException;
import exceptions.InvalidNameException;
import exceptions.InvalidSymbolException;
import model.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) throws InvalidBotCountException {
        GameController gameController = new GameController();
        Scanner scanner = new Scanner(System.in);

        List<Player> playerList = new ArrayList<Player>();

        HashSet<String> nameSet = new HashSet<>();
        HashSet<Character> symbolSet = new HashSet<>();

        System.out.println("Are bots going to play ? (Y/N) ");
        System.out.println("Enter the number of HUMAN players: ");
        int n = scanner.nextInt();
        char isBotPlayer;
        while (true) {
            System.out.println("Are bots going to play? (Y/N) ");
            isBotPlayer = scanner.next().charAt(0);
            if (isBotPlayer == 'Y' || isBotPlayer == 'N') {
                break; // Valid input, exit loop
            }
            System.out.println("Invalid Input! Please enter 'Y' or 'N'.");
        }
        int numberOfPlayers = n;
        BotLevel botLevel = BotLevel.EASY;
        if (isBotPlayer == 'Y') {
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
            numberOfPlayers--;
        }

        for( int i=0; i<numberOfPlayers; i++ ) {
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

            Game game = gameController.createGame(playerList);

            while(gameController.getGameStatus(game) == GameStatus.InProgress){
                gameController.printBoard(game);
                gameController.makeMove(game);
            }


        }
    }
}
