package com.djczq.lottery;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandAdd implements CommandExecutor{

	Lottery lottery;
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	        if (sender instanceof Player && args.length == 1) {
	            Player player = (Player) sender;
	            try{
	        		lottery.addItem(player.getItemInHand().clone(), args[0]);
	            }catch(ArrayIndexOutOfBoundsException e){
	            	player.sendMessage(lottery.getMessage("unnamedLottery"));
	            	return false;
	            }catch(NullPointerException e){
	            	player.sendMessage(lottery.getMessage("unknownLottery"));
	            }

	            return true;            
	        }
		return false;
	}
	CommandAdd(Lottery l){
		lottery=l;
	}

}
