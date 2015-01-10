package cz.ProjectWhitehole.tileentity;

import cofh.api.energy.IEnergyHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class TileEntityGeneratorTest extends TileEntity implements ISidedInventory, IEnergyHandler{
		
	private ItemStack[] slots = new ItemStack[3];
	private String localizedName; 
	
	//Energy
	protected int energy;
	private int maxEnergy;
	
	public void setGuiDisplayName(String displayName) {
		
		this.localizedName = displayName;
		
	}
	
	@Override
	public int getSizeInventory() {
		return this.slots.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return slots[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		if (this.slots[i] != null) {
			ItemStack itemStack;
			if (this.slots[i].stackSize <= j) {
				itemStack = this.slots[i];
				return itemStack;
			}
			else {
				itemStack = this.slots[i].splitStack(j);
				
				if (this.slots[i].stackSize == 0)
                {
                    this.slots[i] = null;
                }

                return itemStack;
			}
		}
		else {
			return null;
		}
		
	}
	
	@Override
	public void updateEntity() {
		if (this.worldObj != null && !this.worldObj.isRemote){
			
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		 if (this.slots[i] != null)
	        {
	            ItemStack itemstack = this.slots[i];
	            this.slots[i] = null;
	            return itemstack;
	        }
	        else
	        {
	            return null;
	        }
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		this.slots[i] = itemstack;

        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit())
        {
            itemstack.stackSize = this.getInventoryStackLimit();
        }
		
	}

	@Override
	public String getInventoryName() {
		return this.hasCustomInventoryName() ? this.localizedName : "container.industrialRefiner";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return this.localizedName != null && this.localizedName.length() > 0;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : entityplayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory() {
		
		
	}

	@Override
	public void closeInventory() {
		
		
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return i== 2 ? false : (i==1 ? isItemFuel(itemstack) : true);
	}
	
	public static boolean isItemFuel(ItemStack itemstack) {
		return getItemBurnTime(itemstack) > 0;
	}
	
		private static int getItemBurnTime(ItemStack itemstack) {
		
		if(itemstack == null) {
			return 0;
		}else{
			Item item = itemstack.getItem();
			
			if(item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air){
				
				Block block = Block.getBlockFromItem(item);
				
                if (block == Blocks.wooden_slab)
                {
                    return 150;
                }

                if (block.getMaterial() == Material.wood)
                {
                    return 300;
                }

                if (block == Blocks.coal_block)
                {
                    return 16000;
                }
            }

            if (item instanceof ItemTool && ((ItemTool)item).getToolMaterialName().equals("WOOD")) return 200;
            if (item instanceof ItemSword && ((ItemSword)item).getToolMaterialName().equals("WOOD")) return 200;
            if (item instanceof ItemHoe && ((ItemHoe)item).getToolMaterialName().equals("WOOD")) return 200;
            if (item == Items.stick) return 100;
            if (item == Items.coal) return 1600;
            if (item == Items.lava_bucket) return 20000;
            if (item == Item.getItemFromBlock(Blocks.sapling)) return 100;
            if (item == Items.blaze_rod) return 2400;
            return GameRegistry.getFuelValue(itemstack);
		}
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		return true;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive,boolean simulate) {
		int energyRecieved = maxReceive;
		if (energyRecieved > maxEnergy - energy)
			energyRecieved = maxReceive - energy;
		if (!simulate)
			energy += energyRecieved;
		return energyRecieved;
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract,boolean simulate) {
		int extractedEnergy = maxExtract;
		if (extractedEnergy > energy) {
			extractedEnergy = energy;
		}
		if (!simulate) {
			energy -= extractedEnergy;
		}
		return extractedEnergy;
	}

	@Override
	public int getEnergyStored(ForgeDirection from) {		
		return energy;
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {
		return maxEnergy;
	}
	

	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, int j) {
		return j != 0 || i != 1 || itemstack.getItem() == Items.bucket;
	}

	@Override
	public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_,
			int p_102008_3_) {		
		return false;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int var1) {
		return var1 == 0 ? new int[]{2,1} : (var1 == 1 ? new int[]{0} : new int[]{1});
	}

}
