package com.square.stages;

import com.badlogic.gdx.Game;
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
import com.square.actors.Background;
import com.square.actors.Border;
import com.square.actors.Circle;
import com.square.actors.EnemyDetector;
import com.square.actors.MapBackground;
import com.square.actors.Wall;
import com.square.actors.Square;
import com.square.actors.menu.GameButton;
import com.square.actors.menu.PauseButton;
import com.square.enums.DetectorDirection;
import com.square.enums.UserDataType;
import com.square.screens.MenuScreen;
import com.square.utils.BodyUtils;
import com.square.utils.WorldUtils;

import static com.square.enums.UserDataType.SQUARE;
import static com.square.utils.Constants.BORDER_HEIGTH;
import static com.square.utils.Constants.BORDER_WIDTH;

import static com.square.utils.Constants.GAME_BUTTON_HEIGHT;
import static com.square.utils.Constants.GAME_BUTTON_WIDTH;
import static com.square.utils.Constants.MENU_BUTTON_HEIGHT;
import static com.square.utils.Constants.MENU_BUTTON_WIDTH;
import static com.square.utils.Constants.DEFAULT_SCREEN_HEIGHT;
import static com.square.utils.Constants.DEFAULT_SCREEN_WIDTH;
import static com.square.utils.Constants.NEW_COORDINATE_PLANE_DELTA;
import static com.square.utils.Resources.MAP_BACKGROUND;
import static com.square.utils.Resources.OUTER_MAP_BACKGROUND;

public class GameStage extends Stage implements ContactListener {

    private final Game game;
    private World world;
    private Wall wall;
    private Square square;
    private MapBackground mapBackground;

    private PauseButton pauseButton;

    private final float TIME_STEP = 1 / 300f;
    private float accumulator = 0f;

    private OrthographicCamera camera;
    private Box2DDebugRenderer renderer;

    private Vector3 touchPoint;

    private Vector2 touchDown;
    private Vector2 actual;
    private Vector2 previous;
    private Background background;

    public GameStage(Game game) {
        this.game = game;
        setUpWorld();
        setUpCamera();
        setUpControls();
        setUpBackground();
        setUpGameObjects();
        setUpGameButtons();
        Gdx.input.setInputProcessor(this);
        renderer = new Box2DDebugRenderer();
    }


    private void setUpWorld() {
        world = WorldUtils.createWorld();
        world.setContactListener(this);
    }

    private void setUpGameObjects() {
        setUpBackground();
        setUpBorder();
        setUpWall();
        setUpCircle();
        setUpSquare();
    }

    private void setUpControls() {
        touchPoint = new Vector3();
        touchDown = new Vector2();
    }

    private void setUpGameButtons() {
        setUpPause();
    }

    @Override
    public void beginContact(Contact contact) {

        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();

        if ((BodyUtils.bodyIsDetector(a) && BodyUtils.bodyIsSquare(b)) ||
                (BodyUtils.bodyIsSquare(a) && BodyUtils.bodyIsDetector(b))) {
            System.out.println("Detector - Square");
        }

        if ((BodyUtils.bodyIsCircle(a) && BodyUtils.bodyIsDetector(b)) ||
                (BodyUtils.bodyIsDetector(a) && BodyUtils.bodyIsCircle(b))) {
            System.out.println("Circle - Detector");
        }

        if ((BodyUtils.bodyIsCircle(a) && BodyUtils.bodyIsSquare(b)) ||
                (BodyUtils.bodyIsSquare(a) && BodyUtils.bodyIsCircle(b))) {
            System.out.println("Circle - Square");
        }
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

    private void setUpBackground() {
        background = new Background(OUTER_MAP_BACKGROUND);
        mapBackground = new MapBackground(
                camera, MAP_BACKGROUND, BORDER_WIDTH, BORDER_HEIGTH);

        addActor(background);
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

    private void setUpDetector() {
        addActor(new EnemyDetector(
                WorldUtils.createEnemyDetector(world, square.getBody(), DetectorDirection.LEFT),
                square.getBody()));
        addActor(new EnemyDetector(
                WorldUtils.createEnemyDetector(world, square.getBody(), DetectorDirection.RIGHT),
                square.getBody()));
        addActor(new EnemyDetector(
                WorldUtils.createEnemyDetector(world, square.getBody(), DetectorDirection.UP),
                square.getBody()));
        addActor(new EnemyDetector(
                WorldUtils.createEnemyDetector(world, square.getBody(), DetectorDirection.DOWN),
                square.getBody()));
    }

    private void setUpCircle() {
        addActor(new Circle(WorldUtils.createCircle(world), camera));
    }

    private void setUpSquare() {
        square = new Square(WorldUtils.createSquare(world), camera);
        addActor(square);
        setUpDetector();
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
    @Override
    public void draw() {
        super.draw();
        renderer.render(world, camera.combined);
    }


    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        screenToWorld(x, y);
        touchDown.set(x, y);

        return super.touchDown(x, y, pointer, button);
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        square.stop();

        return super.touchUp(x, y, pointer, button);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
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

        return super.touchDragged(screenX, screenY, pointer);
    }

    private void screenToWorld(int x, int y) {
        getCamera().unproject(touchPoint.set(x, y, 0));
    }

    private void setUpPause() {
        float coef_y = Gdx.graphics.getHeight() / DEFAULT_SCREEN_HEIGHT;
        float pos_x = 50;
        float width = GAME_BUTTON_WIDTH * coef_y;
        float height = GAME_BUTTON_HEIGHT * coef_y;
        float pos_y = (Gdx.graphics.getHeight() - height - 50);

        Rectangle pauseButtonBounds = new Rectangle(pos_x, pos_y, width, height);
        pauseButton = new PauseButton(pauseButtonBounds, new GamePauseButtonListener());
        addActor(pauseButton);
    }

    private class GamePauseButtonListener implements PauseButton.PauseButtonListener {
        @Override
        public void onPause() {
            clear();
            game.dispose();
        }
    }
}

