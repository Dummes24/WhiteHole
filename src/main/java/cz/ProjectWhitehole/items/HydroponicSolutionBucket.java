package cz.ProjectWhitehole.items;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;
import cpw.mods.fml.common.registry.GameRegistry;
import cz.ProjectWhitehole.mod.ProjectWhiteholeMod;

public class HydroponicSolutionBucket extends ItemBucket {
	
	private String name = "HydroponicSolutionBucket";
	
	public HydroponicSolutionBucket(Block p_i45331_1_) {
		super(p_i45331_1_);
  		setUnlocalizedName(ProjectWhiteholeMod.MODID +"_"+ name);
  		setCreativeTab(ProjectWhiteholeMod.tabWhiteHoleMaterials);
  		setTextureName(ProjectWhiteholeMod.MODID +":"+name);
  		setContainerItem(Items.bucket);
		GameRegistry.registerItem(this, name);
		
	}


}
