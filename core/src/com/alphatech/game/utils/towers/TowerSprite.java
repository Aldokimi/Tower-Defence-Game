package com.alphatech.game.utils.towers;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import com.alphatech.game.helpers.Constants;
import com.alphatech.game.helpers.Position;
import com.alphatech.game.utils.units.Unit;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TowerSprite {
    private CopyOnWriteArrayList<Unit> targets;
    private CopyOnWriteArrayList <FireBall> fires;
    private int updateRate;
    private Placeholder position;


    // Credentials 
    private int healthToBeDecreased;
    private float fireRate;
    private int fireRange;
    private boolean multiAttack;
    private String towerType;

    public TowerSprite(Placeholder position, String towerType){
        this.position  = position;
        this.towerType = towerType;
        this.targets   = new CopyOnWriteArrayList<>();
        this.fires     = new CopyOnWriteArrayList<>();
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
                this.healthToBeDecreased = 80;
                this.fireRate = 2.0f;
                this.multiAttack = false;
                this.fireRange = 4;
                break;

            case Constants.MULTIATTACK_TOWER:
                this.healthToBeDecreased = 100;
                this.fireRate = 2.0f;
                this.multiAttack = true;
                this.fireRange = 3;
                break;

            case Constants.MAGIC_TOWER:
                this.healthToBeDecreased = 75;
                this.fireRate = 2.5f;
                this.multiAttack = false;
                this.fireRange = 5;
                break;
        
            default:
                break;
        }

    }

    public Placeholder getPosition() {
        return position;
    }

    public CopyOnWriteArrayList<Unit> getTargets() {
        return targets;
    }

    public CopyOnWriteArrayList<FireBall> getFires() {
        return fires;
    }

    public String getTowerType(){
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
    private java.awt.Rectangle getColistionBox(Position point, int width, int height){
        return new java.awt.Rectangle((int) point.getX(), (int) point.getY(), width, height);
    }


    /**
     * This function contains the fire shooting logic.
     * 
     * We start by adding fire balles (that will attack all the targets if it is a multi attack
     * tower or will attack one target if it is magic or normal tower) every 30 frame refresh.
     * Then we render the fire ball based on its current position (we call the moveToTarget() function 
     * to update the position of the ball), this update function is callad in every frame refresh.
     * If the ball collides with the target object then we decrease the target health points (the 
     * decreasing amount is based on the tower type) and we remove the fire ball. We also check if the
     * fire ball is out of the firring range or didn't hit any object then we remove it.
     * 
     * @param batch
     */
    public void update(SpriteBatch batch){

        // adding a new ball every 25 frame updates
        for(int i = 1; i < this.targets.size() && (updateRate % 25 == 0); i++){

            Unit target = this.targets.get(i);
            Position targetPosition = new Position((float)target.getPosition().getX(), (float)target.getPosition().getY());

            if(inRange(targetPosition)){
                if(this.targets.size() != 0 && !this.multiAttack){
                    this.targets = new CopyOnWriteArrayList<>();
                    this.targets.add(target);
                }
                FireBall f = new FireBall(new Position(
                    this.position.getX() * Constants.PLACEHOLDER_SIZE + 5,
                    this.position.getY() * Constants.PLACEHOLDER_SIZE + 30
                    ), new Position((float)target.getPosition().getX(), (float)target.getPosition().getY()),
                    this.fireRate, this.towerType);

                this.fires.add(f);
            }
        }
        
        // checking the balles that needs to be removed
        for (FireBall fireBall : fires) {
            Position lasPosition = fireBall.getPosition();
            fireBall.moveToTarget();

            for (Unit target : targets) {
                if(getColistionBox(fireBall.getPosition(), 15, 15).intersects(
                    getColistionBox( new Position((float)target.getPosition().getX(), 
                                                (float)target.getPosition().getY()), 15, 40
                    )
                )){
                    target.setHealth(target.getHealth() - this.healthToBeDecreased);
                    this.fires.remove(fireBall);
                }
            }

            if(!inRange(fireBall.getPosition()) || lasPosition.equals(fireBall.getPosition())){
                this.fires.remove(fireBall);
            }

            //rendering the balles 
            Sprite sprite = new Sprite(fireBall.getTexture());
            sprite.setPosition((float)fireBall.getPosition().getX(), (float)fireBall.getPosition().getY());
            sprite.setSize(15, 15);
            sprite.draw(batch);
        }

        // if we don't have targets then we don't need fire balles
        if(this.targets.isEmpty()) this.fires = new CopyOnWriteArrayList <>();

        updateRate++;
    }


    /**
     * Checking if a target position is inside the range of the current tower sprite.
     * 
     * @param target
     * @return wheather the target is inside the circular range or not.
     */
    public boolean inRange(Position target){

        //(x - center_x)² + (y - center_y)² < radius²
        double x = target.getX();
        double y = target.getY();
        double radius = this.fireRange * Constants.PLACEHOLDER_SIZE;
        double center_x = this.position.getX() * Constants.PLACEHOLDER_SIZE;
        double center_y = this.position.getY() * Constants.PLACEHOLDER_SIZE;
        double dx = Math.abs(x - center_x);
        double dy = Math.abs(y - center_y);

        if(dx > radius || dy > radius) return false;
        if(dx + dy <= radius) return true;

        return ( (dx * dx) + ( dy * dy) < (radius * radius) );
    }


    /**
     * Fill the target array (those target are the enemies that the tower will be attacking).
     * 
     * @param units
     */
    public void setEnemies(ArrayList<Unit> units) {
        this.targets = new CopyOnWriteArrayList<>(units);
    }
}
