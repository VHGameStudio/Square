package com.square.utils;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class AssetsManager {

    private static TextureAtlas textureAtlas;

    private AssetsManager() {

    }

    public static void loadAssets() {
        textureAtlas = new TextureAtlas(Resources.SPRITES_ATLAS_PATH);
    }

    public static TextureAtlas getTextureAtlas() {
        return textureAtlas;
    }
    public static void dispose() {
        textureAtlas.dispose();
    }
}
