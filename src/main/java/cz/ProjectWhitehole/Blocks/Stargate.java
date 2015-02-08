package cz.ProjectWhitehole.Blocks;

import java.util.Iterator;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import org.apache.commons.lang3.Validate;

import com.sun.xml.internal.bind.v2.runtime.Coordinator;

import cz.ProjectWhitehole.mod.Coordinations;
import cz.ProjectWhitehole.mod.ModBlocks;

public class Stargate {
	int x,y,z;
	
	/** Align with X = 0, Align with Z = 1*/
	int direction; 
	public int openTime = 0; //in Ticks
	World world;
	
	boolean isValid;
	boolean isActivated = false;
	
	Block[][] stargateState = {
			{ModBlocks.eccdurusiumStarGateBlock,ModBlocks.chevronBlockStarGateIdle,ModBlocks.eccdurusiumStarGateBlock},
			{ModBlocks.chevronBlockStarGateIdle,Blocks.air,Blocks.air,Blocks.air,ModBlocks.chevronBlockStarGateIdle},
			{ModBlocks.eccdurusiumStarGateBlock,Blocks.air,Blocks.air,Blocks.air,Blocks.air,Blocks.air,ModBlocks.eccdurusiumStarGateBlock},
			{ModBlocks.chevronBlockStarGateIdle,Blocks.air,Blocks.air,Blocks.air,Blocks.air,Blocks.air,ModBlocks.chevronBlockStarGateIdle},
			{ModBlocks.eccdurusiumStarGateBlock,Blocks.air,Blocks.air,Blocks.air,Blocks.air,Blocks.air,ModBlocks.eccdurusiumStarGateBlock},
			{ModBlocks.chevronBlockStarGateIdle,Blocks.air,Blocks.air,Blocks.air,ModBlocks.chevronBlockStarGateIdle},
			{ModBlocks.eccdurusiumStarGateBlock,ModBlocks.chevronBlockStarGateIdle,ModBlocks.eccdurusiumStarGateBlock}
			};	
	
	int[][] chevronCoords = new int[8][3];	
	Coordinations[] insideCoords = new Coordinations[21];
	
	private void negActivation(){
		if (!isActivated) {
			for (Block[] blocks : stargateState) {
				for (Block block : blocks) {
					if (block == Blocks.air) {
						block = Blocks.portal;
					}
				}
			}
			isActivated = true;
		}
		else {
			for (Block[] blocks : stargateState) {
				for (Block block : blocks) {
					if (block == Blocks.portal) {
						block = Blocks.air;
					}
				}
			}
		}
		isActivated = false;
	}
	
	public Stargate(World world, int x , int y , int z,int direction, boolean isActivated){
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
		this.direction = direction;
		this.isActivated = isActivated;
		validate();
		//setChevronCoords();
	}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getZ(){
		return z;
	}
	public boolean getIsValid(){
		return isValid;
	}
	public boolean getIsActivated(){
		return isActivated;
	}
	
	public void validate(){
		isValid = validation();
	}	
	
	private boolean validation(){
		int countChevron = 0;
		int countAir = 0;
		for (int i = 0; i < stargateState.length; i++) {
			//First block in row
			int x,y,z;
			
			//Coords of chevron blocks for dialing
			
			
			if (direction == 0) {
				x = this.x - (int)(stargateState[i].length / 2);
				y = this.y + 6 - i;
				z = this.z;
			}
			else {
				x = this.x;
				y = this.y + 6 - i;
				z = this.z - (int)(stargateState[i].length / 2);
			}
			for (int j = 0; j < stargateState[i].length; j++) {
				if (direction == 0) {
				  if (world.getBlock(x + j, y, z) != stargateState[i][j]) {
					  return false;
					 }
				  else if (stargateState[i][j] == Blocks.air) {
					  insideCoords[countAir] = new Coordinations(x + j, y, z);
					  countAir++;
				  }
				  else if (stargateState[i][j] == ModBlocks.chevronBlockStarGateIdle) {
					  chevronCoords[countChevron][0] = x + j;
					  chevronCoords[countChevron][1] = y;
					  chevronCoords[countChevron][2] = z;
					  countChevron++;
				}
				}
				else {
					if (world.getBlock(x , y, z + j) != stargateState[i][j]) {
						  return false;
					  }
					else if (stargateState[i][j] == Blocks.air) {
						insideCoords[countAir] = new Coordinations(x, y, z + j);
						countAir++;  
					}
					else if (stargateState[i][j] == ModBlocks.chevronBlockStarGateIdle) {
						  chevronCoords[countChevron][0] = x;
						  chevronCoords[countChevron][1] = y;
						  chevronCoords[countChevron][2] = z + j;
						  countChevron++;
					}
				}
			}
		}		
		return true;
	}
	
