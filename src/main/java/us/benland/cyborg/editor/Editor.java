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
