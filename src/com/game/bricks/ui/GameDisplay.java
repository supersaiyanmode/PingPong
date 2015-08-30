package com.game.bricks.ui;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.game.bricks.ui.base.BaseObservable;
import com.game.bricks.ui.base.GenericObservable;
import com.game.bricks.ui.base.GenericObserver;
import com.game.bricks.ui.base.Rectangle;

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
