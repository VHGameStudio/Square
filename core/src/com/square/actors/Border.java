package com.square.actors;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.square.figure2d.BorderUserData;

import static com.square.utils.Resources.BORDER_BLACK;

public class Border extends GameActor {

    public Border(Body body, OrthographicCamera camera) {
        super(body);
        this.camera = camera;

        texture = new Texture(BORDER_BLACK);
    }

    @Override
    public BorderUserData getUserData() {
        return (BorderUserData) userData;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
/*        camera.position.x = det.getPosition().x;
        camera.position.y = det.getPosition().y;
        camera.update();*/

/*        System.out.println("X: " + screenRectangle.x);
        System.out.println("Y: " + screenRectangle.y);
        System.out.println("W: " + screenRectangle.width);
        System.out.println("H: " + screenRectangle.height);*/
/*
        batch.draw(
                texture,
                screenRectangle.x,
                screenRectangle.y,
                screenRectangle.width,
                screenRectangle.height);*/
    }

}
