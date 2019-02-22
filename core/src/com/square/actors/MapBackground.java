package com.square.actors;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.square.actors.GameActor.worldToScreen;

public class MapBackground extends Actor {
    private Texture texture;
    private float width;
    private float height;
    private OrthographicCamera camera;

    public MapBackground(OrthographicCamera camera,
                         String texture_dir,
                         float width,
                         float height)
    {
        this.camera = camera;
        this.texture = new Texture(texture_dir);
        this.height = height;
        this.width = width;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        Vector3 wToS = new Vector3(0 - width / 2, 0 - height / 2, 0);
        camera.project(wToS);

        batch.draw(
                texture,
                wToS.x,
                wToS.y,
                worldToScreen(width),
                worldToScreen(height));
    }

}
