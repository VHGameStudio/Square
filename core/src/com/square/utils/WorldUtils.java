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

/*    public static Body createWall(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.fixedRotation = true;
        bodyDef.position.set(Generate);
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Generate);
        body.createFixture(shape, Generate density);
        body.setUserData(new WallUserData(width and height must be generated somehow));
        shape.dispose();

        return body;
    }*/

    public static Body createTestWall(World world) {
        Random rnd = new Random(System.currentTimeMillis());

        int w = 2 + rnd.nextInt(15 - 2 + 1);
        int h = 2 + rnd.nextInt(15 - 2 + 1);
        int x = -8 + rnd.nextInt(8 - (-8) + 1);
        int y = -15 + rnd.nextInt(15 - (-15) + 1);

        BodyDef bodyDef = new BodyDef();
        bodyDef.fixedRotation = true;
        bodyDef.position.set(new Vector2(x, y));
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(h / 2f, w / 2f);
        body.createFixture(shape, SQUARE_DENSITY);
        shape.dispose();
        return body;
    }


    public static Body createSquare(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.fixedRotation = true;
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(Constants.SQUARE_X / 2, Constants.SQUARE_Y / 2));
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.SQUARE_WIDTH / 2, Constants.SQUARE_HEIGHT / 2);
        Body body = world.createBody(bodyDef);
        body.setUserData(new SquareUserData());
        body.createFixture(shape, SQUARE_DENSITY);
        shape.dispose();

        return body;
    }
}