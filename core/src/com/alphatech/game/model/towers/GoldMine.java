package com.alphatech.game.model.towers;

import com.alphatech.game.model.Player;
import com.badlogic.gdx.graphics.Texture;
import java.util.ArrayList;

public class GoldMine {
    protected Texture goldMineTexture;
    protected ArrayList<Placeholder> placeholders;
    protected ArrayList<Placeholder> availablePlaces;
    protected ArrayList<Placeholder> takenPlaces;
    protected String parentName; // BLUE or RED
    protected Player player;

    // For testing
    public GoldMine(ArrayList<Placeholder> placeholders) {
        this.placeholders = placeholders;
        availablePlaces = new ArrayList<>();
        takenPlaces = new ArrayList<>();
    }

    public GoldMine(Texture texture, ArrayList<Placeholder> placeholders, String parentName) {
        goldMineTexture = texture;
        this.placeholders = placeholders;
        this.parentName = parentName;
        availablePlaces = new ArrayList<>();
        takenPlaces = new ArrayList<>();
    }

    public void setTakenPlaces(ArrayList<Placeholder> takenPlaces) {
        this.takenPlaces = takenPlaces;
    }

    public Texture getGoldMineTexture() {
        return goldMineTexture;
    }

    public String getParentName() {
        return parentName;
    }

    /**
     * Checks if placeholder is free
     * 
     * @param a placeholder
     * @return true if placeholder is free so gold mine can be built
     */
    public boolean canBuild(Placeholder placeholder) {
        return placeholder.isFreePlace();
    }

    /**
     * Measures/Checks the availability of building on the placeholders.
     * By checking if the placeholder has been taken (by himself or his enemy)
     */
    public void build() {
        for (Placeholder p : placeholders) {
            if (canBuild(p) && !contains(takenPlaces, p)
                    && !contains(availablePlaces, p)) {
                availablePlaces.add(p);
            }
        }
    }

    /**
     * Checks if the given placeholder 'p' is in the placeholders array
     * 
     * @param placeholders array of placeholders
     * @param p            given placeholder
     * @return true if it is contained by the array, otherwise false
     */
    public boolean contains(ArrayList<Placeholder> placeholders, Placeholder p) {
        return placeholders.contains(p);
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
        return this.takenPlaces;
    }

    /**
     * Add a placeholder to the occupied placeholders array
     * 
     * @param placeholder
     */
    public void addPlaceholder(Placeholder placeholder) {
        this.takenPlaces.add(placeholder);
    }

    /**
     * Add a placeholder to the available placeholders array
     * 
     * @param placeholder
     */
    public void addAvailablePlaceholder(Placeholder placeholder) {
        this.availablePlaces.add(placeholder);
    }

    /**
     * Add a placeholder to the general placeholders array
     * 
     * @param placeholder
     */
    public void addGeneralPlaceholder(Placeholder placeholder) {
        this.placeholders.add(placeholder);
    }

}
