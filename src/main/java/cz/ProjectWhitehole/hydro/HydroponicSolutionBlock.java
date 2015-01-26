package cz.ProjectWhitehole.hydro;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cz.ProjectWhitehole.mod.ProjectWhiteholeMod;

public class HydroponicSolutionBlock extends BlockFluidClassic {

	@SideOnly(Side.CLIENT)
	protected IIcon stillIcon;
	
	@SideOnly(Side.CLIENT)
	protected IIcon flowIcon;
	
	String name = "hydroponicSolution";
	
	public HydroponicSolutionBlock(Fluid fluid, Material material) {
		super(fluid, material);
		this.setBlockName(ProjectWhiteholeMod.MODID + "_" + name);
		setCreativeTab(ProjectWhiteholeMod.tabWhiteHoleMaterials);
		GameRegistry.registerBlock(this, name);	
	}
	
    @Override
    public IIcon getIcon(int side, int meta) {
            return (side == 0 || side == 1)? stillIcon : flowIcon;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister register) {
            stillIcon = register.registerIcon(ProjectWhiteholeMod.MODID + ":"+ name + "_still");
            flowIcon = register.registerIcon(ProjectWhiteholeMod.MODID + ":"+ name + "_flow");
    }
    
    @Override
    public boolean canDisplace(IBlockAccess world, int x, int y, int z) {
            if (world.getBlock(x,  y,  z).getMaterial().isLiquid()) return false;
            return super.canDisplace(world, x, y, z);
    }
    
    @Override
    public boolean displaceIfPossible(World world, int x, int y, int z) {
            if (world.getBlock(x,  y,  z).getMaterial().isLiquid()) return false;
            return super.displaceIfPossible(world, x, y, z);
    }

}
