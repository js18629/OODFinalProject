package com.uab.model;

import java.util.List;

public class Caret {
	private Composition document;
	private int rowIndex;
	private int columnIndex;
	private int glyphIndex;

	public Caret() {
		this.rowIndex = 0;
		this.columnIndex = 0;
		this.glyphIndex = 0;
	}

	public Caret(Composition document, int rowIndex, int columnIndex, int glyphIndex) {
		this.document = document;
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
		this.glyphIndex = glyphIndex;

	}

	public void setGlyphIndex(int glyphIndex) {
		this.glyphIndex = glyphIndex;
	}

	public Composition getDocument() {
		return document;
	}

	public void setDocument(Composition document) {
		this.document = document;
	}

	public int getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}

	public int getColumnIndex() {
		return columnIndex;
	}

	public void setColumnIndex(int columnIndex) {
		this.columnIndex = columnIndex;
	}

	public void moveLeft() {

	}

	public void moveRight() {

	}

	public void incrementGlyphIndex() {

		if (this.glyphIndex < this.document.getComponents().size()) {

			this.glyphIndex++;
		}

	}

	public int getGlyphIndex() {
		return glyphIndex;
	}

	public void decrementGlyphIndex() {
		if (this.glyphIndex > 0) {
			this.glyphIndex--;

		}

	}

	public void updateCursor() {
		deActivateCursor();
		if (this.glyphIndex == 0) {

			if (this.document.getComponents().size() > 0) {
				Glyph lastGlyph = this.document.getComponents().get(this.glyphIndex);

				lastGlyph.activateHasCursor(true);
			}
			if (this.glyphIndex > 0) {
				Glyph lastGlyph = this.document.getComponents().get(this.glyphIndex - 1);
				if (lastGlyph != null) {
					lastGlyph.activateHasCursor(false);
				}
			}
		}
	}

	public void deActivateCursor() {
		List<Glyph> components = this.document.getComponents();
		for (Glyph g : components) {
			g.deActivateHasCursor();
		}
	}
}
