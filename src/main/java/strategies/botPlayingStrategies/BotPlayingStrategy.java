package strategies.botPlayingStrategies;

import model.Pair;
import model.Board;


public interface BotPlayingStrategy {
    public Pair<Integer, Integer> makeMove(Board board);

}
