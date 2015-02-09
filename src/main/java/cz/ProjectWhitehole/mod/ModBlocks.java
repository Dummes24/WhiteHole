package cz.ProjectWhitehole.mod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cz.ProjectWhitehole.Accelerator.InductionCoil;
import cz.ProjectWhitehole.Accelerator.TilePACInterface;
import cz.ProjectWhitehole.Blocks.CopperOre;
import cz.ProjectWhitehole.Blocks.EccdurusiumOre;
import cz.ProjectWhitehole.Blocks.Generator;
import cz.ProjectWhitehole.Blocks.GermaniumOre;
import cz.ProjectWhitehole.Blocks.IndustrialRefiner;
import cz.ProjectWhitehole.Blocks.MedicalBlock;
import cz.ProjectWhitehole.Blocks.PlatinumOre;
import cz.ProjectWhitehole.Blocks.TinOre;
import cz.ProjectWhitehole.Blocks.UraniumOre;
import cz.ProjectWhitehole.hydro.HydroponicContainer;
import cz.ProjectWhitehole.hydro.HydroponicContainerTile;
import cz.ProjectWhitehole.hydro.HydroponicSolutionBlock;
import cz.ProjectWhitehole.items.HydroponicSolutionBucket;
import cz.ProjectWhitehole.mod.handler.BucketHandler;
import cz.ProjectWhitehole.tileentity.TileEntityGenerator;
import cz.ProjectWhitehole.tileentity.TileEntityIndustrialRefiner;
import cz.ProjectWhitehole.Accelerator.PACInterface;

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

	public static Block medicalBlockActive;
	public static Block medicalBlockIdle;	
	
	public static Block PACInterface;

	public static final int guiIDIndustrialRefiner = 0;
	public static final int guiIDGenerator = 1;
	public static final int guiIDPACInterface = 2;
	
	//Utils blocks
	public static Block HydroponicContainer;
	public static Block inductionCoil;
	
	//Fluid
	public static Fluid hydroponicSolution;
	public static Block hydroponicSolutionBlock;
	public static HydroponicSolutionBucket hydroponicSolutionBucket;


	
	public static void init()
	{
		copperOre = new CopperOre();
		eccdurusiumOre = new EccdurusiumOre();
		germaniumOre = new GermaniumOre();
		platinumOre = new PlatinumOre();
		uraniumOre = new UraniumOre();
		tinOre = new TinOre();
		
		//Machines
		IndustrialRefinerIdle = new IndustrialRefiner(false).setCreativeTab(ProjectWhiteholeMod.tabWhiteHole);
		IndustrialRefinerActive = new IndustrialRefiner(true).setLightLevel(0.5F);
		generatorIdleBlock = new Generator(false).setCreativeTab(ProjectWhiteholeMod.tabWhiteHole);
		generatorActiveBlock = new Generator(true).setLightLevel(0.5F);
		medicalBlockActive = new MedicalBlock(true);
		medicalBlockIdle = new MedicalBlock(false).setCreativeTab(ProjectWhiteholeMod.tabWhiteHole);
		PACInterface = new PACInterface();
		
		
		
		//Utils Block
		HydroponicContainer = new HydroponicContainer();
		inductionCoil = new InductionCoil();
		
		//Fluid
		hydroponicSolution = new Fluid("HydroponicSolution");
		FluidRegistry.registerFluid(hydroponicSolution);
		hydroponicSolutionBlock = new HydroponicSolutionBlock(hydroponicSolution, Material.water);
		hydroponicSolutionBucket = new HydroponicSolutionBucket(hydroponicSolutionBlock);
		
		BucketHandler.INSTANCE.buckets.put(hydroponicSolutionBlock, hydroponicSolutionBucket);
		MinecraftForge.EVENT_BUS.register(BucketHandler.INSTANCE);
		
		//Registering unimplemented Blocks		
		GameRegistry.registerBlock(IndustrialRefinerIdle, "IndustrialRefinerIdle");
		GameRegistry.registerBlock(IndustrialRefinerActive, "IndustrialRefinerActive");
		GameRegistry.registerBlock(generatorActiveBlock, "generatorActiveBlock");
		GameRegistry.registerBlock(generatorIdleBlock, "generatorIdleBlock");
		GameRegistry.registerBlock(medicalBlockActive, "medicalBlockActive");
		GameRegistry.registerBlock(medicalBlockIdle, "medicalBlockIdle");
		
		//Registering TileEntitees
		GameRegistry.registerTileEntity(TileEntityIndustrialRefiner.class, "IndustrialRefiner");
		GameRegistry.registerTileEntity(TileEntityGenerator.class, "Generator");
		GameRegistry.registerTileEntity(HydroponicContainerTile.class, "HydroponicContainer");
		GameRegistry.registerTileEntity(TilePACInterface.class, "PACInterface");
		
	}
}
