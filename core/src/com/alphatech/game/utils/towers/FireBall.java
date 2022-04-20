package com.alphatech.game.utils.towers;

import com.alphatech.game.helpers.Constants;
import com.alphatech.game.helpers.Position;
import com.alphatech.game.helpers.Textures;
import com.badlogic.gdx.graphics.Texture;

public class FireBall {
    private Position position;
    private Position target;
    private float fireRate;
    private Texture texture;

    public FireBall(Position position, Position target, float fireRate, String towerType){
        this.position   = position;
        this.target     = target;
        this.fireRate   = fireRate;
        setFireBall(towerType);
    };

    /**
     * Sets the texture for a fire ball based on the tower type.
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

    public Texture getTexture() {
        return texture;
    }
    
    /**
     * Update the position of the fire ball so it moves towards a target.
     * 
     */
    public void moveToTarget(){
        double targetX = target.getX(); 
        double targetY = target.getY();
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

        if ( distTravel > dist ){
            ballX = destX;
            ballY = destY;
        }else{
            ballX += travelX;
            ballY += travelY;
        }

        this.position = new Position(ballX, ballY);
    }

    public Position getPosition(){
        return this.position;
    }

    /**
     * Resets the position and the target of the fire ball.
     * 
     * @param position
     * @param target
     */
    public void reset(Position position, Position target){
        this.position   = position;
        this.target     = target;
    };
}
