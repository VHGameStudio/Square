package com.square.actors.menu;

import com.badlogic.gdx.math.Rectangle;
import com.square.utils.Resources;

public class PlayButton extends GameButton {

    public interface PlayButtonListener {
        public void onPlay();
    }

    private PlayButtonListener listener;

    public PlayButton(Rectangle bounds, PlayButtonListener listener) {
        super(bounds);
        this.listener = listener;
    }

    @Override
    protected String getRegionName() {
        return Resources.PLAY_REGION_NAME;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void touched() {
        listener.onPlay();
    }


}