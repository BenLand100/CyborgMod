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
