package cz.ProjectWhitehole.mod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cz.ProjectWhitehole.mod.handler.GuiHandler;
import cz.ProjectWhitehole.tileentity.TileEntityIndustrialRefiner;

@Mod(modid = ProjectWhiteholeMod.MODID, version = ProjectWhiteholeMod.VERSION)
public class ProjectWhiteholeMod {
	public static final String MODID = "projectwhitehole";
    public static final String VERSION = "0.0.1";
    
    @Instance(MODID)
    public static ProjectWhiteholeMod instance;
    
	public static CreativeTabs tabWhiteHole = new CreativeTabs("WhiteHole"){
		
		@SideOnly(Side.CLIENT)
		
		public Item getTabIconItem(){
			return Item.getItemFromBlock(Blocks.anvil);
		}
	};
	
	public static CreativeTabs tabWhiteHoleMaterials = new CreativeTabs("WhiteHoleMaterials"){		
		@SideOnly(Side.CLIENT)		
		public Item getTabIconItem(){
			return Item.getItemFromBlock(Blocks.brick_block);
		}
	};
	
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	ModBlocks.init();
    	ModItems.init(); 	    	
    }
 
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
    	ModSmelting.init();
    	ModCrafting.init();
    	NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
    }
 
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
 
    }

	
}
