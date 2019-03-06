package com.square.actors.menu;

import com.badlogic.gdx.math.Rectangle;
import com.square.utils.Resources;


public class PauseButton extends GameButton {
    public interface PauseButtonListener {
        public void onPause();
    }

    private PauseButtonListener listener;

    public PauseButton(Rectangle bounds, PauseButtonListener listener) {
        super(bounds);
        this.listener = listener;
    }

    @Override
    protected String getRegionName() {
        return Resources.PAUSE_REGION_NAME;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void touched() {
        listener.onPause();
    }

}
