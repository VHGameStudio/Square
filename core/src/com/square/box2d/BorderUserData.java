package com.square.box2d;

import com.badlogic.gdx.math.Vector2;
import com.square.enums.UserDataType;

public class BorderUserData extends UserData {
    //TODO

    public BorderUserData(Vector2 x, Vector2 y) {
        super(x, y);
        userDataType = UserDataType.BORDER;
    }
}
