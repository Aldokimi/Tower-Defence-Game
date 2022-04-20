package com.alphatech.game.helpers;

public class Position {
    private double x, y;
    
    public Position(double d, double e){
        this.x = d;
        this.y = e;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Position)) return false;
        return ((Position)other).getX() == x && ((Position)other).getY() == y;
    }

    @Override
    public String toString(){
        return (this.x + " " + this.y);
    }
}
