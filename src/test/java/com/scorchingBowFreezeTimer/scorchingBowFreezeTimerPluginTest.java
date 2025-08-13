package com.scorchingBowFreezeTimer;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class scorchingBowFreezeTimerPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(scorchingBowFreezeTimerPlugin.class);
		RuneLite.main(args);
	}
}