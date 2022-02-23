package com.alphatech.game.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class MouseInput {

    private final Vector2 mouseInWorld2D;
    private final Vector3 mouseInWorld3D;
    private final OrthographicCamera camera;

    public MouseInput(OrthographicCamera camera){
        this.camera = camera;
        this.mouseInWorld2D  = new Vector2();
        this.mouseInWorld3D = new Vector3();
    }

    public Vector2 getMousePosition(){
        this.mouseInWorld3D.x = Gdx.input.getX();
        this.mouseInWorld3D.y = Gdx.input.getY();
        this.mouseInWorld3D.z = 0;
        this.camera.unproject(mouseInWorld3D);
        this.mouseInWorld2D.x = mouseInWorld3D.x;
        this.mouseInWorld2D.y = mouseInWorld3D.y;

        return this.mouseInWorld2D;
    }


}
