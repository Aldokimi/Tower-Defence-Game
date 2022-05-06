package com.alphatech.unittests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;

import com.alphatech.game.model.units.CrazySoldier;
import com.alphatech.game.model.units.NormalSoldier;
import com.alphatech.game.model.units.Unit;

public class UnitsTest {

    Unit soldier;
    NormalSoldier normal;
    CrazySoldier crazy;
    Point2D.Float position;

    @BeforeEach
    void setUp() {
        position = new Point2D.Float(100, 50);

        soldier = new Unit(position);
        normal = new NormalSoldier(position);
        crazy = new CrazySoldier(position);

        soldier.setSpeed(50);
        normal.setSpeed(15);
        crazy.setSpeed(80);
    }

    @Test
    @DisplayName("Get soldier position")
    void testPosition() {
        assertEquals(position, soldier.getPosition());
        assertEquals(position, normal.getPosition());
        assertEquals(position, crazy.getPosition());

        position = new Point2D.Float(40, 15);
        normal.setPosition(position);
        assertEquals(position, normal.getPosition());

        position = new Point2D.Float(84, 72);
        crazy.setPosition(position);
        assertEquals(position, crazy.getPosition());

    }

    @Test
    @DisplayName("Get soldier speed")
    void testSpeed() {
        assertEquals(50, soldier.getSpeed());
        assertEquals(15, normal.getSpeed());
        assertEquals(80, crazy.getSpeed());

        normal.setSpeed(100);
        crazy.setSpeed(200);
        assertEquals(100, normal.getSpeed());
        assertEquals(200, crazy.getSpeed());
    }

}
