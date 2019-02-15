package com.square.utils;

import com.badlogic.gdx.physics.box2d.Body;
import com.square.box2d.UserData;
import com.square.enums.UserDataType;

public class BodyUtils {

    public static boolean bodyIsSquare(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.SQUARE;
    }

    public static boolean bodyIsWall(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.WALL;
    }

}
