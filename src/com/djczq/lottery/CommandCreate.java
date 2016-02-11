package com.djczq.lottery;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandCreate implements CommandExecutor {
	Lottery lottery;
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(args.length == 1){
        	try{
        		lottery.createList(args[0]);
        	}catch(ArrayIndexOutOfBoundsException e){
            	sender.sendMessage(lottery.getMessage("unnamedLottery"));
            	return false;
            }
            return true;  
		}
		return false;
	}
	CommandCreate(Lottery l){
		lottery=l;
	}
}
