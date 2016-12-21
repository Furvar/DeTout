package be.vilevar.DeTout.CraftEtAutres;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class DeathDrop implements Listener{

	public DeathDrop(){}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e){
		if(e.getEntity() instanceof Player){
			Player p = e.getEntity();
			
			ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (byte)3);
			SkullMeta skullM = (SkullMeta) skull.getItemMeta();
			skullM.setOwner(p.getName());
			skull.setItemMeta(skullM);
			
			Random rand = new Random();
			int alea = rand.nextInt(5);
			ItemStack os = new ItemStack(Material.BONE, alea);
			
			p.getWorld().dropItem(p.getLocation(), os);
			p.getWorld().dropItem(p.getLocation(), skull);
		}
	}
}
