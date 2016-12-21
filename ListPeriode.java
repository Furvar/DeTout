package be.vilevar.DeTout.Fetes;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import be.vilevar.DeTout.DeTout;

public enum ListPeriode {
	
	NORMAL(ChatColor.WHITE+"Temps normaux", new ItemStack(Material.AIR), "temps-normaux"),
	HALLOWEEN(ChatColor.GOLD+"Event Halloween", new ItemStack(Material.PUMPKIN), "hallowwen"),
	NOEL_1(ChatColor.RED+"Cadeau de No‘l du Lutin Rouge", new ItemStack(Material.CHEST), "noel"),
	NOEL_2(ChatColor.GREEN+"Cadeau de No‘l du Lutin Vert", new ItemStack(Material.CHEST), "noel"),
	NOEL(ChatColor.RED+"¤oEvent No‘l", new ItemStack(Material.SNOW_BALL), "noel"),
	PAQUE(ChatColor.AQUA+"Event P‰que", new ItemStack(Material.EGG), "paque");

	private String nom;
	private ItemStack icon;
	private String id;
	
	ListPeriode(String name, ItemStack incon, String id){
		nom = name;
		icon = incon;
		this.id = id;
	}
	
	/** For Fetes */
	public boolean CanJoin(){
		return false;
	}
	
	public static void setPeriode(ListPeriode periode){
		if(periode==null)return;
		DeTout.getInstance().actuPeriode = periode;
		DeTout.getInstance().getConfig().set("fete", periode.getId());
		DeTout.getInstance().saveConfig();
	}
	
	public static boolean isPeriode(ListPeriode periode){
		if(periode==null)return false;
		if(DeTout.getInstance().actuPeriode.equals(periode))return true;
		else return false;
	}
	
	public ListPeriode getPeriode(){
		return DeTout.getInstance().actuPeriode;
	}
	
	/** For Save System And Current System */
	public String getId(){
		return id;
	}
	
	public static ListPeriode StringToPeriode(String msg){
		if(msg==null)return null;
		if(msg.equals("temps-normaux"))return NORMAL;
		else if(msg.equals("halloween"))return HALLOWEEN;
		else if(msg.equals("noel"))return NOEL;
		else if(msg.equals("paque"))return PAQUE;
		else return null;
	}
	
	/** For Items */
	public String getPeName(){
		return nom;
	}
	
	public ItemStack getIcon(ListPeriode periode){
		return icon;
	}
	
	public ItemStack getCraft(){
		ItemStack i = icon;
		ItemMeta iM = i.getItemMeta();
		iM.setDisplayName(nom);
		ArrayList<String>desc = new ArrayList<>();
		desc.add(nom);
		iM.setLore(desc);
		i.setItemMeta(iM);
		return i;
	}
}
