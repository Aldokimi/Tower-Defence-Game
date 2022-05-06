package com.alphatech.unittests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.alphatech.game.model.towers.Placeholder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlaceholderTest {
    Placeholder p;

    @BeforeEach
    void setUp() {
        p = new Placeholder(15, 10);
    }

    @Test
    @DisplayName("Coordinates")
    void testCoordinates() {
        assertEquals(15, p.getX());
        assertEquals(10, p.getY());
    }

    @Test
    @DisplayName("Placeholder Avaliability -- Free")
    void testAvaliability() {
        assertTrue(p.isFreePlace());// should be free at initialization
    }

    @Test
    @DisplayName("Placeholder Avaliability -- Occupied")
    void testOccupied() {
        p.takePlace();
        assertFalse(p.isFreePlace());
    }
}
