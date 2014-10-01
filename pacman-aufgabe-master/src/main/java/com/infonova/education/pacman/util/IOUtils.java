package com.infonova.education.pacman.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOUtils {

	public static String[] readLines(final String path) {

		try {
            InputStream stream = ClassLoader.getSystemResourceAsStream(path);

			InputStreamReader reader = new InputStreamReader(stream);
			BufferedReader bufferedReader = new BufferedReader(reader);
			
			final List<String>list = new ArrayList<String>();
			
			String line;
			while((line = bufferedReader.readLine()) != null) {
				list.add(line);
			}
			
			return list.toArray(new String[list.size()]);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
