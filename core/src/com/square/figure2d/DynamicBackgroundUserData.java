package com.square.figure2d;

import com.square.enums.UserDataType;

public class DynamicBackgroundUserData extends UserData {

    public DynamicBackgroundUserData(float width, float height) {
        super(width, height);
        userDataType = UserDataType.BG_PART;
    }

}