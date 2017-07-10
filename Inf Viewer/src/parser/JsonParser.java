package parser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonParser<T> {

	private Class<T> clazz;

	public JsonParser(Class<T> clazz) {
		this.clazz = clazz;
	}

	public T readFromJsonFile(String path) {

		byte[] encoded = null;
		try {
			encoded = Files.readAllBytes(Paths.get(path));

			Gson gson = new GsonBuilder().create();
			return gson.fromJson(new String(encoded, StandardCharsets.UTF_8), clazz);
		} catch (IOException e) {
			// TODO Dialog exception
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * @return the clazz
	 */
	public Class<T> getClazz() {
		return clazz;
	}

	/**
	 * @param clazz
	 *            the clazz to set
	 */
	public void setClazz(Class<T> clazz) {
		this.clazz = clazz;
	}

}
