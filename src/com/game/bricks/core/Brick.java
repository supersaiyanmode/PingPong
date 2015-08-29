package com.game.bricks.core;

import java.awt.Color;
import java.awt.Graphics;

import com.game.bricks.ui.Drawable;
import com.game.bricks.ui.base.Rectangle;

public class Brick implements Drawable {
	
	public Brick(final Rectangle rectangle, final Color color) {
		this.rectangle = rectangle;
		this.color = color;
		this.isActive = true;
	}
	
	public Rectangle getRectangle() {
		return rectangle;
	}
	
	public void draw(final Graphics graphics) {
		graphics.setColor(color);
		graphics.fillRoundRect((int)rectangle.getLeft(), (int)rectangle.getTop(), 
				(int)rectangle.getWidth(), (int)rectangle.getHeight(), 2, 2);
	}
	
	public void setActive(final boolean active) {
		this.isActive = active;
	}
	
	public boolean isActive() {
		return isActive;
	}

	private final Rectangle rectangle;
	private final Color color;
	private boolean isActive;
}
