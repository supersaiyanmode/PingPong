package com.game.bricks.core;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import com.game.bricks.ui.DrawableManager;
import com.game.bricks.ui.FrameRateManager;
import com.game.bricks.ui.Tickable;
import com.game.bricks.ui.base.Rectangle;
import com.game.bricks.ui.base.Vector2D;

public class Game implements Tickable {
	
	public Game(DrawableManager drawableManager, FrameRateManager frm, int rows, int cols, Rectangle dimensions) {
		this.drawableManager = drawableManager;
		this.brickSet = new BrickSet(rows, cols, dimensions, drawableManager);
		this.screenDimensions = dimensions;
		this.ball = getRandomBall(drawableManager, dimensions);
		this.bat = getBat(drawableManager, dimensions);
		this.frameRateManager = frm;
		drawableManager.registerDrawable(this.ball);
		drawableManager.registerDrawable(this.bat);
	}
	
	private Bat getBat(DrawableManager drawableManager, Rectangle dimensions) {
		final double width = 0.1 * dimensions.getWidth();
		final double height = 0.01 * dimensions.getHeight();
		final double left = dimensions.getWidth()/2.0 - width/2.0;
		final double right = left + width;
		final double top = dimensions.getHeight() * 0.95 - height;
		final double bottom = top + height;
		final Rectangle rect = new Rectangle(left, top, right, bottom);
		return new Bat(rect, Color.RED);
	}

	public void draw(Graphics graphics) {
		drawableManager.draw(graphics);
	}
	
	public void tick() {
		ball.move();
		
		//Check collision with Bricks
		final BorderCollision collision = brickSet.checkCollision(ball.getRectangle(), BALL_MOVE_DELTA);
		if (collision.isHorizontalCollision()) {
			ball.bounceHorizontal();
		}
		if (collision.isVerticalCollision()) {
			ball.bounceVertical();
		}
		
		//Check against bat.
		final BorderCollision collision2 = BorderCollision.collides(bat.getRectangle(), ball.getRectangle(), BALL_MOVE_DELTA);
		if (collision2 != null) {
			if (collision2.isHorizontalCollision()) {
				ball.bounceHorizontal();
			}
			if (collision2.isVerticalCollision()) {
				ball.bounceVertical();
			}
		}
		
		//Check with walls.
		if (ball.getLocation().getX() - ball.getRadius() < screenDimensions.getLeft() || 
				ball.getLocation().getX() + ball.getRadius() > screenDimensions.getRight()) {
			ball.bounceVertical();
		}
		
		if (ball.getLocation().getY() - ball.getRadius() < screenDimensions.getTop()) {
			ball.bounceHorizontal();
		}
		
		if (ball.getLocation().getY() + ball.getRadius() > screenDimensions.getBottom()) {
			gameOver();
		}
		
		//Collision testing for the bat
	}
	
	public void moveBatRight() {
		bat.moveRight();
	}
	
	public void moveBatLeft() {
		bat.moveLeft();
	}
	
	public void startGame() {
		frameRateManager.registerTickable(this);
	}
	
	private void gameOver() {
		frameRateManager.unregisterTickable(this);
	}
	
	private static Ball getRandomBall(final DrawableManager drawableManager, final Rectangle dimensions) {
		final Rectangle ballRect = new Rectangle(
				dimensions.getLeft() + dimensions.getWidth()/2.0 - BALL_RADIUS,
				dimensions.getTop() + dimensions.getHeight() * 3.0 / 4.0 - BALL_RADIUS,
				dimensions.getLeft() + dimensions.getWidth()/2.0 + BALL_RADIUS,
				dimensions.getTop() + dimensions.getHeight() * 3.0 / 4.0 + BALL_RADIUS);
        final Random r = new Random(System.currentTimeMillis());
		final Ball ball = new Ball(ballRect, Color.BLUE);
		final Vector2D velocity = new Vector2D(10+r.nextInt(10), 10+r.nextInt(10));
		velocity.setMagnitude(BALL_MOVE_DELTA/3.0);
		velocity.setY(-Math.abs(velocity.getY()));
		ball.setVelocity(velocity);
		return ball;
	}
	
	private BrickSet brickSet;
	private Ball ball;
	private Bat bat;
	
	private DrawableManager drawableManager;
	private Rectangle screenDimensions;
	private FrameRateManager frameRateManager;


	final static int BALL_RADIUS = 10;
	private static final double BALL_MOVE_DELTA = 2;
}
