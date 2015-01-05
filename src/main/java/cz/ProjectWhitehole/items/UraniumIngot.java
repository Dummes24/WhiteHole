package cz.ProjectWhitehole.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;
import cz.ProjectWhitehole.mod.ProjectWhiteholeMod;

public class UraniumIngot extends Item {
	private String name = "uraniumIngot";
  	public UraniumIngot(){
  		
  		setUnlocalizedName(ProjectWhiteholeMod.MODID +"_" + name);
  		setCreativeTab(CreativeTabs.tabMaterials);
  		GameRegistry.registerItem(this, name);
  		setTextureName(ProjectWhiteholeMod.MODID +":"+name);
  	}
}
