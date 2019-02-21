package com.square;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.square.screens.GameScreen;
import com.square.utils.AssetsManager;

public class GameRun extends Game {
    @Override
    public void create() {
        AssetsManager.loadAssets();
        setScreen(new GameScreen());
    }
}
