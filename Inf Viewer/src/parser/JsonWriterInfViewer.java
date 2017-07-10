package parser;

import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

public class JsonWriterInfViewer {
	public static <T> void writeToJsonFile(String path, T model) {
		try {
			// write converted json data to location from path variable
			FileWriter writer = new FileWriter(path);
			writer.write(new Gson().toJson(model));
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
