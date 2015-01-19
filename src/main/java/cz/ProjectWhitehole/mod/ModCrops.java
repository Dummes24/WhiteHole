package cz.ProjectWhitehole.mod;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSeeds;

public final class ModCrops {

	//Blocks
	public static Block cropStrawberryPlant;
	
	//Seeds
	public static Item cropStrawberrySeeds;
	
	//Drops
	public static Item cropStrawberry;
	
	public static void init(){
		
		//Blocks
		
		//Seeds
		cropStrawberrySeeds = new ItemSeeds(cropStrawberryPlant, Blocks.farmland).setUnlocalizedName("StrawberrySeeds").setTextureName(ProjectWhiteholeMod.MODID + ":StrawberrySeeds");
		//Drops
		cropStrawberry = new ItemFood(4, 0.5F, false).setUnlocalizedName("Strawberry").setTextureName(ProjectWhiteholeMod.MODID + ":Strawberry");
	}
}
