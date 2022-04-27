package com.alphatech.game.utils.towers;

public class Placeholder {
    private float x;
    private float y;
    private boolean isFree;

    public Placeholder(float x, float y) {
        this.x = x;
        this.y = y;
        this.isFree = true;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    /**
     * Occupy the placeholder
     */
    public void takePlace() {
        this.isFree = false;
    }

    public void setIsFree(boolean isFree) {
        this.isFree = isFree;
    }

    public boolean getIsFree() {
        return this.isFree;
    }

    /**
     * Check if the placeholder is not occupied
     * 
     * @return boolean true if the placeholer is free otherwise false
     */
    public boolean isFreePlace() {
        return this.isFree;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Placeholder)) {
            return false;
        }

        Placeholder c = (Placeholder) other;

        return (this.x == c.x) && (this.y == c.y);
    }

    @Override
    public String toString() {
        return "Placeholder{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
