package com.game.bricks.main;

import com.game.bricks.core.Game;
import com.game.bricks.ui.DrawableManager;
import com.game.bricks.ui.FrameRateManager;
import com.game.bricks.ui.GameDisplay;
import com.game.bricks.ui.base.Rectangle;

public class Main {
	public static void main(String[] args) {
		//
		final Rectangle displayRectangle = new Rectangle(0, 0, 800, 600);
		final FrameRateManager frm = new FrameRateManager(5);
		final DrawableManager dm = new DrawableManager();
		final Game game = new Game(dm, frm, 10, 10, displayRectangle);
		final GameDisplay gd = new GameDisplay(game, displayRectangle);
		new Thread(gd).start();
	}
}
