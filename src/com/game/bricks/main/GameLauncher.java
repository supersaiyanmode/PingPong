package com.game.bricks.main;

import com.game.bricks.core.Game;
import com.game.bricks.ui.DrawableManager;
import com.game.bricks.ui.GameDisplay;
import com.game.bricks.ui.Runners;
import com.game.bricks.ui.TimerManager;
import com.game.bricks.ui.base.Rectangle;

public class GameLauncher {

	public static void main(String[] args) {

		final Rectangle displayRectangle = new Rectangle(0, 0, 800, 600);

		final DrawableManager drawableManager = new DrawableManager();

		Runners runner = new Game(drawableManager, displayRectangle);

		new GameDisplay(runner, drawableManager, displayRectangle);

		TimerManager timerManager = new TimerManager(runner);
		timerManager.start();

	}

}
