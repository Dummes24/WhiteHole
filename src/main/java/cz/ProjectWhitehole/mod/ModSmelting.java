package cz.ProjectWhitehole.mod;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModSmelting {
	
	public static void init(){
		GameRegistry.addSmelting(new ItemStack(ModBlocks.copperOre), new ItemStack(ModItems.copperIngot,1,1), 0.1F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.eccdurusiumOre), new ItemStack(ModItems.eccdurusiumIngot), 1.0F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.germaniumOre), new ItemStack(ModItems.germaniumIngot), 0.8F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.platinumOre), new ItemStack(ModItems.platinumIngot), 0.9F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.tinOre), new ItemStack(ModItems.tinIngot), 0.5F);
		GameRegistry.addSmelting(new ItemStack(ModBlocks.uraniumOre), new ItemStack(ModItems.uraniumIngot), 0.9F);
	}
}
