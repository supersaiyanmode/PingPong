package com.game.score.core;

import com.game.bricks.ui.base.BaseObservable;
import com.game.bricks.ui.base.GenericObservable;
import com.game.bricks.ui.base.GenericObserver;
import com.game.constants.Constants;

public class ScoreManager implements GenericObserver<Integer>, GenericObservable<Integer> {
	private int score;
	private BaseObservable<Integer> scoreObservable;
	
	public ScoreManager() {
		score = 0;
		scoreObservable = new BaseObservable<Integer>();
	}

	public void update(Integer... data) {
		if (data[0] == Constants.EVENT_SCORE_UPDATE) {
			score += data[1];
		}
		notifyObserver(Constants.EVENT_REPAINT_SCORE, score);
	}

	public void addObserver(GenericObserver<Integer> observer) {
		scoreObservable.addObserver(observer);
	}

	public void removeObserver(GenericObserver<Integer> observer) {
		scoreObservable.removeObserver(observer);
	}
	
	public void removeAllObservers() {
		scoreObservable.removeAllObservers();
	}

	public void notifyObserver(Integer... data) {
		scoreObservable.notifyObserver(data);
	}

}
