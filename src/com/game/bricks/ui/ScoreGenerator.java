package com.game.bricks.ui;

import java.awt.Graphics;

import com.game.bricks.ui.base.GenericObservable;
import com.game.bricks.ui.base.Observer;

public class ScoreGenerator implements Observer, Drawable {

	int score = 0;

	private GenericObservable observable;

	public ScoreGenerator(GenericObservable observable) {
		this.observable = observable;
		this.observable.addObserver(this);
	}

	public void update(Integer... i) {
		score++;
	}

	public void draw(Graphics g) {
		g.drawString("Score: " + score, 300, 150);
	}

}
