package com.square.actors;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.square.box2d.WallUserData;

import static com.square.utils.Constants.SQUARE_HEIGHT;
import static com.square.utils.Constants.SQUARE_WIDTH;
import static com.square.utils.Resources.TEST_WALL;

public class Wall extends GameActor {

    public Wall(Body body, OrthographicCamera camera) {
        super(body);
        this.camera = camera;

        texture = new Texture(TEST_WALL);
    }

    @Override
    public WallUserData getUserData() {
        return (WallUserData) userData;
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
}