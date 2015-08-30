package com.game.bricks.core;

import java.awt.Graphics;

import com.game.bricks.ui.Drawable;
import com.game.bricks.ui.Tickable;

public class TimerDisplay implements Tickable,Drawable {
	
	private int sec;
	private int min;
	
	
	public TimerDisplay(int min, int sec) {
		this.min = min;
		this.sec = sec;
	}

	public void draw(Graphics graphics) {
		
		
	}

	public void tick() {
		
		if (sec != 0) {
			sec--;
		} else if (sec == 0 && min != 0){
			sec = 59;
			min--;
		} else {
			//expired
			
		}
	}
	
}
	
	