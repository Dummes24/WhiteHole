package cz.ProjectWhitehole.items;

import com.sun.org.apache.xml.internal.security.encryption.Reference;

import cpw.mods.fml.common.registry.GameRegistry;
import cz.ProjectWhitehole.mod.ProjectWhiteholeMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public final class CopperIngot extends Item{
	private String name = "copperIngot";
  	public CopperIngot(){
  		
  		setUnlocalizedName(ProjectWhiteholeMod.MODID +"_" + name);
  		setCreativeTab(CreativeTabs.tabMisc);
  		GameRegistry.registerItem(this, name);
  	}
}
