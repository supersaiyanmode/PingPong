package come.game.timer.ui;

import java.awt.Graphics;

import com.game.bricks.ui.base.GenericObserver;
import come.game.constants.Constants;

public class GameTimerDisplay implements GenericObserver<Integer>{
	
	public GameTimerDisplay() {
		
	}
	
	public void draw(Graphics graphics) {
		//use this.minutes and this.seconds to draw the timer.
	}

	public void update(Integer... data) {
		if (data[0] == Constants.EVENT_TIMER) {
			minutes = data[1];
			seconds = data[2];
		}
	}
	
	private int minutes, seconds;
}