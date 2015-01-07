package cz.ProjectWhitehole.mod.gui;

import org.lwjgl.opengl.GL11;

import cz.ProjectWhitehole.mod.ProjectWhiteholeMod;
import cz.ProjectWhitehole.mod.container.ContainerGenerator;
import cz.ProjectWhitehole.tileentity.TileEntityGenerator;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiGenerator extends GuiContainer{

	public TileEntityGenerator Generator;
	
	public static final ResourceLocation bground = new ResourceLocation(ProjectWhiteholeMod.MODID +":"+"textures/gui/GuiGenerator.png");
	
	public GuiGenerator(InventoryPlayer inventoryPlayer, TileEntityGenerator entity) {
		super(new ContainerGenerator(inventoryPlayer,entity));
		
		this.Generator = entity;
		
		this.xSize = 176;
		this.ySize = 166;
	}
	
	public void drawGuiContainerForegroundLayer(int par1, int par2) {
		String name = "Generator";
		
		this.fontRendererObj.drawString(name, this.xSize/2 - this.fontRendererObj.getStringWidth(name)/2,6,4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]),120, this.ySize - 96 + 2,4210752);
		
		
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
	
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(bground);
        this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        if (this.Generator.isBurning())
        {
            int i1 = this.Generator.getBurnTimeRemainingScaled(40);
            int j = 40 - i1;
            this.drawTexturedModalRect(guiLeft + 29, guiTop + 65,176,0,40-j,10);

        }
        
        int k;
        
        k = this.Generator.getEnergyScaled(24);
        this.drawTexturedModalRect(guiLeft + 79, guiTop + 34, 176, 10, k + 1, 17);
        
        
		
	}

}
