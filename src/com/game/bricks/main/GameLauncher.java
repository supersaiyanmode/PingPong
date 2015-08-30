package com.game.bricks.main;

import com.game.bricks.core.Game;
import com.game.bricks.ui.DrawableManager;
import com.game.bricks.ui.GameDisplay;
import com.game.bricks.ui.Timer;
import com.game.bricks.ui.base.Rectangle;

import come.game.timer.core.GameTimer;
import come.game.timer.ui.GameTimerDisplay;

public class GameLauncher {

	public static void main(String[] args) {
		final Rectangle displayRectangle = new Rectangle(0, 0, 800, 600);
		final DrawableManager drawableManager = new DrawableManager();
		final Game game = new Game(drawableManager, displayRectangle);
		final GameDisplay gameDisplay = new GameDisplay(drawableManager, displayRectangle);
		final Timer gameTickTimer = new Timer(10);
		final Timer countdownTimer = new Timer(1000);
		final GameTimer countdownTimerCore = new GameTimer(5, 0);
		final GameTimerDisplay gameTimeDisplay = new GameTimerDisplay();
		
		gameDisplay.addObserver(game); //For input keys
		game.addObserver(gameDisplay); //For display update
		
		gameTickTimer.addObserver(game); //For moving the game forward
		countdownTimer.addObserver(countdownTimerCore); //So that timer makes game timer count down
		countdownTimerCore.addObserver(gameTimeDisplay); //So that game timer knows when to repaint
		
		countdownTimerCore.addObserver(game); //For notifying when time runs out
		
		gameTickTimer.start();
		countdownTimer.start();

	}

}
