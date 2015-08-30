package com.game.score.ui;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.game.bricks.ui.base.GenericObserver;
import com.game.bricks.ui.base.Rectangle;
import com.game.constants.Constants;

public class ScoreDisplay extends JPanel implements GenericObserver<Integer> {
	
	public ScoreDisplay(final Rectangle displayRectangle) {
		score = 0;
		this.displayRectangle = displayRectangle;
	}
	
	@Override
	public void paintComponent(final Graphics graphics) {
		super.paintComponent(graphics);
		graphics.drawString("" + score, 10, 10);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension((int) displayRectangle.getWidth(), (int) displayRectangle.getHeight());
	}
	
	public void update(Integer... data) {
		if (data[0] == Constants.EVENT_REPAINT_SCORE) {
			score = data[1];
			validate();
			repaint();
			System.out.println("Repaint updated score:" + score);
		}
	}
	
	private int score;

	private Rectangle displayRectangle;

	private static final long serialVersionUID = 3035446986384274911L;
}
