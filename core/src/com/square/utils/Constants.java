package com.square.utils;

import com.badlogic.gdx.math.Vector2;

public class Constants {

    public static final double COS_45 = 0.52532198881;

    public static final Vector2 WORLD_GRAVITY = new Vector2(0, 0);

    public static final int RADIUS_EPS = 30;
    public static final int NEW_COORDINATE_PLANE_DELTA = 4;

    public static final float VIEWPORT_WIDTH = 30;

    public static final float SQUARE_X = 0;
    public static final float SQUARE_Y = 0;
    public static final float SQUARE_WIDTH = 2f;
    public static final float SQUARE_HEIGHT = 2f;
    public static float SQUARE_DENSITY = 0.5f;

    public static final float SQUARE_VELOCITY = 10;
    public static final Vector2 SQUARE_ZERO_VELOCITY = new Vector2(0, 0);
    public static final Vector2 SQUARE_UPWARD_VELOCITY = new Vector2(0, SQUARE_VELOCITY);
    public static final Vector2 SQUARE_DOWNWARD_VELOCITY = new Vector2(0, -SQUARE_VELOCITY);
    public static final Vector2 SQUARE_LEFTWARD_VELOCITY = new Vector2(-SQUARE_VELOCITY, 0);
    public static final Vector2 SQUARE_RIGHTWARD_VELOCITY = new Vector2(SQUARE_VELOCITY, 0);

    //TODO: I has randomly chosed this number but it works
    public static final float WORLD_TO_SCREEN = 36;
    //WORLD_TO_SCREEN works only for this screen width
    public static final float DEFAULT_SCREEN_WIDTH = 1080;

}