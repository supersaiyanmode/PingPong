package com.game.bricks.ui.base;

public interface GenericObservable<T> {
	public void addObserver(GenericObserver<T> observer);

	public void removeObserver(GenericObserver<T> observer);
	
	public void notifyObserver(T... data);
}
