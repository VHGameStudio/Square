package com.square.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Background extends Actor {
    private Texture texture;
    private float width;
    private float height;

    public Background(String texture_dir) {
        this.texture = new Texture(texture_dir);
        height = Gdx.graphics.getHeight();
        width = Gdx.graphics.getWidth();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(texture, 0, 0, width, height);
    }
}