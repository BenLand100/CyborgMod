package us.benland.cyborg;

import java.io.IOException;
import java.io.OutputStream;

import net.minecraft.util.ChatComponentText;

public class ChatOutputStream extends OutputStream {

	protected StringBuilder buf = new StringBuilder();
	protected CyborgMod mod;
	
	public ChatOutputStream(CyborgMod mod) {
		this.mod = mod;
	}
	
	@Override
	public void write(int b) throws IOException {
		if (b == '\n') {
			if (mod.p != null) mod.p.addChatMessage(new ChatComponentText(buf.toString()));
			buf.setLength(0);
		} else {
			buf.append((char)b);
		}
	}

}
