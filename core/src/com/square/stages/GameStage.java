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
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.square.actors.Wall;
import com.square.actors.Square;
import com.square.utils.WorldUtils;

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

    public GameStage() {
        setUpWorld();
        setUpCamera();
        Gdx.input.setInputProcessor(this);
        touchPoint = new Vector3();
        touchDown = new Vector2();
        renderer = new Box2DDebugRenderer();
    }

    private void setUpWorld() {
        world = WorldUtils.createWorld();
        world.setContactListener(this);
     //   setUpWall();
        setUpRunner();
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

/*    private void setUpWall() {
        wall = new Wall(WorldUtils.createWall(world));
        addActor(wall);
    }*/

    private void setUpRunner() {
        square = new Square(WorldUtils.createSquare(world));
        //TODO: set a color
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

    @Override
    public void draw() {
        super.draw();
        renderer.render(world, camera.combined);
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        translateScreenToWorldCoordinates(x, y);
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
        Vector2 actual = new Vector2(screenX, screenY);
        square.move(actual, touchDown);

        return super.touchDragged(screenX, screenY, pointer);
    }

    private void translateScreenToWorldCoordinates(int x, int y) {
        getCamera().unproject(touchPoint.set(x, y, 0));
    }
}