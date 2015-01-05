package cz.ProjectWhitehole.Blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cz.ProjectWhitehole.mod.ProjectWhiteholeMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class Generator extends BlockContainer{

	@SideOnly(Side.CLIENT)
	protected IIcon iconFront;
	
	@SideOnly(Side.CLIENT)
	protected IIcon iconTop;
	
	protected boolean isActive;
	public String name = "Generator";
	
	public Generator(boolean isActive) {
		super(Material.iron);
		this.isActive = isActive;
	}
	
	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		this.blockIcon = iconRegister.registerIcon(ProjectWhiteholeMod.MODID + ":"+ name + "_Side");
		this.iconFront = iconRegister.registerIcon(ProjectWhiteholeMod.MODID + ":"+ name + (this.isActive ? "_FrontOn":"_FrontOff"));
		this.iconTop = iconRegister.registerIcon(ProjectWhiteholeMod.MODID + ":"+ name + "_Top");
	}

	@Override
	public IIcon getIcon(int side, int meta) {			
		//return side == 1 ? this.iconTop : (side == 0 ? this.iconTop : (side != meta ? this.blockIcon : this.iconFront));
		
		if (meta == 0 && side == 3)
	   	{
			return this.iconFront;
	   	}
		else
		{
				return side == 1 ? this.iconTop : (side == 0 ? this.iconTop : (side != meta ? this.blockIcon : this.iconFront));
		}
		
	}

	@Override
	public TileEntity createNewTileEntity(World world, int i) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
		this.setDefaultDirection(world, x, y, z);
	}
	
	private void setDefaultDirection(World world, int x, int y, int z) {
		if(!world.isRemote) {
			Block b1 = world.getBlock(x,y,z-1);
			Block b2 = world.getBlock(x,y,z+1);
			Block b3 = world.getBlock(x+1,y,z);
			Block b4 = world.getBlock(x-1,y,z);
			
			byte b0 = 3;
			
			if(b1.func_149730_j() && !b2.func_149730_j()) {
				b0 = 3;
			}
			
			if(b2.func_149730_j() && !b1.func_149730_j()) {
				b0 = 2;
			}
			
			if(b3.func_149730_j() && !b4.func_149730_j()) {
				b0 = 5;
			}
			
			if(b4.func_149730_j() && !b3.func_149730_j()) {
				b0 = 4;
			}
			
			world.setBlockMetadataWithNotify(x, y, z, b0, 2);
		}
	}

}
