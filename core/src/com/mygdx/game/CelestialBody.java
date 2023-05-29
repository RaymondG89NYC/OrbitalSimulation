package com.mygdx.game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.*;

public class CelestialBody {
    private Sprite sprite;
    private float mass;
    private float x;
    private float y;
    private float xSpeed;
    private float ySpeed;
    private float xAccel;
    private float yAccel;
    private float xForce;
    private float yForce;
    private final float GRAVITATIONAL_CONSTANT = 1000000f;
    private final float DISTANCE_MULTIPLIER = 1f;
    private ArrayList<CelestialBody> otherBodies;
    private int line;
    public CelestialBody(Texture img, float x, float y, float mass, ArrayList<CelestialBody> otherBodies, int num){
        sprite = new Sprite(img);
        this.x = x;
        this.y = y;
        this.mass = mass;
        this.otherBodies = otherBodies;
        line = num;
        sprite.setPosition((float) x, (float) y);
    }

    public void draw(SpriteBatch batch){

        sprite.draw(batch);
    }
    public void update() {
        xForce = 0;
        yForce = 0;
        xAccel = 0;
        yAccel = 0;

        for (int i = 0; i < otherBodies.size(); i++) {
            if (i != line) {
                xForce += findXForce(i);
                yForce += findYForce(i);
            }
        }

        xAccel = xForce / mass;
        yAccel = yForce / mass;
        xSpeed += xAccel;
        ySpeed += yAccel;
        x += xSpeed;
        y += ySpeed;
        sprite.setPosition(x, y);
    }

    public float findXForce(int num) {
        float distance = (otherBodies.get(num).getX() - x) * DISTANCE_MULTIPLIER;
        float magnitude = (float) Math.sqrt(distance * distance);
        if (magnitude < 0.1f) {
            return 0;
        }
        float force = (GRAVITATIONAL_CONSTANT * mass * otherBodies.get(num).getMass()) / (magnitude * magnitude);
        return Math.max(-10000f, Math.min(10000f, force * (distance / magnitude)));
    }

    public float findYForce(int num) {
        float distance = (otherBodies.get(num).getY() - y) * DISTANCE_MULTIPLIER;
        float magnitude = (float) Math.sqrt(distance * distance);
        if (magnitude < 0.1f) {
            return 0;
        }
        float force = (GRAVITATIONAL_CONSTANT * mass * otherBodies.get(num).getMass()) / (magnitude * magnitude);
        return Math.max(-10000f, Math.min(10000f, force * (distance / magnitude)));
    }


    public float getX(){
        return x;
    }
    public float getY() {
        return y;
    }
    public float getMass() {
        return mass;
    }
    public float getxAccel() {
        return xAccel;
    }
    public float getxForce() {
        return xForce;
    }
    public float getxSpeed() {
        return xSpeed;
    }
    public float getyAccel() {
        return yAccel;
    }
    public float getyForce() {
        return yForce;
    }
    public float getySpeed() {
        return ySpeed;
    }

    public void setX(float x) {
        this.x = x;
    }
    public void setY(float y) {
        this.y = y;
    }
    public void setMass(float mass) {
        this.mass = mass;
    }

    public void setxAccel(float xAccel) {
        this.xAccel = xAccel;
    }
    public void setxForce(float xForce) {
        this.xForce = xForce;
    }
    public void setxSpeed(float xSpeed) {
        this.xSpeed = xSpeed;
    }
    public void setyAccel(float yAccel) {
        this.yAccel = yAccel;
    }
    public void setyForce(float yForce) {
        this.yForce = yForce;
    }
    public void setySpeed(float ySpeed) {
        this.ySpeed = ySpeed;
    }
}
