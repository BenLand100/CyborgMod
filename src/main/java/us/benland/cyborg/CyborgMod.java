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

package us.benland.cyborg;

import org.lwjgl.input.Keyboard;
import org.python.util.PythonInterpreter;

import us.benland.cyborg.editor.Editor;
import us.benland.cyborg.tasks.WalkTo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.event.world.WorldEvent;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

@Mod(modid = CyborgMod.MODID, version = CyborgMod.VERSION)
public class CyborgMod {
	
    public static final String MODID = "CyborgMod";
    public static final String VERSION = "experimental";
    
    //Public so the rest of the mod has easy access
    public Minecraft mc;
    public World w;
    public EntityPlayer p;
    
    protected class ScriptThread extends Thread {
    	
    	protected String script;
    	
    	public ScriptThread(String script) {
    		System.out.println("Script follows:\n"+script);
    		this.script = script;
    	}
    	
    	public void run() {
    		interp.exec(script);
    	}
    	
    };
    
    protected KeyBinding cyborgKey;
    protected Editor editor;
    protected PythonInterpreter interp;
    protected ScriptThread scriptThread = null;
    
    protected CyborgTask task = null; 
    protected CyborgTask.State lastState = CyborgTask.State.SUCCESS;
    
    @SubscribeEvent
    public void playerTick(TickEvent.PlayerTickEvent event) {
    	p = event.player;
    	if (task != null) {
    		lastState = task.tick(p, w);
    		if (lastState != CyborgTask.State.RUNNING) {
    			task = null;
    		}
    	}
    }
    
    public void runScript(String script) {
    	if (scriptThread != null) scriptThread.stop();
    	scriptThread = new ScriptThread(script);
    	scriptThread.start();
    }
    
    public void stopScript() {
    	if (scriptThread != null) scriptThread.stop();
    	scriptThread = null;
    	task = null;
    }
    
    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
		if (cyborgKey.isPressed()) {
			editor.setVisible(true);
		}
    }
    
    public synchronized CyborgTask.State performTask(CyborgTask task) {
    	this.task = task;
    	while (this.task != null) {
    		try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
			}
    	} 
    	return lastState;
    }
    
    boolean running = false;
    
	@SubscribeEvent
    public void loadWorld(WorldEvent.Load event) {
    	w = event.world;
    }
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
    	mc = Minecraft.getMinecraft();
    	MinecraftForge.EVENT_BUS.register(this);
    	FMLCommonHandler.instance().bus().register(this);
    	interp = new PythonInterpreter();
    	interp.setOut(new ChatOutputStream(this));
    	interp.set("cyborg", new Cyborg(this));
    	cyborgKey = new KeyBinding("key.cyborg", Keyboard.KEY_C, "key.categories.cyborgmod");
        ClientRegistry.registerKeyBinding(cyborgKey);
        editor = new Editor(this);
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	
    }
    
}
