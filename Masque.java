package be.vilevar.DeTout.CraftEtAutres;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import be.vilevar.DeTout.DeTout;

public class Masque implements Listener{
	
	private ArrayList<Player>anonymous = new ArrayList<>();
	private HashMap<Player, Player>tueur = new HashMap<>();
	public HashMap<Player, String>anonyme = new HashMap<>();
	
	public Masque(){
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(DeTout.getPlugin(DeTout.class), new Runnable() {
        	
            @Override
            public void run() {
            	/**
            	 * Alle Players Connecteds
            	 */
    			for(Player p : Bukkit.getServer().getOnlinePlayers()) {
    				/*
    				 * That's Players Must Anonymous
    				 */
    				if(p.getInventory()!=null && p.getInventory().getHelmet()!=null && p.getInventory().getHelmet().getType()!=null && p.getInventory().getHelmet().equals(Crafts.hood)){
    					/** Not Anonymous Player */
    					if(!anonymous.contains(p) && !anonyme.containsKey(p) && !isAnonyme(p)){
      						//Random Anonymous
    						String anon = "anonymous";
	    					Random rand = new Random();
	    					int alea = rand.nextInt(999);
	    					String FTueur = anon+((alea)+1);
	    					
	    					//Set Anonymous
	    					if(!anonymous.contains(p)){
	    						anonymous.add(p);
	    						anonyme.put(p, FTueur);
	    						p.sendMessage(ChatColor.AQUA+"Vous êtes désormais anonyme sous le nom de "+anonyme.get(p)+" (debug : "+FTueur+").");
	    					}	
	    				}
    				}
    				/*
    				 * That's Players Must Not Anonymous
    				 */
    				/** That Player Is Not Anonymous */
    				else if(isAnonyme(p)){
    					if(anonymous.contains(p)){
    						p.sendMessage(ChatColor.AQUA+"Vous n'êtes plus anonyme.");
    						anonymous.remove(p);
    						anonyme.remove(p);
    					}
    				}
    			}
    			/**
    			 * Alle Players Connecteds
    			 */
    			for(Player p : Bukkit.getOnlinePlayers()){
    				/** Anonymous Players Connecteds */
    				if(anonymous.contains(p) && anonyme.containsKey(p) && isAnonyme(p)){
    					/* Remove Invisibility And Glowing For Set 5 Seconds Invisibility And Glowing */
    					if(p.hasPotionEffect(PotionEffectType.INVISIBILITY))p.removePotionEffect(PotionEffectType.INVISIBILITY);
    					if(p.hasPotionEffect(PotionEffectType.GLOWING))p.removePotionEffect(PotionEffectType.GLOWING);
    					p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 5*20, 4));
    					p.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 5*20, 4));
    				}
    			}
            }
        }, 20, 20);
	}
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e){
		/**
		 * De Entity's Zijn Players
		 */
		if(e.getEntity() instanceof Player && e.getDamager() instanceof Player){
			Player cible = (Player)e.getEntity();
			Player p = (Player)e.getDamager();
			/** This Damager Is Anonymous */
			if(isAnonyme(p)){
				//Put Couple Cible-Anonymous
				tueur.put(cible, p);
				return;
			}else if(tueur.containsKey(cible)){
				tueur.remove(cible);
				return;
			}
		}
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e){
		Player mort = e.getEntity();
		/** 
		 * That Player Is Victime For One Anonyme
		 */
		if(tueur.containsKey(mort) && tueur.get(mort)!=null){
			Player anon = tueur.get(mort);
			/** Control Or Anonyme Is Anonyme */
			if(isAnonyme(anon) && anonymous.contains(anon) && anonyme.containsKey(anon) && anonyme.get(anon)!=null){
				/*
				 * Control Victime Is Anonyme
				 */
				if(isAnonyme(mort) && anonymous.contains(mort) && anonyme.containsKey(anon) && anonyme.get(mort)!=null){
					// Set Death Message Anonyme Victime
					e.setDeathMessage(getAnonyme(mort)+" a été tué par "+getAnonyme(anon)+".");
				}else{
					// Set Death Basic Message Anonyme
					e.setDeathMessage(mort.getName()+" a été tué par "+getAnonyme(anon)+".");
				}
			}
		}
		/**
		 * That Player Is Anonyme
		 */
		if(isAnonyme(mort)){
			e.setDeathMessage(getAnonyme(mort)+" est mort par des raisons inconnues.");
		}
	}
	
	public boolean isAnonyme(Player p){
		if(p == null || !anonyme.containsKey(p))return false;
		if((p.getInventory()!=null && p.getInventory().getHelmet()!=null && p.getInventory().getHelmet().getType()!=null && p.getInventory().getHelmet().equals(Crafts.hood)) || (anonyme.containsKey(p) && anonyme.get(p) != null && anonymous.contains(p))){
			return true;
		}else return false;
	}
	
	public String getAnonyme(Player p){
		if(p==null)return null;
		if(isAnonyme(p))return anonyme.get(p);
		else return null;
	}
}
