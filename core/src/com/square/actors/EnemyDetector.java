package com.square.actors;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.square.figure2d.EnemyDetectorUserData;
import com.square.figure2d.UserData;

public class EnemyDetector extends Actor {
    private final EnemyDetectorUserData userData;
    Body square;
    Body body;

    public EnemyDetector(Body det, Body square) {
        userData = new EnemyDetectorUserData();
        this.square = square;
        body = det;
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (body.getUserData() == null) {
            remove();
        } else {
            body.setTransform(square.getPosition(), 0f);
        }
    }

    public UserData getUserData() {
        return userData;
    }

    public Body getBody() {
        return body;
    }

}
