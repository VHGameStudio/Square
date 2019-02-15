package com.square.box2d;

import com.square.enums.UserDataType;

public class WallUserData extends UserData {

    public WallUserData() {
        super();
        userDataType = UserDataType.WALL;
    }

}