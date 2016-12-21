package be.vilevar.DeTout;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import be.vilevar.DeTout.CraftEtAutres.Crafts;
import be.vilevar.DeTout.CraftEtAutres.DeathDrop;
import be.vilevar.DeTout.CraftEtAutres.Masque;
import be.vilevar.DeTout.Events.EventsManager;
import be.vilevar.DeTout.Fetes.Fetes;
import be.vilevar.DeTout.Fetes.ListPeriode;
import be.vilevar.DeTout.Fetes.Noel;
import be.vilevar.DeTout.Staff.FreezeOptions;

public class DeTout extends JavaPlugin implements Listener{
	
	public static DeTout instance;
	
	public static DeTout getInstance(){
		return instance;
	}
	
	public boolean closed = false;
	
	public boolean annonce = true;
	
	public String fete = "temps-normaux";
	public ListPeriode actuPeriode = ListPeriode.NORMAL;
	
	public boolean fetes = true;
	public boolean rename = true;
	public boolean deathdrop = true;
	public boolean crafts = true;
	public boolean masque = true;
	public boolean search = true;
	public boolean freeze = true;
	
	@Override
	public void onEnable(){
		super.onEnable();
		instance = this;
		
      /** For Configuration */
		getConfig().options().copyDefaults(true);
		saveConfig();
		
		if(getConfig().isSet("fetes")){
			fetes = getConfig().getBoolean("fetes");
		}
		if(getConfig().isSet("rename")){
			rename = getConfig().getBoolean("rename");
		}
		if(getConfig().isSet("deathdrop")){
			deathdrop = getConfig().getBoolean("deathdrop"); 
		}
		if(getConfig().isSet("crafts")){
			crafts = getConfig().getBoolean("crafts");
		}
		if(getConfig().isSet("masque")){
			masque = getConfig().getBoolean("masque");
		}
		if(getConfig().isSet("search")){
			search = getConfig().getBoolean("search");
		}
		if(getConfig().isSet("freeze")){
			freeze = getConfig().getBoolean("freeze");
		}
		if(getConfig().isSet("annonce")){
			annonce = getConfig().getBoolean("annonce");
		}
		if(getConfig().isSet("fete")){
			fete = getConfig().getString("fete");
		}
		
	  /** For Fete System */
		ListPeriode.setPeriode(ListPeriode.StringToPeriode(fete));
		
	  /** For Class */
		getServer().getPluginManager().registerEvents(this, this);
		new EventsManager(this).registerEvents();
		
		//Package Fetes
		if(fetes){
			new Fetes();
			new Noel();
		}
		
		//Package CraftEtAutre
		if(deathdrop){
			new DeathDrop();
		}
		if(crafts){
			new Crafts();
		}
		if(masque){	
			new Masque();
		}
		
		//Package Staff
		if(freeze){
			new FreezeOptions();
			closed = false;
		}
	}	
	
	@Override
	public void onDisable(){
		super.onDisable();
		
		closed = true;
	}
}