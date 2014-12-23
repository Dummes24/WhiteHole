package cz.ProjectWhitehole.Blocks;

import com.sun.org.apache.bcel.internal.Constants;

import cpw.mods.fml.common.registry.GameRegistry;
import cz.ProjectWhitehole.mod.ProjectWhiteholeMod;
import scala.reflect.internal.Trees.Super;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;


public class Eccdurusium extends Block{
	
	private String name = "eccdurusium";
	
	public Eccdurusium()
	{
		super(Material.rock);
		this.setBlockName(ProjectWhiteholeMod.MODID + "_" + name);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setStepSound(soundTypeStone);
		this.setHardness(3.0f);
		this.setHarvestLevel("pickaxe", 2);
		GameRegistry.registerBlock(this, name);
		
	}
}
