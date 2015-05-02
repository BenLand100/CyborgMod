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
