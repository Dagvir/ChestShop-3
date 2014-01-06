package com.Acrobot.ChestShop.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * @author CrowsEyes
 */


public class Toggle implements CommandExecutor {
    
	private final static ArrayList<String> toggledPlayers = new ArrayList<String>();
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	    
	    if (sender instanceof Player) {
	        Player player = (Player) sender;
	        ignoreTransactionMessages(player);
	    }
        return true;
    }
	
    public void ignoreTransactionMessages(Player player) {
        if(toggledPlayers.contains(player.getName())) {
            toggledPlayers.remove(player.getName());
            player.sendMessage(ChatColor.GREEN + "You will once again receive buy/sell messages.");
        }
        else {
            toggledPlayers.add(player.getName());
            player.sendMessage(ChatColor.RED + "You will no longer receive buy/sell messages.");
        }
            
    }

    public static boolean isIgnoringTransactionMessages(Player player) {
        return toggledPlayers.contains(player.getName());
    }
	
}