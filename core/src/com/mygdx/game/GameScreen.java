package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by rosera on 04/08/16.
 */
public class GameScreen extends ScreenAdapter {
    private static final float WORLD_WIDTH = 480;
    private static final float WORLD_HEIGHT = 640;

    private ShapeRenderer   mShapeRenderer;
    private Viewport        mViewport;
    private Camera          mCamera;
    private SpriteBatch     mSpriteBatch;
    private Flappee         mFlappee;

    @Override
    public void show() {
        super.show();

        mCamera = new OrthographicCamera();
        mCamera.position.set(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0);
        mCamera.update();

        mViewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, mCamera);
        mShapeRenderer = new ShapeRenderer();
        mSpriteBatch = new SpriteBatch();

        // Initialise the Flappee object
        mFlappee = new Flappee();
        mFlappee.setPosition(WORLD_WIDTH / 4, WORLD_HEIGHT / 2);
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(Color.BLACK.r, Color.BLACK.g,
                Color.BLACK.b, Color.BLACK.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void render(float delta) {
//        super.render(delta);

        clearScreen();

        mSpriteBatch.setProjectionMatrix(mCamera.projection);
        mSpriteBatch.setTransformMatrix(mCamera.view);
        mSpriteBatch.begin();

        mSpriteBatch.end();

//        // Draw Flappee
//        mShapeRenderer.setProjectionMatrix(mCamera.projection);
//        mShapeRenderer.setTransformMatrix(mCamera.view);

        mShapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        mFlappee.drawDebug(mShapeRenderer);
        mShapeRenderer.end();
    }
}
