package com.alphatech.game.utils.towers;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class MultiAttackTower extends Tower {

    public MultiAttackTower(ArrayList<Placeholder> placeholders) {
        super(placeholders);
    }

    public MultiAttackTower(Texture texture, ArrayList<Placeholder> placeholders, String parentName) {
        super(texture, placeholders, parentName);
    }

    @Override
    public String getClassName() {
        return "MultiAttackTower";
    }
}
