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
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import us.benland.cyborg.CyborgTask;

public class WalkTo extends CyborgTask {
	
	protected double planetol = 0.6;
	protected double losttol = 10.0;
	protected double speed = 0.25;
	protected PathEntity path = null;
	protected int targetX, targetY, targetZ;
	
	public WalkTo(int targetX, int targetY, int targetZ) {
		this.targetX = targetX;
		this.targetY = targetY;
		this.targetZ = targetZ;
	}

	@Override
	public State tick(EntityPlayer p, World w) {
		if (path == null) {
	    	double dx = targetX - p.posX;
	    	double dz = targetZ - p.posZ;
	    	double planedist = Math.sqrt(dx*dx+dz*dz);
	    	if (planedist > planetol) {
	    		path = w.getEntityPathToXYZ(p, targetX, targetY, targetZ, (float) (planedist*2.0), true /*open*/, false /*closed*/, true /*avoidwater*/, false /*canswim*/);
	    		return State.RUNNING;
	    	} else {
	    		return State.SUCCESS;
	    	}
    	} else {
    		int i = path.getCurrentPathIndex();
    		PathPoint pt = path.getPathPointFromIndex(i);
	    	double dx = pt.xCoord - p.posX + 0.6;
	    	double dy = pt.yCoord - p.posY + 1.62;
	    	double dz = pt.zCoord - p.posZ + 0.6;
    		double planedist = Math.sqrt(dx*dx+dz*dz);
    		double dist = Math.sqrt(dx*dx+dy*dy+dz*dz);
    		
    		if (dist > losttol) {
    			return State.FAILED;
    		}
    		
	    	if (planedist < planetol) {
	    		path.incrementPathIndex();
	    		if (path.isFinished()) {
	    			return State.SUCCESS;
	    		} else {
	    			return State.RUNNING;
	    		}
	    	} else {
	            if (dy < 0.1D && planedist < 0.1D) {
	            	//fall
	            } else {
	    			p.motionX = dx/planedist*speed;
		    		p.motionZ = dz/planedist*speed;
	            }
	            if (dy > 0.1D && planedist < 1.6D) {
	            	p.jump();
	            }
	            return State.RUNNING;
	    	}
    	}
	}

}
