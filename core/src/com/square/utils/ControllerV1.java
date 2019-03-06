package com.square.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import static com.square.utils.Constants.COS_45;
import static com.square.utils.Constants.RADIUS_EPS;
import static com.square.utils.Constants.SQUARE_DOWNWARD_VELOCITY;
import static com.square.utils.Constants.SQUARE_LEFTWARD_VELOCITY;
import static com.square.utils.Constants.SQUARE_RIGHTWARD_VELOCITY;
import static com.square.utils.Constants.SQUARE_UPWARD_VELOCITY;

public class ControllerV1 implements Controller {

    @Override
    public void move(Body body, Vector2 actual, Vector2 touchDown) {
        float newX = (actual.x - touchDown.x);
        float newY = (actual.y - touchDown.y);
        Vector2 shifted = new Vector2(newX, newY);
        Vector2 axisX = new Vector2(1, 0);

        double scal = shifted.x * axisX.x + shifted.y * axisX.y;
        double cosAngle = scal / (shifted.len() * axisX.len());
        double distance = shifted.len();

        if (distance > RADIUS_EPS) {
            if (COS_45 > cosAngle && (-COS_45) < cosAngle && actual.y < touchDown.y) {
                body.setLinearVelocity(SQUARE_UPWARD_VELOCITY);
            } else if (COS_45 <= cosAngle) {
                body.setLinearVelocity(SQUARE_RIGHTWARD_VELOCITY);
            } else if ((-COS_45) >= cosAngle) {
                body.setLinearVelocity(SQUARE_LEFTWARD_VELOCITY);
            } else if (COS_45 > cosAngle && (-COS_45) < cosAngle && actual.y > touchDown.y) {
                body.setLinearVelocity(SQUARE_DOWNWARD_VELOCITY);
            }
        }
    }

}
