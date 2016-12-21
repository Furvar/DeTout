package be.vilevar.DeTout.Fetes;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FetesCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(cmd.getName().equalsIgnoreCase("fetes")){
			if(sender instanceof Player){
				Player p = (Player) sender;
				if(args.length == 0){
					if(p.isOp()){
						p.sendMessage(ChatColor.GOLD+"Liste : "+ChatColor.GREEN+"normal; halloween; noel; …");
						p.sendMessage(ChatColor.GOLD+"Commandes supplémentaires : "+ChatColor.GREEN+"maintenant.");
						return true;
					}
				}
				if(args.length == 1){
					if(args[0].equalsIgnoreCase("maintenant")){
						if(p.hasPermission("detout.fetes.maintenant")){
							if(ListPeriode.isPeriode(ListPeriode.NORMAL))p.sendMessage(ChatColor.AQUA+"Nous sommes en période "+ListPeriode.NORMAL.getPeName()+ChatColor.AQUA+".");
							if(ListPeriode.isPeriode(ListPeriode.HALLOWEEN))p.sendMessage(ChatColor.AQUA+"Nous sommes en période "+ListPeriode.HALLOWEEN.getPeName()+ChatColor.AQUA+".");
							if(ListPeriode.isPeriode(ListPeriode.NOEL))p.sendMessage(ChatColor.AQUA+"Nous sommes en période "+ListPeriode.NOEL.getPeName()+ChatColor.AQUA+".");
						}
					}
					if(args[0].equalsIgnoreCase("normal")){
						if(p.hasPermission("detout.fetes.normal")){
							ListPeriode.setPeriode(ListPeriode.NORMAL);
							p.sendMessage(ChatColor.DARK_PURPLE+"Vous venez de mettre la période "+ListPeriode.NORMAL.getPeriode().getPeName()+ChatColor.DARK_PURPLE+".");
							return true;
						}
					}
					if(args[0].equalsIgnoreCase("halloween")){
						if(p.hasPermission("detout.fetes.halloween")){
							ListPeriode.setPeriode(ListPeriode.HALLOWEEN);
							p.sendMessage(ChatColor.DARK_PURPLE+"Vous venez de mettre la période "+ListPeriode.HALLOWEEN.getPeriode().getPeName()+ChatColor.DARK_PURPLE+".");
							return true;
						}
					}
					if(args[0].equalsIgnoreCase("noel")){
						if(p.hasPermission("detout.fetes.noel")){
							ListPeriode.setPeriode(ListPeriode.NOEL);
							p.sendMessage(ChatColor.DARK_PURPLE+"Vous venez de mettre la période "+ListPeriode.NOEL.getPeriode().getPeName()+ChatColor.DARK_PURPLE+".");
							return true;
						}
					}
				}
			}
		}
		return false;
	}
}
