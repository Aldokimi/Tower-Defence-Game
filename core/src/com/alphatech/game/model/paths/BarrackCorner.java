package com.alphatech.game.model.paths;

import com.alphatech.game.helpers.Constants;

import java.awt.geom.Point2D;

public class BarrackCorner {

    private Constants.PathNum path;
    private Point2D.Float point ;
    private int nextLevel;

    public BarrackCorner(Constants.PathNum path, Point2D.Float point, int nextLevel) {
        this.path = path;
        this.point = point;
        this.nextLevel = nextLevel;
    }

    public int getNextLevel() {
        return nextLevel;
    }

    public void setNextLevel(int nextLevel) {
        this.nextLevel = nextLevel;
    }

    public Constants.PathNum getPath() {
        return path;
    }

    public void setPath(Constants.PathNum path) {
        this.path = path;
    }

    public Point2D.Float getPoint() {
        return point;
    }

    public void setPoint(Point2D.Float point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return "BarrackCorner{" +
                "path=" + path +
                ", point=" + point +
                ", nextLevel=" + nextLevel +
                '}';
    }
}
