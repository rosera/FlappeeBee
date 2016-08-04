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
}
