package com.game.bricks.core;

import java.awt.Color;
import java.awt.Graphics;

import com.game.bricks.ui.Drawable;
import com.game.bricks.ui.base.Rectangle;
import com.game.bricks.ui.base.Vector2D;

public class Ball implements Drawable {
	
	public Ball(final Rectangle rectangle, final Color color) {
		this.rectangle = rectangle;
		this.color = color;
		this.velocity = new Vector2D(0.0, 0.0);
	}
	
	public void draw(final Graphics graphics) {
		graphics.setColor(color);
		graphics.fillOval((int)rectangle.getLeft(), 
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
	
	public void setLocation(Vector2D location) {
		rectangle.setLocation(location);
	}
	
	public void bounceHorizontal() {
		velocity.setY(-velocity.getY());
	}
	
	public void bounceVertical() {
		velocity.setX(-velocity.getX());
	}
	
	public void move() {
		rectangle.moveRectangle(velocity);
	}
	
	public Rectangle getRectangle() {
		return rectangle;
	}
	
	public double getRadius() {
		return rectangle.getWidth()/2.0;
	}

	
	private Vector2D velocity;
	private final Rectangle rectangle;
	private final Color color;
}
