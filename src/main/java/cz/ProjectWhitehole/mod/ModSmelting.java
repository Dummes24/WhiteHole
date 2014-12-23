package cz.ProjectWhitehole.mod;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModSmelting {
	
	public static void init(){
		GameRegistry.addSmelting(new ItemStack(ModBlocks.copperOre), new ItemStack(ModItems.copperIngot,1,1), 0.1F);
	}
}
