package com.square.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.square.stages.GameStage;

import static com.square.utils.Constants.VIEWPORT_WIDTH;

public class GameScreen implements Screen {

    private GameStage stage;
    private Game game;
    private Screen menuScreen;

    public GameScreen(Game game, Screen menuScreen) {
        stage = new GameStage(game);
        this.game = game;
        this.menuScreen = menuScreen;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
        stage.act(delta);
    }

    @Override
    public void resize(int width, int height) {
        stage.getCamera().viewportWidth = VIEWPORT_WIDTH;
        stage.getCamera().viewportHeight = VIEWPORT_WIDTH * height / width;
        stage.getCamera().update();
    }


    @Override
    public void show() {
        stage.draw();
    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        game.setScreen(menuScreen);
    }

}
