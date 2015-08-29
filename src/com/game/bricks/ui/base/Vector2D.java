package com.game.bricks.ui.base;

public class Vector2D {
	public Vector2D() {
		x = y = 0.0;
	}
	
	public Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @return the posX
	 */
	public double getX() {
		return x;
	}

	/**
	 * @param posX the posX to set
	 */
	public void setX(double posX) {
		this.x = posX;
	}

	/**
	 * @return the posY
	 */
	public double getY() {
		return y;
	}

	/**
	 * @param posY the posY to set
	 */
	public void setY(double posY) {
		this.y = posY;
	}
	
	public double getMagnitude() {
		return Math.sqrt(this.x*this.x + this.y*this.y);
	}
	
	public void setMagnitude(double value) {
		final double angle = getAngle();
		x = value * Math.cos(angle);
		y = value * Math.sin(angle);
	}
	
	public double getAngle() {
		return Math.atan2(y, x);
	}
	
	public void setAngle(double angle) {
		final double magnitude = getMagnitude();
		x = magnitude * Math.cos(angle);
		y = magnitude * Math.sin(angle);
	}

	private double x, y;
}
