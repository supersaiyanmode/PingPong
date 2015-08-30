package com.game.bricks.ui;

public class TimerManager extends Thread {

	boolean running = true;
	Runners runner;

	public TimerManager(Runners runner) {
		this.runner = runner;
	}

	@Override
	public void run() {

		while (running) {
			runner.executeRunner();
			try {
				Thread.sleep(1000 / 50);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
