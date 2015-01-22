package cz.ProjectWhitehole.Blocks;

import java.util.Random;

import cz.ProjectWhitehole.mod.ModBlocks;
import cz.ProjectWhitehole.mod.ProjectWhiteholeMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public class ChevronBlockStarGate extends Block{
	 //TODO Need to setup sides to render activated textures, probably from TileEntityControlBlock
	//TODO variable if whole stargate is assigned to any controlBlock
	String name = "chevronBlockStarGate";	
	boolean isActive;	
	
	protected ChevronBlockStarGate(boolean isActive) {
		super(Material.iron);
		
		this.isActive = isActive;
		
						
		this.setBlockName(ProjectWhiteholeMod.MODID + "_" + name);		
		this.setStepSound(soundTypeMetal);
		this.setHardness(3.0f);
		this.setHarvestLevel("pickaxe", 2);			
	}
	
	@Override
	public void registerBlockIcons(IIconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon(ProjectWhiteholeMod.MODID + ":"+ name);
			
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {				
		return this.blockIcon;	
	}
	
	@Override
	public Item getItemDropped(int i, Random random, int j) {		
		return Item.getItemFromBlock(ModBlocks.medicalBlockIdle);
	}

}
