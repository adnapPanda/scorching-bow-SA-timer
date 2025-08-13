package com.scorchingBowSATimer;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class scorchingBowSATimerPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(scorchingBowSATimerPlugin.class);
		RuneLite.main(args);
	}
}