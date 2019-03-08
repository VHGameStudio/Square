package com.square.enums;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

import static com.square.utils.Constants.DEFAULT_SCREEN_HEIGHT;
import static com.square.utils.Constants.DEFAULT_SCREEN_WIDTH;
import static com.square.utils.Constants.GAME_BUTTON_HEIGHT;
import static com.square.utils.Constants.GAME_BUTTON_WIDTH;
import static com.square.utils.Constants.MENU_BUTTON_DELTA;
import static com.square.utils.Constants.MENU_BUTTON_HEIGHT;
import static com.square.utils.Constants.MENU_BUTTON_WIDTH;
import static com.square.utils.Constants.MENU_BUTTON_Y;

public enum ButtonBounds {
    PLAY {
        public Rectangle getBounds() {
            float coef_y = Gdx.graphics.getHeight() / DEFAULT_SCREEN_HEIGHT;
            float coef_x = Gdx.graphics.getWidth() / DEFAULT_SCREEN_WIDTH;
            float width = MENU_BUTTON_WIDTH * coef_y;
            float height = MENU_BUTTON_HEIGHT * coef_y;
            float pos_x = Gdx.graphics.getWidth() / 2f - width / 2;
            float pos_y = MENU_BUTTON_Y * coef_y;
            return new Rectangle(pos_x, pos_y, width, height);
        }

        ;
    },
    PAUSE {
        public Rectangle getBounds() {
            float coef_y = Gdx.graphics.getHeight() / DEFAULT_SCREEN_HEIGHT;
            float coef_x = Gdx.graphics.getWidth() / DEFAULT_SCREEN_WIDTH;
            float pos_x = 50;
            float width = GAME_BUTTON_WIDTH * coef_y;
            float height = GAME_BUTTON_HEIGHT * coef_y;
            float pos_y = Gdx.graphics.getHeight() - height - pos_x;
            return new Rectangle(pos_x, pos_y, width, height);
        }

        ;
    },
    SETTINGS {
        public Rectangle getBounds() {
            float coef_y = Gdx.graphics.getHeight() / DEFAULT_SCREEN_HEIGHT;
            float coef_x = Gdx.graphics.getWidth() / DEFAULT_SCREEN_WIDTH;
            float width = MENU_BUTTON_WIDTH * coef_y;
            float height = MENU_BUTTON_HEIGHT * coef_y;
            float pos_x = PLAY.getBounds().x - MENU_BUTTON_DELTA * coef_x;
            float pos_y = MENU_BUTTON_Y * coef_y;
            return new Rectangle(pos_x, pos_y, width, height);
        }

        ;
    },
    SOUND {
        public Rectangle getBounds() {
            float coef_y = Gdx.graphics.getHeight() / DEFAULT_SCREEN_HEIGHT;
            float coef_x = Gdx.graphics.getWidth() / DEFAULT_SCREEN_WIDTH;
            float width = MENU_BUTTON_WIDTH * coef_y;
            float height = MENU_BUTTON_HEIGHT * coef_y;
            float pos_x = PLAY.getBounds().x + MENU_BUTTON_DELTA * coef_x;
            float pos_y = MENU_BUTTON_Y * coef_y;
            return new Rectangle(pos_x, pos_y, width, height);
        }

        ;
    };

    public abstract Rectangle getBounds();
}
