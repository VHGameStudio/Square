package com.square.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
import com.square.actors.Wall;
import com.square.actors.Square;
import com.square.utils.WorldUtils;

import static com.square.utils.Constants.NEW_COORDINATE_PLANE_DELTA;

public class GameStage extends Stage implements ContactListener {

    private World world;
    private Wall wall;
    private Square square;

    private final float TIME_STEP = 1 / 300f;
    private float accumulator = 0f;

    private OrthographicCamera camera;
    private Box2DDebugRenderer renderer;

    private Vector3 touchPoint;

    private Vector2 touchDown;
    private Vector2 actual;
    private Vector2 previous;

    public GameStage() {
        setUpCamera();
        setUpWorld();
        Gdx.input.setInputProcessor(this);
        touchPoint = new Vector3();
        touchDown = new Vector2();
        renderer = new Box2DDebugRenderer();
    }

    private void setUpWorld() {
        world = WorldUtils.createWorld();
        world.setContactListener(this);
        setUpBackground();
        setUpWall();
        setUpSquare();
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

    private void setUpWall() {
        for (int i = 0; i < 5; ++i) {
            addActor(new Wall(WorldUtils.createWall(world), camera));
        }
    }

    private void setUpSquare() {
        square = new Square(WorldUtils.createSquare(world), camera);
        addActor(square);
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

    private void setUpBackground() {
        addActor(new Background());
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
}