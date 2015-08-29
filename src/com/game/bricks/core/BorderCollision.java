package com.game.bricks.core;

import com.game.bricks.ui.base.Rectangle;

public class BorderCollision {
	public BorderCollision(boolean horizontalCollision, boolean verticalCollision) {
		this.horizontalCollision = horizontalCollision;
		this.verticalCollision = verticalCollision;
	}
	
	public boolean isHorizontalCollision() {
		return horizontalCollision;
	}
	
	public boolean isVerticalCollision() {
		return verticalCollision;
	}
	
	public static BorderCollision collides(final Rectangle rect1, final Rectangle rect2, double delta) {
		boolean horizCollision = false, vertCollision = false;
		
		//Check north/south collision
		if ((rect2.getLeft() >= rect1.getLeft() && rect2.getLeft() <= rect1.getRight()) ||
				(rect2.getRight() >= rect1.getLeft() && rect2.getRight() <= rect1.getRight())) {
			if (Math.abs(rect2.getTop() - rect1.getBottom()) <= delta ||
					Math.abs(rect2.getBottom() - rect1.getTop()) <= delta) {
				horizCollision = true;
			}
		}
		
		//Check east-west collision
		if ((rect2.getTop() >= rect1.getTop() && rect2.getTop() <= rect1.getBottom()) || 
				(rect2.getBottom() >= rect1.getTop() && rect2.getBottom() <= rect1.getBottom())){
			if (Math.abs(rect2.getLeft() - rect1.getRight()) <= delta ||
					Math.abs(rect2.getRight() - rect1.getLeft()) <= delta) {
				vertCollision = true;
			}
		}
		
		if (horizCollision || vertCollision) {
			return new BorderCollision(horizCollision, vertCollision);
		} else {
			return null;
		}
	}
	
	private boolean horizontalCollision;
	private boolean verticalCollision;
}
