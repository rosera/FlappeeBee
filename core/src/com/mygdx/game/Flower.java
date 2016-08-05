package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
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

    public static final float WIDTH = COLLISION_CIRCLE_RADIUS * 2;


    private final Rectangle mCollisionRectangle;
    private final Circle    mCollisionCircle;

    private float x = 0;
    private float y = 0;

    public Flower() {

        // Make the Flowers a random height
        this.y = MathUtils.random(HEIGHT_OFFSET);

        // Initialise mCollisionRectangle
        this.mCollisionRectangle = new Rectangle(x, y, COLLISION_RECTANGLE_WIDTH,
                COLLISION_RECTANGLE_HEIGHT);

        // Initialise mCollisionCircle
        this.mCollisionCircle = new Circle(x + mCollisionRectangle.width / 2,
                y + mCollisionRectangle.height, COLLISION_CIRCLE_RADIUS);
    }

    // Make the flower scroll at MAX_SPEED_PER_SECOND * delta
    public void update(float delta) {
        setPosition(x - (MAX_SPEED_PER_SECOND * delta));
    }


    public void updateCollisionCircle() {
        mCollisionCircle.setX(x + mCollisionRectangle.width / 2);
        mCollisionCircle.setY(y + mCollisionRectangle.height);
    }

    public void updateCollisionRectangle() {
        mCollisionRectangle.setX(x);
    }


    public void setPosition(float x) {
        this.x = x;
        updateCollisionCircle();
        updateCollisionRectangle();
    }

    public void drawDebug(ShapeRenderer shapeRenderer) {
        shapeRenderer.circle(mCollisionCircle.x, mCollisionCircle.y,
                mCollisionCircle.radius);
        shapeRenderer.rect(mCollisionRectangle.x, mCollisionRectangle.y,
                mCollisionRectangle.width, mCollisionRectangle.height);
    }

    public float getX() {
        return this.x;
    }
}
