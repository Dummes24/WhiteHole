package cz.ProjectWhitehole.mod.gui;

import org.lwjgl.opengl.GL11;

import scala.reflect.internal.Trees.This;
import cz.ProjectWhitehole.mod.Coordinations;
import cz.ProjectWhitehole.mod.ProjectWhiteholeMod;
import cz.ProjectWhitehole.tileentity.TileEntityControlBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiStarGate extends GuiScreen{
	
	private TileEntityControlBlock tileEntity;
	public static final ResourceLocation bground = new ResourceLocation(ProjectWhiteholeMod.MODID +":"+"textures/gui/GuiStargate.png");
	
	int xSize = 176;
	int ySize = 88;
	String[] selectedCoords = new String[3];
	String guiName = "Stargate control panel";
	int fontColor = 0xffffff;
	
	private GuiTextField guiTxtXcoord;	
	private GuiTextField guiTxtYcoord;
	private GuiTextField guiTxtZcoord;
	
	@Override
	protected void keyTyped(char c, int i) {
		super.keyTyped(c, i);
		guiTxtXcoord.textboxKeyTyped(c, i);
		guiTxtYcoord.textboxKeyTyped(c, i);
		guiTxtZcoord.textboxKeyTyped(c, i);
	}
	
	@Override
	protected void mouseClicked(int i, int j, int k) {
		super.mouseClicked(i, j, k);
		guiTxtXcoord.mouseClicked(i, j, k);
		guiTxtYcoord.mouseClicked(i, j, k);
		guiTxtZcoord.mouseClicked(i, j, k);
	}
	
	public GuiStarGate(TileEntityControlBlock tileEntity) {
		super();
		this.tileEntity = tileEntity;
		
	}
	
	@Override
	public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
		drawDefaultBackground();
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		Minecraft.getMinecraft().getTextureManager().bindTexture(bground);
		
		int posX = (this.width - xSize) / 2;
		int posY = (this.height - ySize) / 2;		
		
		drawTexturedModalRect(posX, posY, 0, 0, xSize, ySize);
		drawString(fontRendererObj, guiName, posX + (xSize/2) - (guiName.length() * 5 / 2) , posY + 10 , fontColor);
		guiTxtXcoord.drawTextBox();
		guiTxtYcoord.drawTextBox();
		guiTxtZcoord.drawTextBox();
		drawString(fontRendererObj,"X", posX + 25, posY + 25,fontColor);
		drawString(fontRendererObj,"Y", posX + 59, posY + 25,fontColor);
		drawString(fontRendererObj,"Z", posX + 93, posY + 25,fontColor);
		super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
	}
	
	@Override
	public boolean doesGuiPauseGame() {	
		return false; 
	}
	
	@Override
	protected void actionPerformed(GuiButton button) {
		switch (button.id) {
		case 0:
			//TODO Select gui and coresponding select box	
			break;
		case 1:
			Coordinations coordsToSave = new Coordinations(selectedCoords[0], selectedCoords[1], selectedCoords[2]);
			
			if (coordsToSave.areValid()) {
				//TODO Save coords
				//TODO Request name from user
			}
			
			break;
			
		case 2:			
			Coordinations coordsToDial = new Coordinations(selectedCoords[0], selectedCoords[1], selectedCoords[2]);			
				
				if (tileEntity.hasAssignedValidStargate() && coordsToDial.areValid()) {
					tileEntity.activateFromGui(coordsToDial.getX(), coordsToDial.getY(),coordsToDial.getZ());
				}			
			break;

		default:
			break;
		}		
	}
	
	@Override
	public void updateScreen() {		
		selectedCoords[0] = guiTxtXcoord.getText();
		selectedCoords[1] = guiTxtYcoord.getText();
		selectedCoords[2] = guiTxtZcoord.getText();
	}
	
	
	@Override
	public void initGui() {
		this.buttonList.clear();
		int posX = (this.width - xSize) / 2;
		int posY = (this.height - ySize) / 2;
		//Buttons
		this.buttonList.add(new GuiButton(0,posX + 125, posY + 20, 43, 18,"Select"));
		this.buttonList.add(new GuiButton(1, posX + 125, posY + 40, 43,18,"Add"));
		this.buttonList.add(new GuiButton(2, posX + 125, posY + 60, 43,18,"Dial"));		
		
		//TextFields
		guiTxtXcoord = new GuiTextField(fontRendererObj, posX + 12, posY + 36, 30, 17);
		guiTxtXcoord.setMaxStringLength(8);
		guiTxtYcoord = new GuiTextField(fontRendererObj,posX + 46, posY + 36, 30, 17);
		guiTxtYcoord.setMaxStringLength(8);
		guiTxtZcoord = new GuiTextField(fontRendererObj,posX + 80,posY + 36, 30, 17);
		guiTxtZcoord.setMaxStringLength(8);
		
	}	
	
}
