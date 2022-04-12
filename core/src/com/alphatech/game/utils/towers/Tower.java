package com.alphatech.game.utils.towers;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class Tower {
    protected Texture towerTexture;
    protected ArrayList<Placeholder> placeholders;
    protected ArrayList<Placeholder> availablePlaces;
    protected ArrayList<Placeholder> takenPlaces;

    public Tower(Texture texture, ArrayList<Placeholder> placeholders) {
        this.towerTexture = texture;
        this.placeholders = placeholders;
        this.availablePlaces = new ArrayList<>();
        this.takenPlaces = new ArrayList<>();
    }

    public Texture getTowerTexture() {
        return towerTexture;
    }

    /**
     * Set the tower texture
     *
     * @param towerTexture
     */
    public void setTowerTexture(Texture towerTexture) {
        this.towerTexture = towerTexture;
    }

    /**
     * Checks the distance (at most ~three squares) between two placeholders
     *
     * @param a First placeholder
     * @param b second placeholder
     * @return true if the distance is less than (3.5/~three squares) and the
     *         placeholder is not occupied, otherwise false
     */
    private boolean canBuild(Placeholder a, Placeholder b) {
        return ((Math.sqrt(
                (Math.abs(a.getY() - b.getY()) * Math.abs(a.getY() - b.getY())) +
                        (Math.abs(a.getX() - b.getX()) * Math.abs(a.getX() - b.getX()))) <= 3.5)
                && (b.isFreePlace()));
    }

    /**
     * Measures/Checks the avaliability of building on the placeholders.
     * By checking the distance (~three squares), not taken placeholder(either by
     * the
     * player himself or the enemy)
     */
    public void build() {
        for (Placeholder currentPlace : takenPlaces) {
            for (Placeholder newPlace : placeholders) {
                if (canBuild(currentPlace, newPlace) && !takenPlaces.contains(newPlace)
                        && !takenPlaces.contains(newPlace)) {
                    availablePlaces.add(newPlace);
                }
            }
        }
    }

    /**
     * Gets the avaliable placeholders to build on
     *
     * @return Avaliable placeholders
     */
    public ArrayList<Placeholder> getAvailablePlaces() {
        return availablePlaces;
    }

    /**
     * Release the highlighted placeholders
     */
    public void releaseAvailablePlaces() {
        this.availablePlaces = new ArrayList<>();
    }

    /**
     * get the occupied/taken placeholders
     *
     * @return occupied placeholders
     */
    public ArrayList<Placeholder> getTakenPlaces() {
        return takenPlaces;
    }

    /**
     * Initialize the first point(placeholder) to measure from (checking
     * avaliability to build)
     *
     * @param placeholder
     */
    public void initializeCenterofMeasurement(Placeholder placeholder) {
        this.takenPlaces.add(placeholder);
    }

    /**
     * Add a placeholder to the occupied placeholders array
     *
     * @param placeholder
     */
    public void addTower(Placeholder placeholder) {
        this.takenPlaces.add(placeholder);
    }

    protected void attack() {
    }

}
