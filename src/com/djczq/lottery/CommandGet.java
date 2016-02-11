package com.djczq.lottery;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandGet implements CommandExecutor {
	Lottery lottery;
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
        if (sender instanceof Player && args.length == 1) {
            Player player = (Player) sender;
            return lottery.giveItemToPlayer(args[0], player);
        }
		return false;
	}
	CommandGet(Lottery l){
		lottery=l;
	}
}
