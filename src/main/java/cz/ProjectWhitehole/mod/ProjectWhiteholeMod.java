package cz.ProjectWhitehole.mod;

import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ProjectWhiteholeMod.MODID, version = ProjectWhiteholeMod.VERSION)
public class ProjectWhiteholeMod {
	public static final String MODID = "projectwhitehole";
    public static final String VERSION = "0.1";
    
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	ModBlocks.init();
    	ModItems.init();    	
    	
    }
 
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
    	ModSmelting.init();
    }
 
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
 
    }

	
}
