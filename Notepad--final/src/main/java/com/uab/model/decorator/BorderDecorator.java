package com.uab.model.decorator;

import java.awt.Color;
import java.awt.Graphics;

import com.uab.model.Glyph;
import com.uab.model.Point;

public class BorderDecorator extends Decorator {
	private int width = 1;
	private int borderframeWidth;
	private int borderframeHeight;
	private int borderYLocation = 10;

	public BorderDecorator(Glyph glyph, int width) {
		this.component = glyph;
		this.width = width;

	}

	public int getFrameWidth() {
		return borderframeWidth;
	}

	public void setFrameWidth(int frameWidth) {
		this.borderframeWidth = frameWidth;
	}

	public int getFrameHeight() {
		return borderframeHeight;
	}

	public void setFrameHeight(int frameHeight) {
		this.borderframeHeight = frameHeight;
	}

	private void drawBorder(Graphics g, int width) {
		g.setColor(Color.BLACK);
		g.drawRect(8, borderYLocation, borderframeWidth + 1, borderframeHeight + 5);

	}

	public void draw(Graphics g, Point position, int width, int height) {
		int frameWidth = width - 10;
		this.setFrameHeight(height);
		this.setFrameWidth(frameWidth);
		borderYLocation = position.getY() - 42;
		drawBorder(g, width);
		this.component.draw(g, position, frameWidth, height);

	}

}
