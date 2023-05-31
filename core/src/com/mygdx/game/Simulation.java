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
	Texture arrow1;
	ArrayList<Arrow> arrows;
	int mouseTestFrames;
	float mouseX;
	float mouseY;
	int curIndex;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		planet = new Texture("planet.png");
		star1 = new Texture("pixil-frame-0(5).png");
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
		CelestialBody b1 = new CelestialBody(star1, Gdx.graphics.getWidth()/2f, Gdx.graphics.getHeight()/2f, 100000f, celestialBodies, celestialBodies.size());
		celestialBodies.add(b1);
//		for(int i = 1; i < stars.size(); i ++) {
//			CelestialBody newStar = new CelestialBody(stars.get(i), (float)Helper.random(0, 400), (float)Helper.random(0, 400), 1000000f, celestialBodies, celestialBodies.size());
//			celestialBodies.add(newStar);
//		}
		CelestialBody newStar = new CelestialBody(star2, (float)Helper.random(0, 400), (float)Helper.random(0, 400), 10000f, celestialBodies, celestialBodies.size());
//		newStar.addExternalXForce(500000);
		celestialBodies.add(newStar);

		arrow1 = new Texture("arrow.png");
		arrows = new ArrayList<Arrow>();
		for(int i = 0; i < celestialBodies.size(); i ++){
			CelestialBody curStar = celestialBodies.get(i);
			Arrow newArrow = new Arrow(arrow1, curStar.getX(), curStar.getY(), curStar, curStar.getLine());
			arrows.add(newArrow);
		}

	}

	@Override
	public void render () {
		ScreenUtils.clear(Color.BLACK);
		batch.begin();
		mouseX = Gdx.input.getX();
		mouseY = Gdx.input.getY();

		int tempIndex = 10000;
		for(CelestialBody CB : celestialBodies){
			CB.update();
			CB.draw(batch);
			if(CB.mouseTouched(mouseX, mouseY)){
				System.out.println("TESTESTESTESTESTEST");
				System.out.println(CB.getLine());
				tempIndex = CB.getLine();
			}
		}
		curIndex = tempIndex;

		for(Arrow arrow: arrows){
			arrow.update();
			arrow.draw(batch);
		}

		mouseTestFrames ++;
		if(mouseTestFrames%60==0) {
			System.out.println(Gdx.input.getX() + ", " + Gdx.input.getY());
		}

		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
			if(curIndex != 10000){

			}
		}
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		for(int i = 0; i < stars.size(); i++){
			stars.get(i).dispose();
		}
		arrow1.dispose();
		planet.dispose();
	}
}
