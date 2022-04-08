package com.alphatech.game.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Textures {

        // Main menu
        public static final Texture MAIN_MENU_START_TEXT = new Texture("Texts/StartGame.png");
        public static final Texture MAIN_MENU_LOAD_TEXT = new Texture("Texts/LoadGame.png");
        public static final Texture MAIN_MENU_SETTINGS_TEXT = new Texture("Texts/Settings.png");
        public static final Texture MAIN_MENU_EXIT_TEXT = new Texture("Texts/ExitGame.png");

        // Map
        public static final Texture PLACE_HOLDER = new Texture("GroundTiles/BuildGround.png");
        public static final Texture HIGHLIGHTED_PLACE_HOLDER = new Texture("GroundTiles/highlight.png");
        public static final Texture ENDTURN_TEXT = new Texture("Texts/EndTurn.png");

        // Timer Bar
        public static final Texture TIMER_BAR = new Texture("GUI/TimerBar.png");

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

        // Towers Buttons
        public static final Texture NORMAL_TOWER = new Texture("Towers/NormalTower.png");
        public static final Texture MULTI_ATTACK_TOWER = new Texture("Towers/MultiAttackTower.png");

        // Normal Towers
        public static final Texture RED_NORMAL_TOWER = new Texture("Towers/Red/NormalTower.png");
        public static final Texture BLUE_NORMAL_TOWER = new Texture("Towers/Blue/NormalTower.png");

        // Multi-attack Towers
        public static final Texture RED_MULTI_ATTACK_TOWER = new Texture("Towers/Red/MultiAttackTower.png");
        public static final Texture BLUE_MULTI_ATTACK_TOWER = new Texture("Towers/Blue/MultiAttackTower.png");

        /**
         * Dispose the constants in the game screen
         */
        public static void disposeConstants() {

                MAIN_MENU_START_TEXT.dispose();
                MAIN_MENU_LOAD_TEXT.dispose();
                MAIN_MENU_SETTINGS_TEXT.dispose();
                MAIN_MENU_EXIT_TEXT.dispose();

                PLACE_HOLDER.dispose();
                HIGHLIGHTED_PLACE_HOLDER.dispose();
                ENDTURN_TEXT.dispose();
                TIMER_BAR.dispose();

                SOLDIER1.dispose();
                SOLDIER3.dispose();
                SOLDIER1_IDLE_RED.dispose();
                SOLDIER3_IDLE_RED.dispose();
                SOLDIER1_IDLE_BLUE.dispose();
                SOLDIER3_IDLE_BLUE.dispose();

                NORMAL_TOWER.dispose();
                MULTI_ATTACK_TOWER.dispose();
                RED_NORMAL_TOWER.dispose();
                BLUE_NORMAL_TOWER.dispose();
                RED_NORMAL_TOWER.dispose();
                BLUE_MULTI_ATTACK_TOWER.dispose();
        }

}
