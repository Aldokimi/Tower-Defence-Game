package com.alphatech.game.utils.towers;

import com.alphatech.game.helpers.Constants;
import com.alphatech.game.helpers.Textures;
import com.alphatech.game.utils.Player;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class TowerSettings {
    // Placeholders
    private Sprite placeHolderSprite;
    private static ArrayList<Placeholder> placeHolders;
    private ArrayList<Placeholder> placeHoldersNearBlueCastle;
    private ArrayList<Placeholder> placeHoldersNearRedCastle;

    // Player's Gold
    private BitmapFont playerGold;
    private int redPlayerGoldCounter = Constants.INIT_GOLD_COUNT;
    private int bluePlayerGoldCounter = Constants.INIT_GOLD_COUNT;

    // Prices
    private BitmapFont prices;
    private int normalTowerPrice = Constants.BUILD_NORMAL_TOWER;
    private int multiAttackTowerPrice = Constants.BUILD_MULTIATTACK_TOWER;
    private int crazyTowerPrice = Constants.BUILD_CRAZY_TOWER;
    private int goldMinePrice = Constants.BUILD_GOLDMINE;
    private int normalSoldierPrice = Constants.TRAIN_NORMAL_SOLDIER;
    private int crazySoldierPrice = Constants.TRAIN_CRAZY_SOLDIER;

    // barracks
    private ArrayList<Placeholder> barrackPlaceholders;

    // Towers
    private boolean isHighlighted;
    private ArrayList<Tower> towers;

    // Normal
    private ImageButton normalTowerButton;
    private TextureRegion normalTowerRegion;
    private TextureRegionDrawable normalTowerRegionDrawable;
    private Group normalTowerHighlights;

    // Multi attack
    private ImageButton multiAttackTowerButton;
    private TextureRegion multiAttackTowerRegion;
    private TextureRegionDrawable multiAttackTowerRegionDrawable;
    private Group MultiAttackTowerHighlights;

    // Magic
    private ImageButton magicTowerButton;
    private TextureRegion magicTowerRegion;
    private TextureRegionDrawable magicTowerRegionDrawable;
    private Group magicTowerHighlights;

    // Gold mines
    private ImageButton goldMineButton;
    private TextureRegion goldMineRegion;
    private TextureRegionDrawable goldMineRegionDrawable;
    private Group goldMineHighlights;
    private ArrayList<GoldMine> goldMines;

    public TowerSettings(final Player bluePlayer, final Player redPlayer, final Stage gameScreenButtons) {

        // Place-holders points for buildings
        placeHolders = new ArrayList<>();
        placeHoldersNearBlueCastle = new ArrayList<>(Arrays.asList(
                new Placeholder(24, 23),
                new Placeholder(25, 22),
                new Placeholder(12, 21),
                new Placeholder(11, 19),
                new Placeholder(28, 17),
                new Placeholder(25, 17),
                new Placeholder(23, 17),
                new Placeholder(18, 17),
                new Placeholder(29, 16),
                new Placeholder(19, 15),
                new Placeholder(17, 15),
                new Placeholder(13, 15),
                new Placeholder(2, 10),
                new Placeholder(1, 8),
                new Placeholder(6, 10),
                new Placeholder(5, 8)));
        placeHoldersNearRedCastle = new ArrayList<>(Arrays.asList(
                new Placeholder(19, 15),
                new Placeholder(17, 15),
                new Placeholder(13, 15),
                new Placeholder(6, 10),
                new Placeholder(5, 8),
                new Placeholder(6, 5),
                new Placeholder(16, 5),
                new Placeholder(17, 7),
                new Placeholder(18, 6),
                new Placeholder(21, 8),
                new Placeholder(20, 6),
                new Placeholder(28, 17),
                new Placeholder(25, 17),
                new Placeholder(23, 17),
                new Placeholder(23, 10)));
        fillPlaceHolders(); // Filling the placeholders once
        placeHolderSprite = new Sprite(Textures.PLACE_HOLDER);

        // barracks
        barrackPlaceholders = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            if (i >= 2) {

                Placeholder placeholderToAdd = placeHoldersNearRedCastle
                        .get(new Random().nextInt(placeHoldersNearRedCastle.size()));
                while (barrackPlaceholders.contains(placeholderToAdd)) {
                    placeholderToAdd = placeHoldersNearRedCastle
                            .get(new Random().nextInt(placeHoldersNearRedCastle.size()));
                }
                barrackPlaceholders.add(placeholderToAdd);
            } else {
                Placeholder placeholderToAdd = placeHoldersNearBlueCastle
                        .get(new Random().nextInt(placeHoldersNearBlueCastle.size()));
                while (barrackPlaceholders.contains(placeholderToAdd)) {
                    placeholderToAdd = placeHoldersNearBlueCastle
                            .get(new Random().nextInt(placeHoldersNearBlueCastle.size()));
                }
                barrackPlaceholders.add(placeholderToAdd);
            }
        }

        // Gold and prices
        playerGold = new BitmapFont();
        prices = new BitmapFont();

        // Towers -- Normal
        normalTowerRegion = new TextureRegion(Textures.NORMAL_TOWER);
        normalTowerRegionDrawable = new TextureRegionDrawable(normalTowerRegion);
        normalTowerButton = new ImageButton(normalTowerRegionDrawable);
        normalTowerButton.setSize(Constants.UNIT_SIZE.x * 2, (float) (Constants.UNIT_SIZE.y * 2.3));
        normalTowerButton.setPosition(Constants.UNIT_SIZE.x * 3, (Constants.UNIT_SIZE.y - 11));

        bluePlayer.normalTower = new NormalTower(Textures.BLUE_NORMAL_TOWER, placeHolders);
        redPlayer.normalTower = new NormalTower(Textures.RED_NORMAL_TOWER, placeHolders);

        // Towers -- Multi-Attack
        multiAttackTowerRegion = new TextureRegion(Textures.MULTI_ATTACK_TOWER);
        multiAttackTowerRegionDrawable = new TextureRegionDrawable(multiAttackTowerRegion);
        multiAttackTowerButton = new ImageButton(multiAttackTowerRegionDrawable);
        multiAttackTowerButton.setSize((float) (Constants.UNIT_SIZE.x * 1.4), (float) (Constants.UNIT_SIZE.y * 2.3));
        multiAttackTowerButton.setPosition((float) (Constants.UNIT_SIZE.x * 4.9), (Constants.UNIT_SIZE.y - 14));

        bluePlayer.multiAttackTower = new MultiAttackTower(Textures.BLUE_MULTI_ATTACK_TOWER, placeHolders);
        redPlayer.multiAttackTower = new MultiAttackTower(Textures.RED_MULTI_ATTACK_TOWER, placeHolders);

        // Towers -- Magic
        magicTowerRegion = new TextureRegion(Textures.MAGIC_TOWER);
        magicTowerRegionDrawable = new TextureRegionDrawable(magicTowerRegion);
        magicTowerButton = new ImageButton(magicTowerRegionDrawable);
        magicTowerButton.setSize(Constants.UNIT_SIZE.x * 1.9f, (float) (Constants.UNIT_SIZE.y * 1.8));
        magicTowerButton.setPosition(376, 38);

        bluePlayer.magicTower = new MagicTower(Textures.BLUE_MAGIC_TOWER, placeHolders);
        redPlayer.magicTower = new MagicTower(Textures.RED_MAGIC_TOWER, placeHolders);

        // Prepare all towers for rendering
        towers = new ArrayList<>(
                Arrays.asList(bluePlayer.normalTower, redPlayer.normalTower, bluePlayer.multiAttackTower,
                        redPlayer.multiAttackTower, bluePlayer.magicTower, redPlayer.magicTower));

        // Initializing the center which we will measure from.
        bluePlayer.normalTower.initializeCenterofMeasurement(new Placeholder(2, 20));
        bluePlayer.multiAttackTower.initializeCenterofMeasurement(new Placeholder(2, 20));
        bluePlayer.magicTower.initializeCenterofMeasurement(new Placeholder(2, 20));

        redPlayer.normalTower.initializeCenterofMeasurement(new Placeholder(27, 6));
        redPlayer.multiAttackTower.initializeCenterofMeasurement(new Placeholder(27, 6));
        redPlayer.magicTower.initializeCenterofMeasurement(new Placeholder(27, 6));

        // Tower's buttons listeners
        normalTowerButton.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                normalTowerHighlights = new Group();
                buildTowers(bluePlayer, redPlayer, bluePlayer.normalTower, redPlayer.normalTower, gameScreenButtons,
                        normalTowerHighlights);
            }
        });

        multiAttackTowerButton.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                MultiAttackTowerHighlights = new Group();
                buildTowers(bluePlayer, redPlayer, bluePlayer.multiAttackTower, redPlayer.multiAttackTower,
                        gameScreenButtons,
                        MultiAttackTowerHighlights);
            }
        });

        magicTowerButton.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                magicTowerHighlights = new Group();
                buildTowers(bluePlayer, redPlayer, bluePlayer.magicTower, redPlayer.magicTower, gameScreenButtons,
                        magicTowerHighlights);
            }
        });

        // Gold Mines
        goldMineRegion = new TextureRegion(Textures.GOLD_MINE);
        goldMineRegionDrawable = new TextureRegionDrawable(goldMineRegion);
        goldMineButton = new ImageButton(goldMineRegionDrawable);
        goldMineButton.setSize(110, 90);
        goldMineButton.setPosition(473, 38);

        bluePlayer.goldMine = new GoldMine(Textures.BLUE_GOLD_MINE, placeHolders);
        redPlayer.goldMine = new GoldMine(Textures.RED_GOLD_MINE, placeHolders);
        goldMines = new ArrayList<>(Arrays.asList(bluePlayer.goldMine, redPlayer.goldMine));
        // Gold Mine's button listener
        goldMineButton.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                goldMineHighlights = new Group();
                goldMineHighlights.setName("highlight");
                if (!isHighlighted) {
                    if (bluePlayer.getTurn() && bluePlayer.hasEnoughGold(Constants.BUILD_GOLDMINE)) {
                        bluePlayer.goldMine.build();

                        for (Placeholder p : bluePlayer.goldMine.getAvailablePlaces()) {
                            if (p.isFreePlace() && !getPlaceholdersNearCastle(redPlayer).contains(p))
                                goldMineHighlights.addActor(highlightPlaceforGoldMine(bluePlayer, redPlayer, p,
                                        bluePlayer.goldMine, gameScreenButtons));
                        }
                    } else if (redPlayer.getTurn() && redPlayer.hasEnoughGold(Constants.BUILD_GOLDMINE)) {
                        // Measuring from all directions
                        redPlayer.goldMine.build();

                        for (Placeholder p : redPlayer.goldMine.getAvailablePlaces()) {
                            if (p.isFreePlace() && !getPlaceholdersNearCastle(redPlayer).contains(p))
                                goldMineHighlights.addActor(highlightPlaceforGoldMine(bluePlayer, redPlayer, p,
                                        redPlayer.goldMine, gameScreenButtons));
                        }
                    }
                    isHighlighted = true;
                    gameScreenButtons.addActor(goldMineHighlights);
                } else {
                    removeHighlight(gameScreenButtons);
                }
            }
        });
    }

    // Getters & Setters
    public void setRedPlayerGoldCounter(int redPlayerGoldCounter) {
        this.redPlayerGoldCounter = redPlayerGoldCounter;
    }

    public void setBluePlayerGoldCounter(int bluePlayerGoldCounter) {
        this.bluePlayerGoldCounter = bluePlayerGoldCounter;
    }

    public int getRedPlayerGoldCounter() {
        return redPlayerGoldCounter;
    }

    public int getBluePlayerGoldCounter() {
        return bluePlayerGoldCounter;
    }

    /**
     * Building towers according the player turn.
     *
     * @param blueTower
     * @param redTower
     * @param highlights
     */
    private void buildTowers(Player bluePlayer, Player redPlayer, Tower blueTower, Tower redTower,
            Stage gameScreenButtons, Group highlights) {
        highlights.setName("highlight");
        if (!isHighlighted) {// Checking if the button has been clicked (double click gives the same state)

            if (bluePlayer.getTurn()) {
                buildBlueTowers(bluePlayer, redPlayer, blueTower, highlights, gameScreenButtons);
            } else {
                buildRedTowers(bluePlayer, redPlayer, redTower, highlights, gameScreenButtons);
            }
            isHighlighted = true;
            gameScreenButtons.addActor(highlights);
        } else {
            removeHighlight(gameScreenButtons);
        }
    }

    /**
     * Builds the blue towers when it is the red player turn.
     * We go over each tower available places then we highlight the placeholder
     * according to the given tower.
     * Then we highlight the given places and the player can build on the
     * highlighted places.
     *
     * @param tower
     * @param highlights
     */
    private void buildBlueTowers(Player bluePlayer, Player redPlayer, Tower tower, Group highlights,
            Stage gameScreenButtons) {
        bluePlayer.multiAttackTower.build();
        bluePlayer.normalTower.build();
        bluePlayer.magicTower.build();

        for (Placeholder placeHolder : bluePlayer.magicTower.getAvailablePlaces()) {
            if (placeHolder.isFreePlace() && !barrackPlaceholders.contains(placeHolder))
                highlights.addActor(highlightPlace(bluePlayer, redPlayer, placeHolder, tower, gameScreenButtons));
        }
        for (Placeholder placeHolder : bluePlayer.multiAttackTower.getAvailablePlaces()) {
            if (placeHolder.isFreePlace() && !barrackPlaceholders.contains(placeHolder))
                highlights.addActor(highlightPlace(bluePlayer, redPlayer, placeHolder, tower, gameScreenButtons));
        }
        for (Placeholder placeHolder : bluePlayer.normalTower.getAvailablePlaces()) {
            if (placeHolder.isFreePlace() && !barrackPlaceholders.contains(placeHolder))
                highlights.addActor(highlightPlace(bluePlayer, redPlayer, placeHolder, tower, gameScreenButtons));
        }

    }

    /**
     * Builds the red towers when it is the red player turn.
     * We go over each tower available places then we highlight the placeholder
     * according to the given tower.
     * Then we highlight the given places and the player can build on the
     * highlighted places.
     *
     * @param tower
     * @param highlights
     */
    private void buildRedTowers(Player bluePlayer, Player redPlayer, Tower tower, Group highlights,
            Stage gameScreenButtons) {
        redPlayer.multiAttackTower.build();
        redPlayer.normalTower.build();
        redPlayer.magicTower.build();

        for (Placeholder placeHolder : redPlayer.magicTower.getAvailablePlaces()) {
            if (placeHolder.isFreePlace() && !barrackPlaceholders.contains(placeHolder))
                highlights.addActor(highlightPlace(bluePlayer, redPlayer, placeHolder, tower, gameScreenButtons));
        }
        for (Placeholder placeHolder : redPlayer.multiAttackTower.getAvailablePlaces()) {
            if (placeHolder.isFreePlace() && !barrackPlaceholders.contains(placeHolder))
                highlights.addActor(highlightPlace(bluePlayer, redPlayer, placeHolder, tower, gameScreenButtons));
        }
        for (Placeholder placeHolder : redPlayer.normalTower.getAvailablePlaces()) {
            if (placeHolder.isFreePlace() && !barrackPlaceholders.contains(placeHolder))
                highlights.addActor(highlightPlace(bluePlayer, redPlayer, placeHolder, tower, gameScreenButtons));
        }
    }

    /**
     * highlight the avaliable (to build on) place holder.
     * 
     * @param placeholder Place holder
     * @param type        tower class to add the new build tower to it if the
     *                    highlight is clicked.
     * @return ImageButton, the highlighted placeholder
     */
    private ImageButton highlightPlace(final Player bluePlayer, final Player redPlayer, final Placeholder placeholder,
            final Tower type, final Stage gameScreenButtons) {

        TextureRegion rgn = new TextureRegion(Textures.HIGHLIGHTED_PLACE_HOLDER);
        if (getPlaceholdersNearCastle(redPlayer).contains(placeholder))
            rgn = new TextureRegion(Textures.RED_HIGHLIGHTED_PLACE_HOLDER);
        TextureRegionDrawable rgndrbl = new TextureRegionDrawable(rgn);
        ImageButton btn = new ImageButton(rgndrbl);
        btn.setSize(Constants.PLACEHOLDER_SIZE, Constants.PLACEHOLDER_SIZE);
        btn.setPosition(Constants.PLACEHOLDER_SIZE * (placeholder.getX()),
                Constants.PLACEHOLDER_SIZE * (placeholder.getY()));

        btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int price;
                if (type instanceof NormalTower) {
                    price = Constants.BUILD_NORMAL_TOWER;
                } else {
                    price = Constants.BUILD_MULTIATTACK_TOWER;
                }
                if (bluePlayer.getTurn() && bluePlayer.hasEnoughGold(price)) {
                    if (!getPlaceholdersNearCastle(redPlayer).contains(placeholder)) {
                        type.addTower(placeholder);
                        bluePlayer.buildTower(type);

                        type.addTower(placeholder);

                        placeholder.takePlace();

                        type.releaseAvailablePlaces();

                        bluePlayerGoldCounter = bluePlayer.getGold();
                    }
                } else if (redPlayer.getTurn() && redPlayer.hasEnoughGold(price)) {
                    if (!getPlaceholdersNearCastle(redPlayer).contains(placeholder)) {
                        type.addTower(placeholder);
                        redPlayer.buildTower(type);

                        type.addTower(placeholder);

                        placeholder.takePlace();

                        type.releaseAvailablePlaces();

                        redPlayerGoldCounter = redPlayer.getGold();
                    }
                }
                removeHighlight(gameScreenButtons);
            }
        });
        return btn;
    }

    /**
     * Removes the highlight from the place holders
     */
    public void removeHighlight(Stage gameScreenButtons) {
        for (Actor actor : gameScreenButtons.getActors()) {
            if (actor.getName() == "highlight") {
                actor.remove();
            }
        }
        isHighlighted = false;
    }

    /**
     * Get the placeholder near the opposite castle of each player to prevent
     * building on them.
     *
     * @return array of place holders.
     */
    private ArrayList<Placeholder> getPlaceholdersNearCastle(Player redPlayer) {
        if (redPlayer.getTurn())
            return new ArrayList<>(Arrays.asList(
                    new Placeholder(0, 18),
                    new Placeholder(2, 18),
                    new Placeholder(4, 18),
                    new Placeholder(6, 19),
                    new Placeholder(6, 21),
                    new Placeholder(5, 23)));

        return new ArrayList<>(Arrays.asList(
                new Placeholder(23, 6),
                new Placeholder(25, 8),
                new Placeholder(27, 8),
                new Placeholder(29, 8)));
    }

    /**
     * Filling the place holders on the map.
     * place holders are represented as the Cartesian (Euclidean) Plane R2
     */
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
                        || (y == 10 && (x == 8 || x == 10)) || (y == 8 && (x == 1 || x == 5))
                        || (y == 5 && x == 6) ||
                        // near red castle
                        (y == 8 && (x == 21 || x == 23)) || (y == 4 && x == 21)
                        || (y == 6 && x == 22) || (x == 23 && y == 6)
                        || (y == 10 && (x == 23 || x == 25)) || (y == 11 && (x == 29 || x == 27)) ||
                        (y == 6 && x == 20) || (y == 12 && (x == 14 || x == 10 || x == 8 || x == 23))
                        || (y == 13 && (x == 27 || x == 29)) ||
                        ((y == 4 || y == 6) && x == 18) || (y == 8 && (x == 16 || x == 25 || x == 27 || x == 29))
                        || (y == 15 && (x == 23 || x == 17)) || (y == 16 && x == 29) ||
                        (y == 10 && x == 20) || (y == 17 && x == 28) || (y == 5 && x == 16) || (x == 19 && y == 15) ||
                        (y == 5 && (x == 8 || x == 13 || x == 11)) || (y == 8 && (x == 3 || x == 14))
                        || (y == 13 && (x == 12 || x == 15 || x == 17)) ||
                        (y == 17 && (x == 18 || x == 21 || x == 23)) || ((y == 22 || y == 17) && x == 25)
                        || ((x == 7 || x == 13 || x == 17) && y == 7)) {
                    placeHolders.add(new Placeholder(x, y));
                }
            }
        }
    }

    /**
     * Highlight a placeholder of the map so we can build a gold mine on it.
     *
     * @param placeholder Place holder
     * @param goldMine    if the highlighted placeholder is clicked we build into
     *                    it.
     * @return ImageButton, the highlighted placeholder
     */
    private ImageButton highlightPlaceforGoldMine(final Player bluePlayer, final Player redPlayer,
            final Placeholder placeholder, final GoldMine goldMine,
            final Stage gameScreenButtons) {
        TextureRegion rgn = new TextureRegion(Textures.HIGHLIGHTED_PLACE_HOLDER);
        TextureRegionDrawable rgndrbl = new TextureRegionDrawable(rgn);
        ImageButton btn = new ImageButton(rgndrbl);
        btn.setSize(Constants.PLACEHOLDER_SIZE, Constants.PLACEHOLDER_SIZE);
        btn.setPosition(Constants.PLACEHOLDER_SIZE * (placeholder.getX()),
                Constants.PLACEHOLDER_SIZE * (placeholder.getY()));

        btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                goldMine.addPlaceholder(placeholder);
                placeholder.takePlace();
                goldMine.releaseAvailablePlaces();

                removeHighlight(gameScreenButtons);

                if (bluePlayer.getTurn() && bluePlayer.hasEnoughGold(Constants.BUILD_GOLDMINE)) {
                    bluePlayer.buildGoldMine();
                    bluePlayerGoldCounter = bluePlayer.getGold();
                } else if (redPlayer.hasEnoughGold(Constants.BUILD_GOLDMINE)) {
                    redPlayer.buildGoldMine();
                    redPlayerGoldCounter = redPlayer.getGold();
                }
            }
        });
        return btn;
    }

    /**
     * Add the towers buttons/cards to the game screen
     * 
     * @param gameScreenButtons
     */
    public void setTowersAsActors(Stage gameScreenButtons) {

        gameScreenButtons.addActor(normalTowerButton);
        gameScreenButtons.addActor(multiAttackTowerButton);
        gameScreenButtons.addActor(magicTowerButton);
        gameScreenButtons.addActor(goldMineButton);
    }

    /**
     * Render place holders in the gamescreen
     * 
     * @param batch
     */
    public void renderPlaceHolders(SpriteBatch batch) {
        for (Placeholder placeHolder : placeHolders) {
            placeHolderSprite.setPosition(placeHolder.getX() *
                    Constants.PLACEHOLDER_SIZE,
                    placeHolder.getY() * Constants.PLACEHOLDER_SIZE);
            placeHolderSprite.setSize(Constants.PLACEHOLDER_SIZE,
                    Constants.PLACEHOLDER_SIZE);
            placeHolderSprite.draw(batch);
        }
    }

    /**
     * Render Players' gold and goldmines in the gamescreen
     * 
     * @param batch
     */
    public void renderPlayersGoldAndGoldMines(SpriteBatch batch) {
        playerGold.setColor(Color.YELLOW);
        playerGold.draw(batch, String.valueOf(redPlayerGoldCounter), 702, 843);
        playerGold.draw(batch, String.valueOf(bluePlayerGoldCounter), 220, 843);

        // Rendering prices
        prices.setColor(Color.YELLOW);
        prices.draw(batch, String.valueOf(normalTowerPrice), 230, 22);
        prices.draw(batch, String.valueOf(multiAttackTowerPrice), 325, 22);
        prices.draw(batch, String.valueOf(crazyTowerPrice), 424, 22);
        prices.draw(batch, String.valueOf(goldMinePrice), 520, 22);
        prices.draw(batch, String.valueOf(normalSoldierPrice), 617, 22);
        prices.draw(batch, String.valueOf(crazySoldierPrice), 714, 22);
    }

    /**
     * Render gold-mines in the gamescreen
     * 
     * @param batch
     */
    public void renderGoldMines(SpriteBatch batch) {
        for (GoldMine goldMine : goldMines) {
            Sprite goldMineSprite = new Sprite(goldMine.getGoldMineTexture());
            for (int i = 0; i < goldMine.getTakenPlaces().size(); i++) {
                goldMineSprite.setPosition(
                        (float) (goldMine.getTakenPlaces().get(i).getX() * Constants.PLACEHOLDER_SIZE + 7
                                - Constants.UNIT_SIZE.x * 0.30),
                        goldMine.getTakenPlaces().get(i).getY() * Constants.PLACEHOLDER_SIZE - 2);
                goldMineSprite.setSize(55, 58);
                goldMineSprite.draw(batch);
            }
        }
    }

    /**
     * Render barracks in the gamescreen
     * 
     * @param batch
     */
    public void renderBarracks(SpriteBatch batch) {
        Sprite sprite = new Sprite(Textures.BLUE_BARRACK);
        for (int i = 0; i < barrackPlaceholders.size(); ++i) {
            if (i == barrackPlaceholders.size() / 2)
                sprite = new Sprite(Textures.RED_BARRACK);
            final int x = Constants.PLACEHOLDER_SIZE + Constants.PLACEHOLDER_SIZE / 3;
            final int y = Constants.PLACEHOLDER_SIZE + Constants.PLACEHOLDER_SIZE - Constants.PLACEHOLDER_SIZE / 3;
            sprite.setPosition(barrackPlaceholders.get(i).getX() * Constants.PLACEHOLDER_SIZE - 5,
                    barrackPlaceholders.get(i).getY() * Constants.PLACEHOLDER_SIZE - 9);
            sprite.setSize(x, y);
            sprite.draw(batch);
        }
    }

    /**
     * Render Towers in the gamescreen
     * 
     * @param batch
     */
    public void renderTowers(SpriteBatch batch) {
        for (Tower tower : towers) {
            Sprite towerSprite = new Sprite(tower.getTowerTexture());
            for (int i = 1; i < tower.getTakenPlaces().size(); i++) {
                // Checking if the tower is a magic tower to set different dimentions for it
                if (tower.getTowerTexture() == Textures.RED_MAGIC_TOWER
                        || tower.getTowerTexture() == Textures.BLUE_MAGIC_TOWER) {
                    towerSprite.setPosition(
                            (float) (tower.getTakenPlaces().get(i).getX() * 32 - 1),
                            tower.getTakenPlaces().get(i).getY() * Constants.PLACEHOLDER_SIZE);
                    towerSprite.setSize(35, 58);
                } else {
                    towerSprite.setPosition(
                            (float) (tower.getTakenPlaces().get(i).getX() * Constants.PLACEHOLDER_SIZE + 3
                                    - Constants.UNIT_SIZE.x * 0.30),
                            tower.getTakenPlaces().get(i).getY() * Constants.PLACEHOLDER_SIZE - 2);
                    towerSprite.setSize(61, 63);
                }
                towerSprite.draw(batch);
            }

        }
    }

}
