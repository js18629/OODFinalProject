package com.uab.controller.commands;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.uab.model.Glyph;

public class QuitCommand implements Command {

	private JFrame parent;
	private Glyph document;
	private int needToSave;

	public QuitCommand(Glyph document, JFrame parent) {
		this.document = document;
		this.parent = parent;
	}

	public void execute() {
		createCloseDialogBox(parent);
		if (needToSave == JOptionPane.YES_OPTION) {
			SaveCommand saveCmd = new SaveCommand(this.document, parent);
			saveCmd.execute();
			System.exit(0);
		} else if (needToSave == JOptionPane.NO_OPTION) {
			System.exit(0);
		} else {
			System.out.println("operation cancelled");
		}

	}

	public void undo() {

	}

	public boolean isReversible() {
		return false;
	}

	public void createCloseDialogBox(JFrame parent) {
		this.needToSave = JOptionPane.showConfirmDialog(parent,
				"Do you want to save the file?", "ALERT",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

	}
}
