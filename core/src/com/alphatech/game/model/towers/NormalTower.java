package com.alphatech.game.model.towers;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class NormalTower extends Tower {

    public NormalTower(ArrayList<Placeholder> placeholders) {
        super(placeholders);
    }

    public NormalTower(Texture texture, ArrayList<Placeholder> placeholders, String parentName) {
        super(texture, placeholders, parentName);
    }

    @Override
    public String getClassName() {
        return "NormalTower";
    }
}
