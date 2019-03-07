package com.square.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.square.enums.DetectorDirection;
import com.square.figure2d.UserData;

import static com.square.enums.UserDataType.DETECTOR;
import static com.square.utils.Constants.ENEMY_DETECTOR_LEN;
import static com.square.utils.Constants.WORLD_TO_SCREEN;
import static com.square.utils.Constants.DEFAULT_SCREEN_WIDTH;

public abstract class GameActor extends Actor {

    protected Texture texture;
    protected OrthographicCamera camera;

    protected Body body;
    protected UserData userData;
    protected Rectangle screenRectangle;
    Vector3 coordinates;

    public GameActor(Body body) {
        this.body = body;
        this.userData = (UserData) body.getUserData();
        screenRectangle = new Rectangle();
        coordinates = new Vector3();
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (body.getUserData() == null) {
            remove();
        } else {
            updateRectangle();
        }
    }

    private void updateRectangle() {
        //worldToScreen works for height and width but doesn't work for x and y
        //so I went this way
        coordinates.x = body.getPosition().x - userData.getWidth() / 2;
        coordinates.y = body.getPosition().y - userData.getHeight() / 2;

        camera.project(coordinates);

        screenRectangle.x = coordinates.x;
        screenRectangle.y = coordinates.y;
        screenRectangle.width = worldToScreen(userData.getWidth());
        screenRectangle.height = worldToScreen(userData.getHeight());
    }

    public static float worldToScreen(float w) {
        float coef = WORLD_TO_SCREEN * Gdx.graphics.getWidth() / DEFAULT_SCREEN_WIDTH;
        return w * coef;
    }

    public abstract UserData getUserData();
}