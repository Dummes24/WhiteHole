package cz.ProjectWhitehole.mod.container;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cz.ProjectWhitehole.tileentity.TileEntityGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;

public class ContainerGenerator extends Container {

	private TileEntityGenerator Generator;
	
	public int lastBurnTime;
	public int lastCurrentItemBurnTime;
	public int lastCookTime;
	public int lastItemBurnTime;
	
	public ContainerGenerator(InventoryPlayer inventoryPlayer, TileEntityGenerator tileentity) {
		
		this.Generator = tileentity;
		
        this.addSlotToContainer(new Slot(tileentity, 0, 56, 20));
        this.addSlotToContainer(new Slot(tileentity, 1, 56, 45));
        int i;
        
        for (i = 0; i < 3; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; i++)
        {
            this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
        }
	}
	
    public void addCraftingToCrafters(ICrafting icrafting)
    {
        super.addCraftingToCrafters(icrafting);
        icrafting.sendProgressBarUpdate(this, 0, this.Generator.cookTime);
        icrafting.sendProgressBarUpdate(this, 1, this.Generator.burnTime);
        icrafting.sendProgressBarUpdate(this, 2, this.Generator.currentItemBurnTime);
    }
    
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);

            if (this.lastCookTime != this.Generator.cookTime)
            {
                icrafting.sendProgressBarUpdate(this, 0, this.Generator.cookTime);
            }

            if (this.lastBurnTime != this.Generator.burnTime)
            {
                icrafting.sendProgressBarUpdate(this, 1, this.Generator.burnTime);
            }

            if (this.lastItemBurnTime != this.Generator.currentItemBurnTime)
            {
                icrafting.sendProgressBarUpdate(this, 2, this.Generator.currentItemBurnTime);
            }
        }

        this.lastCookTime = this.Generator.cookTime;
        this.lastBurnTime = this.Generator.burnTime;
        this.lastItemBurnTime = this.Generator.currentItemBurnTime;
    }
    
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int i, int j)
    {
        if (i == 0)
        {
            this.Generator.cookTime = j;
        }

        if (i == 1)
        {
            this.Generator.burnTime = j;
        }

        if (i == 2)
        {
            this.Generator.currentItemBurnTime = j;
        }
    }
	
	
	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return true;
	}
	
	@Override
    public ItemStack transferStackInSlot(EntityPlayer player, int i)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(i);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (i == 2)
            {
                if (!this.mergeItemStack(itemstack1, 3, 39, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (i != 1 && i != 0)
            {
                if (FurnaceRecipes.smelting().getSmeltingResult(itemstack1) != null)
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return null;
                    }
                }
                else if (TileEntityFurnace.isItemFuel(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false))
                    {
                        return null;
                    }
                }
                else if (i >= 3 && i < 30)
                {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false))
                    {
                        return null;
                    }
                }
                else if (i >= 30 && i < 39 && !this.mergeItemStack(itemstack1, 3, 30, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 3, 39, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(player, itemstack1);
        }

        return itemstack;
    }

}
