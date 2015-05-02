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

package us.benland.cyborg.editor;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import us.benland.cyborg.CyborgMod;

public class Editor extends JFrame implements ActionListener {
	
	protected CyborgMod mod;
	
	protected JTextArea script;
	protected JButton run;
	protected JButton stop;
	
	public Editor(CyborgMod mod) {
		
		this.mod = mod;
		
		script = new JTextArea();
		script.setFont(new Font("monospaced", Font.PLAIN, 12));
		run = new JButton("Run Script");
		stop = new JButton("Stop Script");

		run.addActionListener(this);
		stop.addActionListener(this);
		
		setLayout(new BorderLayout());
		add(script,BorderLayout.CENTER);
		JPanel buttons = new JPanel(new GridLayout());
		buttons.add(run);
		buttons.add(stop);
		add(buttons,BorderLayout.SOUTH);
		this.setSize(400, 300);
	}

	@Override
	public void actionPerformed(ActionEvent action) {
		if (action.getSource() == run) {
			mod.runScript(script.getText());
		} else if (action.getSource() == stop) {
			mod.stopScript();
		}
		
	}

}
