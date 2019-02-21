package com.square.actors.menu;

import com.badlogic.gdx.math.Rectangle;
import com.square.utils.Resources;

public class SoundButton extends GameButton {

    public interface SoundButtonListener {
        public void onSound();
    }

    private SoundButton.SoundButtonListener listener;

    public SoundButton(Rectangle bounds, SoundButton.SoundButtonListener listener) {
        super(bounds);
        this.listener = listener;
    }

    @Override
    protected String getRegionName() {
        return Resources.MUSIC_ON_REGION_NAME;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void touched() {
        listener.onSound();
    }


}
