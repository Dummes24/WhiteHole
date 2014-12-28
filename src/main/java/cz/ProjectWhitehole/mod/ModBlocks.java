package cz.ProjectWhitehole.mod;

import net.minecraft.block.Block;
import cz.ProjectWhitehole.Blocks.Copper;
import cz.ProjectWhitehole.Blocks.Eccdurusium;
import cz.ProjectWhitehole.Blocks.Germanium;
import cz.ProjectWhitehole.Blocks.IndustrialRefiner;
import cz.ProjectWhitehole.Blocks.Platinum;
import cz.ProjectWhitehole.Blocks.Tin;
import cz.ProjectWhitehole.Blocks.Uranium;

public final class ModBlocks {
	
	public static Block copperOre;
	public static Block eccdurusiumOre;
	public static Block germaniumOre;
	public static Block platinumOre;
	public static Block uraniumOre;
	public static Block tinOre;
	
	//Machines
	public static Block IndustrialRefinerIdle;
	public static Block IndustrialRefinerActive;
	
	public static final int guiIDIndustrialRefiner = 0; 
	
	public static void init()
	{
		copperOre = new Copper();
		eccdurusiumOre = new Eccdurusium();
		germaniumOre = new Germanium();
		platinumOre = new Platinum();
		uraniumOre = new Uranium();
		tinOre = new Tin();
		
		//Machines
		IndustrialRefinerIdle = new IndustrialRefiner(false);
		IndustrialRefinerActive = new IndustrialRefiner(true).setLightLevel(0.5F).setBlockName("IndsutrialRefinerActive");
		
		//Registering unimplemented Blocks
		
		
		
	}
}
