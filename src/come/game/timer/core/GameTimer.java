package come.game.timer.core;

import com.game.bricks.ui.base.BaseObservable;
import com.game.bricks.ui.base.GenericObservable;
import com.game.bricks.ui.base.GenericObserver;
import come.game.constants.Constants;

public class GameTimer implements GenericObserver<Integer>, GenericObservable<Integer>{
	private int sec;
	private int min;
	
	private final BaseObservable<Integer> observable;
	
	public GameTimer(int min, int sec) {
		this.min = min;
		this.sec = sec;
		observable = new BaseObservable<Integer>();
	}

	public void update(Integer... data) {
		if (sec != 0) {
			sec--;
		} else if (sec == 0 && min != 0){
			sec = 59;
			min--;
		} else {
			observable.notifyObserver(Constants.EVENT_TIMEOUT);
		}
		observable.notifyObserver(Constants.EVENT_TIMER, min, sec);
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
	
	