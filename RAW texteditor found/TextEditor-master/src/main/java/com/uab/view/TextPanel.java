package com.uab.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.uab.controller.ControllerInterface;
import com.uab.listeners.Observer;
import com.uab.model.ArrowModel;
import com.uab.model.CharacterModel;
import com.uab.model.Glyph;
import com.uab.model.ImageModel;
import com.uab.model.Point;

public class TextPanel extends JPanel implements Observer, MouseListener {

	private static final long serialVersionUID = 1L;
	private Glyph glyph;
	private Point startPoistion;

	private ControllerInterface controller;
	private JFrame frame;
	private JMenuBar menubar;
	private JMenu file;
	private JMenu edit;
	private JMenu preferences;

	// File menu items
	private JMenuItem newFile;
	private JMenuItem openFile;
	private JMenuItem closeFile;
	private JMenuItem saveFile;

	// Edit menu items
	private JMenuItem undo;
	private JMenuItem redo;
	private JMenuItem cut;
	private JMenuItem copy;
	private JMenuItem paste;

	// Preferences menu items
//	private JMenuItem spellCheck;
	private JMenuItem border;
	private JMenuItem scroll;

	public TextPanel(Glyph g, ControllerInterface controller) {
		this.glyph = g;
		this.controller = controller;
		glyph.registerObservers((Observer) this);
		startPoistion = new Point(20, 50);

	}

	public void setGlyph(Glyph document) {
		this.glyph = document;
	}

	public Point getStartPoistion() {
		return startPoistion;
	}

	public void setStartPoistion(Point startPoistion) {
		this.startPoistion = startPoistion;
	}

	public void createView() {
		frame = new JFrame("Notepad --");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		creatControls();
		this.setFocusable(true);
		this.addKeyListener(new TypeListener());
		addMouseListener(this);
		frame.add(this);

		frame.setSize(new Dimension(500, 500));
		frame.setVisible(true);
	}

	// top menu bar options
	public void creatControls() {
		menubar = new JMenuBar();

		// File option and sub-obtions
		file = new JMenu("File");
		newFile = new JMenuItem("New");
		file.add(newFile);
		newFile.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				controller.newFile();

			}

		});

		openFile = new JMenuItem("Open");
		file.add(openFile);
		openFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.openFile(frame);
			}

		});
		file.addSeparator();

		saveFile = new JMenuItem("Save");
		file.add(saveFile);
		saveFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.saveFile(frame);

			}

		});
		closeFile = new JMenuItem("Close");
		file.add(closeFile);
		closeFile.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				controller.closeFile(frame);

			}

		});

		//Edit option and sub-options
		edit = new JMenu("Edit");
		undo = new JMenuItem("Undo");
		edit.add(undo);
		undo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				controller.undo();

			}

		});

		redo = new JMenuItem("Redo");
		edit.add(redo);
		redo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				controller.redo();

			}

		});

		edit.addSeparator();
		cut = new JMenuItem("Cut");
		edit.add(cut);
		cut.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				controller.cut();

			}

		});

		copy = new JMenuItem("Copy");
		edit.add(copy);
		copy.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				controller.copy();

			}

		});

		paste = new JMenuItem("Paste");
		edit.add(paste);
		paste.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				controller.paste();

			}

		});

		preferences = new JMenu("Preferences");

		border = new JCheckBoxMenuItem("Border");
		border.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (border.isSelected()) {
					controller.decorateWithBorder();
					if (scroll.isSelected()) {

						scroll.setEnabled(false);
					}
				} else {
					controller.removeBorder();
					if (!scroll.isEnabled()) {
						scroll.setEnabled(true);
					}
				}
			}
		});

		preferences.add(border);

		scroll = new JCheckBoxMenuItem("Scroll");
		scroll.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (scroll.isSelected()) {
					controller.decorateWithScrollBar();
					if (border.isSelected()) {
						border.setEnabled(false);
					}
				} else {
					controller.removeScrollBar();
					if (!border.isEnabled()) {
						border.setEnabled(true);
					}
				}
			}
		});

		preferences.add(scroll);
		preferences.addSeparator();

		//fancy underscore under F and E
		file.setMnemonic('F'); // Create shortcut
		edit.setMnemonic('E');

		menubar.add(file);
		menubar.add(edit);
		menubar.add(preferences);
		frame.setJMenuBar(menubar);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int width = frame.getWidth();
		int height = frame.getHeight();
		Point p = new Point(startPoistion.getX(), startPoistion.getY());

		glyph.draw(g, p, width, height);

	}

	public void update() {

		repaint();

	}

	private class TypeListener implements KeyListener {

		public void keyTyped(KeyEvent e) {

		}

		// WHEN A KEY IS PRESSED SENDS STUFF TO CONSOLE
		public void keyPressed(KeyEvent e) {

			// Writes the characters in the text editor
			if (!e.isControlDown() && !e.isAltDown()) {
				System.out.println(e.getKeyChar() + "   callled ");
				CharacterModel c = new CharacterModel(e.getKeyChar());
				System.out.println(glyph.getCursor().getGlyphIndex());
				controller.insert(c, glyph.getCursor().getGlyphIndex());
			}

		}

		public void keyReleased(KeyEvent e) {

		}

	}

	// MOUSE CLICK WILL GIVE LOCATION IN CONSOLE
	public void mouseClicked(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
