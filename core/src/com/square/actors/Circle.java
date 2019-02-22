package com.square.actors;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.square.box2d.CircleUserData;

import static com.square.utils.Resources.CIRCLE_ORANGE;

public class Circle extends GameActor {

    public Circle(Body body, OrthographicCamera camera) {
        super(body);
        this.camera = camera;

        texture = new Texture(CIRCLE_ORANGE);
    }

    @Override
    public CircleUserData getUserData() {
        return (CircleUserData) userData;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
/*        camera.position.x = body.getPosition().x;
        camera.position.y = body.getPosition().y;
        camera.update();*/

        batch.draw(
                texture,
                screenRectangle.x,
                screenRectangle.y,
                screenRectangle.width,
                screenRectangle.height);
    }

}
