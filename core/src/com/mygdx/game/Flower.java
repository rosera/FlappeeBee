package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by rosera on 05/08/16.
 */
public class Flower {
    private static final float COLLISION_RECTANGLE_WIDTH = 13f;
    private static final float COLLISION_RECTANGLE_HEIGHT = 447f;
    private static final float COLLISION_CIRCLE_RADIUS = 33f;

    private final Rectangle mCollisionRectangle;
    private final Circle    mCollisionCircle;

    private float x = 0;
    private float y = 0;

    public Flower() {

        // Initialise mCollisionRectangle
        this.mCollisionRectangle = new Rectangle(x, y, COLLISION_RECTANGLE_WIDTH,
                COLLISION_RECTANGLE_HEIGHT);

        // Initialise mCollisionCircle
        this.mCollisionCircle = new Circle(x + mCollisionRectangle.width / 2,
                y + mCollisionRectangle.height, COLLISION_CIRCLE_RADIUS);
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

}
