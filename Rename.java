package be.vilevar.DeTout.UtilsCmd;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Rename implements CommandExecutor {

	public ItemStack is;
	public ItemMeta im;
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(cmd.getName().equalsIgnoreCase("rename") && sender instanceof Player && sender.hasPermission("detout.utilscmd.rename")){
			Player p = (Player)sender;
			if(args.length != 1){
				p.sendMessage(ChatColor.DARK_RED+"Error to command perform.");
				return true;
			}else{
				if(p.getInventory().getItemInHand() == null){
					p.sendMessage(ChatColor.DARK_RED+"Error: "+ChatColor.RED+"You have no item in you hand.");
					return true;
				}else{
					ItemStack is = p .getInventory().getItemInHand();
					ItemMeta im = is.getItemMeta();
					this.im = im;
					this.is = is;
					
					p.getInventory().setItemInHand(null);
					p.updateInventory();
					
					String name = args[0].replace('_', ' ').replace('&', '¤');
					this.im.setDisplayName(name);
					this.is.setItemMeta(this.im);
					p.getInventory().setItemInHand(this.is);
					p.updateInventory();
					
 					return true;
				}
			}
		}
		return false;
	}

}
