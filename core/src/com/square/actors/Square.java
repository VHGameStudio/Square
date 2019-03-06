package com.square.actors;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.square.figure2d.SquareUserData;
import com.square.utils.Controller;
import com.square.utils.ControllerV1;

import static com.square.utils.Constants.COS_45;
import static com.square.utils.Constants.RADIUS_EPS;
import static com.square.utils.Constants.SQUARE_DOWNWARD_VELOCITY;
import static com.square.utils.Constants.SQUARE_LEFTWARD_VELOCITY;
import static com.square.utils.Constants.SQUARE_RIGHTWARD_VELOCITY;
import static com.square.utils.Constants.SQUARE_UPWARD_VELOCITY;
import static com.square.utils.Constants.SQUARE_ZERO_VELOCITY;
import static com.square.utils.Resources.SQUARE_BLUE;

public class Square extends GameActor {

    private Controller controller;

    public Square(Body body, OrthographicCamera camera) {
        super(body);
        this.camera = camera;

        controller = new ControllerV1();

        texture = new Texture(SQUARE_BLUE);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        camera.position.x = body.getPosition().x;
        camera.position.y = body.getPosition().y;
        camera.update();

        batch.draw(
                texture,
                screenRectangle.x,
                screenRectangle.y,
                screenRectangle.width,
                screenRectangle.height);
    }

    public void move(Vector2 actual, Vector2 touchDown) {
        controller.move(body, actual, touchDown);
    }

    @Override
    public SquareUserData getUserData() {
        return (SquareUserData) userData;
    }

    public void stop() {
        body.setLinearVelocity(SQUARE_ZERO_VELOCITY);
    }
}