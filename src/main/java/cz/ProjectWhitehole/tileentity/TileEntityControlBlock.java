package cz.ProjectWhitehole.tileentity;


import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.io.filefilter.FalseFileFilter;
import org.omg.CORBA.Environment;

import com.sun.org.apache.xml.internal.dtm.ref.EmptyIterator;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import akka.util.Collections;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyReceiver;
import cz.ProjectWhitehole.Blocks.Stargate;
import cz.ProjectWhitehole.mod.ModBlocks;
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
		
	Stargate assiggnedStargate;
	int dialStage,dialTime= 0;	
	
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
		if (assiggnedStargate == null) {
			assiggnedStargate = assignStarGate();
		}
		else {
			
			//Dial
			if (worldObj.getBlock(xCoord, yCoord - 1, zCoord) == Blocks.redstone_block && dialStage == 0) { //Noobish activation
				worldObj.setBlock(xCoord, yCoord -1,  zCoord, Blocks.air);
				dialStage = assiggnedStargate.dial(dialStage);
			}
			if (dialStage != 0) {
				dialTime++;
			}
			if (dialStage != 0 && dialTime >= 20) {
				dialStage = assiggnedStargate.dial(dialStage);
				dialTime = 0;
			}
			
			//Opened gate
			if (assiggnedStargate.getIsActivated()) {
				if (assiggnedStargate.openTime <= 0) {
					assiggnedStargate.closeGate();
				}
				else{
					assiggnedStargate.openTime--;
				}
				//Teleport
				Iterator<Entity> entities = assiggnedStargate.entitesInStargate();
				while (entities.hasNext()) {
					//TODO Replace fix coords with GUI selection
					assiggnedStargate.teleportEntity(entities.next(), worldObj,assiggnedStargate.getX() + 10,assiggnedStargate.getY() + 2,assiggnedStargate.getZ() + 10);
				}
			}
		}		
			
		//TODO Opening wormhole on both sides (needs load chunk)					
		
	}
	
	private Stargate assignStarGate()
	{
		//Find all possible stargates around control Block
		ArrayList<Stargate> possibleSG =  new ArrayList<Stargate>();
		
		if (worldObj.getBlock(xCoord + 2, yCoord, zCoord) == ModBlocks.chevronBlockStarGateIdle) {
			possibleSG.add(new Stargate(worldObj, xCoord + 2, yCoord, zCoord, 0,false));
		}
		else if (worldObj.getBlock(xCoord - 2, yCoord, zCoord) == ModBlocks.chevronBlockStarGateIdle) {
			possibleSG.add(new Stargate(worldObj, xCoord - 2, yCoord, zCoord, 0,false));
		}
		else if (worldObj.getBlock(xCoord, yCoord, zCoord + 2) == ModBlocks.chevronBlockStarGateIdle) {
			possibleSG.add(new Stargate(worldObj, xCoord, yCoord, zCoord + 2, 1,false));
		}
		else if (worldObj.getBlock(xCoord, yCoord, zCoord - 2) == ModBlocks.chevronBlockStarGateIdle) {
			possibleSG.add(new Stargate(worldObj, xCoord, yCoord, zCoord - 2, 1,false));
		}
		else {
			return null;
		}
		
		//Return first valid
		if (!possibleSG.isEmpty()) {
			for (Stargate stargate : possibleSG) {
				stargate.validate();
				if (stargate.getIsValid()) {
					
					//Debug
					System.out.printf("Validated SG at %d %d %d" + System.getProperty("line.separator"), stargate.getX(),stargate.getY(), stargate.getZ());
					
					return stargate;
				}
			}			
		}
		else {
			return null;
		}
		return null;
	}
	
}
