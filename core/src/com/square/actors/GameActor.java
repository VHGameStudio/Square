package com.square.actors;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.square.box2d.UserData;

import static com.square.utils.Constants.WORLD_TO_SCREEN;

public abstract class GameActor extends Actor {

    protected Texture texture;
    protected OrthographicCamera camera;

    protected Body body;
    protected UserData userData;
    protected Rectangle screenRectangle;

    public GameActor(Body body) {
        this.body = body;
        this.userData = (UserData) body.getUserData();
        screenRectangle = new Rectangle();
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (body.getUserData() != null) {
            updateRectangle();
        } else {
            remove();
        }

    }

    private void updateRectangle() {
        screenRectangle.x = worldToScreen(body.getPosition().x - userData.getWidth() / 2);
        screenRectangle.y = worldToScreen(body.getPosition().y - userData.getHeight() / 2);
        screenRectangle.width = worldToScreen(userData.getWidth());
        screenRectangle.height = worldToScreen(userData.getHeight());
    }

    protected float worldToScreen(float n) {
        return WORLD_TO_SCREEN * n;
    }

    public abstract UserData getUserData();
}