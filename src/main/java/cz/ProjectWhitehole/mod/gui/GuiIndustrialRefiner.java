package cz.ProjectWhitehole.mod.gui;

import cz.ProjectWhitehole.tileentity.TileEntityIndustrialRefiner;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class GuiIndustrialRefiner extends GuiContainer{

	public TileEntityIndustrialRefiner industrialRefiner;
	
	public GuiIndustrialRefiner(InventoryPlayer inventoryPlayer, TileEntityIndustrialRefiner entity) {
		super(ContainerIndustrialrefiner(inventoryPlayer,entity));
		
		this.industrialRefiner = entity;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_,
			int p_146976_2_, int p_146976_3_) {
		// TODO Auto-generated method stub
		
	}

}
