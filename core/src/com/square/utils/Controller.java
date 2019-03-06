package com.square.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public interface Controller {

    void move(Body body, Vector2 actual, Vector2 touchDown);

}
