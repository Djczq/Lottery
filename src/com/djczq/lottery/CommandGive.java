package com.djczq.lottery;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandGive implements CommandExecutor {
	Lottery lottery;
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(args.length == 2){
        	Player player = lottery.getPlayer(args[1]);   
        	try{
        	return lottery.giveItemToPlayer(args[0], player); 
        	}catch(NullPointerException e){
            	sender.sendMessage(lottery.getMessage("invalidName"));
            	return true;
            }
        }
		return false;
	}
	CommandGive(Lottery l){
		lottery=l;
	}
}
