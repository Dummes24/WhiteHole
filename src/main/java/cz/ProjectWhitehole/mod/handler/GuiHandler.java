package cz.ProjectWhitehole.mod.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cz.ProjectWhitehole.mod.ModBlocks;
import cz.ProjectWhitehole.mod.container.ContainerGenerator;
import cz.ProjectWhitehole.mod.container.ContainerIndustrialRefiner;
import cz.ProjectWhitehole.mod.gui.GuiGenerator;
import cz.ProjectWhitehole.mod.gui.GuiIndustrialRefiner;
import cz.ProjectWhitehole.tileentity.TileEntityGenerator;
import cz.ProjectWhitehole.tileentity.TileEntityIndustrialRefiner;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity entity = world.getTileEntity(x, y, z);
		
		if(entity != null ){
			
			switch(ID) {
			
				case ModBlocks.guiIDIndustrialRefiner:
				if(entity instanceof TileEntityIndustrialRefiner) {
					return new ContainerIndustrialRefiner(player.inventory, (TileEntityIndustrialRefiner)entity);
				}
				return null;
				
				case ModBlocks.guiIDGenerator:
				if(entity instanceof TileEntityIndustrialRefiner) {
					return new ContainerGenerator(player.inventory, (TileEntityGenerator)entity);
				}
				return null;
			}
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity entity = world.getTileEntity(x, y, z);
		
		if(entity != null ){
			
			switch(ID) {
			
				case ModBlocks.guiIDIndustrialRefiner:
				if(entity instanceof TileEntityIndustrialRefiner) {
					return new GuiIndustrialRefiner(player.inventory, (TileEntityIndustrialRefiner)entity);
				}
				return null;
				
				case ModBlocks.guiIDGenerator:
				if(entity instanceof TileEntityIndustrialRefiner) {
					return new GuiGenerator(player.inventory, (TileEntityGenerator)entity);
				}
				return null;
			}
		}
		return null;
	}

}
