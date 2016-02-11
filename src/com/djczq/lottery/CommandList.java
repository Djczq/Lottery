package com.djczq.lottery;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandList implements CommandExecutor {

	Lottery lottery;
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length == 0){
			if(lottery.getKeySet()==null){
				sender.sendMessage(lottery.getMessage("noLottery"));
			}else{
				sender.sendMessage(lottery.getMessage("availableLottery"));
				for(String s : lottery.getKeySet())
					sender.sendMessage("+  "+s);
			}
	      return true;
		}else{
			return false;
		}
	}
	CommandList(Lottery l){
		lottery=l;
	}
}
