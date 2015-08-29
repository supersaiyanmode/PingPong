package com.game.bricks.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FrameRateManager implements Runnable{
	public FrameRateManager(int sleepTime) {
		this.sleepTime = sleepTime;
		this.running = true;
		tickables = Collections.synchronizedList(new ArrayList<Tickable>(10));
		new Thread(this).start();
	}
	
	public void registerTickable(final Tickable tickable) {
		synchronized (tickableLock) {
			tickables.add(tickable);
		}
	}
	
	public void unregisterTickable(final Tickable tickable) {
		synchronized (tickableLock) {
			tickables.remove(tickable);
		}
	}
	
	public void run() {
		while (running) {
			synchronized (tickableLock) {
				for (final Tickable tickable: tickables) {
					tickable.tick();
				}
			}
			//System.out.println("Tick..");
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void stop() {
		running = false;
	}
	
	private final List<Tickable> tickables;
	private final Object tickableLock = new Object();
	private int sleepTime;
	private boolean running;
	
}
