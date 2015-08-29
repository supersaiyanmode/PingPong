package com.game.bricks.ui;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DrawableManager {
	public DrawableManager() {
		drawables = Collections.synchronizedList(new ArrayList<Drawable>());
	}
	
	public void draw(Graphics graphics) {
		for (final Drawable drawable: drawables) {
			drawable.draw(graphics);
		}
	}
	
	public void registerDrawable(final Drawable drawable) {
		drawables.add(drawable);
	}
	
	public void unregisterDrawable(final Drawable drawable) {
		drawables.remove(drawable);
	}
	
	final List<Drawable> drawables;
}
