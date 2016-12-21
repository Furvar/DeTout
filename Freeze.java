package be.vilevar.DeTout.Staff;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Freeze implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender p, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("freeze") && p.hasPermission("detout.staff.freeze")){
			ArrayList<Player>notMsg = new ArrayList<>();
			notMsg.clear();
			if(args.length == 0){
				p.sendMessage(ChatColor.RED+"/freeze <joueur>");
				return true;
			}
			/** Default Message */
			if(args.length == 1){
				Player player = Bukkit.getPlayer(args[0]);
				/*
				 *  Player Not Connected 
				 */
				if(player == null){
					p.sendMessage(ChatColor.RED+"Le joueur cible n'existe pas ou n'est pas connectŽ.");
					return true;
				/*
				 *  Player Connected 
				 */
				}else{
					/* Freeze Player => UnFreeze Player */
					if(FreezeOptions.isFreeze(player)){
						p.sendMessage(ChatColor.GOLD+"Vous venez de dŽcongeler "+player.getDisplayName()+ChatColor.GOLD+".");
						
						// Message For Alle Players Met Freeze Permission */
						for(Player pls : Bukkit.getOnlinePlayers()){
							if(pls.hasPermission("detout.staff.freeze") && !notMsg.contains(pls)){
								pls.sendMessage(ChatColor.RED+player.getDisplayName()+ChatColor.GOLD+" c'est fait dŽcongeler par "+ChatColor.RED+p.getName()+ChatColor.GOLD+".");
								notMsg.add(pls);
							}
						}
					}
					/* Not Freeze Player => Freeze Player */
					if(!FreezeOptions.isFreeze(player)){
						p.sendMessage(ChatColor.GOLD+"Vous venez de freeze "+player.getDisplayName()+ChatColor.GOLD+".");
						
						// Message For Alle Players Met Freeze Permission 
						for(Player pls : Bukkit.getOnlinePlayers()){
							if(pls.hasPermission("detout.staff.freeze") && !notMsg.contains(pls)){
								pls.sendMessage(ChatColor.RED+player.getDisplayName()+ChatColor.GOLD+" c'est fait congeler par "+ChatColor.RED+p.getName()+ChatColor.GOLD+".");
								notMsg.add(pls);
							}
						}
					}
					/** UnFreeze Or Freeze That Player */
					FreezeOptions.freezeAction(player, true);
					return true;
				}
			}
			/** Custom Message */
			if(args.length == 2){
				Player player = Bukkit.getPlayer(args[0]);
				/* 
				 * Player Not Connected
				 */
				if(player == null){
					p.sendMessage(ChatColor.RED+"Le joueur cible n'existe pas ou n'est pas connectŽ.");
					return true;
				/* 
				 * Player Connected 
				 */
				}else{
					String msg = args[1].replace('_', ' ').replace('&', '¤');
					
					/* Player Freeze => UnFreeze Player */
					if(FreezeOptions.isFreeze(player)){
						p.sendMessage(ChatColor.GOLD+"Vous venez de dŽcongeler "+player.getDisplayName()+ChatColor.GOLD+".");
						player.sendMessage(ChatColor.GREEN+"Vous tes dŽcongelŽ car "+msg+ChatColor.GREEN+".");
						
						// Message For Alle Players Met Freeze Permission
						for(Player pls : Bukkit.getOnlinePlayers()){
							if(pls.hasPermission("detout.staff.freeze") && !notMsg.contains(pls)){
								pls.sendMessage(ChatColor.RED+player.getDisplayName()+ChatColor.GOLD+" c'est fait dŽcongeler par "+ChatColor.RED+p.getName()+ChatColor.GOLD+" car "+msg+ChatColor.GOLD+".");
								notMsg.add(pls);
							}
						}
					}
					/* Player Not Freeze => Freeze Player */
					if(!FreezeOptions.isFreeze(player)){
						p.sendMessage(ChatColor.GOLD+"Vous venez de freeze "+player.getDisplayName()+ChatColor.GOLD+".");
						player.sendMessage(ChatColor.GREEN+"Vous tes congelŽ car "+msg+ChatColor.GREEN+".");
						
						// Message For Alle Players Met Freeze Permission 
						for(Player pls : Bukkit.getOnlinePlayers()){
							if(pls.hasPermission("detout.staff.freeze") && !notMsg.contains(pls)){
								pls.sendMessage(ChatColor.RED+player.getDisplayName()+ChatColor.GOLD+" c'est fait congeler par "+ChatColor.RED+p.getName()+ChatColor.GOLD+" car "+msg+ChatColor.GOLD+".");
								notMsg.add(pls);
							}
						}
					}
					/** UnFreeze Or Freeze That Player */
					FreezeOptions.freezeAction(player, false);
					return true;
				}
			}
			if(args.length > 2){
				p.sendMessage(ChatColor.RED+"/freeze <joueur> [message]");
				return true;
			}
		}
		return false;
	}

}
