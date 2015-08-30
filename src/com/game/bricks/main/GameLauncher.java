package com.game.bricks.main;

import java.util.Arrays;

import com.game.bricks.core.Game;
import com.game.bricks.ui.DrawableManager;
import com.game.bricks.ui.GameDisplay;
import com.game.bricks.ui.Timer;
import com.game.bricks.ui.base.Rectangle;
import com.game.score.core.ScoreManager;
import com.game.score.ui.ScoreDisplay;
import com.game.timer.core.GameTimer;
import com.game.timer.ui.GameTimerDisplay;
import com.game.ui.GameWindow;

public class GameLauncher {

	public static void main(String[] args) {
		final Rectangle gameRectangle = new Rectangle(0, 0, 800, 600);
		final Rectangle scoreRectangle = new Rectangle(0, 0, 800, 30);
		final Rectangle timerRectangle = new Rectangle(0, 0, 800, 30);
		
		//The triggering timers
		final Timer gameTickTimer = new Timer(10);
		final Timer countdownTimer = new Timer(1000);
		
		//Components of the core game
		final DrawableManager drawableManager = new DrawableManager();
		final Game game = new Game(drawableManager, gameRectangle);
		final GameDisplay gameDisplay = new GameDisplay(drawableManager, gameRectangle);
		
		//Game Timer components
		final GameTimer countdownTimerCore = new GameTimer(5, 0);
		final GameTimerDisplay gameTimerDisplay = new GameTimerDisplay(timerRectangle);
		
		//Score components
		final ScoreManager scoreManager = new ScoreManager();
		final ScoreDisplay scoreDisplay = new ScoreDisplay(scoreRectangle);
		
		
		@SuppressWarnings("unchecked")
		final GameWindow window = new GameWindow(Arrays.asList(gameDisplay, gameTimerDisplay, scoreDisplay));
		
		window.addObserver(game); //For input keys
		game.addObserver(gameDisplay); //For display update
		game.getScoreObservable().addObserver(scoreManager); //For score updates
		scoreManager.addObserver(scoreDisplay); //to repaint updated score
		
		gameTickTimer.addObserver(game); //For moving the game forward
		countdownTimer.addObserver(countdownTimerCore); //So that timer makes game timer count down
		countdownTimerCore.addObserver(gameTimerDisplay); //So that game timer knows when to repaint
		
		countdownTimerCore.addObserver(game); //For notifying when time runs out
		
		
		gameTickTimer.start();
		countdownTimer.start();

	}

}
