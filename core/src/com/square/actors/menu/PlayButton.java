package com.square.actors.menu;

import com.badlogic.gdx.math.Rectangle;
import com.square.utils.Constants;

public class PlayButton extends GameButton {

    public interface PlayButtonListener {
        public void onStart();
    }

    private PlayButtonListener listener;

    public PlayButton(Rectangle bounds, PlayButtonListener listener) {
        super(bounds);
        this.listener = listener;
    }

    @Override
    protected String getRegionName() {
        return Constants.PLAY_REGION_NAME;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void touched() {
        listener.onStart();
    }


}