package cz.ProjectWhitehole.Blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import cz.ProjectWhitehole.mod.ProjectWhiteholeMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public final class PlatinumOre extends Block{
	private String name = "platinumOre";
	
	public PlatinumOre()
	{
		super(Material.rock);
		this.setBlockName(ProjectWhiteholeMod.MODID + "_" + name);
		this.setCreativeTab(ProjectWhiteholeMod.tabWhiteHoleMaterials);
		this.setStepSound(soundTypeStone);
		this.setHardness(3.0f);
		this.setHarvestLevel("pickaxe", 2);
		setBlockTextureName(ProjectWhiteholeMod.MODID + ":" + name);
		GameRegistry.registerBlock(this, name);
	}
}
