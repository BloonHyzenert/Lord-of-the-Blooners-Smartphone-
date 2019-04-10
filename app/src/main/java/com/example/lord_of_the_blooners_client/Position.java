package com.example.lord_of_the_blooners_client;

public class Position {

    private double x;
    private double y;

    public Position() {
        this.setX(0);
        this.setY(0);
    }

    public Position(double tx, double ty) {
        this.setX(tx);
        this.setY(ty);
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public String toString() {
        return x+","+y;
    }
}
