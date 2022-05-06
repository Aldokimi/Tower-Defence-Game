package com.alphatech.unittests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.*;

import com.alphatech.game.model.towers.GoldMine;
import com.alphatech.game.model.towers.Placeholder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GoldMinesTest {
    private GoldMine goldMine;
    private ArrayList<Placeholder> placeholders;

    @BeforeEach
    void setUp() {
        goldMine = new GoldMine(placeholders);
        
    }

    @Test
    @DisplayName("Taken Places -- Empty array")
    void emptyTakenPlaceholders() {

        assertTrue(goldMine.getTakenPlaces().isEmpty());
        assertEquals(0, goldMine.getTakenPlaces().size());
    }

    @Test
    @DisplayName("Taken Places -- Full array")
    void TakenPlaceholders() {

        goldMine.addPlaceholder(new Placeholder(12, 12));
        assertFalse(goldMine.getTakenPlaces().isEmpty());
        assertEquals(1, goldMine.getTakenPlaces().size());
    }

    @Test
    @DisplayName("Available Places -- empty array")
    void emptyAvaliablePlaceholders() {

        goldMine.releaseAvailablePlaces();
        assertTrue(goldMine.getAvailablePlaces().isEmpty());

    }

    @Test
    @DisplayName("Check if goldmine can be built - free placeholder")
    void emptyPlaceholder() {

        assertTrue(goldMine.canBuild(new Placeholder(2, 2)));
    }

    @Test
    @DisplayName("Check if goldmine can be built - taken placeholder")
    void takenPlaceholder() {

        Placeholder p = new Placeholder(2, 2);
        p.takePlace();
        assertFalse(goldMine.canBuild(p));
    }

    @Test
    @DisplayName("Check which placeholders can we build the goldmine on - none")
    void buildGoldmine() {

        placeholders = new ArrayList<Placeholder>();
        goldMine.releaseAvailablePlaces();
        goldMine.getTakenPlaces();
        assertTrue(goldMine.getAvailablePlaces().isEmpty());
    }

    @Test
    @DisplayName("We can't build goldmine - taken placeholder")
    void buildGoldMineNot() {
        
        goldMine.addPlaceholder(new Placeholder(12, 12));
        assertTrue(goldMine.getAvailablePlaces().isEmpty());
    }

    @Test
    @DisplayName("We can build goldmine")
    void buildGoldMineYes() {

        Placeholder p = new Placeholder(3, 3);
        goldMine.addAvailablePlaceholder(p);
        assertFalse(goldMine.getAvailablePlaces().isEmpty());
        assertEquals(1, goldMine.getAvailablePlaces().size());
    }
   
}
