package com.square.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.square.actors.DynamicBackground;
import com.square.box2d.BorderUserData;
import com.square.box2d.CircleUserData;
import com.square.box2d.DynamicBackgroundUserData;
import com.square.box2d.WallUserData;
import com.square.box2d.SquareUserData;

import java.util.Random;

import static com.square.utils.Constants.BORDER_DENSITY;
import static com.square.utils.Constants.CIRCLE_DENSITY;
import static com.square.utils.Constants.CIRCLE_RADIUS;
import static com.square.utils.Constants.SQUARE_DENSITY;
import static com.square.utils.Constants.WALL_DENSITY;

public class WorldUtils {

    public static World createWorld() {
        return new World(Constants.WORLD_GRAVITY, true);
    }

    public static Body createWall(World world) {
        Random rnd = new Random(System.currentTimeMillis());

        float w = 2 + rnd.nextInt(10 - 2 + 1);
        float h = 2 + rnd.nextInt(10 - 2 + 1);
        float x = -20 + rnd.nextInt(20 - (-20) + 1);
        float y = -20 + rnd.nextInt(20 - (-20) + 1);

        BodyDef bodyDef = new BodyDef();
        bodyDef.fixedRotation = true;
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(new Vector2(x, y));
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(w / 2, h / 2);
        body.createFixture(shape, WALL_DENSITY);
        body.setUserData(new WallUserData(w, h));
        shape.dispose();

        return body;
    }

    public static Body createCircle(World world) {
        Random rnd = new Random(System.currentTimeMillis());

        float x = -20 + rnd.nextInt(20 - (-20) + 1);
        float y = -20 + rnd.nextInt(20 - (-20) + 1);

        BodyDef bodyDef = new BodyDef();
        bodyDef.fixedRotation = true;
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(x, y));
        CircleShape shape = new CircleShape();
        shape.setRadius(CIRCLE_RADIUS);
        Body body = world.createBody(bodyDef);
        body.setUserData(new CircleUserData(CIRCLE_RADIUS));
        body.createFixture(shape, CIRCLE_DENSITY);
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

    public static Body createBorder(World world, Vector2 x, Vector2 y) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.fixedRotation = true;
        bodyDef.type = BodyDef.BodyType.StaticBody;
        EdgeShape shape = new EdgeShape();
        shape.set(x, y);
        Body body = world.createBody(bodyDef);
        body.setUserData(new BorderUserData());
        body.createFixture(shape, BORDER_DENSITY);
        shape.dispose();

        return body;
    }

    public static Body createBackgroundRect(World world) {
        Random rnd = new Random(System.currentTimeMillis());

        float w = 2 + rnd.nextInt(10 - 2 + 1);
        float h = 2 + rnd.nextInt(10 - 2 + 1);
        float x = -20 + rnd.nextInt(20 - (-20) + 1);
        float y = -20 + rnd.nextInt(20 - (-20) + 1);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.fixedRotation = true;
        bodyDef.position.set(new Vector2(x, y));
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(w / 2, h / 2);
        body.createFixture(shape, 0f);
        body.setUserData(new DynamicBackgroundUserData(w, h));
        shape.dispose();

        return body;
    }
}