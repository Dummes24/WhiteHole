package cz.ProjectWhitehole.Blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import cz.ProjectWhitehole.mod.ProjectWhiteholeMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class EccdurusiumStarGateBlock extends Block{

	String name = "eccdurusiumStarGateBlock";
	
	public EccdurusiumStarGateBlock() {
		super(Material.iron);
		this.setBlockName(ProjectWhiteholeMod.MODID + "_" + name);
		this.setStepSound(soundTypeMetal);
		this.setHardness(9.0f);
		this.setHarvestLevel("pickaxe", 5);
		this.setBlockTextureName(ProjectWhiteholeMod.MODID + ":" + name);
		GameRegistry.registerBlock(this, name);
	}
	
}
