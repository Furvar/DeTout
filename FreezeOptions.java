package be.vilevar.DeTout.Staff;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import be.vilevar.DeTout.DeTout;

public class FreezeOptions implements Listener{

	private boolean ServerClosed = DeTout.getInstance().closed;
	private boolean PlayerDisconnect = false;
	
	private static HashMap<Player, Location>freeze = new HashMap<>();
	private static HashMap<Player, Integer>freezeC = new HashMap<>();
	
	public FreezeOptions(){
		
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(DeTout.getPlugin(DeTout.class), new Runnable() {
			
			@Override
			public void run() {
				for(Player player : Bukkit.getOnlinePlayers()){
					if(FreezeOptions.isFreeze(player)){
						if(!FreezeOptions.PlayerIsLocFreeze(player)){
							FreezeOptions.freezePunition(player, false, false);
						}
					}
				}
			}
			
		}, 20, 20);
	}
	
	
	public static boolean isFreeze(Player player){
		if(player == null)return false;
		if(freeze.containsKey(player)){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean PlayerIsLocFreeze(Player player){
		if(player == null)return false;
		if(FreezeOptions.isFreeze(player)){
			if(freeze.get(player) != null){
				if(player.getLocation().equals(freeze.get(player))){
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void freezePunition(Player player, boolean ServerClosed, boolean PlayerDisconnect){
		if(!FreezeOptions.isFreeze(player))return;
		//deco
		if(PlayerDisconnect){
			if(!ServerClosed){
				player.setBanned(true);
				freeze.remove(player);
				freezeC.remove(player);
				return;
			}else{
				System.out.println(player.getName()+" était freeze lors de la fermeture, il ne le sera plus lors de la réouverture.");
				return;
			}
		}
		if(player == null)return;
		//no freeze loc
		if(!FreezeOptions.PlayerIsLocFreeze(player)){
			player.teleport(freeze.get(player));
		}
		//luck
		Integer c = 1;
		if(freezeC.containsKey(player) && freezeC.get(player) != null){
			//ban : luck = 5
			if(freezeC.get(player) >= 5){
				player.kickPlayer("Vous avez épuisé vos chances.");
				player.setBanned(true);
				freeze.remove(player);
				freezeC.remove(player);
				return;
			//add luck : luck < 5
			}else{
				c = freezeC.get(player)+1;
				freezeC.remove(player);
			}
		}
		freezeC.put(player, c);
		player.sendMessage(ChatColor.RED+"Vous avez utilisé "+c+" chance sur 5.");
	}
	
	public static void freezeAction(Player player, boolean msg){
		if(player == null)return;
		if(freeze.containsKey(player)){
			freeze.remove(player);
			if(freezeC.containsKey(player)){
				freezeC.remove(player);
			}
			if(msg) player.sendMessage(ChatColor.GREEN+"Vous êtes décongelé !");
		}else{
			freeze.put(player, player.getLocation());
			player.sendMessage(ChatColor.RED+"Vous êtes freeze. "+ChatColor.GREEN+" Il vous est interdit de: vous déplacer; frapper; casser; poser; vous deconnecter.");
			player.sendMessage(ChatColor.GOLD+"Si vous vous enfreindriez une de ces règles (sauf voir déconnection), vous perdez un de vos 5 chance.");
			player.sendMessage(ChatColor.GOLD+"Si vous vous déplacez, vous êtes téléporté là où vous étiez.");
			player.sendMessage(ChatColor.GOLD+"Si vous tappez une entité, les dégats seront annulés.");
			player.sendMessage(ChatColor.GOLD+"Si vous cassez un bloc, ce sera annulé.");
			player.sendMessage(ChatColor.GOLD+"Si vous poser un bloc, ce sera aussi annulé.");
			player.sendMessage(ChatColor.LIGHT_PURPLE+"Si vous vous déconnectez, vous serez directement banni. Si c'est dû à un crash, veuillez poster une plainte sur le forum, §4§oavec un screenshot contenant §r§c§o§ll'heure et la date§r"+ChatColor.LIGHT_PURPLE+" du crash.");
		}
	}
	
	
	@EventHandler
	public void onDamageByEnt(EntityDamageByEntityEvent e){
		if(e.getDamager() instanceof Player){
			Player player = (Player)e.getDamager();
			if(FreezeOptions.isFreeze(player)){
				e.setCancelled(true);
				FreezeOptions.freezePunition(player, ServerClosed, PlayerDisconnect);
			}
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e){
		if(e.getEntity() instanceof Player && FreezeOptions.isFreeze((Player)e.getEntity())){
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e){
		if(FreezeOptions.isFreeze(e.getPlayer())){
			FreezeOptions.freezePunition(e.getPlayer(), ServerClosed, PlayerDisconnect);
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e){
		if(FreezeOptions.isFreeze(e.getPlayer())){
			FreezeOptions.freezePunition(e.getPlayer(), ServerClosed, PlayerDisconnect);
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onDisconnect(PlayerQuitEvent e){
		if(FreezeOptions.isFreeze(e.getPlayer())){
			PlayerDisconnect = true;
			FreezeOptions.freezePunition(e.getPlayer(), ServerClosed, PlayerDisconnect);
			PlayerDisconnect = false;
		}
	}
}










