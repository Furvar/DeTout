package be.vilevar.DeTout.Staff;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import be.vilevar.DeTout.CraftEtAutres.Crafts;
import be.vilevar.DeTout.CraftEtAutres.Masque;

public class Anonyme implements CommandExecutor {

	private String UnknowCMD = ChatColor.RED+"/anonyme pour voir la liste des commandes.";
	private String notCible = ChatColor.RED+"Le joueur cible n'existe pas ou n'est pas connecté.";
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(cmd.getName().equalsIgnoreCase("anonyme") && sender instanceof Player && sender.hasPermission("detout.staff.anonyme")){
			Player p = (Player)sender;
			Masque m = new Masque();
			/** Nor Meer : Anonyme's Command's List */
			if(args.length==0){
				p.sendMessage(ChatColor.GOLD+"/anonyme "+ChatColor.RED+"<list>:"+ChatColor.GREEN+" Pour voir la liste des joueurs anonymes.");
				p.sendMessage(ChatColor.GOLD+"/anonyme "+ChatColor.RED+"<give>:"+ChatColor.GREEN+" Pour se giver un masque.");
				p.sendMessage(ChatColor.GOLD+"/anonyme "+ChatColor.RED+"<set> [player]:"+ChatColor.GREEN+" Pour rendre un joueur anonyme.");
				p.sendMessage(ChatColor.GOLD+"/anonyme "+ChatColor.RED+"<unset> [player]:"+ChatColor.GREEN+" Pour enlever l'anonymat d'un joueur s'il est anonyme.");
				if(m.isAnonyme(p)){
					p.sendMessage(ChatColor.GOLD+"/anonyme "+ChatColor.RED+"<modify> [nombre]:"+ChatColor.GREEN+" Pour modifier votre chiffre anonyme (nombre entre 1 et 1000 compris).");
				}
			}
			/** One Argument Meer */
			else if(args.length==1){
				// /anonyme list
				if(args[0].equalsIgnoreCase("list")){
					for(Player pls : Bukkit.getOnlinePlayers()){
						if(m.isAnonyme(pls)) p.sendMessage(pls.getDisplayName()+ChatColor.GREEN+" est anonyme.");
					}
					return true;
				}
				// /anonyme give
				else if(args[0].equalsIgnoreCase("give")){
					p.sendMessage(ChatColor.LIGHT_PURPLE+"Vous venez de vous give un masque.");
					p.getInventory().addItem(Crafts.hood);
					return true;
				}
				// /anonyme set
				else if(args[0].equalsIgnoreCase("set")){
					p.sendMessage(ChatColor.DARK_PURPLE+"Vous venez de vous rendre anonyme.");
					p.getInventory().getHelmet().setType(Crafts.hood.getType());
					p.getInventory().getHelmet().setItemMeta(Crafts.hood.getItemMeta());
					p.getInventory().getHelmet().setData(Crafts.hood.getData());
					p.getInventory().getHelmet().setAmount(Crafts.hood.getAmount());
					return true;
				}
				// /anonyme unset
				else if(args[0].equalsIgnoreCase("unset")){
					if(m.isAnonyme(p)){
						p.sendMessage(ChatColor.DARK_PURPLE+"Vous venez de vous faire perdre votre anonymat.");
						p.getInventory().getHelmet().setType(Material.AIR);
						return true;
					}else{
						p.sendMessage(ChatColor.DARK_RED+"Error: "+ChatColor.RED+"vous n'êtes pas anonyme.");
						return true;
					}
				}
				// /anonyme modify
				else if(args[0].equalsIgnoreCase("modify")){
					p.sendMessage(ChatColor.GOLD+"/anonyme modify "+ChatColor.RED+"[nombre]");
					return true;
				}
				// /anonyme <unknow>
				else{
					p.sendMessage(UnknowCMD);
					return true;
				}
			}
			/** Twoo Arguments Meer */
			else if(args.length==2){
				// /anonyme set <player>
				if(args[0].equalsIgnoreCase("set")){
					Player cible = Bukkit.getPlayer(args[1]);
					if(cible == null){
						p.sendMessage(notCible);
						return true;
					}else{
						p.sendMessage(ChatColor.DARK_PURPLE+"Vous venez de rendre "+cible.getDisplayName()+ChatColor.DARK_PURPLE+" anonyme.");
						cible.getInventory().setHelmet(Crafts.hood);
						return true;
					}
				}
				// /anonyme unset <player>
				else if(args[0].equalsIgnoreCase("unset")){
					Player cible = Bukkit.getPlayer(args[1]);
					if(cible == null){
						p.sendMessage(notCible);
						return true;
					}else{
						if(m.isAnonyme(p)){
							p.sendMessage(ChatColor.DARK_PURPLE+"Vous venez de faire perdre l'anonymat de "+cible.getDisplayName()+ChatColor.DARK_PURPLE+" à "+cible.getDisplayName()+ChatColor.DARK_PURPLE+".");
							cible.getInventory().setHelmet(new ItemStack(Material.AIR));
							return true;
						}else{
							p.sendMessage(ChatColor.DARK_RED+"Error: "+ChatColor.RED+cible.getDisplayName()+ChatColor.RED+" n'est pas anonyme.");
							return true;
						}
					}
				}
				// /anonyme modify <number>
				else if(args[0].equalsIgnoreCase("modify")){
					if(m.isAnonyme(p)){
						int nb = Integer.parseInt(args[1]);
						if(nb > 0 && nb < 1001){
							String anon = "anonymous";
							m.anonyme.remove(p);
							m.anonyme.put(p, anon+nb);
							p.sendMessage(ChatColor.AQUA+"Vous êtes désormais anonyme sous le nom de "+m.anonyme.get(p)+".");
							return true;
						}else{
							p.sendMessage(ChatColor.DARK_RED+"Error: "+ChatColor.RED+"le nombre est hors des limites.");
							return true;
						}
					}else{
						p.sendMessage(ChatColor.DARK_RED+"Error: "+ChatColor.RED+"vous n'êtes pas anonyme.");
						return true;
					}
				}
				// /anonyme <unknow> <unknow>
				else{
					p.sendMessage(UnknowCMD);
					return true;
				}
			}else{
				p.sendMessage(UnknowCMD);
				return true;
			}
		}
		return false;
	}

}
