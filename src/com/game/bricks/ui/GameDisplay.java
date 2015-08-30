package com.game.bricks.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.game.bricks.core.Game;
import com.game.bricks.ui.base.Observable;
import com.game.bricks.ui.base.Observer;
import com.game.bricks.ui.base.Rectangle;

public class GameDisplay extends JPanel implements Observer {

	private Observable observable;
	private Rectangle displayRectangle;
	private DrawableManager drawableManager;

	public GameDisplay(Observable observable, DrawableManager drawableManager, Rectangle rectangle) {
		this.observable = observable;
		this.observable.addObserver(this);
		this.displayRectangle = rectangle;
		this.drawableManager = drawableManager;
		setupUI();
	}

	public void setupUI() {
		final GameDisplay gameDisplay = this;

		final Game game = this.observable instanceof Game ? (Game) this.observable : null;

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				final JFrame frame = new JFrame("Game");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLayout(new BorderLayout());
				frame.add(gameDisplay);
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);

				frame.addKeyListener(new KeyListener() {

					public void keyTyped(KeyEvent event) {
						/*
						 * if (event.getKeyChar() == ' ') { this.update(); }
						 */
						game.handleKeyTypedEvent(event);

					}

					public void keyReleased(KeyEvent arg0) {

					}

					public void keyPressed(KeyEvent arg0) {
						// TODO Auto-generated method stub

					}
				});
				
			}
		});
	}

	

	@Override
	public void paintComponent(final Graphics graphics) {
		super.paintComponent(graphics);
		this.drawableManager.draw(graphics);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension((int) displayRectangle.getWidth(), (int) displayRectangle.getHeight());
	}

	public void update(Integer... data) {
		System.out.println("rrrrrrrrrrrr");
		this.validate();
		this.repaint();

	}

}
