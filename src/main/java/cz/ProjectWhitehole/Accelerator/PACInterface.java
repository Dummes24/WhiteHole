package cz.ProjectWhitehole.Accelerator;

import java.util.Random;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cz.ProjectWhitehole.mod.ModBlocks;
import cz.ProjectWhitehole.mod.ProjectWhiteholeMod;
import cz.ProjectWhitehole.Accelerator.TilePACInterface;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class PACInterface extends BlockContainer {

	String name = "PACInterface";
	
	@SideOnly(Side.CLIENT)
	private IIcon iconTop;
	
	@SideOnly(Side.CLIENT)
	private IIcon iconFront;
	
	boolean isActive;
	static boolean keepInventory;
	Random rand = new Random();
	
	public PACInterface() {
		super(Material.iron);
		this.setCreativeTab(ProjectWhiteholeMod.tabWhiteHole);
		this.setStepSound(soundTypeMetal);
		this.setBlockName(ProjectWhiteholeMod.MODID + "_" + name);
		this.setTickRandomly(true);
		GameRegistry.registerBlock(this, name);	
		
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
	
	public Item getItemDropped(int i, Random random, int j) {
		
		return Item.getItemFromBlock(this);
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
		// TODO Rename 
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
		return new TilePACInterface();
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
			((TilePACInterface)world.getTileEntity(x,y,z)).setGuiDisplayName(itemstack.getDisplayName());
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
	
	   public void breakBlock(World world, int x, int y, int z, Block block, int oldMeta)
	   {
	        if (!keepInventory)
	        {
	            TilePACInterface tileentity = (TilePACInterface)world.getTileEntity(x, y, z);

	            if (tileentity != null)
	            {
	                for (int i = 0; i < tileentity.getSizeInventory(); ++i)
	                {
	                    ItemStack itemstack = tileentity.getStackInSlot(i);

	                    if (itemstack != null)
	                    {
	                        float f = this.rand.nextFloat() * 0.8F + 0.1F;
	                        float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
	                        float f2 = this.rand.nextFloat() * 0.8F + 0.1F;

	                        while (itemstack.stackSize > 0)
	                        {
	                            int j = this.rand.nextInt(21) + 10;

	                            if (j > itemstack.stackSize)
	                            {
	                                j = itemstack.stackSize;
	                            }

	                            itemstack.stackSize -= j;
	                            EntityItem entityitem = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(itemstack.getItem(), j, itemstack.getItemDamage()));

	                            if (itemstack.hasTagCompound())
	                            {
	                                entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
	                            }

	                            float f3 = 0.05F;
	                            entityitem.motionX = (double)((float)this.rand.nextGaussian() * f3);
	                            entityitem.motionY = (double)((float)this.rand.nextGaussian() * f3 + 0.2F);
	                            entityitem.motionZ = (double)((float)this.rand.nextGaussian() * f3);
	                            world.spawnEntityInWorld(entityitem);
	                        }
	                    }
	                }

	                world.func_147453_f(x, y, z, block);
	            }
	        }

	        super.breakBlock(world, x, y, z, block, oldMeta);
	    }
	   
	    @SideOnly(Side.CLIENT)
	    public Item getItem(World world, int x, int y, int z)
	    {
	        return Item.getItemFromBlock(this);
	    }

}
