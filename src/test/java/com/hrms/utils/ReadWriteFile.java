package com.hrms.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ReadWriteFile {

	public static String readTextFromFile(String filePath) {
		String fileText = "";
		try {
			fileText = new String(Files.readAllBytes(Paths.get(filePath)));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return fileText;
	}
	
	public static void writeTextIntoFile(String filePath, String text) {
		try {
			Files.write(Paths.get(filePath), text.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
