package strategies.winningStrategies;

import model.Cell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderOneWinningStrategy implements PlayerWonStrategy{

    List<HashMap<Character, Integer>> rowMaps;
    List<HashMap<Character, Integer>> columnMaps;
    HashMap<Character, Integer> diagonalMap;
    HashMap<Character, Integer> reverseDiagonalMap;
    int size;

    public OrderOneWinningStrategy(int size){
        this.size = size;
        this.rowMaps = new ArrayList<>();
        for(int i = 0; i < size; i++){
            this.rowMaps.add(new HashMap<>());
        }
        this.columnMaps = new ArrayList<>();
        for(int i = 0; i < size; i++){
            this.columnMaps.add(new HashMap<>());
        }
        this.diagonalMap = new HashMap<>();
        this.reverseDiagonalMap = new HashMap<>();
    }

    @Override
    public boolean checkIfWon(Cell cell) {
        // update HashMaps
        // check if the player has won using the hashmap

        int x = cell.getX();
        int y = cell.getY();
        char symbol = cell.getPlayer().getSymbol();

        rowMaps.get(x).put(symbol, rowMaps.get(x).getOrDefault(symbol,0) + 1);
        columnMaps.get(y).put(symbol, columnMaps.get(y).getOrDefault(symbol,0) + 1);

        if(checkIfCellsIsOnDiagonal(x,y)){
            diagonalMap.put(symbol, diagonalMap.getOrDefault(symbol, 0) + 1);
        }

        if(checkIfCellsIsOnReverseDiagonal(x,y)){
            reverseDiagonalMap.put(symbol, reverseDiagonalMap.getOrDefault(symbol, 0) + 1);
        }
//-----------------------------------------step one done--------------------

        //implementing step two
        if(rowMaps.get(x).get(symbol) == size){
            return true;
        }
        if(columnMaps.get(y).get(symbol) == size){
            return true;
        }
        if(checkIfCellsIsOnDiagonal(x,y)){
            return diagonalMap.get(symbol) == size;
        }
        if(checkIfCellsIsOnReverseDiagonal(x,y)){
            return reverseDiagonalMap.get(symbol) == size;
        }


        return false;

    }


    private boolean checkIfCellsIsOnDiagonal(int x, int y){
        return x == y;
    }
    private boolean checkIfCellsIsOnReverseDiagonal(int x, int y){
        return x+ y == this.size -1;
    }
}
