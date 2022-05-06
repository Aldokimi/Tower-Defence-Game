package com.alphatech.game.model.towers;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class MagicTower extends Tower {
    public MagicTower(ArrayList<Placeholder> placeholders) {
        super(placeholders);
    }

    public MagicTower(Texture texture, ArrayList<Placeholder> placeholders, String parentName) {
        super(texture, placeholders, parentName);
    }

    @Override
    public String getClassName() {
        return "MagicTower";
    }
}
