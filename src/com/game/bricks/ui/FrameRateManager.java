package com.game.bricks.ui;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class FrameRateManager implements Runnable{
	public FrameRateManager(int sleepTime) {
		this.sleepTime = sleepTime;
		this.running = true;
		tickables = new CopyOnWriteArrayList<Tickable>();
	}
	
	public void registerTickable(final Tickable tickable) {
		tickables.add(tickable);
	}
	
	public void unregisterTickable(final Tickable tickable) {
		tickables.remove(tickable);
	}
	
	public void run() {
		while (running) {
			for (final Tickable tickable: tickables) {
				tickable.tick();
			}
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				//ignore
			}
		}
	}
	
	public void stop() {
		running = false;
	}
	
	private final List<Tickable> tickables;
	private int sleepTime;
	private boolean running;
	
}
