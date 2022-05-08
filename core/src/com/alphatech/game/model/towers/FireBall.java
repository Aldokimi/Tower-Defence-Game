package com.alphatech.game.model.towers;

import com.alphatech.game.helpers.Constants;
import com.alphatech.game.helpers.Textures;
import com.badlogic.gdx.graphics.Texture;

import java.awt.geom.Point2D;

public class FireBall {
    private Point2D.Float position;
    private Point2D.Float target;
    private float fireRate;
    private Texture texture;
    private String towerType;

    public FireBall(Point2D.Float position, Point2D.Float target, float fireRate, String towerType) {
        this.position = position;
        this.target = target;
        this.fireRate = fireRate;
        this.towerType = towerType;
        setFireBall(towerType);
    }

    ;

    /**
     * Sets the texture for a fire ball based on the tower type.
     *
     * @param towerType
     */
    private void setFireBall(String towerType) {
        switch (towerType) {
            case Constants.NORMAL_TOWER:
                this.texture = Textures.NORMAL_FIRE_BALL;
                break;

            case Constants.MULTIATTACK_TOWER:
                this.texture = Textures.MULTIATTACK_FIRE_BALL;
                break;

            case Constants.MAGIC_TOWER:
                this.texture = Textures.MAGIC_FIRE_BALL;
                break;

            default:
                break;
        }
    }

    public Point2D.Float getPosition() {
        return this.position;
    }

    public void setPosition(Point2D.Float position) {
        this.position = position;
    }

    public Texture getTexture() {
        return this.texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Point2D.Float getTarget() {
        return this.target;
    }

    public void setTarget(Point2D.Float target) {
        this.target = target;
    }

    public float getFireRate() {
        return this.fireRate;
    }

    public void setFireRate(float fireRate) {
        this.fireRate = fireRate;
    }

    public String getTowerType() {
        return this.towerType;
    }

    /**
     * Update the position of the fire ball so it moves towards a target.
     */
    public void moveToTarget() {

        double targetX = this.target.getX();
        double targetY = this.target.getY();
        double ballX = this.position.getX();
        double ballY = this.position.getY();

        double destX = targetX - ballX;
        double destY = targetY - ballY;

        double dist = Math.sqrt(destX * destX + destY * destY);
        destX = destX / dist;
        destY = destY / dist;

        double travelX = destX * this.fireRate;
        double travelY = destY * this.fireRate;

        double distTravel = Math.sqrt(travelX * travelX + travelY * travelY);

        if (distTravel > dist) {
            ballX = destX;
            ballY = destY;
        } else {
            ballX += travelX;
            ballY += travelY;
        }

        this.position = new Point2D.Float((float) ballX, (float) ballY);
    }

    /**
     * Resets the position and the target of the fire ball.
     *
     * @param position
     * @param target
     */
    public void reset(Point2D.Float position, Point2D.Float target) {
        this.position = position;
        this.target = target;
    }

}
