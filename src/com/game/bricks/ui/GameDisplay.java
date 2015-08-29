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
import com.game.bricks.ui.base.Rectangle;

public class GameDisplay extends JPanel implements Tickable {
	

	public GameDisplay(final Game game, Rectangle rectangle, FrameRateManager frm) {
		this.game = game;
		this.displayRectangle = rectangle;
		this.frameRateManager =  frm;
		setupUI();
	}
	
	public void setupUI() {
		final GameDisplay gameDisplay = this;
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
					
					public void keyTyped(KeyEvent arg0) {
						if (arg0.getKeyChar() == ' ') {
							game.tick();
						}
						if (arg0.getKeyChar() == 'a' || arg0.getKeyChar() == 'A') {
							game.moveBatLeft();
						}
						
						if (arg0.getKeyChar() == 'd' || arg0.getKeyChar() == 'D') {
							game.moveBatRight();
						}
					}
					
					public void keyReleased(KeyEvent arg0) {
						
					}
					
					public void keyPressed(KeyEvent arg0) {
						// TODO Auto-generated method stub
						
					}
				});
				frameRateManager.registerTickable(gameDisplay);
			}
		});
	}
	
	public void tick() {
		this.validate();
		this.repaint();
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension((int)displayRectangle.getWidth(), (int)displayRectangle.getHeight());
	}
	
	@Override
	public void paintComponent(final Graphics graphics) {
		super.paintComponent(graphics);
		game.draw(graphics);
	}
	
	private Game game;
	private Rectangle displayRectangle;
	private FrameRateManager frameRateManager;

	
	private static final long serialVersionUID = -5411389959096633548L;
}
