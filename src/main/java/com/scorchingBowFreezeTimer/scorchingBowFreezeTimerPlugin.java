package com.scorchingBowFreezeTimer;

import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.VarbitChanged;
import net.runelite.api.gameval.InventoryID;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;

@Slf4j
@PluginDescriptor(
	name = "Scorching Bow Freeze Timer"
)
public class scorchingBowFreezeTimerPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private ItemManager itemManager;

	@Inject
	private InfoBoxManager infoBoxManager;

	private boolean bowSAUsed = false;
	int bindDuration;
	float shortTimer; //A short timer that will remove the overlay after 20 seconds
	ScorchingBowFreezeTimerOverlay counter;

	@Override
	protected void startUp() throws Exception
	{
		shortTimer = 20;
		bindDuration = 20;
		counter = null;
	}

	@Override
	protected void shutDown() throws Exception
	{closeCounter();}

	private void updateCounter() {
		if (counter == null)
		{
			counter = new ScorchingBowFreezeTimerOverlay(itemManager.getImage(29591), this, bindDuration);
			infoBoxManager.addInfoBox(counter);
		}
		else
		{
			counter.timeLeft(bindDuration);
			counter.setImage(itemManager.getImage(29591)); // Update overlay image
			infoBoxManager.updateInfoBoxImage(counter);
		}
	}

	@Subscribe
	private void onGameTick(GameTick event) {
		if (bowSAUsed) {
			if (bindDuration >= 0) {
				bindDuration -= 1;
				updateCounter();
			}
			shortTimer -= 0.6;
			if (shortTimer <= 0) {
				closeCounter();
				bowSAUsed = false;
			}
		}
	}

	@Subscribe
	public void onVarbitChanged(VarbitChanged varbitChanged)
	{
		//special attack used
		if (varbitChanged.getVarpId() == 4524)
		{
			final ItemContainer itemContainer = client.getItemContainer(InventoryID.WORN);
			if (itemContainer != null) {
				final Item weapon = itemContainer.getItem(EquipmentInventorySlot.WEAPON.getSlotIdx());
				if (weapon != null) {
					String weaponName = itemManager.getItemComposition(weapon.getId()).getName();
					if (weaponName.equals("Scorching bow"))
					{
						bindDuration = 20;
						bowSAUsed = true;
						shortTimer = 20;
						updateCounter();
					}
				}
			}
		}
	}

	private void closeCounter() {
		if (counter != null) {
			infoBoxManager.removeInfoBox(counter);
			counter = null;
		}
	}
}
