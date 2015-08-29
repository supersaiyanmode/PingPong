package com.game.bricks.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.game.bricks.core.Game;
import com.game.bricks.ui.base.Rectangle;

public class GameDisplay extends JPanel implements Runnable {
	static { 
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

	public GameDisplay(final Game game, Rectangle rectangle) {
		this.game = game;
		this.running = true;
		this.displayRectangle = rectangle;
	}
	
	public void run() {
		final JPanel currentPanel = this;
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				final JFrame frame = new JFrame("Game");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLayout(new BorderLayout());
				frame.add(currentPanel);
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
			}
		});
		
		while (running) {
			this.validate();
			this.repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void stop() {
		this.running = false;
		SwingUtilities.getWindowAncestor(this).setVisible(false);
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
	
	private boolean running;
	private Game game;
	private Rectangle displayRectangle;
	
	private static final long serialVersionUID = -5411389959096633548L;
}
