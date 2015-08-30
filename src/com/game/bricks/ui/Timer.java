package com.game.bricks.ui;

import com.game.bricks.ui.base.BaseObservable;
import com.game.bricks.ui.base.GenericObservable;
import com.game.bricks.ui.base.GenericObserver;
import com.game.constants.Constants;

public class Timer extends Thread implements GenericObservable<Integer>{

	private boolean running;
	private final int sleepTime;
	private BaseObservable<Integer> observable;

	public Timer(int sleepTime) {
		this.sleepTime = sleepTime;
		running = true;
		this.observable = new BaseObservable<Integer>();
	}

	@Override
	public void run() {
		while (running) {
			notifyObserver(Constants.EVENT_TIMER);
			try {
				Thread.sleep(sleepTime);
			} catch (Exception e) {
				//ignore
			}
		}
	}

	public void addObserver(GenericObserver<Integer> observer) {
		observable.addObserver(observer);
	}

	public void removeObserver(GenericObserver<Integer> observer) {
		observable.removeObserver(observer);
	}

	public void notifyObserver(Integer... data) {
		observable.notifyObserver(data);
	}

}
