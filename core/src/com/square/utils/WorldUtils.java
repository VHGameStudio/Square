package com.square.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.square.box2d.WallUserData;
import com.square.box2d.SquareUserData;

import java.util.Random;

import static com.square.utils.Constants.SQUARE_DENSITY;

public class WorldUtils {

    public static World createWorld() {
        return new World(Constants.WORLD_GRAVITY, true);
    }

    public static Body createWall(World world) {
        Random rnd = new Random(System.currentTimeMillis());

        float w = -10 + rnd.nextInt(10 - (-10) + 1);
        float h = -10 + rnd.nextInt(10 - (-10) + 1);
        float x = -20 + rnd.nextInt(20 - (-20) + 1);
        float y = -20 + rnd.nextInt(20 - (-20) + 1);

        BodyDef bodyDef = new BodyDef();
        bodyDef.fixedRotation = true;
        bodyDef.position.set(new Vector2(x, y));
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(w / 2, h / 2);
        body.createFixture(shape, 0);
        body.setUserData(new WallUserData(w, h));
        shape.dispose();

        return body;
    }

    public static Body createSquare(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.fixedRotation = true;
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(Constants.SQUARE_X, Constants.SQUARE_Y));
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.SQUARE_WIDTH / 2, Constants.SQUARE_HEIGHT / 2);
        Body body = world.createBody(bodyDef);
        body.setUserData(new SquareUserData(Constants.SQUARE_WIDTH, Constants.SQUARE_HEIGHT));
        body.createFixture(shape, SQUARE_DENSITY);
        shape.dispose();

        return body;
    }
}