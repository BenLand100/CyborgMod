/*
    This file is part of CyborgMod.

    CyborgMod is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Foobar is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with CyborgMod.  If not, see <http://www.gnu.org/licenses/>.
*/

package us.benland.cyborg;

import us.benland.cyborg.tasks.WalkTo;

public class Cyborg {
	
	protected CyborgMod mod;
	
	public Cyborg(CyborgMod mod) {
		this.mod = mod;
	}
    
    public boolean walkTo(int x, int y, int z) {
    	return mod.performTask(new WalkTo(x,y,z)) == CyborgTask.State.SUCCESS;
    }
    
    public int heightAt(int x, int z) {
    	return mod.w.getHeightValue(x, z);
    }
    
}
