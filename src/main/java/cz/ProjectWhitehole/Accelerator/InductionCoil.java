package cz.ProjectWhitehole.Accelerator;

import cpw.mods.fml.common.registry.GameRegistry;
import cz.ProjectWhitehole.mod.ProjectWhiteholeMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class InductionCoil extends Block {
	
	String name = "InductionCoil";
	
	TilePACInterface assignedTile;

	public InductionCoil() {
		super(Material.iron);
		this.setCreativeTab(ProjectWhiteholeMod.tabWhiteHole);
		this.setStepSound(soundTypeMetal);
		this.setBlockName(ProjectWhiteholeMod.MODID + "_" + name);
		this.setTickRandomly(true);
		this.setBlockTextureName(ProjectWhiteholeMod.MODID + ":" + name);
		GameRegistry.registerBlock(this, name);	
	}
	
	public void assingInterface(TilePACInterface tile){
		
		this.assignedTile = tile;
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int oldMeta){
		
		//assignedTile.checkAndValidate();
	}
	
	
	

}
