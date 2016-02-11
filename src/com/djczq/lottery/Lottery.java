package com.djczq.lottery;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Lottery extends JavaPlugin {
	Map<String,ArrayList<ItemStack>> map;
	Map<String,PluginCommand> commands;
	Map<String,String> messages;
	
    public void onEnable() {
    	commands = new TreeMap<String,PluginCommand>();
    	messages = new TreeMap<String,String>();
		this.loadConfigFiles();
		this.createCommand("add", new CommandAdd(this));
		this.createCommand("create", new CommandCreate(this));
		this.createCommand("get", new CommandGet(this));
		this.createCommand("list", new CommandList(this));
		this.createCommand("reload", new CommandReload(this));
		this.createCommand("show", new CommandShow(this));
		this.createCommand("give", new CommandGive(this));

    }
     @SuppressWarnings("unchecked")
	void loadConfigFiles(){
     	File lang_fr = new File( this.getDataFolder()+"lang_fr.yml" );
     	if(!lang_fr.exists())
     		this.saveResource("lang_fr.yml",true);
     	
     	File lang_en = new File( this.getDataFolder()+"lang_en.yml" );
     	if(!lang_en.exists())
     		this.saveResource("lang_en.yml",true);
     	
     	File lang = new File( this.getDataFolder()+File.separator+this.getConfig().getString("language"));
     	
     	this.getLogger().info(lang.getAbsolutePath());
     	FileConfiguration langFileConfiguration = YamlConfiguration.loadConfiguration(lang);
     	Set<String> messagesSet = langFileConfiguration.getConfigurationSection("commandDescription").getKeys(true);
     	
     	for(String s1 : messagesSet) {
     		this.messages.put(s1, langFileConfiguration.getString("commandDescription."+s1));
     	}
     	
     	messagesSet = langFileConfiguration.getConfigurationSection("messages").getKeys(true);
     	for(String s1 : messagesSet) {
     		this.messages.put(s1, langFileConfiguration.getString("messages."+s1));
     	}
     	
     	saveDefaultConfig();
     	this.reloadConfig();
 		map = new TreeMap<String,ArrayList<ItemStack>>();
 		 
     	Set<String> skeys = this.getConfig().getConfigurationSection("list").getKeys(true);
 		
     	for(String s : skeys){
     		map.put(s,( ArrayList<ItemStack>)this.getConfig().getList("list."+s));
     	}
 		this.getConfig().set("list", map);
 		saveConfig();
     	getLogger().info("Djczq Lottery Plugin Enabled");
    }
	void addItem(ItemStack itemInHand, String name) {
		map.get(name).add(itemInHand);
		this.getConfig().set("list", map);
		this.saveConfig();
	}

	List<ItemStack> getList(String name) {
		return map.get(name);
		
	}
	void createList(String name){
		map.put(name, new ArrayList<ItemStack>());
		this.getConfig().set("list", map);
		saveConfig();
		 
	}
	Set<String> getKeySet(){
		return map.keySet();
	}
	
	void createCommand(String reducedName, CommandExecutor cmdEx){
		this.commands.put(reducedName, this.getCommand("lottery"+reducedName));
		List<String> aliases = new ArrayList<String>();
		aliases.add("l"+reducedName);
		this.commands.get(reducedName).setAliases(aliases);
		this.commands.get(reducedName).setExecutor(cmdEx);
		this.commands.get(reducedName).setPermission("lottery."+reducedName);
		this.commands.get(reducedName).setPermissionMessage(this.getMessage("noPermission")+" lottery."+reducedName);
		this.commands.get(reducedName).setDescription(this.getMessage(reducedName));
	}
	
	String getMessage(String key){
		return this.messages.get(key);
	}
	Player getPlayer(String name){
		return this.getServer().getPlayer(name);
		
	}
	boolean giveItemToPlayer(String lotteryName,Player player){
        try{
        	Random randomGenerator = new Random();
            int num = randomGenerator.nextInt(map.get(lotteryName).size());
            ItemStack item = map.get(lotteryName).get(num);
            String name=item.getItemMeta().getDisplayName();
            if(name==null)
            		name = item.getType().name();
            player.sendMessage(this.getMessage("itemReward")+" : "+item.getAmount()+" "+name);
            player.getInventory().addItem(item);
        }catch(ArrayIndexOutOfBoundsException e){
        	player.sendMessage(this.getMessage("unnamedLottery"));
        	return false;
        }catch(NullPointerException e){
        	player.sendMessage(this.getMessage("unknownLottery"));
        	return false;
        }
        return true;
	}
}
