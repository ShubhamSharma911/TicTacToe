package model;

public class Pair<T, T1> {
    private T x;
    private  T1 y;
    public Pair(T x, T1 y) {
        this.x = x;
        this.y = y;
    }

    public T getX() {
        return x;
    }

    public void setX(T x) {
        this.x = x;
    }

    public T1 getY() {
        return y;
    }

    public void setY(T1 y) {
        this.y = y;
    }
}
