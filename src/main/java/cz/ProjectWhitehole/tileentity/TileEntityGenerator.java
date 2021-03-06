package cz.ProjectWhitehole.tileentity;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.ForgeDirection;
import cz.ProjectWhitehole.Blocks.Generator;
import cz.ProjectWhitehole.tileentity.TileEntityMachineBase;

public class TileEntityGenerator extends TileEntityMachineBase{
	
	public int burnTime = 0;
	public int currentItemBurnTime = 0;
	public int furnaceSpeed = 100;
	public int storedEnergy;
	
	private static final int[] slots_top = new int[]{0};
	private static final int[] slots_bottom = new int[]{2,1};
	private static final int[] slots_side = new int[]{1};
	
	public TileEntityGenerator(){
		slots = new ItemStack[2];
	}
	
	public void setGuiDisplayName(String displayName) {
		
		this.localizedName = displayName;
		
	}
	
	public boolean isBurning(){
		return this.burnTime > 0;
	}

		
	public void updateEntity(){
		boolean flag = this.burnTime > 0;
		boolean flag1 = false;
		this.storedEnergy = storage.getEnergyStored();
		
		//Burn energy and add energy 
		if (this.burnTime > 0) {
			this.burnTime--;
			this.storage.modifyEnergyStored(10);
		}
		
		
		//Celý pøekopat desync mezi klinetem a serverem
		//If whole 1 fuel was burnt if available burn next fuel,decrease fuel stack size , update block itself
		if (!this.worldObj.isRemote) {

			if (burnTime == 0 && slots[0] != null){
				this.currentItemBurnTime = this.burnTime = getItemBurnTime(slots[0]);
				
				if(slots[0].stackSize > 1)
				    this.slots[0].stackSize--;
				    else {
				     slots[0] = null;
				    
			}
			if (this.isBurning()) {

				if (slots[0] != null) {
					if (burnTime == 0) {
						burnTime = getItemBurnTime(slots[0]);
						if (burnTime > 0) {
							flag1 = true;
							if (slots[0] != null) {
								if (slots[0].stackSize > 0) {
									--slots[0].stackSize; //Check when only 1 item not stack
								}			
								if (slots[0].stackSize == 0) {
									slots[0] = null;
									//slots[0] = slots[0].getItem().getContainerItem(this.slots[0]);
								}
							}
						}
								
					}	
			
			if (flag != burnTime > 0) {

				Generator.updateGeneratorBlockState(burnTime > 0, this.worldObj,this.xCoord,this.yCoord,this.zCoord);
			}
				}
		}
			if (flag1) {
				//Update industrial furnace
				this.markDirty();
			}}}
				
	}
	
	
    @SideOnly(Side.CLIENT)
    public int getBurnTimeRemainingScaled(int i)
    {
        if (this.currentItemBurnTime == 0)
        {
            this.currentItemBurnTime = this.furnaceSpeed;
        }

        return this.burnTime * i / this.currentItemBurnTime;
    }
    
    @SideOnly(Side.CLIENT)
    public int getEnergyProgressScaled(int i)
    {
        return this.storedEnergy * i / storage.getMaxEnergyStored();
    }
    
    @Override
    public void writeToNBT(NBTTagCompound save)
    {
        super.writeToNBT(save);
        save.setShort("BurnTime", (short)this.burnTime);
        save.setShort("CurrentBurnTime",(short)this.currentItemBurnTime);
        save.setInteger("StoredEnergyLoL", (int)this.storedEnergy);
        storage.writeToNBT(save);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.slots.length; ++i)
        {
            if (this.slots[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.slots[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        save.setTag("Items", nbttaglist);

        if (this.hasCustomInventoryName())
        {
            save.setString("CustomName", this.localizedName);
        }
    }
    
    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        NBTTagList nbttaglist = nbt.getTagList("Items", 10);
        this.slots = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.slots.length)
            {
                this.slots[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
        this.burnTime = (int)nbt.getShort("BurnTime");
        this.currentItemBurnTime = (int)nbt.getShort("CurrentBurnTime");
        this.storedEnergy = nbt.getInteger("StoredEnergyLoL");
        storage.setEnergyStored(16000);
        if (nbt.hasKey("CustomName", 8))
        {
            this.localizedName = nbt.getString("CustomName");
        }
    }
    
    
    
    //Inventory

public String getInventoryName() {
	return this.hasCustomInventoryName() ? this.localizedName : "container.industrialRefiner";
	
}

public boolean hasCustomInventoryName() {
	
	return this.localizedName != null && this.localizedName.length() > 0  ;
}

public int getSizeInventory() {
	return  this.slots.length;
}

@Override
public ItemStack getStackInSlot(int i) {
	
	return this.slots[i];
}

@Override
public ItemStack decrStackSize(int i, int j) {
    if (this.slots[i] != null)
    {
        ItemStack itemstack;

        if (this.slots[i].stackSize <= j)
        {
            itemstack = this.slots[i];
            this.slots[i] = null;
            return itemstack;
        }
        else
        {
            itemstack = this.slots[i].splitStack(j);

            if (this.slots[i].stackSize == 0)
            {
                this.slots[i] = null;
            }

            return itemstack;
        }
    }
    else
    {
        return null;
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
public int getInventoryStackLimit() {
	return 64;
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
    
    //Inventory - END
}
