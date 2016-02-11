package com.djczq.lottery;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;

public class CommandShow implements CommandExecutor {
	Lottery lottery;
	List<ItemStack> list;
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length == 1){
			list=lottery.getList(args[0]);
			try{
		        for(ItemStack item : list){
		        	String name=item.getItemMeta().getDisplayName();
		        	if(name==null)
		        		name = item.getType().name();
		        	sender.sendMessage("+  "+item.getAmount()+" "+name);
		        }
			}catch(NullPointerException e){
				sender.sendMessage(lottery.getMessage("emptyLottery"));
			}catch(ArrayIndexOutOfBoundsException e){
	        	sender.sendMessage(lottery.getMessage("unnamedLottery"));
	        	return false;
	        }
	        return true;  
		}
		return false;

	}
	CommandShow(Lottery l){
		lottery=l;
	}

}
