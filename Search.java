package be.vilevar.DeTout.Staff;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Search implements CommandExecutor {
	
	public String noPerm = ChatColor.DARK_RED+"Vous n'avez pas la permission requise pour.";
	public String noCible = ChatColor.RED+"Le joueur cible n'existe pas ou n'est pas connecté.";
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("search") && sender instanceof Player){
			Player p = (Player)sender;
			if(args.length == 0){
				p.sendMessage(ChatColor.GREEN+"/search "+ChatColor.GOLD+"<ip> [joueur]:"+ChatColor.AQUA+" pour regarder s'il y a des joueurs avec la même adresse ip.");
				return true;
			}
			if(args.length == 1){
				// /search ip
				if(args[0].equalsIgnoreCase("ip")){
					if(p.hasPermission("detout.staff.search.ip")){
						HashMap<String, Player> adresse = new HashMap<>();
						adresse.clear();
						for(Player pls : Bukkit.getOnlinePlayers()) {
							if(adresse.containsKey(pls.getAddress().getAddress().toString())){
								Player pl = adresse.get(pls.getAddress().getAddress().toString());
								p.sendMessage(ChatColor.GOLD+"L'ip de "+ChatColor.AQUA+pl.getName()+ChatColor.RED+" ("+pl.getAddress().getAddress().toString()+")"+ChatColor.GOLD+" est utilisée par "+ChatColor.AQUA+pls.getName()+ChatColor.RED+" ("+pls.getAddress().getAddress().toString()+")"+ChatColor.GOLD+".");
							}
							adresse.put(pls.getAddress().getAddress().toString(), pls);
						}
					}else{
						p.sendMessage(noPerm);
						return true;
					}
				}
			}
			if(args.length == 2){
				// /search ip <player>
				if(args[0].equalsIgnoreCase("ip")){
 					if(!p.hasPermission("detout.staff.search.ip")){
 						p.sendMessage(noPerm);
 						return true;
 					}else{
 						Player cible = Bukkit.getPlayer(args[1]);
 						ArrayList<Player>msg = new ArrayList<>();
 						msg.clear();
 						if(cible != null){
 							for(Player pls : Bukkit.getOnlinePlayers()){
 								if(pls != null && pls != cible && pls.getAddress().getAddress().toString().equals(cible.getAddress().getAddress().toString())){
 									msg.add(p);
 									p.sendMessage(ChatColor.GOLD+"L'ip de "+ChatColor.AQUA+cible.getName()+ChatColor.RED+" ("+cible.getAddress().getAddress().toString()+")"+ChatColor.GOLD+" est utilisée par "+ChatColor.AQUA+pls.getName()+ChatColor.RED+" ("+pls.getAddress().getAddress().toString()+")"+ChatColor.GOLD+"");
 									return true;
 								}else if(!msg.contains(p)){
 									msg.add(p);
 									p.sendMessage(ChatColor.GREEN+"Le plugin n'a pas repéré de joueur ayant la même adresse ip ("+cible.getAddress().getAddress().toString()+").");
 								}
 							}
 						}else{
 							p.sendMessage(noCible);
 							return true;
 						}
 					}
				}
			}
		}
		return false;
	}

}
