package com.game.bricks.ui.base;
import java.util.ArrayList;
import java.util.List;

public class GenericObservable implements Observable {
	
	protected List<Observer> observers = new ArrayList<Observer>();
	
	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	public void removeObserver(Observer observer) {
		observers.remove(observer);

	}

	public void notifyObserver() {
		for (Observer observer : observers) {
			observer.update();
		}
	}

}
