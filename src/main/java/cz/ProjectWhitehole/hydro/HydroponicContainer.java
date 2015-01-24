package cz.ProjectWhitehole.hydro;

import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cz.ProjectWhitehole.mod.ModBlocks;
import cz.ProjectWhitehole.mod.ProjectWhiteholeMod;
import cz.ProjectWhitehole.tileentity.TileEntityMedicalBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class HydroponicContainer extends BlockContainer {

	String name = "HydroponicContainer";
	
	@SideOnly(Side.CLIENT)
	private IIcon iconTop;
	
	public HydroponicContainer() {
		super(Material.iron);
		this.setCreativeTab(ProjectWhiteholeMod.tabWhiteHole);
		this.setStepSound(soundTypeMetal);
		this.setBlockName(ProjectWhiteholeMod.MODID + "_" + name);
		GameRegistry.registerBlock(this, name);	
		
	}
	
	@Override
	public void registerBlockIcons(IIconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon(ProjectWhiteholeMod.MODID + ":"+ name + "_Side");
		this.iconTop = iconRegister.registerIcon(ProjectWhiteholeMod.MODID + ":"+ name + "_Top");	
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
    public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable)
    {
    	if(plantable instanceof BlockSapling){
    		return false;
    	}
    	return true;
    }
	
    @Override
   public void onBlockAdded(World p_149726_1_, int p_149726_2_,
   		int p_149726_3_, int p_149726_4_) {    	
   	super.onBlockAdded(p_149726_1_, p_149726_2_, p_149726_3_, p_149726_4_);
   	
   }
    
	@Override
	public Item getItemDropped(int i, Random random, int j) {		
		return Item.getItemFromBlock(this);
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		
		return new HydroponicContainerTile();
	}

}
