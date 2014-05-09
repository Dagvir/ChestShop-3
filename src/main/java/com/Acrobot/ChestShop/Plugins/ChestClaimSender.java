package com.Acrobot.ChestShop.Plugins;

import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.Acrobot.Breeze.Utils.InventoryUtil;
import com.Acrobot.ChestShop.Events.EmptyShopRemovedEvent;
import com.octagami.chestclaim.util.ClaimUtil;

/**
 * @author Acrobot
 */
public class ChestClaimSender implements Listener {
    
    public final static String EMPTY_LABEL = "Empty Shop - ";
    public final static String DEPLETED_LABEL = "Depleted Shop - ";
    
	public ChestClaimSender() {

	}

	@EventHandler
	public void onEmptyShopRemoved(EmptyShopRemovedEvent event) {

		Chest chest = event.getChest();
		
		if (chest == null) {
		    return;
		}
		
		String claimLabel = DEPLETED_LABEL + event.getItem();
		
		if (InventoryUtil.isEmpty(chest.getInventory())) {
		    claimLabel = EMPTY_LABEL + event.getItem();
		}

		ClaimUtil.sendContainer("ChestShop", event.getOwner(), chest.getBlock(), null, chest.getInventory(), claimLabel);
	}

}
