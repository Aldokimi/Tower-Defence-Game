package com.alphatech.game.utils.towers;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class CrazyTower extends Tower{
    public CrazyTower(ArrayList<Placeholder> placeholders) {
        super(placeholders);
    }

    public CrazyTower(Texture texture, ArrayList<Placeholder> placeholders) {
        super(texture, placeholders);
    }
}
