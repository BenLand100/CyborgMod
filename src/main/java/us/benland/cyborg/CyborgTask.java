package us.benland.cyborg;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public abstract class CyborgTask {
	
	public static enum State { RUNNING, FAILED, SUCCESS };
	
	public abstract State tick(EntityPlayer p, World w);
	
}
