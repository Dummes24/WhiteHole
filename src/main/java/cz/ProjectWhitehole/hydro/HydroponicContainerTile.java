package cz.ProjectWhitehole.hydro;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.tileentity.TileEntity;

public class HydroponicContainerTile extends TileEntity {

	int growChance = 5;
	int tickCount = 0;
	
	public void updateEntity(){
		tickCount++;
		int cropMeta = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord+1, this.zCoord);
		Block cropBlock = this.worldObj.getBlock(this.xCoord, this.yCoord+1, this.zCoord);
		Random rand = new Random();
		//IGrowable test = (IGrowable)cropBlock;
		if(cropBlock instanceof IGrowable){
			
			if(rand.nextInt(100) < 5 && (((IGrowable)cropBlock).func_149851_a(this.worldObj, xCoord, yCoord+1, zCoord, true)) ){
			
				//cropBlock.updateTick(worldObj, xCoord, yCoord+1, zCoord, rand);
				this.worldObj.setBlockMetadataWithNotify(xCoord, yCoord+1, zCoord, cropMeta+1, 2);
				tickCount = 0;
				System.out.println("Applied");
			
		}
		}
	}
}
