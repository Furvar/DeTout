package be.vilevar.DeTout.Events;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import be.vilevar.DeTout.DeTout;
import be.vilevar.DeTout.CraftEtAutres.DeathDrop;
import be.vilevar.DeTout.CraftEtAutres.Masque;
import be.vilevar.DeTout.Fetes.Fetes;
import be.vilevar.DeTout.Fetes.FetesCmd;
import be.vilevar.DeTout.Fetes.Noel;
import be.vilevar.DeTout.Staff.Anonyme;
import be.vilevar.DeTout.Staff.Freeze;
import be.vilevar.DeTout.Staff.FreezeOptions;
import be.vilevar.DeTout.Staff.Search;
import be.vilevar.DeTout.UtilsCmd.Rename;

public class EventsManager {

	private boolean fetes = DeTout.getInstance().fetes;
	private boolean rename = DeTout.getInstance().rename;
	private boolean deathdrop = DeTout.getInstance().deathdrop;
	private boolean masque = DeTout.getInstance().masque;
	private boolean search = DeTout.getInstance().search;
	private boolean freeze = DeTout.getInstance().freeze;
	
	public DeTout pl;
	
	public EventsManager(DeTout deTout) {
		this.pl = deTout;
	}

	public void registerEvents() {
		
		PluginManager pm = Bukkit.getPluginManager();
		
		//Package Fetes
		if(fetes){
			pm.registerEvents(new Fetes(), pl);
			pl.getCommand("fetes").setExecutor(new FetesCmd());
			pm.registerEvents(new Noel(), pl);
		}
			
		//Package UtilsCmd
		if(rename){
			pl.getCommand("rename").setExecutor(new Rename());
		}
		
		//Package CraftEtAutre
		if(deathdrop){
			pm.registerEvents(new DeathDrop(), pl);
		}
		if(masque){
			pm.registerEvents(new Masque(), pl);
			pl.getCommand("anonyme").setExecutor(new Anonyme());
		}
		
		//Package Staff
		if(search){
			pl.getCommand("search").setExecutor(new Search());
		}
		if(freeze){	
			pl.getCommand("freeze").setExecutor(new Freeze());
			pm.registerEvents(new FreezeOptions(), pl);
		}
	}
}
