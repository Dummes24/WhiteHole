package cz.ProjectWhitehole.tileentity;

import com.sun.org.apache.xml.internal.security.keys.storage.StorageResolver;
import com.sun.xml.internal.bind.v2.TODO;
import com.typesafe.config.ConfigException.Parse;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityControlBlock extends TileEntity implements IEnergyReceiver{
	//TODO GUI
	//TODO local adresses, Save and load to NBT
	//TODO method to check if SG is complete

	EnergyStorage storage = new EnergyStorage(262144);
	
	@Override
	public boolean canConnectEnergy(ForgeDirection from) {		
		return true;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate){		
		return storage.receiveEnergy(maxReceive, simulate);
	}

	@Override
	public int getEnergyStored(ForgeDirection from) {		
		return storage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {		
		return storage.getMaxEnergyStored();
	}
	
	@Override
	public void updateEntity(){
		//TODO Dialing
		//TODO Opening wormhole on both sides (needs load chunk)
		//TODO Teleporting entities (+adding close time)
		
	}
	
	/**
	 * Return true if Stargate at coord is complete = usable, Pass coordiantions of bottom middle block
	 * @param activated
	 * 				Decides if method checks for portal blocks inside stargate
	 * */
	private boolean isStargateComplete(boolean activated,World world, int xCoord , int yCoord , int zCoord){
		
		Block[][] offStargateBig = {
								{Blocks.obsidian,Blocks.redstone_lamp,Blocks.obsidian},
								{Blocks.redstone_lamp,Blocks.air,Blocks.air,Blocks.air,Blocks.redstone_lamp},
								{Blocks.obsidian,Blocks.air,Blocks.air,Blocks.air,Blocks.air,Blocks.air,Blocks.obsidian},
								{Blocks.redstone_lamp,Blocks.air,Blocks.air,Blocks.air,Blocks.air,Blocks.air,Blocks.redstone_lamp},
								{Blocks.obsidian,Blocks.air,Blocks.air,Blocks.air,Blocks.air,Blocks.air,Blocks.obsidian},
								{Blocks.redstone_lamp,Blocks.air,Blocks.air,Blocks.air,Blocks.redstone_lamp},
								{Blocks.obsidian,Blocks.redstone_lamp,Blocks.obsidian}
								};
		Block[][] offStargateHalf = {{Blocks.obsidian,Blocks.redstone_lamp,Blocks.obsidian},
									 {Blocks.redstone_lamp,Blocks.air,Blocks.air,Blocks.air,Blocks.redstone_lamp},
									 {Blocks.obsidian,Blocks.air,Blocks.air,Blocks.air,Blocks.air,Blocks.air,Blocks.obsidian},
									 {Blocks.redstone_lamp,Blocks.air,Blocks.air,Blocks.air,Blocks.air,Blocks.air,Blocks.redstone_lamp}};
		int gateDirection = 0; //Gate on X = 1 , Z = 2
		
		//Direction set / check
		if (world.getBlock(xCoord, yCoord, zCoord) == Blocks.redstone_lamp) {
			if (world.getBlock(xCoord - 1, yCoord, zCoord) == Blocks.obsidian && world.getBlock(xCoord+1,yCoord,zCoord)== Blocks.obsidian){
				gateDirection = 1;
			}
			else if (world.getBlock(xCoord, yCoord, zCoord -1) == Blocks.obsidian && world.getBlock(xCoord,yCoord,zCoord + 1)== Blocks.obsidian) {
				gateDirection = 2;
			}
			else {
				//Debug
				System.out.printf("Stargate is not valid at %d, %d, %d",xCoord,yCoord,zCoord);
				return false;
			}
		}
		else {
			//Debug
			System.out.printf("Stargate is not valid at %d, %d, %d",xCoord,yCoord,zCoord);
			return false;
		}
		
		//Check rest
		//TODO Change array for activated gate		 
		boolean checkResult = true;
		
		for (int i = 0; i < offStargateBig.length; i++) {
			//First block in row
			int x,y,z;
			if (gateDirection == 1) {
				x = xCoord - (int)(offStargateBig[i].length / 2) - 1;
				y = yCoord + 7 - i;
				z = zCoord;
			}
			else {
				x = xCoord;
				y = yCoord + 7 - i;
				z = zCoord - (int)(offStargateBig[i].length / 2) - 1;
			}
			for (int j = 0; j < offStargateBig[i].length * 2; j++) {
				if (gateDirection == 1) {
				  if (world.getBlock(x + j, y, z) != offStargateBig[i][j]) {
					  //Debug
					  System.out.printf("Stargate is not valid at %d, %d, %d",xCoord,yCoord,zCoord);					  
					  return false;
				  }
				}
			}
		}
		
		//Delete
		return false;
	}
	
	
	
}
