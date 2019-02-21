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
import com.square.actors.Background;
import com.square.actors.Border;
import com.square.actors.Circle;
import com.square.actors.Wall;
import com.square.actors.Square;
import com.square.actors.menu.PlayButton;
import com.square.enums.GameState;
import com.square.utils.WorldUtils;

import static com.square.utils.Constants.BORDER_HEIGTH;
import static com.square.utils.Constants.BORDER_WIDTH;
import static com.square.utils.Constants.NEW_COORDINATE_PLANE_DELTA;

public class GameStage extends Stage implements ContactListener {

    private World world;
    private Wall wall;
    private Square square;

    private GameState gameState;

    private PlayButton playButton;

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

    private class GamePlayButtonListener implements PlayButton.PlayButtonListener {

        @Override
        public void onStart() {
            clear();
            setUpStageBase();
            gameState = GameState.RUNNING;
        }
    }

    private void setUpWorld() {
        world = WorldUtils.createWorld();
        world.setContactListener(this);
        setUpBackground();
    }

    private void setUpGameObjects() {
        setUpWall();
        setUpBorder();
        setUpCircle();
        setUpSquare();
    }

    private void setUpControls() {
        touchPoint = new Vector3();
        touchDown = new Vector2();
    }

    private void setUpMainMenu() {
        setUpPlay();
    }

    private void setUpPlay() {
        float width = 150;
        float height = 150;
        float pos_x = (getCamera().position.x + Gdx.graphics.getWidth() - width) / 2;
        float pos_y = (getCamera().position.y + Gdx.graphics.getHeight() - height) / 2;
        Rectangle playButtonBounds = new Rectangle(pos_x,
                pos_y, width,
                height);
        playButton = new PlayButton(playButtonBounds, new GamePlayButtonListener());
        addActor(playButton);
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
        addActor(new Background());
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