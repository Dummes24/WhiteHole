package cz.ProjectWhitehole.mod.gui;

import cz.ProjectWhitehole.mod.ProjectWhiteholeMod;
import cz.ProjectWhitehole.tileentity.TileEntityIndustrialRefiner;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiIndustrialRefiner extends GuiContainer{

	public TileEntityIndustrialRefiner industrialRefiner;
	
	public static final ResourceLocation bground = new ResourceLocation(ProjectWhiteholeMod.MODID, "textures/gui/GuiIndustrialRefiner.png");
	
	public GuiIndustrialRefiner(InventoryPlayer inventoryPlayer, TileEntityIndustrialRefiner entity) {
		super(ContainerIndustrialrefiner(inventoryPlayer,entity));
		
		this.industrialRefiner = entity;
		
		this.xSize = 176;
		this.ySize = 166;
	}
	
	public void drawGuiContainerForegroundLayer(int par1, int par2) {
		String name = this.industrialRefiner.hasCustomInventoryName() ? this.industrialRefiner.getInventoryName() : I18n.format(this.industrialRefiner.getInventoryName(), new Object[0]);
		
		this.fontRendererObj.drawString(name, this.xSize/2 - this.fontRendererObj.getStringWidth(name)/2,6,4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]),128, this.ySize - 96 + 2,4210752);
		
		
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_,
			int p_146976_2_, int p_146976_3_) {
		// TODO Auto-generated method stub
		
	}

}
