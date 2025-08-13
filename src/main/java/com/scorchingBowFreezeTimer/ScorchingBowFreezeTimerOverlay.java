package com.scorchingBowFreezeTimer;

import net.runelite.client.ui.overlay.infobox.Counter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ScorchingBowFreezeTimerOverlay extends Counter {

	public ScorchingBowFreezeTimerOverlay(BufferedImage image, scorchingBowFreezeTimerPlugin plugin, int setCount) {
		super(image, plugin, setCount);
	}

	public void timeLeft(int time) {
		setCount(time);
	}

	@Override
	public Color getTextColor()
	{
		int count = getCount();
		return count > 0 ? Color.WHITE : Color.RED;
	}
}