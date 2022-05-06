package com.alphatech.game.model.towers;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class Tower {
    protected Texture towerTexture;
    protected String parentName;
    protected ArrayList<Placeholder> placeholders;
    protected ArrayList<Placeholder> availablePlaces;
    protected ArrayList<TowerSprite> takenPlaces;

    // For Testing
    public Tower(ArrayList<Placeholder> placeholders) {
        this.placeholders = placeholders;
        availablePlaces = new ArrayList<>();
        takenPlaces = new ArrayList<>();

    }

    public Tower(Texture texture, ArrayList<Placeholder> placeholders, String parentName) {
        this.towerTexture = texture;
        this.placeholders = placeholders;
        this.availablePlaces = new ArrayList<>();
        this.takenPlaces = new ArrayList<>();
        this.parentName = parentName;
    }

    public ArrayList<Placeholder> getPlaceholders() {
        return this.placeholders;
    }

    public void setPlaceholders(ArrayList<Placeholder> placeholders) {
        this.placeholders = placeholders;
    }

    public Texture getTowerTexture() {
        return this.towerTexture;
    }

    /**
     * Set the tower texture
     *
     * @param towerTexture
     */
    public void setTowerTexture(Texture towerTexture) {
        this.towerTexture = towerTexture;
    }

    public String getParentName() {
        return this.parentName;
    }

    /**
     * get the name of the class
     *
     * @return string representation of class name
     */
    public String getClassName() {
        return "Tower";
    }

    /**
     * Checks the distance (at most ~three squares) between two placeholders
     *
     * @param a First placeholder
     * @param b second placeholder
     * @return true if the distance is less than (3.5/~three squares) and the
     * placeholder is not occupied, otherwise false
     */
    public boolean canBuild(Placeholder a, Placeholder b) {
        return ((Math.sqrt(
                (Math.abs(a.getY() - b.getY()) * Math.abs(a.getY() - b.getY())) +
                        (Math.abs(a.getX() - b.getX()) * Math.abs(a.getX() - b.getX()))) <= 3.5)
                && (b.isFreePlace()));
    }

    /**
     * Measures/Checks the avaliability of building on the placeholders.
     * By checking the distance (~three squares), not taken placeholder(either by
     * the player himself or the enemy)
     */
    public void build() {
        for (TowerSprite currentPlace : takenPlaces) {
            for (Placeholder newPlace : placeholders) {
                if (canBuild(currentPlace.getPosition(), newPlace) && !contains(takenPlaces, newPlace)) {
                    availablePlaces.add(newPlace);
                }
            }
        }
    }

    /**
     * Check wheather a list contains a specific placeholder or not.
     *
     * @param towers
     * @param checkPlace
     * @return wheather a list of towers contain the place holder or not.
     */
    private boolean contains(ArrayList<TowerSprite> towers, Placeholder checkPlace) {
        for (TowerSprite towerSprite : towers) {
            if (towerSprite.getPosition().equals(checkPlace))
                return true;
        }
        return false;
    }

    /**
     * Gets the avaliable placeholders to build on
     *
     * @return Avaliable placeholders
     */
    public ArrayList<Placeholder> getAvailablePlaces() {
        return availablePlaces;
    }

    public void setAvailablePlaces(ArrayList<Placeholder> availablePlaces) {
        this.availablePlaces = availablePlaces;
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
    public ArrayList<TowerSprite> getTakenPlaces() {
        return takenPlaces;
    }

    public void setTakenPlaces(ArrayList<TowerSprite> takenPlaces) {
        this.takenPlaces = takenPlaces;
    }

    /**
     * Initialize the first point(placeholder) to measure from (checking
     * avaliability to build)
     *
     * @param placeholder
     */
    public void initializeCenterofMeasurement(Placeholder placeholder) {
        this.takenPlaces.add(new TowerSprite(placeholder, "NONE"));
    }

    /**
     * get the first point(placeholder) which we measure from (checking
     * avaliability to build)
     * requirement of usage: if (this.takenPlaces.size() > 0)
     *
     * @return placeholder
     */
    public Placeholder getCenterofMeasurement() {

        return this.takenPlaces.get(0).getPosition();

    }

    /**
     * Add a placeholder to the occupied placeholders array
     *
     * @param placeholder
     */
    public void addTower(Placeholder placeholder, String towerType) {
        TowerSprite towerSprite = new TowerSprite(placeholder, towerType);
        this.takenPlaces.add(towerSprite);
    }

}
