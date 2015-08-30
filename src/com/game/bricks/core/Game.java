package com.game.bricks.core;

import java.awt.Color;
import java.util.Random;

import com.game.bricks.ui.DrawableManager;
import com.game.bricks.ui.ScoreManager;
import com.game.bricks.ui.base.BaseObservable;
import com.game.bricks.ui.base.GenericObservable;
import com.game.bricks.ui.base.GenericObserver;
import com.game.bricks.ui.base.Rectangle;
import com.game.bricks.ui.base.Vector2D;
import come.game.constants.Constants;

public class Game implements GenericObservable<Integer>, GenericObserver<Integer> {
	private BrickSet brickSet;
	private Ball ball;
	private Bat bat;

	private Rectangle screenDimensions;
	DrawableManager drawableManager;
	final static int BALL_RADIUS = 10;
	private static final double BALL_MOVE_DELTA = 2;
	
	private BaseObservable<Integer> stateUpdateObservable;

	public Game(DrawableManager drawableManager, Rectangle dimensions) {
		this.stateUpdateObservable = new BaseObservable<Integer>();
		this.drawableManager = drawableManager;
		this.screenDimensions = dimensions;
		this.brickSet = new BrickSet(drawableManager, dimensions);
		this.ball = getBall();
		this.bat = getBat();
		drawableManager.registerDrawable(this.ball);
		drawableManager.registerDrawable(this.bat);

		ScoreManager scoreGenerator = new ScoreManager();
		brickSet.addObserver(scoreGenerator);
		drawableManager.registerDrawable(scoreGenerator);
	}

	/**
	 * Sets the dimensions of the bat w.r.t frame dimensions.
	 * 
	 * @return Bat
	 */
	private Bat getBat() {
		final double width = 0.1 * screenDimensions.getWidth();
		final double height = 0.01 * screenDimensions.getHeight();
		final double left = screenDimensions.getWidth() / 2.0 - width;
		final double right = left + width;
		final double top = screenDimensions.getHeight() * 0.95 - height;
		final double bottom = top + height;
		final Rectangle rect = new Rectangle(left, top, right, bottom);
		return new Bat(rect, Color.RED);
	}

	/**
	 * Sets the dimensions of the ball w.r.t frame dimensions.
	 * 
	 * @return Ball
	 */
	private Ball getBall() {
		final Rectangle ballRect = new Rectangle(
				screenDimensions.getLeft() + screenDimensions.getWidth() / 2.0 - BALL_RADIUS,
				screenDimensions.getTop() + screenDimensions.getHeight() * 3.0 / 4.0 - BALL_RADIUS,
				screenDimensions.getLeft() + screenDimensions.getWidth() / 2.0 + BALL_RADIUS,
				screenDimensions.getTop() + screenDimensions.getHeight() * 3.0 / 4.0 + BALL_RADIUS);
		final Random rNum = new Random(System.currentTimeMillis());
		final Ball ball = new Ball(ballRect, Color.BLUE);
		final Vector2D velocity = new Vector2D(10 + rNum.nextInt(10), 10 + rNum.nextInt(10));
		velocity.setMagnitude(BALL_MOVE_DELTA / 3.0);
		velocity.setY(-Math.abs(velocity.getY()));
		ball.setVelocity(velocity);
		return ball;
	}

	/**
	 * Executes the game operations
	 * 
	 * 
	 */
	public void executeRunner() {

		
	}

	public void update(Integer... event) {
		if (event[0] == Constants.EVENT_KEY_LEFT) {
			bat.moveLeft();
		}
		else if (event[0] == Constants.EVENT_KEY_RIGHT) {
			bat.moveRight();
		}
		else if (event[0] == Constants.EVENT_TIMER) {
			ball.move();

			// Check collision with Bricks
			final BorderCollision collision = brickSet.checkCollision(drawableManager, ball.getRectangle(),
					BALL_MOVE_DELTA);
			if (collision.isHorizontalCollision()) {
				ball.bounceHorizontal();
			}
			if (collision.isVerticalCollision()) {
				ball.bounceVertical();
			}

			// Check against bat.
			final BorderCollision collision2 = BorderCollision.collides(bat.getRectangle(), ball.getRectangle(),
					BALL_MOVE_DELTA);
			if (collision2 != null) {
				if (collision2.isHorizontalCollision()) {
					ball.bounceHorizontal();
				}
				if (collision2.isVerticalCollision()) {
					ball.bounceVertical();
				}
			}

			// Check with walls.
			if (ball.getLocation().getX() - ball.getRadius() < screenDimensions.getLeft()
					|| ball.getLocation().getX() + ball.getRadius() > screenDimensions.getRight()) {
				ball.bounceVertical();
			}

			if (ball.getLocation().getY() - ball.getRadius() < screenDimensions.getTop()) {
				ball.bounceHorizontal();
			}

			if (ball.getLocation().getY() + ball.getRadius() > screenDimensions.getBottom()) {
				notifyObserver(-1);
			}
			
			notifyObserver(1);
		}
	}

	public void addObserver(GenericObserver<Integer> observer) {
		stateUpdateObservable.addObserver(observer);
	}

	public void removeObserver(GenericObserver<Integer> observer) {
		stateUpdateObservable.removeObserver(observer);
	}

	public void notifyObserver(Integer... data) {
		stateUpdateObservable.notifyObserver(data);
	}
}
