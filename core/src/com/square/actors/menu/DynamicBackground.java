package com.square.actors.menu;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.square.actors.GameActor;
import com.square.box2d.DynamicBackgroundUserData;

public class DynamicBackground extends GameActor {

    public DynamicBackground(Body body, OrthographicCamera camera) {
        super(body);
        this.camera = camera;

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.draw(
                texture,
                screenRectangle.x,
                screenRectangle.y,
                screenRectangle.width,
                screenRectangle.height);
    }

    @Override
    public DynamicBackgroundUserData getUserData() {
        return (DynamicBackgroundUserData) userData;
    }

}
