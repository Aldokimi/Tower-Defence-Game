package com.alphatech.game.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Textures {

        // Map
        public static final Texture PLACE_HOLDER = new Texture("GroundTiles/BuildGround.png");
        public static final Texture ENDTURN_TEXT = new Texture("Texts/EndTurn.png");

        // Soldiers Buttons
        public static final Texture SOLDIER1 = new Texture("Enemy1/soldier1.png");
        public static final Texture SOLDIER3 = new Texture("Enemy3/soldier3.png");

        // Idle Red Soldiers
        public static final TextureAtlas SOLDIER1_IDLE_RED = new TextureAtlas(
                        Gdx.files.internal("Enemy1/Red/Idle/Soldier.atlas"));
        public static final TextureAtlas SOLDIER3_IDLE_RED = new TextureAtlas(
                        Gdx.files.internal("Enemy3/Red/Idle/Soldier.atlas"));

        // Idle Blue Soldiers
        public static final TextureAtlas SOLDIER1_IDLE_BLUE = new TextureAtlas(
                        Gdx.files.internal("Enemy1/Blue/Idle/Soldier.atlas"));
        public static final TextureAtlas SOLDIER3_IDLE_BLUE = new TextureAtlas(
                        Gdx.files.internal("Enemy3/Blue/Idle/Soldier.atlas"));

        /**
         * Dispose the constants in the game screen
         */
        public static void disposeConstants() {

                PLACE_HOLDER.dispose();
                ENDTURN_TEXT.dispose();
                SOLDIER1.dispose();
                SOLDIER3.dispose();
                SOLDIER1_IDLE_RED.dispose();
                SOLDIER3_IDLE_RED.dispose();
                SOLDIER1_IDLE_BLUE.dispose();
                SOLDIER3_IDLE_BLUE.dispose();

        }

}
