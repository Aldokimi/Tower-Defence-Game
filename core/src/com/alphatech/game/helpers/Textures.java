package com.alphatech.game.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Textures {

        // Main menu
        public static final Texture MAIN_MENU_START_TEXT = new Texture("Texts/StartGame.png");
        public static final Texture MAIN_MENU_LOAD_TEXT = new Texture("Texts/LoadGame.png");
        public static final Texture MAIN_MENU_INSTRUCTIONS_TEXT = new Texture("Texts/Instructions.png");
        public static final Texture MAIN_MENU_EXIT_TEXT = new Texture("Texts/ExitGame.png");

        // Instructions/GameOver Screen Buttons
        public static final Texture INSTRUCTIONS_START_BUTTON = new Texture("Texts/play.png");
        public static final Texture INSTRUCTIONS_EXIT_BUTTON = new Texture("Texts/exit.png");
        public static final Texture INSTRUCTIONS_MAIN_MENU_BUTTON = new Texture("Texts/home.png");
        public static final Texture GAMEOVER_PLAY_AGAIN_BUTTON = new Texture("Texts/replay.png");

        // Map
        public static final Texture PLACE_HOLDER = new Texture("GroundTiles/BuildGround.png");
        public static final Texture HIGHLIGHTED_PLACE_HOLDER = new Texture("GroundTiles/highlight.png");
        public static final Texture RED_HIGHLIGHTED_PLACE_HOLDER = new Texture("GroundTiles/redPlaceholder.png");
        public static final Texture ENDTURN_TEXT = new Texture("Texts/EndTurn.png");
        public static final Texture OPTIONS = new Texture("GUI/options.png");
        public static final Texture SAVE = new Texture("GUI/save.png");
        public static final Texture GROUND = new Texture("GroundTiles/GrassGround.png");

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

        // Walking Red Soldiers
        public static final TextureAtlas SOLDIER1_WALKING_RED = new TextureAtlas(
                        Gdx.files.internal("Enemy1/Red/Walking/Soldier.atlas"));
        public static final TextureAtlas SOLDIER3_WALKING_RED = new TextureAtlas(
                        Gdx.files.internal("Enemy3/Red/Walking/Soldier.atlas"));

        // Walking Blue Soldiers
        public static final TextureAtlas SOLDIER1_WALKING_BLUE = new TextureAtlas(
                        Gdx.files.internal("Enemy1/Blue/Walking/Soldier.atlas"));
        public static final TextureAtlas SOLDIER3_WALKING_BLUE = new TextureAtlas(
                        Gdx.files.internal("Enemy3/Blue/Walking/Soldier.atlas"));

        // Attacking Red Soldiers
        public static final TextureAtlas SOLDIER1_ATTACKING_RED = new TextureAtlas(
                        Gdx.files.internal("Enemy1/Red/Attacking/Soldier.atlas"));
        public static final TextureAtlas SOLDIER3_ATTACKING_RED = new TextureAtlas(
                        Gdx.files.internal("Enemy3/Red/Attacking/Soldier.atlas"));

        // Attacking Blue Soldiers
        public static final TextureAtlas SOLDIER1_ATTACKING_BLUE = new TextureAtlas(
                        Gdx.files.internal("Enemy1/Blue/Attacking/Soldier.atlas"));
        public static final TextureAtlas SOLDIER3_ATTACKING_BLUE = new TextureAtlas(
                        Gdx.files.internal("Enemy3/Blue/Attacking/Soldier.atlas"));

        // Path Arrow
        public static final Texture PathArrowB = new Texture("GUI/pathArrowB.png");
        public static final Texture PathArrowR = new Texture("GUI/pathArrowR.png");

        // Towers Buttons
        public static final Texture NORMAL_TOWER = new Texture("Towers/NormalTower.png");
        public static final Texture MULTI_ATTACK_TOWER = new Texture("Towers/MultiAttackTower.png");
        public static final Texture MAGIC_TOWER = new Texture("Towers/MagicTower.png");

        // Normal Towers
        public static final Texture RED_NORMAL_TOWER = new Texture("Towers/Red/NormalTower.png");
        public static final Texture BLUE_NORMAL_TOWER = new Texture("Towers/Blue/NormalTower.png");

        // Multi-attack Towers
        public static final Texture RED_MULTI_ATTACK_TOWER = new Texture("Towers/Red/MultiAttackTower.png");
        public static final Texture BLUE_MULTI_ATTACK_TOWER = new Texture("Towers/Blue/MultiAttackTower.png");

        // Magic tower
        public static final Texture RED_MAGIC_TOWER = new Texture("Towers/MagicTower.png");
        public static final Texture BLUE_MAGIC_TOWER = new Texture("Towers/Blue/MagicTower.png");

        // Barracks
        public static final Texture RED_BARRACK = new Texture("Towers/Red/Knight Post Front.png");
        public static final Texture BLUE_BARRACK = new Texture("Towers/Blue/Knight Post Front.png");

        // Gold Mine
        public static final Texture GOLD_MINE = new Texture("Towers/GoldMine.png");
        public static final Texture RED_GOLD_MINE = new Texture("Towers/Red/GoldMine.png");
        public static final Texture BLUE_GOLD_MINE = new Texture("Towers/Blue/GoldMine.png");

        // Heath-bar
        public static final Texture HEALTH_BAR_FRAME = new Texture("GUI/healthbar_frame.png");
        public static final Texture HEALTH_BAR = new Texture("GUI/health_bar.png");
        public static final Texture CASTLE_HEALTH_BAR_FRAME = new Texture("GUI/Castle_Health_Frame.png");
        public static final Texture VERTICAL_HEALTH_BAR = new Texture("GUI/health_bar_Vertical.png");

        // Fire ball
        public static final Texture NORMAL_FIRE_BALL = new Texture("GUI/fireBall.png");
        public static final Texture MULTIATTACK_FIRE_BALL = new Texture("GUI/Rock 05.png");
        public static final Texture MAGIC_FIRE_BALL = new Texture("GUI/heart.png");

        // Treasure Chest
        public static final Texture TREASURE_CHEST = new Texture("GUI/treasureChest.png");

        /**
         * Dispose the constants in the game screen
         */
        public static void disposeConstants() {

                MAIN_MENU_START_TEXT.dispose();
                MAIN_MENU_LOAD_TEXT.dispose();
                MAIN_MENU_INSTRUCTIONS_TEXT.dispose();
                MAIN_MENU_EXIT_TEXT.dispose();

                INSTRUCTIONS_START_BUTTON.dispose();
                INSTRUCTIONS_EXIT_BUTTON.dispose();
                INSTRUCTIONS_MAIN_MENU_BUTTON.dispose();

                PLACE_HOLDER.dispose();
                HIGHLIGHTED_PLACE_HOLDER.dispose();
                RED_HIGHLIGHTED_PLACE_HOLDER.dispose();
                TREASURE_CHEST.dispose();
                ENDTURN_TEXT.dispose();
                TIMER_BAR.dispose();
                OPTIONS.dispose();
                SAVE.dispose();
                GROUND.dispose();
                GAMEOVER_PLAY_AGAIN_BUTTON.dispose();

                SOLDIER1.dispose();
                SOLDIER3.dispose();

                SOLDIER1_IDLE_RED.dispose();
                SOLDIER1_WALKING_RED.dispose();
                SOLDIER1_ATTACKING_RED.dispose();

                SOLDIER1_IDLE_BLUE.dispose();
                SOLDIER1_WALKING_BLUE.dispose();
                SOLDIER1_ATTACKING_BLUE.dispose();

                SOLDIER3_IDLE_RED.dispose();
                SOLDIER3_WALKING_RED.dispose();
                SOLDIER3_ATTACKING_RED.dispose();

                SOLDIER3_IDLE_BLUE.dispose();
                SOLDIER3_WALKING_BLUE.dispose();
                SOLDIER3_ATTACKING_BLUE.dispose();

                RED_BARRACK.dispose();
                BLUE_BARRACK.dispose();

                NORMAL_TOWER.dispose();
                MULTI_ATTACK_TOWER.dispose();
                MAGIC_TOWER.dispose();

                RED_NORMAL_TOWER.dispose();
                BLUE_NORMAL_TOWER.dispose();
                RED_MULTI_ATTACK_TOWER.dispose();
                BLUE_MULTI_ATTACK_TOWER.dispose();
                RED_MAGIC_TOWER.dispose();
                BLUE_MAGIC_TOWER.dispose();

                GOLD_MINE.dispose();
                RED_GOLD_MINE.dispose();
                BLUE_GOLD_MINE.dispose();

                HEALTH_BAR_FRAME.dispose();
                HEALTH_BAR.dispose();
                CASTLE_HEALTH_BAR_FRAME.dispose();
                VERTICAL_HEALTH_BAR.dispose();

                NORMAL_FIRE_BALL.dispose();
                MULTIATTACK_FIRE_BALL.dispose();
                MAGIC_FIRE_BALL.dispose();

        }

}
