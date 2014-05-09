package com.Acrobot.ChestShop.Listeners.PostTransaction;

import static com.Acrobot.ChestShop.Signs.ChestShopSign.ITEM_LINE;

import com.Acrobot.Breeze.Utils.InventoryUtil;
import com.Acrobot.ChestShop.Configuration.Messages;
import com.Acrobot.ChestShop.Configuration.Properties;
import com.Acrobot.ChestShop.Events.EmptyShopRemovedEvent;
import com.Acrobot.ChestShop.Events.TransactionEvent;
import com.Acrobot.ChestShop.Signs.ChestShopSign;
import com.Acrobot.ChestShop.Utils.uBlock;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * @author Acrobot
 */
public class EmptyShopDeleter implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public static void onTransaction(TransactionEvent event) {
        if (event.getTransactionType() != TransactionEvent.TransactionType.BUY) {
            return;
        }

        Inventory ownerInventory = event.getOwnerInventory();
        Sign sign = event.getSign();
        Chest connectedChest = uBlock.findConnectedChest(sign);

        if (!shopShouldBeRemoved(ownerInventory, event.getStock())) {
            return;
        }

        sign.getBlock().setType(Material.AIR);
        ownerInventory.addItem(new ItemStack(Material.SIGN, 1));

        if (Properties.REMOVE_EMPTY_CHESTS && !ChestShopSign.isAdminShop(ownerInventory)) {
            Event removeEvent = new EmptyShopRemovedEvent(sign, connectedChest, event.getOwner().getName(), event.getSign().getLine(ITEM_LINE));
            Bukkit.getPluginManager().callEvent(removeEvent);
            connectedChest.getBlock().setType(Material.AIR);
        } 
        
        event.getClient().sendMessage(Messages.prefix(Messages.SHOP_EMPTY));
        
        Player player = Bukkit.getPlayerExact(event.getOwner().getName());

        if (player != null) {
            String message = Messages.YOUR_SHOP_EMPTY.replace("%material", event.getSign().getLine(ITEM_LINE));
            player.sendMessage(message);
        }
    }

    private static boolean shopShouldBeRemoved(Inventory inventory, ItemStack[] stock) {
        return Properties.REMOVE_EMPTY_SHOPS && !ChestShopSign.isAdminShop(inventory) && !InventoryUtil.hasItems(stock, inventory);
    }
}
