package be.vilevar.DeTout.CraftEtAutres;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import be.vilevar.DeTout.DeTout;

public class Crafts {

	@SuppressWarnings("deprecation")
	private MaterialData md = new MaterialData(Material.SKULL_ITEM, (byte)1);
	public static ItemStack hood = new ItemStack(Item(Material.SKULL_ITEM, (byte)1, 1, true, Enchantment.PROTECTION_ENVIRONMENTAL, 4, true, ChatColor.DARK_BLUE+"Masque", true, ChatColor.BLACK+"Masque"));;
	
	public Crafts(){
		
		//nametag's
		ShapedRecipe nametag1 = new ShapedRecipe(Item(Material.NAME_TAG, (byte)0, 1, false, null, 0, false, "", false, ""));
		nametag1.shape(new String[] {"   ", "FPP", "   "});
		nametag1.setIngredient('F', Material.STRING).setIngredient('P', Material.PAPER);
		DeTout.getInstance().getServer().addRecipe(nametag1);
		
		ShapedRecipe nametag2 = new ShapedRecipe(Item(Material.NAME_TAG, (byte)0, 1, false, null, 0, false, "", false, ""));
		nametag2.shape(new String[] {"FPP", "   ", "   "});
		nametag2.setIngredient('F', Material.STRING).setIngredient('P', Material.PAPER);
		DeTout.getInstance().getServer().addRecipe(nametag2);
		
		ShapedRecipe nametag3 = new ShapedRecipe(Item(Material.NAME_TAG, (byte)0, 1, false, null, 0, false, "", false, ""));
		nametag3.shape(new String[] {"   ", "   ", "FPP"});
		nametag3.setIngredient('F', Material.STRING).setIngredient('P', Material.PAPER);
		DeTout.getInstance().getServer().addRecipe(nametag3);
		
		ShapedRecipe nametag4 = new ShapedRecipe(Item(Material.NAME_TAG, (byte)0, 1, false, null, 0, false, "", false, ""));
		nametag4.shape(new String[] {"F  ", "P  ", "P  "});
		nametag4.setIngredient('F', Material.STRING).setIngredient('P', Material.PAPER);
		DeTout.getInstance().getServer().addRecipe(nametag4);
		
		ShapedRecipe nametag5 = new ShapedRecipe(Item(Material.NAME_TAG, (byte)0, 1, false, null, 0, false, "", false, ""));
		nametag5.shape(new String[] {" F ", " P ", " P "});
		nametag5.setIngredient('F', Material.STRING).setIngredient('P', Material.PAPER);
		DeTout.getInstance().getServer().addRecipe(nametag5);
		
		ShapedRecipe nametag6 = new ShapedRecipe(Item(Material.NAME_TAG, (byte)0, 1, false, null, 0, false, "", false, ""));
		nametag6.shape(new String[] {"  F", "  P", "  P"});
		nametag6.setIngredient('F', Material.STRING).setIngredient('P', Material.PAPER);
		DeTout.getInstance().getServer().addRecipe(nametag6);
		
		//hoods helmets
		ShapedRecipe hood1 = new ShapedRecipe(Item(Material.SKULL_ITEM, (byte)1, 1, true, Enchantment.PROTECTION_ENVIRONMENTAL, 4, true, ChatColor.DARK_BLUE+"Masque", true, ChatColor.BLACK+"Masque"));
		hood1.shape(new String[] {"   ", "FFF", "FTF"});
		hood1.setIngredient('T', md).setIngredient('F', Material.STRING);
		DeTout.getInstance().getServer().addRecipe(hood1);
		
		ShapedRecipe hood2 = new ShapedRecipe(Item(Material.SKULL_ITEM, (byte)1, 1, true, Enchantment.PROTECTION_ENVIRONMENTAL, 4, true, ChatColor.DARK_BLUE+"Masque", true, ChatColor.BLACK+"Masque"));
		hood2.shape(new String[] {"FFF", "FTF", "   "});
		hood2.setIngredient('T', md).setIngredient('F', Material.STRING);
		DeTout.getInstance().getServer().addRecipe(hood2);
	}

	private static ItemStack Item(Material mat, byte data, int nb, boolean misE, Enchantment ench, int lvl, boolean misN, String name, boolean misD, String desc) {
		ItemStack i = new ItemStack(mat, nb, data);
		ItemMeta iM = i.getItemMeta();
		if(misN){
			iM.setDisplayName(name);
		}
		if(misE){
			iM.addEnchant(ench, lvl, misE);
		}
		if(misD){
			ArrayList<String> d = new ArrayList<>();
			d.add(desc);
			iM.setLore(d);
		}
		i.setItemMeta(iM);
		return i;
	}
}
