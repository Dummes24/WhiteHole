package cz.ProjectWhitehole.mod;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public final class ModCrafting {
	
	public static void init(){
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.bronzeIngot,4), new Object[]{
			ModItems.copperIngot,ModItems.copperIngot,ModItems.copperIngot,ModItems.tinIngot
		});
	}
	
}
