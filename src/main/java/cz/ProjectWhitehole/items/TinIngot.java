package cz.ProjectWhitehole.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;
import cz.ProjectWhitehole.mod.ProjectWhiteholeMod;

public class TinIngot extends Item {

	private String name = "tinIngot";
  	public TinIngot(){
  		
  		setUnlocalizedName(ProjectWhiteholeMod.MODID +"_" + name);
  		setCreativeTab(CreativeTabs.tabMaterials);
  		GameRegistry.registerItem(this, name);
  		setTextureName(ProjectWhiteholeMod.MODID +":"+name);
  	}
}
