package com.square.actors;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Body;
import com.square.box2d.BorderUserData;

public class Border extends GameActor {

    public Border(Body body, OrthographicCamera camera) {
        super(body);
        this.camera = camera;
    }

    @Override
    public BorderUserData getUserData() {
        return (BorderUserData) userData;
    }

}
