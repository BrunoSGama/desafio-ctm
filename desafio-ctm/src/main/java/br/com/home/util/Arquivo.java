package br.com.home.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

public class Arquivo {

	private Arquivo() {}

	public static BufferedReader getBufferedReaderForResourceFile(String resourceFile, Object context) {
		InputStream inputStream = context.getClass().getResourceAsStream(resourceFile);
		return new BufferedReader(new InputStreamReader(inputStream));
	}

	public static BufferedReader getBufferedReaderForResourceFile(File file) throws FileNotFoundException {
		InputStream is = new FileInputStream(file);
		return new BufferedReader(new InputStreamReader(is));
	}

	public static boolean contentEquals(String resourceFile, String string, Object context) throws IOException {
		BufferedReader fileReader = getBufferedReaderForResourceFile(resourceFile, context);
		BufferedReader stringReader = new BufferedReader(new StringReader(string));
		while (true) {
			String fileLine = getNextLine(fileReader);
			String stringLine = getNextLine(stringReader);
			if (fileLine == null && stringLine == null) {
				break;
			}

			if (fileLine == null || stringLine == null) {
				return false;
			}

			if (!fileLine.equals(stringLine)) {
				return false;
			}
		}
		return true;
	}

	private static String getNextLine(BufferedReader reader) throws IOException {
		String line = "";
		while ((line = reader.readLine()) != null) {
			if (line.trim().length() != 0) {
				return line;
			}
		}
		return null;
	}

}
