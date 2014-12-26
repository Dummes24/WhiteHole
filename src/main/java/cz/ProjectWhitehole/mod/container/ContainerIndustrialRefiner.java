package cz.ProjectWhitehole.mod.container;

import cz.ProjectWhitehole.tileentity.TileEntityIndustrialRefiner;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class ContainerIndustrialRefiner extends Container {

	private TileEntityIndustrialRefiner industrialRefiner;
	
	public ContainerIndustrialRefiner(InventoryPlayer invetoryPlayer, TileEntityIndustrialRefiner tileentity) {
		
		this.industrialRefiner = tileentity;
	}
	
	
	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		// TODO Auto-generated method stub
		return false;
	}

}
