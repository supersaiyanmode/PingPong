package com.game.bricks.core;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.game.bricks.ui.DrawableManager;
import com.game.bricks.ui.base.GenericObservable;
import com.game.bricks.ui.base.Rectangle;

public class BrickSet extends GenericObservable {

	int rows = 10;
	int cols = 10;
	private List<List<Brick>> bricks;

	public BrickSet(DrawableManager drawableManager ,Rectangle dimensions) {
		bricks = getNewBricks(rows, cols, dimensions);

		for (final List<Brick> brickRow : this.bricks) {
			for (final Brick brick : brickRow) {
				drawableManager.registerDrawable(brick);
			}
		}
	}

	public BorderCollision checkCollision(DrawableManager drawableManager ,Rectangle rectangle, double delta) {
		boolean horizCollision = false, vertCollision = false;

		for (final List<Brick> brickRow : this.bricks) {
			for (final Brick brick : brickRow) {
				if (brick.isActive()) {
					final BorderCollision collision = BorderCollision.collides(brick.getRectangle(), rectangle, delta);
					if (collision != null) {
						brick.setActive(false);
						drawableManager.unregisterDrawable(brick);
						horizCollision |= collision.isHorizontalCollision();
						vertCollision |= collision.isVerticalCollision();
					}
				}
			}
		}

		return new BorderCollision(horizCollision, vertCollision);
	}

	private static List<List<Brick>> getNewBricks(int rows, int cols, Rectangle dimensions) {
		final List<List<Brick>> bricks = new ArrayList<List<Brick>>();

		final double totalWidth = dimensions.getRight() - dimensions.getLeft();
		final double totalHeight = (dimensions.getBottom() - dimensions.getTop()) / 2.0;
		final double brickWidth = totalWidth / cols;
		final double brickHeight = totalHeight / rows;

		for (int i = 0; i < rows; i++) {
			final List<Brick> row = new ArrayList<Brick>(cols);

			for (int j = 0; j < cols; j++) {
				final double left = j * brickWidth;
				final double right = left + brickWidth;
				final double top = i * brickHeight;
				final double bottom = top + brickHeight;
				final Rectangle rectangle = new Rectangle(left, top, right, bottom);

				final Color color = new Color((float) Math.random(), (float) Math.random(), (float) Math.random());
				row.add(new Brick(rectangle, color));
			}
			bricks.add(row);
		}
		return bricks;
	}

}
