package com.game.bricks.ui.base;

public class Rectangle {
	public Rectangle(final double left, final double top, final double right, final double bottom) {
		this.left =  left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
	}
	
	public Rectangle() {
		left = top = right = bottom = 0.0;
	}
	
	/**
	 * @return the left
	 */
	public double getLeft() {
		return left;
	}



	/**
	 * @param left the left to set
	 */
	public void setLeft(double left) {
		this.left = left;
	}



	/**
	 * @return the top
	 */
	public double getTop() {
		return top;
	}



	/**
	 * @param top the top to set
	 */
	public void setTop(double top) {
		this.top = top;
	}



	/**
	 * @return the right
	 */
	public double getRight() {
		return right;
	}

	
	/**
	 * @param right the right to set
	 */
	public void setRight(double right) {
		this.right = right;
	}



	/**
	 * @return the bottom
	 */
	public double getBottom() {
		return bottom;
	}



	/**
	 * @param bottom the bottom to set
	 */
	public void setBottom(double bottom) {
		this.bottom = bottom;
	}

	
	
	public double getWidth() {
		return right - left;
	}
	
	
	
	public double getHeight() {
		return bottom - top;
	}



	public void setLocation(Vector2D location) {
		double curWidth = getWidth();
		double curHeight = getHeight();
		
		left = location.getX() - curWidth/2.0;
		right = left + curWidth;
		top = location.getY() - curHeight/2.0;
		bottom = top + curHeight;
	}

	public Vector2D getLocation() {
		return new Vector2D((left+right)/2.0, (top + bottom)/2.0);
	}
	
	public void moveRectangle(Vector2D vector) {
		left += vector.getX();
		right += vector.getX();
		top += vector.getY();
		bottom += vector.getY();
	}

	

	private double left, top, right, bottom;

}
