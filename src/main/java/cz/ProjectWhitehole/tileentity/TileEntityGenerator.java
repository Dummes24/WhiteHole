package cz.ProjectWhitehole.tileentity;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraftforge.common.util.ForgeDirection;
import cz.ProjectWhitehole.tileentity.TileEntityMachineBase;

public class TileEntityGenerator extends TileEntityMachineBase{
	
	public int burnTime = 0;
	private String localizedName;
	public int currentItemBurnTime = 0;
	public int furnaceSpeed = 100; 
	
	public TileEntityGenerator() {
		slots = new ItemStack[2];
	}
	
	public void setGuiDisplayName(String displayName) {
		
		this.localizedName = displayName;
		
	}
	
	public Boolean isBurning(){
		return burnTime > 0;
	}
	
	public String getInventoryName() {
		return this.hasCustomInventoryName() ? this.localizedName : "container.generator";		
	}
	
	public boolean hasCustomInventoryName() {
		
		return this.localizedName != null && this.localizedName.length() > 0  ;
	}
	
	public int getSizeInventory() {
		return  this.slots.length;
	}
		
	public void updateEntity(){
		boolean flag = this.burnTime > 0;
		if (this.burnTime > 0) {
			this.burnTime--;
		}
		if (!this.worldObj.isRemote) {
			if (burnTime == 0 && slots[0] != null){
				burnTime = getItemBurnTime(slots[1]);
				if (slots[0] != null) {
					this.slots[0].stackSize--;
				}
			}
			if ((burnTime > 0) != flag) {
				//Update industrial furnace
				this.markDirty();
			}
		}
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
    public int getEnergyScaled(int i)
    {    	
        return this.getEnergyStored(ForgeDirection.UNKNOWN) * i / 100;
    } 	
}
