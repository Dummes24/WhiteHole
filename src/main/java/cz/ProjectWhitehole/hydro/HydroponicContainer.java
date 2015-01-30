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

public class HydroponicContainer extends Block {

	String name = "HydroponicContainer";
	
	@SideOnly(Side.CLIENT)
	private IIcon iconTop;
	
	public HydroponicContainer() {
		super(Material.iron);
		this.setCreativeTab(ProjectWhiteholeMod.tabWhiteHole);
		this.setStepSound(soundTypeMetal);
		this.setBlockName(ProjectWhiteholeMod.MODID + "_" + name);
		this.setTickRandomly(true);
		GameRegistry.registerBlock(this, name);	
		
	}
	
	@Override
	public void registerBlockIcons(IIconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon(ProjectWhiteholeMod.MODID + ":"+ name + "_Side");
		this.iconTop = iconRegister.registerIcon(ProjectWhiteholeMod.MODID + ":"+ name + "_Top");	
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand){
		
		Block cropBlock = world.getBlock(x, y+1, z);
		
        if (cropBlock instanceof IPlantable)
        {
            for (int i = 0; i < 30; i++) {
				cropBlock.updateTick(world, x, y + 1, z, rand);
			}
        }
        world.setBlock(x+1, y, z, Block.getBlockById(9));
        
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
	

}
