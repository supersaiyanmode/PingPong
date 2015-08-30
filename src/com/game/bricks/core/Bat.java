package com.game.bricks.core;

import java.awt.Color;
import java.awt.Graphics;

import com.game.bricks.ui.Drawable;
import com.game.bricks.ui.base.Rectangle;
import com.game.bricks.ui.base.Vector2D;

public class Bat implements Drawable {
	
	public Bat(final Rectangle rectangle, final Color color) {
		this.rectangle = rectangle;
		this.color = color;
		this.velocity = new Vector2D(0.0, 0.0);
	}
	
	public void draw(final Graphics graphics) {
		graphics.setColor(color);
		graphics.fillRect((int)rectangle.getLeft(), 
					(int)rectangle.getTop(), (int)rectangle.getWidth(), 
					(int)rectangle.getHeight());
	}
	
	public Vector2D getVelocity() {
		return this.velocity;
	}
	
	public void setVelocity(final Vector2D velocity) {
		this.velocity = velocity;
	}
	
	public Vector2D getLocation() {
		return rectangle.getLocation();
	}
	
	public void moveLeft() {
		rectangle.moveRectangle(new Vector2D(-50.0, 0));
	}
	
	public void moveRight() {
		rectangle.moveRectangle(new Vector2D(50.0, 0));
	}
	
	public Rectangle getRectangle() {
		return rectangle;
	}
		
	private Vector2D velocity;
	private final Rectangle rectangle;
	private final Color color;
}
