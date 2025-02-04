package model;

public abstract  class Player {
    protected String name;
    protected char symbol;

    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }
    abstract Pair<Integer, Integer> makeMove(Board board);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }
}
