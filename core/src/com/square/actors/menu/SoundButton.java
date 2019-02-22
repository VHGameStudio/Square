package com.square.actors.menu;

import com.badlogic.gdx.math.Rectangle;
import com.square.utils.Resources;

public class SoundButton extends GameButton {
    private int toogle = 1;

    public interface SoundButtonListener {
        public void onSound();
    }

    private SoundButton.SoundButtonListener listener;

    public SoundButton(Rectangle bounds) {
        super(bounds);
        loadTextureRegion();
    }

    @Override
    protected String getRegionName() {
        String region_name = toogle == 1 ?
                Resources.MUSIC_ON_REGION_NAME : Resources.MUSIC_OFF_REGION_NAME;
        return region_name;
    }

    @Override
    public void touched() {
        toogle_sound();
    }

    public void toogle_sound() {
        toogle = toogle == 1 ? 0 : 1;
    }


}
