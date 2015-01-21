package cz.ProjectWhitehole.tileentity;

import java.util.Iterator;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import cz.ProjectWhitehole.Blocks.MedicalBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.AchievementList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityMedicalBlock extends TileEntity implements IEnergyReceiver{
	
	protected EnergyStorage storage = new EnergyStorage(2000);	
	int healTimer = 0;
	
	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		if (from == ForgeDirection.UP) {
			return false;
		}
		else {
			return true;
		}
	}	
	

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive,boolean simulate) {
		return storage.receiveEnergy(maxReceive, simulate);
	}

	@Override
	public int getEnergyStored(ForgeDirection from) {
		return storage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from){
		return storage.getMaxEnergyStored();
	}
	
	public void updateEntity() {		
		//TODO Check for energy, energy consumption		
		
			Iterator iterator = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class,AxisAlignedBB.getBoundingBox((double) this.xCoord,(double) this.yCoord,(double) this.zCoord,(double) (this.xCoord + 1)
																	,(double) (this.yCoord + 2),(double) (this.zCoord + 1))).iterator();
			
			if (iterator.hasNext())	{	//&& storage.getEnergyStored() >= 100)
				healTimer++;
				MedicalBlock.updateMedicalBlockState(true, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
			}
			else {
				MedicalBlock.updateMedicalBlockState(false, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
			}
			
			
			if (healTimer >= 10) {			
			while (iterator.hasNext())// && storage.getEnergyStored() >= 100) slow it down!
			{
				EntityPlayer entityplayer = (EntityPlayer) iterator.next();
				entityplayer.heal(1f);
				//entityplayer.addPotionEffect(new PotionEffect(Potion.regeneration.id,180,0, false)); //Just adds wibly hearts and doesn't end 
				//storage.modifyEnergyStored(-100);				
			}		
			healTimer = 0;
			}
	}	
	
	
}
