package cz.ProjectWhitehole.Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.common.registry.GameRegistry;
import cz.ProjectWhitehole.mod.ProjectWhiteholeMod;

public final class GermaniumOre extends Block{

	private String name = "germaniumOre";
	
	public GermaniumOre()
	{
		super(Material.rock);
		this.setBlockName(ProjectWhiteholeMod.MODID + "_" + name);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setStepSound(soundTypeStone);
		this.setHardness(3.0f);
		this.setHarvestLevel("pickaxe", 2);
		setBlockTextureName(ProjectWhiteholeMod.MODID + ":" + name);
		GameRegistry.registerBlock(this, name);
	}
}
