package cz.ProjectWhitehole.items;

import cpw.mods.fml.common.registry.GameRegistry;
import cz.ProjectWhitehole.mod.ProjectWhiteholeMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class EccdurusiumIngot extends Item {

	private String name = "eccdurusiumIngot";
  	public EccdurusiumIngot(){
  		
  		setUnlocalizedName(ProjectWhiteholeMod.MODID +"_" + name);
  		setCreativeTab(ProjectWhiteholeMod.tabWhiteHoleMaterials);
  		GameRegistry.registerItem(this, name);
  		setTextureName(ProjectWhiteholeMod.MODID +":"+name);
  	}
}
