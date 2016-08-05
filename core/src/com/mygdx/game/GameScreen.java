package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by rosera on 04/08/16.
 */
public class GameScreen extends ScreenAdapter {
    private static final float WORLD_WIDTH = 480;
    private static final float WORLD_HEIGHT = 640;
    private static final float GAP_BETWEEN_FLOWERS = 200f;

    private ShapeRenderer   mShapeRenderer;
    private Viewport        mViewport;
    private Camera          mCamera;
    private SpriteBatch     mSpriteBatch;
    private Flappee         mFlappee;
    private Array<Flower>   mFlowers;

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

        // Initialise the Flower object
        mFlowers = new Array<Flower>();
    }

    // Clear the screen
    private void clearScreen() {
        Gdx.gl.glClearColor(Color.BLACK.r, Color.BLACK.g,
                Color.BLACK.b, Color.BLACK.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    // Update the for each screen cycle
    private void update(float delta) {
        mFlappee.update();

        // Check whether the user needs to fly up
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE))
            mFlappee.flyUp();

        // Add world constraints
        blockFlappeeLevingTheWorld();

        updateFlowers(delta);
    }

    // Output the screen
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

        // Update the flappee bee object
        update(delta);

        // Update the Flowers
        for (Flower flower: mFlowers) {
            flower.drawDebug(mShapeRenderer);
        }

        mShapeRenderer.end();

    }


    // Limit the viewport to the defined WORLD_HEIGHT
    private void blockFlappeeLevingTheWorld() {
        mFlappee.setPosition(mFlappee.getX(),
                MathUtils.clamp(mFlappee.getY(), 0, WORLD_HEIGHT));
    }


    // Create onscreen flowers
    private void createNewFlower() {
        Flower newFlower = new Flower();
        newFlower.setPosition((WORLD_WIDTH + Flower.WIDTH));
        mFlowers.add(newFlower);
    }

    private void checkIfNewFlowerIsNeeded() {
        if (mFlowers.size == 0) {
            createNewFlower();
        } else {
            Flower flower = mFlowers.peek();
            if (flower.getX() < WORLD_WIDTH - GAP_BETWEEN_FLOWERS) {
                createNewFlower();
            }
        }
    }

    private void removeFlowersIfPassed() {
        if (mFlowers.size > 0) {
            Flower firstFlower = mFlowers.first();
            if (firstFlower.getX() < -Flower.WIDTH) {
                mFlowers.removeValue(firstFlower, true);
            }
        }
    }

    private void updateFlowers(float delta) {
        for (Flower flower: mFlowers) {
            flower.update(delta);
        }

        checkIfNewFlowerIsNeeded();
        removeFlowersIfPassed();
    }

}
