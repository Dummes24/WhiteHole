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
	private int cookTime;
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
		this.storedEnergy = storage.getEnergyStored();
		//Burn energy and add energy 
		if (this.burnTime > 0) {
			this.burnTime--;
			this.storage.modifyEnergyStored(100);
		}
		
		//If whole 1 fuel was burnt if available burn next fuel, update block itself
		if (!this.worldObj.isRemote) {
			if (burnTime == 0 && slots[0] != null){
				this.currentItemBurnTime = this.burnTime = getItemBurnTime(slots[0]);				
				this.slots[0].stackSize--;
			}
			if (this.isBurning()) {
				Generator.updateGeneratorBlockState(burnTime > 0, this.worldObj,this.xCoord,this.yCoord,this.zCoord);
			}
		}
			if ((burnTime > 0) != flag) {
				//Update industrial furnace
				this.markDirty();
			}
				
	}
	
	
	
	public boolean canSmelt(){
		
		if(this.slots[0] == null){
			return false;
		}
		else{
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.slots[0]);
			
            if (itemstack == null) return false;
            if (this.slots[2] == null) return true;
            if (!this.slots[2].isItemEqual(itemstack)) return false;
            int result = slots[2].stackSize + itemstack.stackSize;
            return result <= getInventoryStackLimit() && result <= this.slots[2].getMaxStackSize();
		}
	}
		
	public void smeltItem()
	{
        if (this.canSmelt())
        {
            ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.slots[0]);

            if (this.slots[2] == null)
            {
                this.slots[2] = itemstack.copy();
            }
            else if (this.slots[2].getItem() == itemstack.getItem())
            {
                this.slots[2].stackSize += itemstack.stackSize; // Forge BugFix: Results may have multiple items
            }

            this.slots[0].stackSize--;

            if (this.slots[0].stackSize <= 0)
            {
                this.slots[0] = null;
            }
        }
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
    
    public void writeToNBT(NBTTagCompound save)
    {
        super.writeToNBT(save);
        save.setShort("BurnTime", (short)this.burnTime);
        save.setShort("CookTime", (short)this.cookTime);
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
        this.cookTime = (int)nbt.getShort("CookTime");
        this.currentItemBurnTime = getItemBurnTime(this.slots[1]);

        if (nbt.hasKey("CustomName", 8))
        {
            this.localizedName = nbt.getString("CustomName");
        }
    }
}
