package model;

import java.util.Scanner;

public class HumanPlayer extends Player {

    public HumanPlayer(String name, char symbol) {
        super(name,symbol);
    }

    Pair<Integer, Integer> makeMove() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter row :  ");
        int x = scanner.nextInt();
        System.out.println("Enter column :  ");
        int y = scanner.nextInt();

        return new Pair(x,y);
    }


    void undo(){}
}
