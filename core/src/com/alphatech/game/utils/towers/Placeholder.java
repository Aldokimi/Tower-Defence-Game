package com.alphatech.game.utils.towers;

public class Placeholder {
    private float x;
    private float y;
    private boolean isFree;
    private String occupiedBy;

    public Placeholder(float x, float y) {
        this.x = x;
        this.y = y;
        this.isFree = true;
        this.occupiedBy = "none";
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

    /**
     * Check if the placeholder is not occupied
     * 
     * @return boolean true if the placeholer is free otherwise false
     */
    public boolean isFreePlace() {
        return this.isFree;
    }

    public void occupy(String playerColor){
        this.occupiedBy = playerColor;
    }

    public String occupier(){
        return this.occupiedBy;
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

}
