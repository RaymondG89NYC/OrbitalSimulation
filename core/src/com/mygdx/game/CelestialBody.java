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
    private float externalXForce;
    private float externalYForce;
    private final float GRAVITATIONAL_CONSTANT = 0.001f;
    private final float DISTANCE_MULTIPLIER = 10f;
    private final float DEFAULT_RADIUS = 11f;
    private final float MAX_FORCE = 1000f;
    private ArrayList<CelestialBody> otherBodies;
    private int line;
    private float radius;
    public CelestialBody(Texture img, float x, float y, float mass, ArrayList<CelestialBody> otherBodies, int num){
        sprite = new Sprite(img);
        this.x = x;
        this.y = y;
        this.mass = mass;
        this.otherBodies = otherBodies;
        line = num;
        sprite.setPosition((float) x, (float) y);
        radius = 11;
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
        xForce += externalXForce;
        yForce += externalYForce;
        externalXForce = 0;
        externalYForce = 0;

        System.out.println("Number: " + line + ", xForce: " + xForce + ", yForce: " + yForce);
        xAccel = xForce / mass;
        yAccel = yForce / mass;
        xSpeed += xAccel;
        ySpeed += yAccel;
        x += xSpeed;
        y += ySpeed;
        sprite.setPosition(x, y);

        radius = calculateRadius(mass);
        sprite.setScale(radius/DEFAULT_RADIUS);
    }

    private float findXForce(int num) {
        float distance = (otherBodies.get(num).getX() - x) * DISTANCE_MULTIPLIER;
        float magnitude = (float) Math.sqrt(distance * distance);
        if (magnitude < 50f) {
            return 0;
        }
        float force = (GRAVITATIONAL_CONSTANT * mass * otherBodies.get(num).getMass()) / (magnitude * magnitude);
        return Math.max(-MAX_FORCE, Math.min(MAX_FORCE, force * (distance / magnitude)));
    }

    private float findYForce(int num) {
        float distance = (otherBodies.get(num).getY() - y) * DISTANCE_MULTIPLIER;
        float magnitude = (float) Math.sqrt(distance * distance);
        if (magnitude < 50f) {
            return 0;
        }
        float force = (GRAVITATIONAL_CONSTANT * mass * otherBodies.get(num).getMass()) / (magnitude * magnitude);
        return Math.max(-MAX_FORCE, Math.min(MAX_FORCE, force * (distance / magnitude)));
    }

    private float calculateRadius(float mass) {
        float density = 1f;
        float volume = mass / density;
        return (float) Math.pow((3 * volume) / (4 * Math.PI), 1 / 3f);
    }
    public boolean mouseTouched(float x, float y){
        return(this.x + radius >= x && this.x - radius <= x
        && this.y + radius >= y && this.y - radius <= y);
    }
    public void addExternalXForce(float force){
        externalXForce += force;
    }
    public void addExternalYForce(float force){
        externalYForce += force;
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
    public int getLine(){
        return line;
    }
    public float getRadius(){
        return radius;
    }
    public float getTotalForce(){
        return (float)Math.sqrt(Math.pow(xForce,2) + Math.pow(yForce,2));
    }
    public float getTotalVelocity(){
        return (float)Math.sqrt(Math.pow(xSpeed,2) + Math.pow(ySpeed,2));
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