	//Debug
	private boolean simpleValidation(){
	if ((world.getBlock(x-1, y, z) == ModBlocks.eccdurusiumStarGateBlock && world.getBlock(x+1, y, z) == ModBlocks.eccdurusiumStarGateBlock)||(world.getBlock(x, y, z-1) == ModBlocks.eccdurusiumStarGateBlock && world.getBlock(x, y, z + 1) == ModBlocks.eccdurusiumStarGateBlock)) {
		return true;
	}
	else {
		return false;
	}
	}

	public Iterator<Entity> entitesInStargate(){
		AxisAlignedBB box = boundingBoxOfStarGate();		
		return world.getEntitiesWithinAABB(Entity.class, box).iterator();	
	}
	
	private AxisAlignedBB boundingBoxOfStarGate(){
		if (direction == 0) {			
			return AxisAlignedBB.getBoundingBox((double)(x - 2), (double)y + 1, (double)z, (double)(x + 3), (double)(y + 6), (double)(z + 1));
		}
		else {			
			return AxisAlignedBB.getBoundingBox((double)(x), (double)y+1, (double)(z - 2), (double)(x + 1), (double)(y + 6), (double)(z + 3));
		}		
	}
	
	public boolean teleportEntity(Entity entity, World world, int xCoord , int yCoord , int zCoord){		
		//Debug
		System.out.printf("Teleported %s to %d %d %d" + System.getProperty("line.separator"),entity.getCommandSenderName(),xCoord,yCoord,zCoord);
		
		entity.setPosition((double)xCoord, (double)yCoord, (double)zCoord);
		openTime += 0;
		return true;
	}	
	
	private void setChevronCoords(){
		if (direction == 0) {
			chevronCoords[0][0] = x;
			chevronCoords[0][1] = y + 6;
			chevronCoords[0][2] = z;
			chevronCoords[1][0] = x + 2;
			chevronCoords[1][1] = y + 5;
			chevronCoords[1][2] = z;
			chevronCoords[2][0] = x + 3;
			chevronCoords[2][1] = y + 3;
			chevronCoords[2][2] = z;
			chevronCoords[3][0] = x + 2;
			chevronCoords[3][1] = y + 1;
			chevronCoords[3][2] = z;
			chevronCoords[4][0] = x;
			chevronCoords[4][1] = y;
			chevronCoords[4][2] = z;
			chevronCoords[5][0] = x - 2;
			chevronCoords[5][1] = y + 1;
			chevronCoords[5][2] = z;
			chevronCoords[6][0] = x - 3;
			chevronCoords[6][1] = y + 3;
			chevronCoords[6][2] = z;
			chevronCoords[7][0] = x - 2;
			chevronCoords[7][1] = y + 5;
			chevronCoords[7][2] = z;
		}
		else {
			chevronCoords[0][0] = x;
			chevronCoords[0][1] = y + 6;
			chevronCoords[0][2] = z;
			chevronCoords[1][0] = x;
			chevronCoords[1][1] = y + 5;
			chevronCoords[1][2] = z + 2;
			chevronCoords[2][0] = x ;
			chevronCoords[2][1] = y + 3;
			chevronCoords[2][2] = z + 3;
			chevronCoords[3][0] = x;
			chevronCoords[3][1] = y + 1;
			chevronCoords[3][2] = z + 2;
			chevronCoords[4][0] = x;
			chevronCoords[4][1] = y;
			chevronCoords[4][2] = z;
			chevronCoords[5][0] = x;
			chevronCoords[5][1] = y + 1;
			chevronCoords[5][2] = z - 2;
			chevronCoords[6][0] = x;
			chevronCoords[6][1] = y + 3;
			chevronCoords[6][2] = z - 3;
			chevronCoords[7][0] = x;
			chevronCoords[7][1] = y + 5;
			chevronCoords[7][2] = z - 2;
		}
	}

	
	public int dial(int stage){		
		world.setBlock(chevronCoords[stage][0],chevronCoords[stage][1],chevronCoords[stage][2],ModBlocks.chevronBlockStarGateActive);
		if (stage == 7) {
			activateGate();
			return 0;
		}
		else{
		return ++stage;
		}
	}

	private void activateGate() {
		
		this.isActivated = true;
		openTime = 200;
		
		for (Coordinations coord : insideCoords) {
			world.setBlock(coord.getX(), coord.getY(), coord.getZ(), ModBlocks.portalBlockStarGate);
		}
		
	}

	public void closeGate() {		
		this.isActivated = false;
		openTime = 0;
		for (int[] chevronCoord : chevronCoords) {
			world.setBlock(chevronCoord[0], chevronCoord[1], chevronCoord[2], ModBlocks.chevronBlockStarGateIdle);
		}
		for (Coordinations coord : insideCoords) {
			world.setBlock(coord.getX(), coord.getY(), coord.getZ(), Blocks.air);
		}
		
	}

}

