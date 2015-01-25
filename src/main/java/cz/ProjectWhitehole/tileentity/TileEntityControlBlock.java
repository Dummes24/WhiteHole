package cz.ProjectWhitehole.tileentity;


import java.util.Iterator;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityControlBlock extends TileEntity implements IEnergyReceiver{
	//TODO GUI
	//TODO local adresses, Save and load to NBT

	EnergyStorage storage = new EnergyStorage(262144);	
		
	int xStargate = xCoord - 2;
	int yStargate = yCoord;
	int zStargate = zCoord;
	
	int gateDirection = 0; //Gate on X = 1 , Z = 2
	
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
		
		Iterator<Entity> entitiesInside = entitesInStargate();
		while (entitiesInside.hasNext()) {
			Entity toTeleport = entitiesInside.next();
			teleportEntity(toTeleport, this.worldObj, (int)toTeleport.posX + 2, (int)toTeleport.posY + 2 , (int)toTeleport.posZ + 2);
		}
		
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
		
		//Change for activated SG
		if(activated) {
			for (Block[] row : offStargateBig){
				for (Block block : row) {
					if (block == Blocks.air) {
						block = Blocks.portal;
					}
				}
			}
		}
		
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
				else {
					if (world.getBlock(x , y, z + j) != offStargateBig[i][j]) {
						  //Debug
						  System.out.printf("Stargate is not valid at %d, %d, %d",xCoord,yCoord,zCoord);					  
						  return false;
					  }
				}
			}
		}		
		return true;
	}
	
	private Iterator<Entity> entitesInStargate(){
		
		return this.worldObj.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getBoundingBox((double)xCoord, (double)yCoord, (double)zCoord, (double)xCoord+1, (double)yCoord + 2, (double)zCoord + 1)).iterator();
	}
	
	private AxisAlignedBB boundingBoxOfStarGate(){
		
		if (gateDirection == 1) {
			return AxisAlignedBB.getBoundingBox((double)(xStargate - 2), (double)yStargate, (double)zStargate, (double)(xStargate + 2), (double)(yStargate + 5), (double)(zStargate + 1));
		}
		else {
			return AxisAlignedBB.getBoundingBox((double)(xStargate), (double)yStargate, (double)(zStargate - 2), (double)(xStargate + 1), (double)(yStargate + 5), (double)(zStargate + 2));
		}
	}
	
	//TODO Implement teleporting
	/** 	 
	 * @param entity Entity to teleport
	 * @param world Destination world
	 * @param xCoord Destination x coordinates 
	 * @param yCoord Destination y coordinates
	 * @param zCoord Destination z coordinates
	 * @return true if entity was teleported
	 */
	private boolean teleportEntity(Entity entity, World world, int xCoord , int yCoord , int zCoord){
		
		//entity.setLocationAndAngles(xCoord, yCoord, zCoord, entity.rotationYaw, entity.rotationPitch);
		System.out.printf("Teleported to %d %d %d",xCoord,yCoord,zCoord);
		entity.setPosition((double)xCoord, (double)yCoord, (double)zCoord);
		//entity.moveEntity((double)xCoord, (double)yCoord, (double)zCoord);
		
		return true;
	}
	
	private directionResult getDirectionOfStarGate(World world, int x, int y, int z){
		if (world.getBlock(x, y, z) == Blocks.redstone_lamp) {
			if (world.getBlock(x - 1, y, z) == Blocks.obsidian && world.getBlock(x+1,y,z)== Blocks.obsidian){
				return new directionResult(true, 1);
			}
			else if (world.getBlock(x, y, z -1) == Blocks.obsidian && world.getBlock(x,y,z + 1)== Blocks.obsidian) {
				return new directionResult(true, 2);
			}
			else {
				//Debug
				System.out.printf("Stargate is not valid at %d, %d, %d",xCoord,yCoord,zCoord);
				return new directionResult(false, 0);
			}
		}
		else {
			//Debug
			System.out.printf("Stargate is not valid at %d, %d, %d",xCoord,yCoord,zCoord);
			return new directionResult(false, 0);
		}
	}
	
	//Class for return of getDirection
	private class directionResult{
		boolean isStarGateValid;
		int direction;
		public directionResult(boolean isStargateValid,int direction)
		{
			this.isStarGateValid = isStargateValid;
			this.direction = direction;
		}
		public boolean getValidation(){
			return this.isStarGateValid;			
		}
		
		public int getDirection(){
			return this.direction;
		}
	}
	
}
