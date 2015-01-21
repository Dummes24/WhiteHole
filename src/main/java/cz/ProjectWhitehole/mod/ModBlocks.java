package cz.ProjectWhitehole.mod;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import cz.ProjectWhitehole.Blocks.CopperOre;
import cz.ProjectWhitehole.Blocks.EccdurusiumOre;
import cz.ProjectWhitehole.Blocks.Generator;
import cz.ProjectWhitehole.Blocks.GermaniumOre;
import cz.ProjectWhitehole.Blocks.IndustrialRefiner;
import cz.ProjectWhitehole.Blocks.MedicalBlock;
import cz.ProjectWhitehole.Blocks.PlatinumOre;
import cz.ProjectWhitehole.Blocks.TinOre;
import cz.ProjectWhitehole.Blocks.UraniumOre;
import cz.ProjectWhitehole.tileentity.TileEntityGenerator;
import cz.ProjectWhitehole.tileentity.TileEntityIndustrialRefiner;

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
	
	public static Block generatorActiveBlock;
	public static Block generatorIdleBlock;

	public static Block GeneratorIdle;
	public static Block GeneratorActive;
	

	public static Block medicalBlockActive;
	public static Block medicalBlockIdle;
	

	public static final int guiIDIndustrialRefiner = 0;
	public static final int guiIDGenerator = 1; 
	

	
	public static void init()
	{
		copperOre = new CopperOre();
		eccdurusiumOre = new EccdurusiumOre();
		germaniumOre = new GermaniumOre();
		platinumOre = new PlatinumOre();
		uraniumOre = new UraniumOre();
		tinOre = new TinOre();
		
		//Machines
		IndustrialRefinerIdle = new IndustrialRefiner(false).setCreativeTab(CreativeTabs.tabBlock);
		IndustrialRefinerActive = new IndustrialRefiner(true).setLightLevel(0.5F);
		generatorActiveBlock = new Generator(false).setCreativeTab(CreativeTabs.tabBlock);
		generatorIdleBlock = new Generator(true).setLightLevel(0.5F);
		medicalBlockActive = new MedicalBlock(true);
		medicalBlockIdle = new MedicalBlock(false).setCreativeTab(CreativeTabs.tabBlock);

		
		
		
		//Registering unimplemented Blocks
		
		GameRegistry.registerBlock(IndustrialRefinerIdle, "IndustrialRefinerIdle");
		GameRegistry.registerBlock(IndustrialRefinerActive, "IndustrialRefinerActive");
		GameRegistry.registerBlock(generatorActiveBlock, "generatorActiveBlock");
		GameRegistry.registerBlock(generatorIdleBlock, "generatorIdleBlock");
		GameRegistry.registerBlock(medicalBlockActive, "medicalBlockActive");
		GameRegistry.registerBlock(medicalBlockIdle, "medicalBlockIdle");
		
		
		GameRegistry.registerTileEntity(TileEntityIndustrialRefiner.class, "IndustrialRefiner");
		GameRegistry.registerTileEntity(TileEntityGenerator.class, "Generator");
		
	}
}
