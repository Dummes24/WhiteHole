package cz.ProjectWhitehole.Blocks;

import com.sun.org.apache.bcel.internal.Constants;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cz.ProjectWhitehole.mod.ProjectWhiteholeMod;
import scala.reflect.internal.Trees.Super;
import scala.reflect.internal.Trees.This;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;


public class EccdurusiumOre extends Block{
	
	private String name = "Eccdurusium";
	
	public EccdurusiumOre()
	{
		super(Material.rock);
		this.setBlockName(ProjectWhiteholeMod.MODID + "_" + name);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setStepSound(soundTypeStone);
		this.setHardness(3.0f);
		this.setHarvestLevel("pickaxe", 2);
		this.setBlockTextureName(ProjectWhiteholeMod.MODID+"_"+ this.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(this, name);
		
	}
}
