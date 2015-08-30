package com.game.bricks.ui.base;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class BaseObservable<T> implements GenericObservable<T> {
	
	protected List<GenericObserver<T>> observers = new CopyOnWriteArrayList<GenericObserver<T>>();
	
	public void addObserver(GenericObserver<T> observer) {
		observers.add(observer);
	}

	public void removeObserver(GenericObserver<T> observer) {
		observers.remove(observer);

	}
	
	public void removeAllObservers() {
		observers.clear();
	}

	public void notifyObserver(T... data) {
		for (GenericObserver<T> observer : observers) {
			observer.update(data);
		}
	}
}
