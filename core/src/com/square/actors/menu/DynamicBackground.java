package com.square.actors.menu;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.square.actors.GameActor;
import com.square.figure2d.DynamicBackgroundUserData;
import com.square.utils.AssetsManager;

import java.util.Random;

import static com.square.utils.Constants.DYNAMIC_MENU_DELTA;
import static com.square.utils.Constants.DYNAMIC_SPAWN_EPS;
import static java.lang.Math.abs;

public class DynamicBackground extends GameActor {
    Skin skin;
    TextureRegion textureRegion;
    Vector2 spawnPoint;
    boolean moving;

    public DynamicBackground(Body body, OrthographicCamera camera, Integer color, boolean moving) {
        super(body);
        this.camera = camera;

        spawnPoint = new Vector2(body.getPosition());

        this.moving = moving;
        if (moving) {
            body.setLinearVelocity(randVel());
        }

        skin = new Skin(AssetsManager.getColorsTextureAtlas());
        textureRegion = skin.getRegion(color.toString());
    }

    private Vector2 randVel() {
        Random rnd = new Random(System.currentTimeMillis());
        int minVel = -5;
        int maxVel = 5;
        float x = minVel + rnd.nextInt(maxVel - minVel + 1);
        float y = minVel + rnd.nextInt(maxVel - minVel + 1);
        x /= 2;
        y /= 2;

        return new Vector2(x, y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        if (moving) {
            Vector2 dist1 = new Vector2(
                    body.getPosition().x - spawnPoint.x,
                    body.getPosition().y - spawnPoint.y);

            if (dist1.len() < DYNAMIC_SPAWN_EPS) {
                body.setLinearVelocity(randVel());
            }

            Vector2 dist = new Vector2(
                    body.getPosition().x - spawnPoint.x,
                    body.getPosition().y - spawnPoint.y);

            if (dist.len() > DYNAMIC_MENU_DELTA) {
 /*           body.getLinearVelocity().x *= -1;
            body.getLinearVelocity().y *= -1;*/
                body.setLinearVelocity(body.getLinearVelocity().x * -1, body.getLinearVelocity().y * -1);
                System.out.println("!!!!!!!");
            }
        }

        batch.draw(
                textureRegion,
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
