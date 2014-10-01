package com.infonova.education.pacman;

import javax.swing.*;
import java.net.URL;

public enum BackgroundType {
	FREE("images/free.png"),
	WALL("images/wall.png"),
	DOT("images/dot.png"),
	SUPERDOT("images/superdot.png"),
	HERO("images/hero.png"),
	ENEMY("images/enemy.png");
	
	
	private BackgroundType(String imgPath) {
		this.imgPath = imgPath;
	}
	
	private String imgPath;

    public ImageIcon getImageIcon() {
        URL url = ClassLoader.getSystemResource(imgPath);
        return new ImageIcon(url);
	}

	public static BackgroundType fromChar(char c) {
		switch(c) {
			case 'F':
				return FREE;
			case 'W':
				return WALL;
			case 'P':
				return DOT;
			case 'X':
				return SUPERDOT;
			case 'H':
				return HERO;
			case 'E':
				return ENEMY;
			default:
				throw new IllegalArgumentException("'"+c+"' is not a legal BackgroundType!");
		
		}
	}
}
