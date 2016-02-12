package com.djczq.lottery;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandReload implements CommandExecutor {
	Lottery lottery;
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length == 0){
			lottery.loadConfigFiles();
			return true;
		}
		return false;
	}
	//test
	//commit via eclipse
	CommandReload(Lottery l){
		lottery=l;
	}
}
