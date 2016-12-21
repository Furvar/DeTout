package be.vilevar.DeTout.Fetes;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Noel implements Listener{

	@EventHandler
	public void onCadeau(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && e.getItem() != null && (e.getItem().equals(ListPeriode.NOEL_1.getCraft()) || e.getItem().equals(ListPeriode.NOEL_2.getCraft()))){
			if(ListPeriode.isPeriode(ListPeriode.NOEL)){
				e.setCancelled(true);
				if(p.getInventory().getItemInMainHand().getAmount() > 1){
					p.sendMessage(ChatColor.GOLD+"Un ˆ la fois.");
					return;
				}
				if(p.getInventory().getItemInMainHand().getAmount() == 1){
					Random rand = new Random();
					int cadeau = rand.nextInt(49);
					//3 diamonds (2/50)
					if(cadeau == 0 || cadeau == 1){
						p.getInventory().setItemInMainHand(new ItemStack(Material.DIAMOND));
						p.getInventory().getItemInMainHand().setAmount(3);
						return;
					}
					//1 diamond (3/50)
					if(cadeau == 2 || cadeau == 3 || cadeau == 4){
						p.getInventory().setItemInMainHand(new ItemStack(Material.DIAMOND));
						return;
					}
					//2 iron blocks (5/50)
					if(cadeau == 5 || cadeau == 6 || cadeau == 7 || cadeau == 8 || cadeau == 9){
						p.getInventory().setItemInMainHand(new ItemStack(Material.IRON_BLOCK));
						p.getInventory().getItemInMainHand().setAmount(2);
						return;
					}
					//1 gold block (7/50)
					if(cadeau == 10 || cadeau == 11 || cadeau == 12 || cadeau == 13 || cadeau == 14 || cadeau == 15 || cadeau == 16){
						p.getInventory().setItemInMainHand(new ItemStack(Material.GOLD_BLOCK));
						return;
					}
					//10 grass blocks (10/50)
					if(cadeau == 17 || cadeau == 18 || cadeau == 19 || cadeau == 20 || cadeau == 21 || cadeau == 22 || cadeau == 23 || cadeau == 24 || cadeau == 25 || cadeau == 26){
						p.getInventory().setItemInMainHand(new ItemStack(Material.GRASS));
						p.getInventory().getItemInMainHand().setAmount(10);
						return;
					}
					//5 podzol blocks (5/50)
					if(cadeau == 27 || cadeau == 28 || cadeau == 29 || cadeau == 30 || cadeau == 31){
						p.getInventory().setItemInMainHand(new ItemStack(Material.DIRT, (byte)2));
						p.getInventory().getItemInMainHand().setAmount(5);
						return;
					}
					//1 mycelium block (4/50)
					if(cadeau == 32 || cadeau == 33 || cadeau == 34 || cadeau == 35){
						p.getInventory().setItemInMainHand(new ItemStack(Material.MYCEL));
						return;
					}
					//1 diamond sword sharpness 7 (1/50)
					if(cadeau == 36){
						p.getInventory().setItemInMainHand(createItem(Material.DIAMOND_SWORD, (byte)0, Enchantment.DAMAGE_ALL, 7));
						return;
					}
					//1 bow power 5 (3/50)
					if(cadeau == 37 || cadeau == 38 || cadeau == 39){
						p.getInventory().setItemInMainHand(createItem(Material.BOW, (byte)0, Enchantment.ARROW_DAMAGE, 5));
						return;
					}
					//10 coals (10/50)
					if(cadeau > 39){
						p.getInventory().setItemInMainHand(new ItemStack(Material.COAL));
						p.getInventory().getItemInMainHand().setAmount(10);
						return;
					}
				}
			}else{
				p.sendMessage(ChatColor.RED+"Nous ne sommes pas ˆ no‘l.");
			}
		}
	}
	
	private ItemStack createItem(Material mat, byte data, Enchantment ench, int lvlEnch){
		if(mat == null || ench == null || lvlEnch <= 0)return null;
		ItemStack is = new ItemStack(mat, 1, data);
		ItemMeta im = is.getItemMeta();
		im.addEnchant(ench, lvlEnch, true);
		is.setItemMeta(im);
		return is;
	}
}
