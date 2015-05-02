/*
    This file is part of CyborgMod.

    CyborgMod is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    CyborgMod is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with CyborgMod.  If not, see <http://www.gnu.org/licenses/>.
*/

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
