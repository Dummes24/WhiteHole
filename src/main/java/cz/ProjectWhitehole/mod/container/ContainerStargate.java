package cz.ProjectWhitehole.mod.container;

import cz.ProjectWhitehole.tileentity.TileEntityControlBlock;
import cz.ProjectWhitehole.tileentity.TileEntityIndustrialRefiner;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class ContainerStargate extends Container {

	TileEntityControlBlock tileEntity;
	
	public ContainerStargate(InventoryPlayer inventoryPlayer, TileEntityControlBlock tileentity)  {
		this.tileEntity = tileentity;
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return true;
	}

}
