package cz.ProjectWhitehole.Blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cz.ProjectWhitehole.mod.ModBlocks;
import cz.ProjectWhitehole.mod.ProjectWhiteholeMod;
import cz.ProjectWhitehole.tileentity.TileEntityIndustrialRefiner;

public final class IndustrialRefiner extends BlockContainer{
	
	public String name = "IndustrialRefiner";
	private final boolean isActive;
	
	@SideOnly(Side.CLIENT)
	private IIcon iconTop;
	
	@SideOnly(Side.CLIENT)
	private IIcon iconFront;
	
	private static boolean keepInventory;
	
	public IndustrialRefiner(boolean isActive)
	{
		super(Material.iron);
		this.setBlockName(ProjectWhiteholeMod.MODID + "_" + name);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setStepSound(soundTypeMetal);
		this.setHardness(3.0f);
		this.setHarvestLevel("pickaxe", 2);
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
		
		public Item getItemDropped(World world, int x, int y, int z) {
			
			return Item.getItemFromBlock(ModBlocks.IndustrialRefinerIdle);
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
		
		public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ){
			
			if(!world.isRemote){
				FMLNetworkHandler.openGui(player, ProjectWhiteholeMod.instance, ModBlocks.guiIDIndustrialRefiner, world, x, y, z);
			}
			return true;
		}

		public String returnName(){
			return name;
		}

		@Override
		public TileEntity createNewTileEntity(World world, int i ) {
			return new TileEntityIndustrialRefiner();
		}
		
	    @SideOnly(Side.CLIENT)
	    public void randomDisplayTick(World world, int x, int y, int z, Random generator)
	    {
	        if (this.isActive)
	        {
	            int direction = world.getBlockMetadata(x, y, z);
	            float x1 = (float)x + 0.5F;
	            float x2 = (float)y + 0.3F + generator.nextFloat() * 6.0F / 16.0F;
	            float x3 = (float)z + 0.5F;
	            
	            float f1 = 0.52F;
	            float f2 = generator.nextFloat() * 0.6F - 0.3F;

	            if (direction == 4)
	            {
	                world.spawnParticle("smoke", (double)(x1 - f1), (double)x2, (double)(x3 + f2), 0.0D, 0.0D, 0.0D);
	                world.spawnParticle("flame", (double)(x1 - f1), (double)x2, (double)(x3 + f2), 0.0D, 0.0D, 0.0D);
	            }
	            else if (direction == 5)
	            {
	                world.spawnParticle("smoke", (double)(x1 + f1), (double)x2, (double)(x3 + f2), 0.0D, 0.0D, 0.0D);
	                world.spawnParticle("flame", (double)(x1 + f1), (double)x2, (double)(x3 + f2), 0.0D, 0.0D, 0.0D);
	            }
	            else if (direction == 2)
	            {
	                world.spawnParticle("smoke", (double)(x1 + f2), (double)x2, (double)(x3 - f1), 0.0D, 0.0D, 0.0D);
	                world.spawnParticle("flame", (double)(x1 + f2), (double)x2, (double)(x3 - f1), 0.0D, 0.0D, 0.0D);
	            }
	            else if (direction == 3)
	            {
	                world.spawnParticle("smoke", (double)(x1 + f2), (double)x2, (double)(x3 + f1), 0.0D, 0.0D, 0.0D);
	                world.spawnParticle("flame", (double)(x1 + f2), (double)x2, (double)(x3 + f1), 0.0D, 0.0D, 0.0D);
	            }
	        }
	    }
		
		public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityPlayer, ItemStack itemstack) {
			
			int l = MathHelper.floor_double((double)(entityPlayer.rotationYaw*4.0F / 360.F)+0.5D) & 3;
			
			if(l==0) {
				world.setBlockMetadataWithNotify(x, y, z, 2, 2);
				
			}
			
			if(l==1) {
				world.setBlockMetadataWithNotify(x, y, z, 5, 2);
				
			}
			
			if(l==2) {
				world.setBlockMetadataWithNotify(x, y, z, 3, 2);
				
			}
			
			if(l==3) {
				world.setBlockMetadataWithNotify(x, y, z, 4, 2);
				
			}
			
			if(itemstack.hasDisplayName()) {
				((TileEntityIndustrialRefiner)world.getTileEntity(x,y,z)).setGuiDisplayName(itemstack.getDisplayName());
			}
		}

		public static void updateIndustrialRefinerBlockState(boolean active, World worldObj, int xCoord, int yCoord, int zCoord) {
			
			int i = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
					
			TileEntity tileentity = worldObj.getTileEntity(xCoord, yCoord, zCoord);
			keepInventory = true;
			
			if(active){
				worldObj.setBlock(xCoord,yCoord,zCoord,ModBlocks.IndustrialRefinerActive);
			}
			else{
				worldObj.setBlock(xCoord,yCoord,zCoord,ModBlocks.IndustrialRefinerIdle);
			}
			
			keepInventory = false;
			
			worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, i, 2);
			
	        if (tileentity != null)
	        {
	            tileentity.validate();
	            worldObj.setTileEntity(xCoord, yCoord, zCoord, tileentity);
	        }
		}
}
