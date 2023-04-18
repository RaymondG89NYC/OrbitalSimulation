package com.mygdx.game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.*;

public class CelestialBody {
    private Sprite sprite;
    private double mass;
    private double x;
    private double y;
    private double xSpeed;
    private double ySpeed;
    private double xAccel;
    private double yAccel;
    private double xForce;
    private double yForce;
    private final double GRAVITATIONAL_CONSTANT = 100;
    private ArrayList<CelestialBody> otherBodies;
    private int line;
    public CelestialBody(Texture img, double x, double y, double mass, ArrayList<CelestialBody> otherBodies, int num){
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
        for(int i = 0; i < otherBodies.size(); i++) {
            if(i != line) {
                xAccel = findXForce(i) / mass;
                yAccel = findYForce(i) / mass;
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
        if((otherBodies.get(num).getX()-x) == 0){
            return 0;
        }
//        System.out.println("Top val: " + (GRAVITATIONAL_CONSTANT*mass* otherBodies.get(num).getMass()));
//        System.out.println("Bottom Bal: " + (Math.pow((otherBodies.get(num).getX()-x)*1,2)));
        return (float)((GRAVITATIONAL_CONSTANT*mass* otherBodies.get(num).getMass())
                /
                ((Math.pow((otherBodies.get(num).getX()-x)*1,2)) * Math.signum(otherBodies.get(num).getX()-x)));
    }
    public float findYForce(int num){
        if((otherBodies.get(num).getY()-y) == 0){
            return 0;
        }
        return (float)((GRAVITATIONAL_CONSTANT*mass* otherBodies.get(num).getMass())
                /
                (Math.pow((otherBodies.get(num).getY()-y)*1, 2) * Math.signum(otherBodies.get(num).getY()-y)));
    }


    public double getX(){
        return x;
    }
    public double getY() {
        return y;
    }
    public double getMass() {
        return mass;
    }
    public double getxAccel() {
        return xAccel;
    }
    public double getxForce() {
        return xForce;
    }
    public double getxSpeed() {
        return xSpeed;
    }
    public double getyAccel() {
        return yAccel;
    }
    public double getyForce() {
        return yForce;
    }
    public double getySpeed() {
        return ySpeed;
    }

    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
    public void setMass(double mass) {
        this.mass = mass;
    }

    public void setxAccel(double xAccel) {
        this.xAccel = xAccel;
    }
    public void setxForce(double xForce) {
        this.xForce = xForce;
    }
    public void setxSpeed(double xSpeed) {
        this.xSpeed = xSpeed;
    }
    public void setyAccel(double yAccel) {
        this.yAccel = yAccel;
    }
    public void setyForce(double yForce) {
        this.yForce = yForce;
    }
    public void setySpeed(double ySpeed) {
        this.ySpeed = ySpeed;
    }
}
