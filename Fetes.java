package be.vilevar.DeTout.Fetes;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import be.vilevar.DeTout.DeTout;

public class Fetes implements Listener {

	public String bravo = ChatColor.GREEN+"Bravo, vous venez de crafter l'item de l'"; 
	public String notPe = ChatColor.RED+"Nous ne sommes pas à la période de l'";
	
	@SuppressWarnings("deprecation")
	public Fetes(){
		//Halloween
		ShapedRecipe CitHal = new ShapedRecipe(ListPeriode.HALLOWEEN.getCraft());
		CitHal.shape(new String[] {" G ", "GCG", " G "});
		CitHal.setIngredient('G', Material.PUMPKIN_SEEDS);
		CitHal.setIngredient('C', Material.PUMPKIN);
		DeTout.getInstance().getServer().addRecipe(CitHal);
		//Noel
		  //1
		ShapedRecipe noel1 = new ShapedRecipe(ListPeriode.NOEL_1.getCraft());
		noel1.shape(new String[] {"RRR", "RNR", "RRR"});
		noel1.setIngredient('N', Material.SNOW_BLOCK);
		noel1.setIngredient('R', Material.WOOL, (byte)14);
		DeTout.getInstance().getServer().addRecipe(noel1);
		  //2
		ShapedRecipe noel2 = new ShapedRecipe(ListPeriode.NOEL_2.getCraft());
		noel2.shape(new String[] {"VVV", "VNV", "VVV"});
		noel2.setIngredient('N', Material.SNOW_BLOCK);
		noel2.setIngredient('V', Material.WOOL, (byte)5);
		DeTout.getInstance().getServer().addRecipe(noel2);
	}

	@EventHandler
	public void onRemoveRecipe(CraftItemEvent e){
		ItemStack res = e.getRecipe().getResult();
		Player p = (Player) e.getWhoClicked();
		if(!res.hasItemMeta())return;
		else if(res.hasItemMeta() && res.getItemMeta() != null && res.getItemMeta().getDisplayName() != null){
			//Halloween
			if(res.getItemMeta().getDisplayName().equals(ListPeriode.HALLOWEEN.getPeName())){
				if(ListPeriode.isPeriode(ListPeriode.HALLOWEEN)){
					p.sendMessage(bravo+ListPeriode.HALLOWEEN.getPeName()+ChatColor.GREEN+" !");
				}
				else if(ListPeriode.HALLOWEEN.getPeriode() != ListPeriode.HALLOWEEN){
					p.sendMessage(notPe+ListPeriode.HALLOWEEN.getPeName()+ChatColor.RED+" !");
					e.setCancelled(true);
				}
			}
			//Noel
			if(res.getItemMeta().getDisplayName().equals(ListPeriode.NOEL_1.getPeName()) || res.getItemMeta().getDisplayName().equals(ListPeriode.NOEL_2.getPeName())){
				if(ListPeriode.isPeriode(ListPeriode.NOEL)){
					p.sendMessage(bravo+ListPeriode.NOEL.getPeName()+"!");
				}
				else if(ListPeriode.NOEL.getPeriode() != ListPeriode.NOEL){
					p.sendMessage(notPe+ListPeriode.NOEL.getPeName()+"!");
					e.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		if(DeTout.getInstance().annonce){
			e.getPlayer().sendMessage(ChatColor.GOLD+"Hey ! "+ChatColor.GREEN+"Il y a des nouveautés Noël tel que le craft Noël §d§l§oinédit§r§a et le masque et les jobs ! Profites-en !");
		}
	}
}