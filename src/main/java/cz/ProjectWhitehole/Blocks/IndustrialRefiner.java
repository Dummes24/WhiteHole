package cz.ProjectWhitehole.Blocks;

import scala.tools.nsc.doc.model.Public;

import com.sun.org.apache.bcel.internal.Constants;
import com.sun.security.ntlm.Client;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cz.ProjectWhitehole.mod.ProjectWhiteholeMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public final class IndustrialRefiner extends BlockContainer{
	
	public String name = "industrialRefiner";
	private IIcon[] icons = new IIcon[6];
	
	public IndustrialRefiner(boolean isActive)
	{
		super(Material.iron);
		this.setBlockName(ProjectWhiteholeMod.MODID + "_" + name);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setStepSound(soundTypeStone);
		this.setHardness(3.0f);
		this.setHarvestLevel("pickaxe", 2);
		GameRegistry.registerBlock(this, name);		
		
	}

		@Override
		public void registerBlockIcons(IIconRegister iconRegister) {
			for (int i = 0; i < icons.length; i++) {
				icons[i] = iconRegister.registerIcon(ProjectWhiteholeMod.MODID + ":" + name); //za name je mozny dat cislo = jiny file pro kazdou stranu
			}		
	}

		@Override
		public IIcon getIcon(int side, int meta) {			
			return icons[side];
		}
		
		public String returnName(){
			return name;
		}

		@Override
		public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
			// TODO Auto-generated method stub
			return null;
		}
	
}
