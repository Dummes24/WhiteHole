package cz.ProjectWhitehole.mod;

import cz.ProjectWhitehole.items.BronzeIngot;
import cz.ProjectWhitehole.items.CopperIngot;
import cz.ProjectWhitehole.items.EccdurusiumIngot;
import cz.ProjectWhitehole.items.GermaniumIngot;
import cz.ProjectWhitehole.items.HydroponicSolutionBucket;
import cz.ProjectWhitehole.items.PlatinumIngot;
import cz.ProjectWhitehole.items.TinIngot;
import cz.ProjectWhitehole.items.UraniumIngot;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSeeds;

public final class ModItems {
	
	public static BronzeIngot bronzeIngot;
	public static CopperIngot copperIngot;	
	public static EccdurusiumIngot eccdurusiumIngot;
	public static GermaniumIngot germaniumIngot;
	public static PlatinumIngot platinumIngot;	
	public static TinIngot tinIngot;	
	public static UraniumIngot uraniumIngot;
	
	
	public static void init()
	{
		bronzeIngot = new BronzeIngot();
		copperIngot = new CopperIngot();
		eccdurusiumIngot = new EccdurusiumIngot();
		germaniumIngot = new GermaniumIngot();
		platinumIngot = new PlatinumIngot();		
		tinIngot = new TinIngot();
		uraniumIngot = new UraniumIngot();
		

	}

}
