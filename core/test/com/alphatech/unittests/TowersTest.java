package com.alphatech.unittests;

import com.alphatech.game.utils.towers.MultiAttackTower;
import com.alphatech.game.utils.towers.NormalTower;
import com.alphatech.game.utils.towers.Placeholder;
import com.alphatech.game.utils.towers.Tower;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TowersTest {
    public NormalTower normalTower;
    public Tower tower;
    public MultiAttackTower multiAttTower;
    public ArrayList<Placeholder> placeholders;

    @BeforeEach
    void setUp() {
        placeholders = new ArrayList<>();

        tower = new Tower(placeholders);

        normalTower = new NormalTower(placeholders);

        multiAttTower = new MultiAttackTower(placeholders);

    }

    @Test
    @DisplayName("Taken Places -- Empty array")
    void canBuildTowerEmpty() {
        assertTrue(tower.getTakenPlaces().isEmpty());

        assertTrue(normalTower.getTakenPlaces().isEmpty());

        assertTrue(multiAttTower.getTakenPlaces().isEmpty());
    }

    @Test
    @DisplayName("Taken Places -- Full array")
    void canBuildTowerFull() {
        tower.initializeCenterofMeasurement(new Placeholder(12, 12));
        assertFalse(tower.getTakenPlaces().isEmpty());
        assertEquals(1, tower.getTakenPlaces().size());

        normalTower.initializeCenterofMeasurement(new Placeholder(122, 22));
        assertFalse(normalTower.getTakenPlaces().isEmpty());
        assertEquals(1, normalTower.getTakenPlaces().size());

        multiAttTower.initializeCenterofMeasurement(new Placeholder(44, 14));
        assertFalse(multiAttTower.getTakenPlaces().isEmpty());
        assertEquals(1, multiAttTower.getTakenPlaces().size());
    }

    @Test
    @DisplayName("Adding a tower")
    void addingTower() {
        tower.addTower(new Placeholder(11, 222));
        assertFalse(tower.getTakenPlaces().isEmpty());
        assertEquals(1, tower.getTakenPlaces().size());

        normalTower.addTower(new Placeholder(121, 221));
        assertFalse(normalTower.getTakenPlaces().isEmpty());
        assertEquals(1, normalTower.getTakenPlaces().size());

        multiAttTower.addTower(new Placeholder(131, 2222));
        assertFalse(multiAttTower.getTakenPlaces().isEmpty());
        assertEquals(1, multiAttTower.getTakenPlaces().size());
    }
}
