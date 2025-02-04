package model;

import factories.BotPlayingStrategyFactory;
import strategies.botPlayingStrategies.BotPlayingStrategy;

public class Bot extends Player {

    private BotPlayingStrategy botPlayingStrategy;

    public Bot(char symbol, String name, BotLevel botLevel){
        super(name,symbol);
        this.botLevel = botLevel;
        this.botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategy(botLevel);
    }


    private BotLevel botLevel;

    public Pair<Integer, Integer> makeMove(Board board) {
        return botPlayingStrategy.makeMove(board);
    }
}
