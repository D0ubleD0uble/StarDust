package com.StarDust.system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;

public class Input extends GestureDetector implements GestureListener{

	public Input(GestureListener listener) {
		super(listener);
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public boolean fling(float arg0, float arg1, int arg2) {
		System.out.println("fling");
		return false;
	}

	@Override
	public boolean longPress(float arg0, float arg1) {
		System.out.println("longpress");
		return false;
	}

	@Override
	public boolean pan(float arg0, float arg1, float arg2, float arg3) {
		System.out.println("pan");
		return false;
	}

	@Override
	public boolean panStop(float arg0, float arg1, int arg2, int arg3) {
		System.out.println("panStop");
		return false;
	}

	@Override
	public boolean pinch(Vector2 arg0, Vector2 arg1, Vector2 arg2, Vector2 arg3) {
		System.out.println("pinch");
		return false;
	}

	@Override
	public boolean tap(float arg0, float arg1, int arg2, int arg3) {
		System.out.println("tap");
		return false;
	}

	@Override
	public boolean touchDown(float arg0, float arg1, int arg2, int arg3) {
		System.out.println("touchdown");
		return false;
	}

	@Override
	public boolean zoom(float arg0, float arg1) {
		System.out.println("zoom");
		return false;
	}
}
