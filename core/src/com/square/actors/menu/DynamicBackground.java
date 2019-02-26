package com.square.actors.menu;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.square.actors.GameActor;
import com.square.box2d.DynamicBackgroundUserData;
import com.square.utils.AssetsManager;

public class DynamicBackground extends GameActor {
    Skin skin;
    TextureRegion textureRegion;

    public DynamicBackground(Body body, OrthographicCamera camera, Integer color) {
        super(body);
        this.camera = camera;

        skin = new Skin(AssetsManager.getColorsTextureAtlas());
        textureRegion = skin.getRegion(color.toString());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.draw(
                textureRegion,
                screenRectangle.x,
                screenRectangle.y,
                screenRectangle.width,
                screenRectangle.height);
    }

    @Override
    public DynamicBackgroundUserData getUserData() {
        return (DynamicBackgroundUserData) userData;
    }

}
