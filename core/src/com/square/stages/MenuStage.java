package com.square.stages;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.square.actors.Background;
import com.square.actors.menu.PlayButton;
import com.square.actors.menu.SettingsButton;
import com.square.actors.menu.SoundButton;
import com.square.enums.GameState;
import com.square.screens.GameScreen;
import com.square.screens.MenuScreen;

import static com.square.utils.Constants.BUTTON_DELTA;
import static com.square.utils.Constants.BUTTON_HEIGHT;
import static com.square.utils.Constants.BUTTON_WIDTH;
import static com.square.utils.Constants.BUTTON_Y;
import static com.square.utils.Constants.DEFAULT_SCREEN_HEIGHT;
import static com.square.utils.Constants.DEFAULT_SCREEN_WIDTH;
import static com.square.utils.Resources.BACKGROUND_MENU;

public class MenuStage extends Stage {

    private Game game;
    //private GameState gameState;
    private PlayButton playButton;
    private SettingsButton settingsButton;
    private SoundButton soundButton;
    private OrthographicCamera camera;


    public MenuStage(Game game) {
        this.game = game;
       // gameState = GameState.MENU;
        setUpCamera();
        setUpBackground();
        setUpMainMenu();
        Gdx.input.setInputProcessor(this);
    }

    private class GamePlayButtonListener implements PlayButton.PlayButtonListener {

        @Override
        public void onPlay() {
            clear();
            //gameState = GameState.RUNNING;
            //setUpStageBase();
            game.dispose();
            game.setScreen(new GameScreen(game));
        }
    }

    private class GameSettingsButtonListener implements SettingsButton.SettingsButtonListener {

        @Override
        public void onSettings() {

        }
    }

    private void setUpBackground() {
        addActor(new Background(BACKGROUND_MENU));
    }

    private void setUpMainMenu() {
        setUpPlay();
        setUpSettings();
        setUpSound();
    }

    private void setUpPlay() {
        float coef_y = Gdx.graphics.getHeight() / DEFAULT_SCREEN_HEIGHT;
        float coef_x = Gdx.graphics.getWidth() / DEFAULT_SCREEN_WIDTH;
        float pos_x = (getCamera().position.x + Gdx.graphics.getWidth() - BUTTON_WIDTH * coef_y) / 2;
        float pos_y = (BUTTON_Y * coef_y);
        float width = BUTTON_WIDTH * coef_y;
        float height = BUTTON_HEIGHT * coef_y;

        Rectangle playButtonBounds = new Rectangle(pos_x,
                pos_y, width, height);
        playButton = new PlayButton(playButtonBounds, new GamePlayButtonListener());
        addActor(playButton);
    }

    private void setUpSettings() {
        float coef_y = Gdx.graphics.getHeight() / DEFAULT_SCREEN_HEIGHT;
        float coef_x = Gdx.graphics.getWidth() / DEFAULT_SCREEN_WIDTH;
        float pos_x = playButton.getX()-BUTTON_DELTA*coef_x;
        float pos_y = (BUTTON_Y * coef_y);
        float width = BUTTON_WIDTH * coef_y;
        float height = BUTTON_HEIGHT * coef_y;

        Rectangle settingsButtonBounds = new Rectangle(pos_x,
                pos_y, width, height);
        settingsButton = new SettingsButton(settingsButtonBounds, new GameSettingsButtonListener());
        addActor(settingsButton);
    }

    private void setUpSound() {
        float coef_y = Gdx.graphics.getHeight() / DEFAULT_SCREEN_HEIGHT;
        float coef_x = Gdx.graphics.getWidth() / DEFAULT_SCREEN_WIDTH;
        float pos_x = playButton.getX()+BUTTON_DELTA*coef_x;
        float pos_y = (BUTTON_Y * coef_y);
        float width = BUTTON_WIDTH * coef_y;
        float height = BUTTON_HEIGHT * coef_y;

        Rectangle soundButtonBounds = new Rectangle(pos_x,
                pos_y, width, height);
        soundButton = new SoundButton(soundButtonBounds);
        addActor(soundButton);
    }

    private void setUpCamera() {
        camera = new OrthographicCamera();
        camera.position.set(0, 0, 0);
        camera.update();
    }

    @Override
    public OrthographicCamera getCamera() {
        return camera;
    }

}
