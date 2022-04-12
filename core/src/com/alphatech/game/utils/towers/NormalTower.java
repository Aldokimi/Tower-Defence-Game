package com.alphatech.game.utils.towers;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class NormalTower extends Tower {

    public NormalTower(ArrayList<Placeholder> placeholders) {
        super(placeholders);
    }

    public NormalTower(Texture texture, ArrayList<Placeholder> placeholders) {
        super(texture, placeholders);
    }
}