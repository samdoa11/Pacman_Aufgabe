package com.infonova.education.pacman.support;

import com.infonova.education.pacman.BackgroundElement;
import com.infonova.education.pacman.BackgroundType;
import com.infonova.education.pacman.Hero;
import com.infonova.education.pacman.Level;
import com.infonova.education.pacman.util.IOUtils;


public class LevelFactory {
	
	private static final int HERO_START_X = 0;
	private static final int HERO_START_Y = 0;
	
	private static int maxX;
	private static int maxY;
	
	public static Level createLevel(String fileName) {
		
		BackgroundType[][] levelMap = loadLevel(fileName);
		
		BackgroundType type;
		BackgroundElement bg;
		Level level = new Level(maxX, maxY);
		
		for (int x=0; x<maxX; x++) {
			for (int y=0; y < maxY; y++) {
				type = levelMap[y][x];
				bg = createBackgroundElement(type, x, y);
				level.addBg(bg);
			}
		}

		Hero hero = new Hero(HERO_START_X, HERO_START_Y);
		level.setHero(hero);
		
		return level;
	}

	private static BackgroundElement createBackgroundElement(BackgroundType type, int x, int y) {
		return new BackgroundElement(type, x, y);
	}
	
	static BackgroundType[][]loadLevel(final String level) {
		final String[]lines = IOUtils.readLines("level/" + level);
		
		final int columns = lines[0].length();
		
		final BackgroundType[][]array  = new BackgroundType[lines.length][columns];
		
		for (int i = 0; i < lines.length; i++) {
			String line = lines[i];
			for (int j = 0; j < line.length(); j++) {
				array[i][j] = BackgroundType.fromChar(line.charAt(j));
			}
		}
				
		LevelFactory.maxX = columns;
		LevelFactory.maxY = lines.length;
				
		return array;
	}
}
