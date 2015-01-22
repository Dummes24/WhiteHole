package cz.ProjectWhitehole.tileentity;

import com.sun.org.apache.xml.internal.security.keys.storage.StorageResolver;
import com.sun.xml.internal.bind.v2.TODO;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityControlBlock extends TileEntity implements IEnergyReceiver{
	//TODO GUI
	//TODO local adresses, Save, load to NBT
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
		//TODO Opening wormhole on both sides (needs )
		//TODO Teleporting entities (+adding close time)
	}
	
}
