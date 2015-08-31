package com.game.bricks.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.game.bricks.ui.base.BaseObservable;
import com.game.bricks.ui.base.GenericObservable;
import com.game.bricks.ui.base.GenericObserver;
import com.game.bricks.ui.base.Rectangle;
import com.game.constants.Constants;

public class GameDisplay extends JPanel implements GenericObserver<Integer>, GenericObservable<Integer>  {
	public GameDisplay(DrawableManager drawableManager, Rectangle rectangle) {
		this.displayRectangle = rectangle;
		this.drawableManager = drawableManager;
		this.inputKeyObservable = new BaseObservable<Integer>();
	}

	public void addObserver(GenericObserver<Integer> observer) {
		inputKeyObservable.addObserver(observer);
	}

	public void removeObserver(GenericObserver<Integer> observer) {
		inputKeyObservable.removeObserver(observer);
	}
	
	public void removeAllObservers() {
		inputKeyObservable.removeAllObservers();
	}

	public void notifyObserver(Integer... data) {
		inputKeyObservable.notifyObserver(data);
	}

	@Override
	public void paintComponent(final Graphics graphics) {
		if (isGameOver) {
			drawGameOver(graphics);
		} else {
			super.paintComponent(graphics);
			this.drawableManager.draw(graphics);
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension((int) displayRectangle.getWidth(), (int) displayRectangle.getHeight());
	}

	public void update(Integer... data) {
		if (data[0] == Constants.EVENT_REPAINT) {
			this.validate();
			this.repaint();
		}
		else if (data[0] == Constants.EVENT_GAMEOVER) {
			isGameOver = true;
			this.validate();
			this.repaint();
		}
	}
	
	private void drawGameOver(final Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect((int)displayRectangle.getLeft(),
				(int)displayRectangle.getTop(), 
				(int)displayRectangle.getRight(),
				(int)displayRectangle.getBottom());
		g.setColor(Color.WHITE);
		g.setFont(new Font("serif", Font.BOLD, 32));
		g.drawString("GAME OVER :-(", 10, 60);
	}

	private Rectangle displayRectangle;
	private DrawableManager drawableManager;
	private BaseObservable<Integer> inputKeyObservable;
	private boolean isGameOver = false;

	private static final long serialVersionUID = -7210838590467742537L;
}
