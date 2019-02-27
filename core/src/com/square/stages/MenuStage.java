package com.square.stages;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.square.actors.menu.DynamicBackground;
import com.square.actors.menu.PlayButton;
import com.square.actors.menu.SettingsButton;
import com.square.actors.menu.SoundButton;
import com.square.screens.GameScreen;
import com.square.utils.WorldUtils;

import java.util.Random;

import static com.square.utils.Constants.DEFAULT_SCREEN_HEIGHT;
import static com.square.utils.Constants.DEFAULT_SCREEN_WIDTH;
import static com.square.utils.Constants.MENU_BUTTON_DELTA;
import static com.square.utils.Constants.MENU_BUTTON_HEIGHT;
import static com.square.utils.Constants.MENU_BUTTON_WIDTH;
import static com.square.utils.Constants.MENU_BUTTON_Y;
import static com.square.utils.Constants.VIEWPORT_WIDTH;

public class MenuStage extends Stage {

    private Game game;

    //private GameState gameState;
    private PlayButton playButton;
    private SettingsButton settingsButton;
    private SoundButton soundButton;
    private OrthographicCamera camera;
    private World world;


    public MenuStage(Game game) {
        this.game = game;
        setUpWorld();
        setUpCamera();
        setUpBackground();
        setUpMainMenu();
        setUpControls();
    }

    public void setUpControls() {
        Gdx.input.setInputProcessor(this);
    }

    private class MenuPlayButtonListener implements PlayButton.PlayButtonListener {

        @Override
        public void onPlay() {
            game.setScreen(new GameScreen(game, game.getScreen()));
        }
    }

    private class MenuSettingsButtonListener implements SettingsButton.SettingsButtonListener {

        @Override
        public void onSettings() {

        }
    }

    private void setUpWorld() {
        world = WorldUtils.createWorld();
    }

    private void setUpBackground() {
        Random rnd = new Random(System.currentTimeMillis());

        int step = 5;
        int H = (int) (step * 2 + camera.viewportHeight / 2);
        int W = (int) (step * 2 + camera.viewportWidth / 2);

        for (int i = 0 - H; i < H; i += step) {
            for (int j = 0 - W; j < W; j += step) {
                Vector2 tmp1 = new Vector2(i, j);
                Vector2 tmp2 = new Vector2(i + step, j + step);
                int color = rnd.nextInt(20 + 1);
                addActor(new DynamicBackground(
                        WorldUtils.createBackRect(world, tmp1, tmp2),
                        camera,
                        color));
            }
        }

        int devisor1 = 7;
        addActor(new DynamicBackground(WorldUtils.createBackRect(
                world,
                new Vector2(0, 0 - camera.viewportHeight / 2 + devisor1 / 2f),
                camera.viewportWidth,
                camera.viewportHeight / devisor1), camera, 19));

        // A background strip for name label
        int devisor2 = 5;
        addActor(new DynamicBackground(WorldUtils.createBackRect(
                world,
                new Vector2(0, camera.viewportHeight / 7),
                camera.viewportWidth,
                camera.viewportHeight / devisor2), camera, 19));
    }

    private void setUpMainMenu() {
        setUpPlay();
        setUpSettings();
        setUpSound();
    }

    private void setUpPlay() {
        //TODO: make implimentation for calculation buttons bounds
        float coef_y = Gdx.graphics.getHeight() / DEFAULT_SCREEN_HEIGHT;
        float coef_x = Gdx.graphics.getWidth() / DEFAULT_SCREEN_WIDTH;
        float pos_x = (getCamera().position.x + Gdx.graphics.getWidth() - MENU_BUTTON_WIDTH * coef_y) / 2;
        float pos_y = (MENU_BUTTON_Y * coef_y);
        float width = MENU_BUTTON_WIDTH * coef_y;
        float height = MENU_BUTTON_HEIGHT * coef_y;

        Rectangle playButtonBounds = new Rectangle(pos_x,
                pos_y, width, height);
        playButton = new PlayButton(playButtonBounds, new MenuPlayButtonListener());
        addActor(playButton);
    }

    private void setUpSettings() {
        float coef_y = Gdx.graphics.getHeight() / DEFAULT_SCREEN_HEIGHT;
        float coef_x = Gdx.graphics.getWidth() / DEFAULT_SCREEN_WIDTH;
        float pos_x = playButton.getX() - MENU_BUTTON_DELTA * coef_x;
        float pos_y = (MENU_BUTTON_Y * coef_y);
        float width = MENU_BUTTON_WIDTH * coef_y;
        float height = MENU_BUTTON_HEIGHT * coef_y;

        Rectangle settingsButtonBounds = new Rectangle(pos_x,
                pos_y, width, height);
        settingsButton = new SettingsButton(settingsButtonBounds, new MenuSettingsButtonListener());
        addActor(settingsButton);
    }

    private void setUpSound() {
        float coef_y = Gdx.graphics.getHeight() / DEFAULT_SCREEN_HEIGHT;
        float coef_x = Gdx.graphics.getWidth() / DEFAULT_SCREEN_WIDTH;
        float pos_x = playButton.getX() + MENU_BUTTON_DELTA * coef_x;
        float pos_y = (MENU_BUTTON_Y * coef_y);
        float width = MENU_BUTTON_WIDTH * coef_y;
        float height = MENU_BUTTON_HEIGHT * coef_y;

        Rectangle soundButtonBounds = new Rectangle(pos_x,
                pos_y, width, height);
        soundButton = new SoundButton(soundButtonBounds);
        addActor(soundButton);
    }

    private void setUpCamera() {
        camera = new OrthographicCamera();
        camera.position.set(0, 0, 0);
        camera.viewportWidth = VIEWPORT_WIDTH;
        camera.viewportHeight = VIEWPORT_WIDTH * Gdx.graphics.getHeight() / Gdx.graphics.getWidth();
        camera.update();
        camera.update();
    }

    @Override
    public OrthographicCamera getCamera() {
        return camera;
    }

}
