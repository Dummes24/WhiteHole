package cz.ProjectWhitehole.Blocks;

import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cz.ProjectWhitehole.mod.ModBlocks;
import cz.ProjectWhitehole.mod.ProjectWhiteholeMod;
import cz.ProjectWhitehole.tileentity.TileEntityControlBlock;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ControlBlockStargate extends Block implements ITileEntityProvider{
	//TODO One way texture
	
	String name = "controlBlockStargate";
	

	public ControlBlockStargate() {
		super(Material.iron);		
		this.setBlockName(ProjectWhiteholeMod.MODID + "_" + name);
		this.setStepSound(soundTypeMetal);
		this.setHardness(9.0f);
		this.setHarvestLevel("pickaxe", 5);
		this.setBlockTextureName(ProjectWhiteholeMod.MODID + ":" + name);
		GameRegistry.registerBlock(this, name);
	}
	
	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		this.blockIcon = iconRegister.registerIcon(ProjectWhiteholeMod.MODID + ":"+ name);
		
		
	}
	
	public String returnName(){
		return name;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int i){
		return new TileEntityControlBlock();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ){
		//TODO Implement GUI
		return false;
	}
	
	@Override
	public Item getItemDropped(int i, Random random, int j) {		
		return Item.getItemFromBlock(ModBlocks.controlBlockStargateBlock);
	}
}
