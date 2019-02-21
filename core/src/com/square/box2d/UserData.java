package com.square.box2d;

import com.badlogic.gdx.math.Vector2;
import com.square.enums.UserDataType;

public abstract class UserData {

    protected UserDataType userDataType;
    protected float width;
    protected float height;
    protected float radius;
    protected Vector2 x;
    protected Vector2 y;

    public UserData(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public UserData(float radius) {
        this.radius = radius;
        this.width = this.height = radius * 2;
    }

    public UserData(Vector2 x, Vector2 y) {
        this.x = x;
        this.y = y;
        //todo ?
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public UserDataType getUserDataType() {
        return userDataType;
    }

}

