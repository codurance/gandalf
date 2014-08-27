package com.codurance.infrastructure;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;

import java.io.FileReader;

import static com.codurance.infrastructure.Throwables.executeQuery;

public class JsonReader {

	public static String jsonArrayAsString(String jsonFile) {
		return jsonArray(jsonFile).toString();
	}

	public static JsonArray jsonArray(String jsonFile) {
		return executeQuery(() -> JsonArray.readFrom(new FileReader(jsonFile)));
	}

	public static String jsonObject(String jsonFile) {
		return executeQuery(() -> JsonObject.readFrom(new FileReader(jsonFile)).toString());
	}
}
