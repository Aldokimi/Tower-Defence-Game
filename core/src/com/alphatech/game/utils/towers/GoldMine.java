package com.alphatech.game.utils.towers;

import com.badlogic.gdx.graphics.Texture;
import java.util.ArrayList;

public class GoldMine {
    protected Texture goldMineTexture;
    protected ArrayList<Placeholder> placeholders;
    protected ArrayList<Placeholder> availablePlaces;
    protected ArrayList<Placeholder> takenPlaces;

    public GoldMine(Texture texture, ArrayList<Placeholder> placeholders) {
        goldMineTexture = texture;
        this.placeholders = placeholders;
        availablePlaces = new ArrayList<>();
        takenPlaces = new ArrayList<>();
    }

    public Texture getGoldMineTexture() {
        return goldMineTexture;
    }

    /**
     * Set the goldmine texture
     * 
     * @param goldMineTexture
     */
    public void setGoldMineTexture(Texture goldMineTexture) {
        this.goldMineTexture = goldMineTexture;
    }

     /**
     * Checks if placeholder is free
     * 
     * @param a placeholder
     * @return true if placeholder is free so gold mine can be built
     */
    private boolean canBuild(Placeholder placeholder) {
        return placeholder.isFreePlace();
    }

    /**
     * Measures/Checks the availability of building on the placeholders.
     * By checking if the placeholder has been taken (by himself or his enemy)
     */
    public void build() {
            for(Placeholder p : placeholders) {  
                if(canBuild(p) && !contains(takenPlaces, p)
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
    private boolean contains(ArrayList<Placeholder> placeholders, Placeholder p) {
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
        return takenPlaces;
    }

    /**
     * Add a placeholder to the occupied placeholders array
     * 
     * @param placeholder
     */
    public void addPlaceholder(Placeholder placeholder) {
        this.takenPlaces.add(placeholder);
    }

    

}
