package factories;

import model.BotLevel;
import strategies.botPlayingStrategies.BotPlayingStrategy;
import strategies.botPlayingStrategies.EasyBotPlayingStrategy;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategy(BotLevel level) {
        if(level == BotLevel.EASY){
            return new EasyBotPlayingStrategy();
        }
        else if(level == BotLevel.HARD){
            return new EasyBotPlayingStrategy();
        }
        else return new EasyBotPlayingStrategy();// returns meduiumBotplYING STRATEGY
    }
}
