package com.infonova.education.pacman;

import java.awt.*;

public class BackgroundElement extends GameObject {
	
	private BackgroundType type;

	public BackgroundType getType() {
		return type;
	}

	public void setType(BackgroundType type) {
		this.type = type;
	}
	
	public BackgroundElement(BackgroundType type, int x, int y) {
		super(x,y);
		this.type = type;
	}


	public Image getImage() {
		return type.getImageIcon().getImage();
	}


}
