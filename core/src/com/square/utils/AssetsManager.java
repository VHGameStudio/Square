package com.square.utils;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class AssetsManager {

    private static TextureAtlas buttonTextureAtlas;
    private static TextureAtlas colorsTextureAtlas;

    private AssetsManager() {

    }

    public static void loadAssets() {
        buttonTextureAtlas = new TextureAtlas(Resources.SPRITES_ATLAS_PATH);
        colorsTextureAtlas = new TextureAtlas(Resources.SIMPLE_COLORS_ATLAS_PATH);
    }

    public static TextureAtlas getButtonTextureAtlas() {
        return buttonTextureAtlas;
    }

    public static TextureAtlas getColorsTextureAtlas() {
        return colorsTextureAtlas;
    }

    public static void dispose() {
        buttonTextureAtlas.dispose();
    }
}
