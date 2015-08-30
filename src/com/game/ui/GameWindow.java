package com.game.ui;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.game.bricks.ui.base.BaseObservable;
import com.game.bricks.ui.base.GenericObservable;
import com.game.bricks.ui.base.GenericObserver;
import com.game.constants.Constants;

public class GameWindow extends JFrame implements KeyListener, GenericObservable<Integer> {

	private BaseObservable<Integer> inputKeyObservable;
	
	public GameWindow(final JPanel gamePanel, final JPanel scorePanel, final JPanel timerPanel) {
		inputKeyObservable = new BaseObservable<Integer>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		add(timerPanel, BorderLayout.PAGE_START);
		add(gamePanel, BorderLayout.CENTER);
		add(scorePanel, BorderLayout.PAGE_END);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

		addKeyListener(this);
	}
	
	public void keyTyped(KeyEvent event) {
		if (event.getKeyChar() == 'A' || event.getKeyChar() == 'a') {
			inputKeyObservable.notifyObserver(Constants.EVENT_KEY_LEFT);
		}
		if (event.getKeyChar() == 'D' || event.getKeyChar() == 'd') {
			inputKeyObservable.notifyObserver(Constants.EVENT_KEY_RIGHT);
		}
	}

	public void keyReleased(KeyEvent arg0) {
		//ignore
	}

	public void keyPressed(KeyEvent arg0) {
		//ignore
	}
	public void addObserver(GenericObserver<Integer> observer) {
		inputKeyObservable.addObserver(observer);
	}
	
	public void removeObserver(GenericObserver<Integer> observer) {
		inputKeyObservable.removeObserver(observer);
	}
	
	public void removeAllObservers() {
		inputKeyObservable.removeAllObservers();
	}
	
	public void notifyObserver(Integer... data) {
		inputKeyObservable.notifyObserver(data);
	}

	private static final long serialVersionUID = 6285271827582255854L;
}
