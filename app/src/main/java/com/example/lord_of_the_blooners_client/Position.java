package com.example.lord_of_the_blooners_client;

public class Position {

    private int x;
    private int y;

    public Position() {
        setX(0);
        setY(0);
    }

    public Position(int tx, int ty) {
        setX(tx);
        setY(ty);
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public String toString() {
        return x+","+y;
    }
}
