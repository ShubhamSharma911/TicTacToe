package model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<List<Cell>> grid;

    public List<List<Cell>> getGrid() {
        return grid;
    }

    public Board(int size) {
        grid = new ArrayList<>(size);
        for(int row=0; row<size; row++) {
           List<Cell> rowArr = new ArrayList<>();

           for(int col = 0; col<size; col++) {
               rowArr.add(new Cell(row, col));
           }
           grid.add(rowArr);
        }
    }

    public void printBoard() {
        for(List<Cell> row : grid){
            int count = 0;
            for(Cell cell : row) {
                cell.print();
                count++;
                if(count <row.size()){
                    System.out.print(" | ");
                }
            }
            System.out.println();
        }

    }

//    public void setPlayer(int row, int col, Player player) {
//
//    }

    public Cell getCell(int x, int y) {
        Cell cell = this.grid.get(x).get(y);
        if(x< 0 || x>= grid.size() || y<0 || y>= grid.get(x).size()){
            throw new IndexOutOfBoundsException();

        }
        return cell;
    }

    public void setPlayer(int x, int y, Player player) {
        Cell cell = this.grid.get(x).get(y);
        cell.setPlayer(player);

    }

    public void clearBoard() {
        for(List<Cell> row : grid){
            for(Cell cell : row){
                cell.setPlayer(null);
            }
        }
    }

}
