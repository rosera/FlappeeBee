package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by rosera on 05/08/16.
 */
public class Flower {
    private static final float COLLISION_RECTANGLE_WIDTH = 13f;
    private static final float COLLISION_RECTANGLE_HEIGHT = 447f;
    private static final float COLLISION_CIRCLE_RADIUS = 33f;
    private static final float MAX_SPEED_PER_SECOND = 100f;
    private static final float HEIGHT_OFFSET = -400f;

    private static final float DISTANCE_BETWEEN_FLOOR_AND_CEILING = 225f;
    public static final float WIDTH = COLLISION_CIRCLE_RADIUS * 2;

    private final Circle    mFloorCollisionCircle;
    private final Rectangle mFloorCollisionRectangle;

    private final Circle    mCeilingCollisionCircle;
    private final Rectangle mCeilingCollisionRectangle;

    private float x = 0;
    private float y = 0;

    public Flower() {
        // Make the Flowers a random height
        this.y = MathUtils.random(HEIGHT_OFFSET);

        // Initialise the floor flowers
        this.mFloorCollisionRectangle = new Rectangle(x, y,
                COLLISION_RECTANGLE_WIDTH, COLLISION_RECTANGLE_HEIGHT);

        this.mFloorCollisionCircle = new Circle(
                x + mFloorCollisionRectangle.width / 2,
                y + mFloorCollisionRectangle.height,
                COLLISION_CIRCLE_RADIUS);

        // Initialise the ceiling flowers
        this.mCeilingCollisionRectangle = new Rectangle(
                x, mFloorCollisionCircle.y + DISTANCE_BETWEEN_FLOOR_AND_CEILING,
                COLLISION_RECTANGLE_WIDTH, COLLISION_RECTANGLE_HEIGHT);

        this.mCeilingCollisionCircle = new Circle(
                x + mCeilingCollisionRectangle.width / 2,
                mCeilingCollisionRectangle.y, COLLISION_CIRCLE_RADIUS);
    }

    // Make the flower scroll at MAX_SPEED_PER_SECOND * delta
    public void update(float delta) {
        setPosition(x - (MAX_SPEED_PER_SECOND * delta));
    }


    public void updateCollisionCircle() {
        // Set the floor based Flowers
        mFloorCollisionCircle.setX(x + mFloorCollisionRectangle.width / 2);
        mFloorCollisionCircle.setY(y + mFloorCollisionRectangle.height);

        // Set the ceiling based flowers
        mCeilingCollisionCircle.setX(x + mCeilingCollisionRectangle.width / 2);
    }

    public void updateCollisionRectangle() {
        mFloorCollisionRectangle.setX(x);
        mCeilingCollisionRectangle.setX(x);
    }


    public void setPosition(float x) {
        this.x = x;
        updateCollisionCircle();
        updateCollisionRectangle();
    }

    public void drawDebug(ShapeRenderer shapeRenderer) {
        // Draw the floor based Flowers
        shapeRenderer.circle(mFloorCollisionCircle.x, mFloorCollisionCircle.y,
                mFloorCollisionCircle.radius);
        shapeRenderer.rect(mFloorCollisionRectangle.x, mFloorCollisionRectangle.y,
                mFloorCollisionRectangle.width, mFloorCollisionRectangle.height);

        // Draw the ceiling based Flowers
        shapeRenderer.circle(mCeilingCollisionCircle.x, mCeilingCollisionCircle.y,
                mCeilingCollisionCircle.radius);
        shapeRenderer.rect(mCeilingCollisionCircle.x, mCeilingCollisionCircle.y,
                mCeilingCollisionRectangle.width, mCeilingCollisionRectangle.height);
    }

    public float getX() {
        return this.x;
    }

    // Add collision detection
    public boolean isFlappeeColliding(Flappee flappee) {
        Circle flappeeCollisionCircle = flappee.getCollisionCircle();

        return  Intersector.overlaps(flappeeCollisionCircle, mCeilingCollisionCircle) ||
                Intersector.overlaps(flappeeCollisionCircle, mFloorCollisionCircle) ||
                Intersector.overlaps(flappeeCollisionCircle, mCeilingCollisionRectangle) ||
                Intersector.overlaps(flappeeCollisionCircle, mFloorCollisionRectangle);
    }
}
