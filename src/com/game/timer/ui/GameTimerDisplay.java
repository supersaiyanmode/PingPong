package com.game.timer.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.game.bricks.ui.base.GenericObserver;
import com.game.bricks.ui.base.Rectangle;
import com.game.constants.Constants;

public class GameTimerDisplay extends JPanel implements GenericObserver<Integer>{
	
	
	public GameTimerDisplay(Rectangle displayRectangle) {
		this.displayRectangle = displayRectangle;
	}
	
	@Override
	public void paintComponent(final Graphics graphics) {
		super.paintComponent(graphics);
		graphics.setFont(new Font("serif", Font.BOLD, 24));
		final String text = String.format("Time Left: %d:%02d", minutes, seconds);
		graphics.drawString(text, 10, 24);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension((int) displayRectangle.getWidth(), (int) displayRectangle.getHeight());
	}
	
	public void update(Integer... data) {
		if (data[0] == Constants.EVENT_TIMER) {
			minutes = data[1];
			seconds = data[2];
			validate();
			repaint();
		}
	}
	
	private int minutes, seconds;
	private Rectangle displayRectangle;

	private static final long serialVersionUID = -3928076119777106217L;

}