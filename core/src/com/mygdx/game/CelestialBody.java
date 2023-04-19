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
    private final float GRAVITATIONAL_CONSTANT = 0.000001f;
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
    public void update(){
        xAccel = 0;
        yAccel = 0;
        xForce = 0;
        yForce = 0;
        for(int i = 0; i < otherBodies.size(); i++) {
            if(i != line) {
                xForce += findXForce(i);
                yForce += findYForce(i);
                yAccel = xForce / mass;
                xAccel = yForce / mass;
//                xAccel = xForce / mass;
//                yAccel = yForce / mass;
            }
        }
        xSpeed += xAccel;
        ySpeed += yAccel;

        x+=(float)xSpeed;
        y+=(float)ySpeed;
        sprite.setPosition((float) x, (float) y);
//        sprite.translate((float)xSpeed, (float)ySpeed);
    }

    public float findXForce(int num){
        if((otherBodies.get(num).getX()-x) >= -1f && (otherBodies.get(num).getX()-x) <= 1f){
            return 0;
        }
//        System.out.println("Top val: " + (GRAVITATIONAL_CONSTANT*mass* otherBodies.get(num).getMass()));
        System.out.println("Denominator " + num + ":" + ((Math.pow((otherBodies.get(num).getX()-x)*1,2)) * Math.signum(otherBodies.get(num).getX()-x)));
        return (float)((GRAVITATIONAL_CONSTANT*mass* otherBodies.get(num).getMass())
                /
                ((Math.pow((otherBodies.get(num).getX()-x)*10,2)) * Math.signum(otherBodies.get(num).getX()-x)));
    }
    public float findYForce(int num){
        if((otherBodies.get(num).getY()-y) >= -1f && (otherBodies.get(num).getY()-y) <= 1f){
            return 0;
        }
        return (float)((GRAVITATIONAL_CONSTANT*mass* otherBodies.get(num).getMass())
                /
                ((Math.pow((otherBodies.get(num).getY()-y)* 10, 2)) * Math.signum(otherBodies.get(num).getY()-y)));
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
