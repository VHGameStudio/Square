package com.square.box2d;

import com.square.enums.UserDataType;

public class SquareUserData extends UserData {

    public SquareUserData(float width, float height) {
        super(width, height);
        userDataType = UserDataType.SQUARE;
    }

}