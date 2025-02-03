package strategies.winningStrategies;

import model.Cell;

public interface PlayerWonStrategy {
    boolean checkIfWon(Cell cell);
}
