package com.square.figure2d;

import com.square.enums.UserDataType;

public class CircleUserData extends UserData {

    public CircleUserData(float radius) {
        super(radius);
        userDataType = UserDataType.CIRCLE;
    }

}
