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
import java.util.ArrayList;


public class Simulation extends ApplicationAdapter {
	SpriteBatch batch;
	Texture planet;
	Texture star;
	ArrayList<CelestialBody> celestialBodies;
	int mouseTestFrames;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		planet = new Texture("planet.png");
		star = new Texture("star.png");
		celestialBodies = new ArrayList<CelestialBody>();
		CelestialBody b1 = new CelestialBody(planet, 0, 0, 1, celestialBodies, celestialBodies.size());
		celestialBodies.add(b1);
		CelestialBody b2 = new CelestialBody(star, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 10, celestialBodies, celestialBodies.size());
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
		mouseTestFrames ++;
		if(mouseTestFrames%60==0) {
			System.out.println(Gdx.input.getX() + ", " + Gdx.input.getY());
		}
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		star.dispose();
		planet.dispose();
	}
}
