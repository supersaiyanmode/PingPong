package com.game.bricks.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.game.bricks.ui.base.BaseObservable;
import com.game.bricks.ui.base.GenericObservable;
import com.game.bricks.ui.base.GenericObserver;
import com.game.bricks.ui.base.Rectangle;
import come.game.constants.Constants;

public class GameDisplay extends JPanel implements GenericObserver<Integer>, GenericObservable<Integer>  {
	public GameDisplay(DrawableManager drawableManager, Rectangle rectangle) {
		this.displayRectangle = rectangle;
		this.drawableManager = drawableManager;
		this.inputKeyObservable = new BaseObservable<Integer>();
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

					public void keyTyped(KeyEvent event) {
						if (event.getKeyChar() == 'A' || event.getKeyChar() == 'a') {
							inputKeyObservable.notifyObserver(Constants.EVENT_KEY_LEFT);
						}
						if (event.getKeyChar() == 'D' || event.getKeyChar() == 'd') {
							inputKeyObservable.notifyObserver(Constants.EVENT_KEY_RIGHT);
						}
					}

					public void keyReleased(KeyEvent arg0) {
						//ignore
					}

					public void keyPressed(KeyEvent arg0) {
						//ignore
					}
				});
			}
		});
	}

	public void addObserver(GenericObserver<Integer> observer) {
		inputKeyObservable.addObserver(observer);
	}

	public void removeObserver(GenericObserver<Integer> observer) {
		inputKeyObservable.removeObserver(observer);
	}

	public void notifyObserver(Integer... data) {
		inputKeyObservable.notifyObserver(data);
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
		this.validate();
		this.repaint();
	}

	private Rectangle displayRectangle;
	private DrawableManager drawableManager;
	private BaseObservable<Integer> inputKeyObservable; 

	private static final long serialVersionUID = -7210838590467742537L;
	
}
