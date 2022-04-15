package com.alphatech.game.utils.towers;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class MagicTower extends Tower{
    public MagicTower(ArrayList<Placeholder> placeholders) {
        super(placeholders);
    }

    public MagicTower(Texture texture, ArrayList<Placeholder> placeholders) {
        super(texture, placeholders);
    }
}
