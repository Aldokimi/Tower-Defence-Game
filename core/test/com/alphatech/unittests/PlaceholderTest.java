package com.alphatech.unittests;

import com.alphatech.game.utils.towers.Placeholder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        assertEquals(p.getX(), 15);
        assertEquals(p.getY(), 10);
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