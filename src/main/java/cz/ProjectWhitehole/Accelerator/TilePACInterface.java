package cz.ProjectWhitehole.Accelerator;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyReceiver;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cz.ProjectWhitehole.Blocks.Generator;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TilePACInterface extends TileEntity implements ISidedInventory, IEnergyReceiver {

	public int burnTime = 0;
	public int currentItemBurnTime = 0;
	public int pacSpeed = 100;
	private String localizedName;
	
	private static final int[] slots_top = new int[]{0};
	private static final int[] slots_bottom = new int[]{2,1};
	private static final int[] slots_side = new int[]{1};
	
	private static ItemStack[] slots;
	private static EnergyStorage storage = new EnergyStorage(120000);
	private boolean havePac = false;
	
	private static Block[] assignedBlocks = new Block[36];
	private static boolean[] scheme = new boolean[]{
		
		false,true,true,true,true,false,
		true,true,false,false,true,true,
		true,false,false,false,false,true,
		true,false,false,false,false,true,
		true,true,false,false,true,true,
		false,true,true,true,true,false,
	};
	
	public TilePACInterface(){
		slots = new ItemStack[2];
	}
	
	public void setGuiDisplayName(String displayName) {
		
		this.localizedName = displayName;
		
	}
	
	public boolean isBurning(){
		return this.burnTime > 0;
	}

		
	public void updateEntity(){
				
		if(!havePac){
		checkAndValidate();
		}
	}
	
	public void checkAndValidate(){
					
			int ID = 0;
			
			switch(checkForPlane()){
			// X-planes
				case 1:
				
					for(int x = 1; x<7; x++){
					
						for (int z = -1; z < 5; z++) {
						
							assignedBlocks[ID] = worldObj.getBlock(xCoord + x, yCoord, zCoord + z);
							System.out.println("Got block on " + (xCoord + x)+ " " + yCoord+ " " + (zCoord + z) +" "+ ID ); //Debug
							ID++;
						}
					}
					
					havePac = true;
					
				break;
				
				case 2:
					
					for(int x = 1; x<7; x++){
						
						for (int z = -1; z < 5; z++) {
						
							//worldObj.getBlock(xCoord - x, yCoord, zCoord - z);
							worldObj.setBlock(xCoord-x, yCoord, zCoord-z, Block.getBlockById(5));
							ID++;
						}
					}
					
					havePac = true;
						
				break;
				
				// Z-planes
				case 3:
					
					for(int z = 1; z<7; z++){
					
						for (int x = -1; x < 5; x++) {
						
							worldObj.getBlock(xCoord + x, yCoord, zCoord + z);
							ID++;
						}
					}
					
				break;
				
				case 4:
					
					for(int z = 1; z<7; z++){
						
						for (int x = -1; x < 5; z++) {
						
							worldObj.getBlock(xCoord - x, yCoord, zCoord - z);
							ID++;
						}
					}
						
				break;
			}
			
			//Validate
			
			

	}
			
	
	
	public int checkForPlane(){
		
		if(this.worldObj.getBlock(xCoord+1, yCoord, zCoord) instanceof InductionCoil ){
			
			return 1; // Plane x+1
		}
		else if(this.worldObj.getBlock(xCoord-1, yCoord, zCoord) instanceof InductionCoil ){
			
			return 2; // Plane x-1
		}
		else if(this.worldObj.getBlock(xCoord, yCoord, zCoord+1) instanceof InductionCoil){
			
			return 3; // Plane z+1
		}
		else if(this.worldObj.getBlock(xCoord, yCoord, zCoord-1) instanceof InductionCoil ){
			
			return 4; // Plane z-1
		}
		
		return 0;
	}
	
    @SideOnly(Side.CLIENT)
    public int getBurnTimeRemainingScaled(int i)
    {
        if (this.currentItemBurnTime == 0)
        {
            this.currentItemBurnTime = this.pacSpeed;
        }

        return this.burnTime * i / this.currentItemBurnTime;
    }
    
    
    @Override
    public void writeToNBT(NBTTagCompound save)
    {
        super.writeToNBT(save);
        save.setShort("BurnTime", (short)this.burnTime);
        save.setShort("CurrentBurnTime",(short)this.currentItemBurnTime);
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
	//TODO Item validation
	return true;
}

@Override
public boolean isUseableByPlayer(EntityPlayer entityplayer) {
	return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : entityplayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
}

@Override
public int[] getAccessibleSlotsFromSide(int side) {
	return side == 0 ? slots_bottom : (side == 1 ? slots_top : slots_side);
}

@Override
public boolean canInsertItem(int i, ItemStack itemstack,
		int p_102007_3_) {
	return this.isItemValidForSlot(i, itemstack);

}

@Override
public boolean canExtractItem(int i, ItemStack itemstack,
		int j) {
	return j != 0 || i != 1 || itemstack.getItem() == Items.bucket;
}

//Inventory - END

//Energy
@Override
public boolean canConnectEnergy(ForgeDirection from) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public int getEnergyStored(ForgeDirection from) {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public int getMaxEnergyStored(ForgeDirection from) {
	//TODO
	return 0;
}
    
    
}

