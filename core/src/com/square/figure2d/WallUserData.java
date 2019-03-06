package com.square.figure2d;

import com.square.enums.UserDataType;

public class WallUserData extends UserData {

    public WallUserData(float width, float height) {
        super(width, height);
        userDataType = UserDataType.WALL;
    }

}