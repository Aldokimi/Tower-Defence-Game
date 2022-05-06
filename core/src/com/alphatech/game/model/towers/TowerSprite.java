package com.alphatech.game.model.towers;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import com.alphatech.game.helpers.Constants;
import com.alphatech.game.model.units.Unit;

import java.awt.geom.Point2D;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TowerSprite {
    private CopyOnWriteArrayList<Unit> targets;
    private CopyOnWriteArrayList<FireBall> fires;
    private int updateRate;
    private Placeholder position;
    private String towerType;

    // Credentials
    private int healthToBeDecreased;
    private float fireRate;
    private double fireRange;
    private boolean multiAttack;

    public TowerSprite(Placeholder position, String towerType) {
        this.position = position;
        this.towerType = towerType;
        this.targets = new CopyOnWriteArrayList<>();
        this.fires = new CopyOnWriteArrayList<>();
        setCredentials();
        updateRate = 0;
    }

    /**
     * Sets the credentials of a tower sprite based on the tower type.
     * 
     */
    private void setCredentials() {
        switch (this.towerType) {
            case Constants.NORMAL_TOWER:
                this.healthToBeDecreased = 10;
                this.fireRate = 2.0f;
                this.multiAttack = false;
                this.fireRange = 4.0;
                break;

            case Constants.MULTIATTACK_TOWER:
                this.healthToBeDecreased = 15;
                this.fireRate = 1.8f;
                this.multiAttack = true;
                this.fireRange = 4.0;
                break;

            case Constants.MAGIC_TOWER:
                this.healthToBeDecreased = 10;
                this.fireRate = 2.8f;
                this.multiAttack = false;
                this.fireRange = 5.0;
                break;

            default:
                break;
        }

    }

    public void setTargets(CopyOnWriteArrayList<Unit> targets) {
        this.targets = targets;
    }

    public void setUpdateRate(int updateRate) {
        this.updateRate = updateRate;
    }

    public void setFires(CopyOnWriteArrayList<FireBall> fires) {
        this.fires = fires;
    }

    public Placeholder getPosition() {
        return position;
    }

    public CopyOnWriteArrayList<Unit> getTargets() {
        return targets;
    }

    public int getUpdateRate() {
        return updateRate;
    }

    public CopyOnWriteArrayList<FireBall> getFires() {
        return fires;
    }

    public String getTowerType() {
        return this.towerType;
    }

    /**
     * Gets the box that srounds an object at some point
     * based on the hieght and the width of the object.
     * 
     * @param point
     * @param width
     * @param height
     * @return Rectangle that srounds the object.
     */
    private java.awt.Rectangle getColistionBox(Point2D.Float point, int width, int height) {
        return new java.awt.Rectangle((int) point.x, (int) point.getY(), width, height);
    }

    /**
     * This function contains the fire shooting logic.
     * 
     * We start by adding fire balls (that will attack all the targets if it is a
     * multi attack tower or will attack one target if it is magic or normal
     * tower) every 25 frame refresh. Then we render the fire ball based on its
     * current position (we call the moveToTarget() function to update the
     * position of the ball), this update function is callad in every frame
     * refresh. If the ball collides with the target object then we decrease the
     * target health points (the decreasing amount is based on the tower type) and
     * we remove the fire ball. We also check if the fire ball is out of the
     * firing range or didn't hit any object then we remove it.
     * 
     * @param batch
     */
    public void update(SpriteBatch batch) {

        // adding a new ball every 25 frame updates
        for (int i = 0; i < this.targets.size() && (updateRate % 25 == 0) && updateRate != 0; i++) {

            Unit target = this.targets.get(i);
            Point2D.Float targetPosition = new Point2D.Float(target.getPosition().x,
                    target.getPosition().y);

            if (inRange(targetPosition) && target.getMovedInPath()) {
                if (this.targets.size() != 0 && !this.multiAttack) {
                    this.targets = new CopyOnWriteArrayList<>();
                    this.targets.add(target);
                }
                FireBall f = new FireBall(new Point2D.Float(
                        this.position.getX() * Constants.PLACEHOLDER_SIZE + 5,
                        this.position.getY() * Constants.PLACEHOLDER_SIZE + 30),
                        new Point2D.Float(target.getPosition().x, target.getPosition().y),
                        this.fireRate, this.towerType);

                this.fires.add(f);
            }
        }

        // checking the balls that needs to be removed
        for (FireBall fireBall : fires) {
            Point2D.Float lasPosition = fireBall.getPosition();
            fireBall.moveToTarget();

            for (Unit target : targets) {
                if (getColistionBox(fireBall.getPosition(), 15, 15).intersects(
                        getColistionBox(target.getPosition(), 15, 40))) {
                    target.setHealth(target.getHealth() - this.healthToBeDecreased);
                    this.fires.remove(fireBall);
                }
            }

            if (!inRange(fireBall.getPosition()) || lasPosition.equals(fireBall.getPosition())) {
                this.fires.remove(fireBall);
            }

            // rendering the balls
            Sprite sprite = new Sprite(fireBall.getTexture());
            sprite.setPosition(fireBall.getPosition().x, fireBall.getPosition().y);
            sprite.setSize(15, 15);
            sprite.draw(batch);
        }

        // if we don't have targets then we don't need fire balls
        if (this.targets.isEmpty())
            this.fires = new CopyOnWriteArrayList<>();

        updateRate++;

        if (updateRate >= 500)
            updateRate = 0;
    }

    /**
     * Checking if a target position is inside the range of the current tower
     * sprite.
     * 
     * @param target
     * @return whether the target is inside the circular range or not.
     */
    public boolean inRange(Point2D.Float target) {

        // (x - center_x)² + (y - center_y)² < radius²
        double x = target.getX();
        double y = target.getY();
        double radius = this.fireRange * Constants.PLACEHOLDER_SIZE;
        double center_x = this.position.getX() * Constants.PLACEHOLDER_SIZE;
        double center_y = this.position.getY() * Constants.PLACEHOLDER_SIZE;
        double dx = Math.abs(x - center_x);
        double dy = Math.abs(y - center_y);

        if (dx > radius || dy > radius)
            return false;
        if (dx + dy <= radius)
            return true;

        return ((dx * dx) + (dy * dy) < (radius * radius));
    }

    /**
     * Fill the target array (those target are the enemies that the tower will be
     * attacking).
     * 
     * @param units
     */
    public void setEnemies(ArrayList<Unit> units) {
        this.targets = new CopyOnWriteArrayList<>(units);
    }
}
