package com.codurance.infrastructure;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;

import java.io.FileReader;
import java.io.IOException;

public class JsonReader {

	public static String jsonArray(String jsonFile) {
		try {
			return JsonArray.readFrom(new FileReader(jsonFile)).toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static String jsonObject(String jsonFile) {
		try {
			return JsonObject.readFrom(new FileReader(jsonFile)).toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
