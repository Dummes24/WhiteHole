package cz.ProjectWhitehole.Blocks;

import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cz.ProjectWhitehole.mod.ModBlocks;
import cz.ProjectWhitehole.mod.ProjectWhiteholeMod;
import cz.ProjectWhitehole.tileentity.TileEntityGenerator;
import cz.ProjectWhitehole.tileentity.TileEntityMedicalBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class MedicalBlock extends BlockContainer{	
	
	Boolean isActive;
	String name = "medicalBlock";
	private static Boolean keepInventory;
	
	@SideOnly(Side.CLIENT)
	private IIcon iconTop;
	
	public MedicalBlock(Boolean isActive) {
		super(Material.anvil);
		
		this.isActive = isActive;		
		this.setBlockName(ProjectWhiteholeMod.MODID + "_" + name);		
		this.setStepSound(soundTypeMetal);
		this.setHardness(3.0f);
		this.setHarvestLevel("pickaxe", 2);		
	}    

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		
		return new TileEntityMedicalBlock();
	}
	
	public static void updateMedicalBlockState(boolean isActive, World worldObj, int xCoord, int yCoord, int zCoord) {
		
		int i = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);		
		TileEntity tileentity = worldObj.getTileEntity(xCoord, yCoord, zCoord);
		
		keepInventory = true;		
		if(isActive)
			worldObj.setBlock(xCoord, yCoord, zCoord, ModBlocks.medicalBlockActive);
		else
			worldObj.setBlock(xCoord, yCoord, zCoord, ModBlocks.medicalBlockIdle);		
		keepInventory = false; 
		
		worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, i, 2);
		
        if (tileentity != null)
        {
            tileentity.validate();
            worldObj.setTileEntity(xCoord, yCoord, zCoord, tileentity);
        }
	}
	
	@Override
	public void registerBlockIcons(IIconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon(ProjectWhiteholeMod.MODID + ":"+ name);
		this.iconTop = iconRegister.registerIcon(ProjectWhiteholeMod.MODID + ":"+ name + (this.isActive ? "_TopOn":"_TopOff"));		
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {				
		if (side == 1)
	   	{
			return this.iconTop;
	   	}
		else			
		{
				return this.blockIcon;
		}		
	}
	
	@Override
	public Item getItemDropped(int i, Random random, int j) {		
		return Item.getItemFromBlock(ModBlocks.medicalBlockIdle);
	}
	
	public String returnName(){
		return name;
	}
	
	 @SideOnly(Side.CLIENT)
	    public Item getItem(World world, int x, int y, int z)
	    {
	        return Item.getItemFromBlock(ModBlocks.medicalBlockIdle);
	    }
	 /*Potøeba doøešit náhodnou zmìnu spawnu particle
	 @SideOnly(Side.CLIENT)
	 public void randomDisplayTick(World world, int x, int y, int z, Random generator)
	 {
		 if (this.isActive) {
			 	float x1 = (float)x + 0.3F;
	            float x2 = (float)y + 0.5F; 
	            float x3 = (float)z + 0.3F;
	            
	            float f1 = 0.52F;
	            float f2 = generator.nextFloat() * 0.6F - 0.3F;
	            
	            world.spawnParticle("smoke", (double)(x1 - f2), (double)(x2 + f1) , (double)(x3 + f2), 0.0D, 0.0D, 0.0D);
                world.spawnParticle("flame", (double)(x1 - f2), (double)(x2  + f1), (double)(x3 + f2), 0.0D, 0.0D, 0.0D);
		}
	 }*/
	
}
