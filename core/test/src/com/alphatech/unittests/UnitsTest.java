package com.alphatech.unittests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.alphatech.game.helpers.Textures;
import com.alphatech.game.utils.CrazySoldier;
import com.alphatech.game.utils.NormalSoldier;
import com.alphatech.game.utils.Unit;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.brashmonkey.spriter.Point;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class UnitsTest {

    Unit soldier;
    NormalSoldier normal;
    CrazySoldier crazy;
    Point position;

    @BeforeEach
    void setUp() {
        position = new Point(100, 50);

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

        position = new Point(40, 15);
        normal.setPosition(position);
        assertEquals(position, normal.getPosition());

        position = new Point(84, 72);
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
