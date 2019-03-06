package com.square.utils;

import com.badlogic.gdx.math.Vector2;

public class Constants {

    public static final double COS_45 = 0.52532198881;

    public static final Vector2 WORLD_GRAVITY = new Vector2(0, 0);

    public static final int RADIUS_EPS = 30;
    public static final int NEW_COORDINATE_PLANE_DELTA = 4;

    public static final float VIEWPORT_WIDTH = 30;

    public static float WALL_DENSITY = 0f;

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

    public static final float CIRCLE_RADIUS = 1;
    public static float CIRCLE_DENSITY = 0.5f;
    public static final float CIRCLE_VELOCITY = 15;

    public static final float BORDER_DENSITY = 0f;
    public static final float BORDER_WIDTH = 60;
    public static final float BORDER_HEIGTH = 80;

    public static final float WORLD_TO_SCREEN = 36;
    public static final float DEFAULT_SCREEN_WIDTH = 1080;
    public static final float DEFAULT_SCREEN_HEIGHT = 2160;

    public static final float MENU_BUTTON_DELTA = 400;
    public static final float MENU_BUTTON_WIDTH = 200;
    public static final float MENU_BUTTON_HEIGHT = 200;
    public static final float MENU_BUTTON_Y = 50;

    public static final float GAME_BUTTON_DELTA = 400;
    public static final float GAME_BUTTON_WIDTH = 100;
    public static final float GAME_BUTTON_HEIGHT = 100;
    public static final float GAME_BUTTON_Y = 50;


    public static final int SIMPLE_COLOR_NUM = 21;

    public static final float DYNAMIC_MENU_DELTA = 10;
    public static final float DYNAMIC_SPAWN_EPS = 0.1f;

}