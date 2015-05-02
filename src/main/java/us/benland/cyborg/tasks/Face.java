package us.benland.cyborg.tasks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import us.benland.cyborg.CyborgTask;

public class Face extends CyborgTask {
	
	protected double pitch, yaw;
	
	public Face(double pitch, double yaw) {
		this.pitch = pitch;
		this.yaw = yaw;
	}

	@Override
	public State tick(EntityPlayer p, World w) {
		p.rotationPitch = (float)pitch;
		p.rotationYaw = (float)yaw;
		return State.SUCCESS;
	}

}
