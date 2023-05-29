package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.Color;
import org.w3c.dom.Text;

import java.util.ArrayList;


public class Simulation extends ApplicationAdapter {
	SpriteBatch batch;
	Texture planet;
	Texture star1;
	Texture star2;
	Texture star3;
	Texture star4;
	Texture star5;
	Texture star6;
	ArrayList<Texture> stars;
	ArrayList<CelestialBody> celestialBodies;
	int mouseTestFrames;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		planet = new Texture("planet.png");
		star1 = new Texture("star.png");
		star2 = new Texture("pixil-frame-0.png");
		star3 = new Texture("pixil-frame-0(1).png");
		star4 = new Texture("pixil-frame-0(2).png");
		star5 = new Texture("pixil-frame-0(3).png");
		star6 = new Texture("pixil-frame-0(4).png");
		stars = new ArrayList<Texture>();
		stars.add(star1);
		stars.add(star2);
		stars.add(star3);
		stars.add(star4);
		stars.add(star5);
		stars.add(star6);
		celestialBodies = new ArrayList<CelestialBody>();
		CelestialBody b1 = new CelestialBody(star1, 400f, 100f, 1000000f, celestialBodies, celestialBodies.size());
		celestialBodies.add(b1);
		CelestialBody b2 = new CelestialBody(star2, Gdx.graphics.getWidth()/2f, Gdx.graphics.getHeight()/2f, 10000000000f, celestialBodies, celestialBodies.size());
		celestialBodies.add(b2);

	}

	@Override
	public void render () {
		ScreenUtils.clear(Color.BLACK);
		batch.begin();
		for(CelestialBody CB : celestialBodies){
			CB.draw(batch);
			CB.update();
		}
//		mouseTestFrames ++;
//		if(mouseTestFrames%60==0) {
//			System.out.println(Gdx.input.getX() + ", " + Gdx.input.getY());
//		}
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		star1.dispose();
		planet.dispose();
	}
}
