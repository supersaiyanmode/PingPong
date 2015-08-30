package com.game.bricks.ui;

import java.awt.Graphics;

import com.game.bricks.ui.base.GenericObserver;
import come.game.constants.Constants;

public class ScoreManager implements GenericObserver<Integer>, Drawable {
	int score;
	
	public ScoreManager() {
		score = 0;
	}

	public void update(Integer... data) {
		if (data[0] == Constants.EVENT_SCORE_UPDATE) {
			score += data[1];
		}
	}

	public void draw(Graphics g) {
		g.drawString("Score: " + score, 300, 550);
	}

}
