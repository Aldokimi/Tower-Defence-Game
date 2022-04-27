package com.alphatech.game.utils.units;

import java.awt.geom.Point2D;

public class NormalSoldier extends Unit {

    public NormalSoldier(Point2D.Float position) {
        super(position);
    }

    @Override
    public String getClassName() {
        return "NormalSoldier";
    }
}
