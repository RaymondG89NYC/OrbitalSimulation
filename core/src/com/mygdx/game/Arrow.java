package com.mygdx.game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.*;

public class Arrow {
    private Sprite sprite;
    private CelestialBody star;
    private float length;
    private float x;
    private float y;
    private float angle;
    private float mouseX;
    private float mouseY;


    public Arrow(Texture img, float x, float y, CelestialBody star, int num){
        sprite = new Sprite(img);
        this.star = star;
    }
    public void draw(SpriteBatch batch){
        sprite.draw(batch);
    }
    public void update() {
        angle = Helper.findDegree(this, star);
        x = star.getX();
        y = star.getY();
        sprite.setPosition(x, y);
        sprite.setRotation(angle);
        sprite.setScale(star.getTotalVelocity()*2, star.getRadius()/11);
    }
    public void setMouseX(float x){
        mouseX = x;
    }

    public void setMouseY(float mouseY) {
        this.mouseY = mouseY;
    }
}
