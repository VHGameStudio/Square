package com.square.actors.menu;


import com.badlogic.gdx.math.Rectangle;
import com.square.utils.Resources;

public class SettingsButton extends GameButton {

    public interface SettingsButtonListener {
        public void onSettings();
    }

    private SettingsButtonListener listener;

    public SettingsButton(Rectangle bounds, SettingsButtonListener listener) {
        super(bounds);
        this.listener = listener;
    }

    @Override
    protected String getRegionName() {
        return Resources.SETTINGS_REGION_NAME;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void touched() {
        listener.onSettings();
    }


}