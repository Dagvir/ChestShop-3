package com.Acrobot.ChestShop.Events;

import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import javax.annotation.Nullable;

/**
 * @author octagami
 */
public class EmptyShopRemovedEvent extends Event {
	
    private static final HandlerList handlers = new HandlerList();

    private final Sign sign;
    private final Chest chest;
    private final String owner;
    private final String item;

    public EmptyShopRemovedEvent(Sign sign, @Nullable Chest chest, String owner, String item) {
        this.sign = sign;
        this.chest = chest;
        this.owner = owner;
        this.item = item;
    }

    @Nullable public Chest getChest() {
        return chest;
    }

    public Sign getSign() {
        return sign;
    }
    
    public String getOwner() {
        return owner;
    }
    
    public String getItem() {
        return item;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
