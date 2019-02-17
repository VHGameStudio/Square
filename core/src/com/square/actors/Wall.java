package com.square.actors;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Body;
import com.square.box2d.WallUserData;

public class Wall extends GameActor {

    public Wall(Body body, OrthographicCamera camera) {
        super(body);

        this.camera = camera;
    }

    @Override
    public WallUserData getUserData() {
        return (WallUserData) userData;
    }


}