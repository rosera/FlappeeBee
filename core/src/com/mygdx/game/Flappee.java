package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;

/**
 * Created by rosera on 04/08/16.
 */
public class Flappee {
    private static final float COLLISION_RADIUS = 24f;
    private final Circle mCollisionCircle;
    private static final float FLY_ACCEL = 5F;
    private static  final float DIVE_ACCEL = 0.30F;
    private float mYAxisSpeed = 0;

    private float x = 0;
    private float y = 0;

    public Flappee() {
        mCollisionCircle = new Circle(x, y, COLLISION_RADIUS);
    }

    /*
     * Apply a circle to represent the bee object on screen
     */
    public void drawDebug(ShapeRenderer shapeRenderer) {
//        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(mCollisionCircle.x, mCollisionCircle.y, mCollisionCircle.radius);
    }

    private void updateCollisionCircle() {
        mCollisionCircle.setX(x);
        mCollisionCircle.setY(y);
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;

        updateCollisionCircle();
    }

    public void update() {
        mYAxisSpeed -= DIVE_ACCEL;
        setPosition(x, y + mYAxisSpeed);
    }

    public void flyUp() {
        mYAxisSpeed = FLY_ACCEL;
        setPosition(x, y + mYAxisSpeed);
    }

    // Return y position
    public float getY() {
        return y;
    }

    // Return x position
    public float getX() {
        return x;
    }

}
