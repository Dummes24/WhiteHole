package cz.ProjectWhitehole.mod;

import cz.ProjectWhitehole.Blocks.Copper;
import cz.ProjectWhitehole.items.CopperIngot;
import net.minecraft.item.Item;

public final class ModItems {
	
	public static CopperIngot copperIngot;
	
	public static void init()
	{
		copperIngot = new CopperIngot();
	}

}
