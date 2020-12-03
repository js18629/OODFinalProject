package com.uab.model;

import java.awt.Graphics;
import java.awt.Image;


//For image importing
public class ImageModel extends Glyph {

	private static final long serialVersionUID = 1L;
	private String fileName;
	private transient Image image;
	private Rect bounds;
	private boolean hasCursor;
	private boolean before;

	public ImageModel() {

	}

	public ImageModel(Image image, String fileName) {
		super();
		this.image = image;
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
		notifyObservers();
	}

	@Override
	public int getWidth() {
		return image.getWidth(null);
	}

	@Override
	public int getHeigth() {
		return image.getHeight(null);
	}

	@Override
	public void draw(Graphics g, Point position, int width, int height) {
		System.out.println("Importing Image");
		g.drawImage(image, position.getX(), position.getY(), null);
		bounds = new Rect(position.getX(), position.getY() - 20,
				image.getWidth(null), image.getHeight(null));
		position.incrementXBy(getWidth());
		if (this.hasCursor) {
			if (before)
				g.fillRect(bounds.getLeft() - 3, bounds.getBottom(), 2,
						bounds.getHeight());
			else
				g.fillRect(bounds.getLeft() + bounds.getWidth() - 3,
						bounds.getBottom(), 2, bounds.getHeight());
		}
	}

	@Override
	public Rect getBounds() {
		return this.bounds;
	}

	@Override
	public void setBounds(Rect rectangle) {
		this.bounds = rectangle;
	}

	public void activateHasCursor(boolean before) {
		this.hasCursor = true;
		this.before = before;
	}

	public void deActivateHasCursor() {
		this.hasCursor = false;
	}

}
