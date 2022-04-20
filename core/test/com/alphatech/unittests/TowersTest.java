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
        fillPlaceHolders();
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
        tower.addTower(new Placeholder(11, 222), "NONE");
        assertFalse(tower.getTakenPlaces().isEmpty());
        assertEquals(1, tower.getTakenPlaces().size());

        normalTower.addTower(new Placeholder(121, 221), "NONE");
        assertFalse(normalTower.getTakenPlaces().isEmpty());
        assertEquals(1, normalTower.getTakenPlaces().size());

        multiAttTower.addTower(new Placeholder(131, 2222), "NONE");
        assertFalse(multiAttTower.getTakenPlaces().isEmpty());
        assertEquals(1, multiAttTower.getTakenPlaces().size());
    }

    @Test
    @DisplayName("Can build a tower with a small given position")
    void canBuildBeginTower() {
        assertEquals(true, tower.canBuild(new Placeholder(1, 1), new Placeholder(1, 1)));
        assertEquals(true, tower.canBuild(new Placeholder(1, 1), new Placeholder(2, 2)));
        assertEquals(true, tower.canBuild(new Placeholder(1, 2), new Placeholder(1, 2)));
        assertEquals(true, tower.canBuild(new Placeholder(2, 1), new Placeholder(2, 1)));
        assertEquals(true, tower.canBuild(new Placeholder(1, 1), new Placeholder(3, 3)));
        assertEquals(true, tower.canBuild(new Placeholder(1, 1), new Placeholder(2, 3)));
        assertEquals(true, tower.canBuild(new Placeholder(1, 1), new Placeholder(1, 3)));
        assertEquals(true, tower.canBuild(new Placeholder(1, 2), new Placeholder(3, 3)));
        assertEquals(true, tower.canBuild(new Placeholder(1, 3), new Placeholder(3, 3)));
        assertEquals(true, tower.canBuild(new Placeholder(3, 1), new Placeholder(3, 3)));
        assertEquals(true, tower.canBuild(new Placeholder(3, 2), new Placeholder(3, 3)));
        assertEquals(true, tower.canBuild(new Placeholder(3, 3), new Placeholder(3, 3)));
        assertFalse(tower.canBuild(new Placeholder(1, 1), new Placeholder(4, 4)));
        assertFalse(tower.canBuild(new Placeholder(1, 1), new Placeholder(3, 4)));
        assertFalse(tower.canBuild(new Placeholder(24, 24), new Placeholder(3, 4)));

    }

    @Test
    @DisplayName("Can build a tower with a big given position")
    void canBuildEndTower() {

        assertFalse(tower.canBuild(new Placeholder(19, 9), new Placeholder(21, 20)));
        assertFalse(tower.canBuild(new Placeholder(29, 19), new Placeholder(29, 8)));
        assertFalse(tower.canBuild(new Placeholder(4, 24), new Placeholder(3, 4)));

    }

    @Test
    @DisplayName("Building a tower")
    void buildingTower() {
        tower.initializeCenterofMeasurement(new Placeholder(16, 16));
        tower.build();
        normalTower.initializeCenterofMeasurement(new Placeholder(16, 16));
        normalTower.build();
        assertEquals(1, tower.getTakenPlaces().size());
        assertEquals(1, normalTower.getTakenPlaces().size());
        assertEquals(tower.getAvailablePlaces().size(), normalTower.getAvailablePlaces().size());

        tower.releaseAvailablePlaces();
        normalTower.releaseAvailablePlaces();
        assertEquals(0, tower.getAvailablePlaces().size());
        assertEquals(0, normalTower.getAvailablePlaces().size());

    }

    public void fillPlaceHolders() {

        for (int x = 0; x < 30; x++) {
            for (int y = 0; y < 28; y++) {
                if (// near blue castle
                (x == 6 && y == 21) || (y == 23 && (x == 5 || x == 7 || x == 10 || x == 12 || x == 14 || x == 17)) ||
                        ((x == 8 || x == 10) && y == 21) || (y == 19 && x == 6)
                        || ((y == 16 || y == 18) && (x == 2 || x == 0)) || ((y == 15 || y == 18) && x == 4) ||
                        (y == 19 && x == 8)
                        || (y == 14 && (x == 2 || x == 0) || (x == 21 && y == 23)) ||
                        (y == 21 && x == 12) || (y == 12 && (x == 2 || x == 0))
                        || (y == 10 && (x == 2 || x == 4 || x == 6)) ||
                        (x == 17 && (y == 21) || (x == 19 && y == 21) || (y == 23 && x == 24))
                        || (y == 21 && x == 15) ||
                        ((y == 15 || y == 19 || y == 17) && (x == 11 || x == 13))
                        || (y == 10 && (x == 8 || x == 10)) || (y == 8 && x == 1)
                        || (y == 5 && x == 6)
                        || (y == 8 && (x == 21 || x == 23)) || (y == 4 && x == 21)
                        || (y == 6 && x == 22) || (x == 23 && y == 6)
                        || (y == 10 && (x == 23 || x == 25)) || (y == 11 && (x == 29 || x == 27)) ||
                        (y == 6 && x == 20) || (y == 12 && (x == 13 || x == 10 || x == 8 || x == 23))
                        || (y == 13 && (x == 27 || x == 29)) ||
                        ((y == 4 || y == 6) && x == 18) || (y == 8 && (x == 17 || x == 25 || x == 27 || x == 29))
                        || (y == 15 && (x == 29 || x == 23 || x == 17)) ||
                        (y == 10 && x == 20) || (y == 17 && x == 28) || (y == 5 && x == 16) || (x == 19 && y == 15) ||
                        (y == 5 && (x == 8 || x == 13 || x == 11)) || (y == 8 && (x == 3 || x == 14))
                        || (y == 13 && (x == 12 || x == 15 || x == 17)) ||
                        (y == 17 && (x == 18 || x == 21 || x == 23)) || ((y == 21 || y == 17) && x == 25)
                        || ((x == 7 || x == 13 || x == 16) && y == 7)) {
                    this.placeholders.add(new Placeholder(x, y));
                }
            }
        }
    }
}
