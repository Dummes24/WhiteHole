package cz.ProjectWhitehole.mod;

import cz.ProjectWhitehole.Blocks.Copper;
import cz.ProjectWhitehole.Blocks.Eccdurusium;
import cz.ProjectWhitehole.Blocks.Germanium;
import cz.ProjectWhitehole.Blocks.Platinum;
import cz.ProjectWhitehole.Blocks.Tin;
import cz.ProjectWhitehole.Blocks.Uranium;
import net.minecraft.block.Block;
import scala.tools.nsc.doc.model.Public;

public final class ModBlocks {
	
	public static Block copper;
	public static Block eccdurusium;
	public static Block germanium;
	public static Block platinum;
	public static Block uranium;
	public static Block tin;
	
	public static void init()
	{
		copper = new Copper();
		eccdurusium = new Eccdurusium();
		germanium = new Germanium();
		platinum = new Platinum();
		uranium = new Uranium();
		tin = new Tin();		
	}
}
