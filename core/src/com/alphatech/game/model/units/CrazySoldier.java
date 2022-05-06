package com.alphatech.game.model.units;

import java.awt.geom.Point2D;

// Unit that moves through obstacles 
public class CrazySoldier extends Unit {
    public CrazySoldier(Point2D.Float position) {
        super(position);
    }

    @Override
    public String getClassName() {
        return "CrazySoldier";
    }
}
