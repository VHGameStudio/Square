package com.square.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.square.actors.Background;
import com.square.actors.Border;
import com.square.actors.Circle;
import com.square.actors.MapBackground;
import com.square.actors.Wall;
import com.square.actors.Square;
import com.square.actors.menu.PlayButton;
import com.square.actors.menu.SettingsButton;
import com.square.actors.menu.SoundButton;
import com.square.enums.GameState;
import com.square.utils.BodyUtils;
import com.square.utils.WorldUtils;

import static com.square.utils.Constants.BORDER_HEIGTH;
import static com.square.utils.Constants.BORDER_WIDTH;
import static com.square.utils.Constants.BUTTON_DELTA;
import static com.square.utils.Constants.BUTTON_HEIGHT;
import static com.square.utils.Constants.BUTTON_WIDTH;
import static com.square.utils.Constants.BUTTON_Y;
import static com.square.utils.Constants.DEFAULT_SCREEN_HEIGHT;
import static com.square.utils.Constants.DEFAULT_SCREEN_WIDTH;
import static com.square.utils.Resources.BACKGROUND_MENU;

import static com.square.utils.Constants.NEW_COORDINATE_PLANE_DELTA;
import static com.square.utils.Resources.MAP_BACKGROUND;
import static com.square.utils.Resources.OUTER_MAP_BACKGROUND;

public class GameStage extends Stage implements ContactListener {

    private World world;
    private Wall wall;
    private Square square;
    private Background background;
    private MapBackground mapBackground;
    private Background logo;

    private GameState gameState;

    private PlayButton playButton;
    private SettingsButton settingsButton;
    private SoundButton soundButton;

    private final float TIME_STEP = 1 / 300f;
    private float accumulator = 0f;

    private OrthographicCamera camera;
    private Box2DDebugRenderer renderer;

    private Vector3 touchPoint;

    private Vector2 touchDown;
    private Vector2 actual;
    private Vector2 previous;

    public GameStage() {
        gameState = GameState.MENU;
        setUpCamera();
        setUpWorld();
        setUpMainMenu();
        Gdx.input.setInputProcessor(this);
        renderer = new Box2DDebugRenderer();
    }


    private void setUpWorld() {
        world = WorldUtils.createWorld();
        world.setContactListener(this);
        setUpBackground();
    }

    private void setUpGameObjects() {
        setUpMapBackground();
        setUpBorder();
        setUpWall();
        setUpCircle();
        setUpSquare();
    }

    private void setUpControls() {
        touchPoint = new Vector3();
        touchDown = new Vector2();
    }

    private class GamePlayButtonListener implements PlayButton.PlayButtonListener {

        @Override
        public void onPlay() {
            clear();
            gameState = GameState.RUNNING;
            setUpStageBase();

        }
    }

    private class GameSettingsButtonListener implements SettingsButton.SettingsButtonListener {

        @Override
        public void onSettings() {

        }
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

    private void setUpStageBase() {
        setUpWorld();
        setUpGameObjects();
        setUpControls();
    }

    @Override
    public void beginContact(Contact contact) {

        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();

/*        if ((BodyUtils.bodyIsSquare(a) && BodyUtils.bodyIsWall(b)) ||
                (BodyUtils.bodyIsWall(a) && BodyUtils.bodyIsSquare(b))) {
            if (BodyUtils.bodyIsWall(a)) {
              //  a.setUserData(null);
                world.destroyBody(a);

            } else {
             //   b.setUserData(null);
            }
        }*/

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    private void setUpMapBackground() {
        mapBackground = new MapBackground(
                camera, MAP_BACKGROUND, BORDER_WIDTH, BORDER_HEIGTH);
        addActor(mapBackground);
    }

    private void setUpBorder() {
        addActor(new Border(WorldUtils.createBorder(
                world,
                new Vector2(0 - BORDER_WIDTH / 2, 0 - BORDER_HEIGTH / 2),
                new Vector2(BORDER_WIDTH / 2, 0 - BORDER_HEIGTH / 2)), camera));
        addActor(new Border(WorldUtils.createBorder(
                world,
                new Vector2(0 - BORDER_WIDTH / 2, BORDER_HEIGTH / 2),
                new Vector2(BORDER_WIDTH / 2, BORDER_HEIGTH / 2)), camera));
        addActor(new Border(WorldUtils.createBorder(
                world,
                new Vector2(0 - BORDER_WIDTH / 2, 0 - BORDER_HEIGTH / 2),
                new Vector2(0 - BORDER_WIDTH / 2, BORDER_HEIGTH / 2)), camera));
        addActor(new Border(WorldUtils.createBorder(
                world,
                new Vector2(BORDER_WIDTH / 2, 0 - BORDER_HEIGTH / 2),
                new Vector2(BORDER_WIDTH / 2, BORDER_HEIGTH / 2)), camera));
    }

    private void setUpWall() {
        for (int i = 0; i < 5; ++i) {
            addActor(new Wall(WorldUtils.createWall(world), camera));
        }
    }

    private void setUpCircle() {
        //TODO: a lot of things for circles...
        addActor(new Circle(WorldUtils.createCircle(world), camera));
    }

    private void setUpSquare() {
        square = new Square(WorldUtils.createSquare(world), camera);
        addActor(square);
    }

    private void setUpBackground() {
        if (background != null) {
            background.addAction(Actions.removeActor());
        }

        if (gameState == GameState.MENU) {
            background = new Background(BACKGROUND_MENU);
        }
        if (gameState == GameState.RUNNING) {
            background = new Background(OUTER_MAP_BACKGROUND);
        }

        addActor(background);
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

    @Override
    public void act(float delta) {
        super.act(delta);

        accumulator += delta;

        while (accumulator >= delta) {
            world.step(TIME_STEP, 6, 2);
            accumulator -= TIME_STEP;
        }
    }

    //TODO: comment this after we put the textures on
/*    @Override
    public void draw() {
        super.draw();
        renderer.render(world, camera.combined);
    }*/

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        if (GameState.RUNNING == gameState) {
            screenToWorld(x, y);
            touchDown.set(x, y);
        }
        return super.touchDown(x, y, pointer, button);
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        if (GameState.RUNNING == gameState) {
            square.stop();
        }
        return super.touchUp(x, y, pointer, button);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (GameState.RUNNING == gameState) {
            actual = new Vector2(screenX, screenY);
            Vector2 tmp;

            if (previous != null) {
                tmp = new Vector2(
                        actual.x - previous.x,
                        actual.y - previous.y);

                if (tmp.len() < NEW_COORDINATE_PLANE_DELTA) {
                    touchDown = previous;
                }
            }

            square.move(actual, touchDown);
            previous = actual;
        }

        return super.touchDragged(screenX, screenY, pointer);
    }

    private void screenToWorld(int x, int y) {
        getCamera().unproject(touchPoint.set(x, y, 0));
    }
}