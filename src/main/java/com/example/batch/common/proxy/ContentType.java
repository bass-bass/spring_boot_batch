package com.example.batch.common.proxy;

import java.util.HashMap;
import java.util.Map;

public enum ContentType {
	JSON("application/json"),
	PDF("application/pdf"),
	XML("application/xml"),
	HTML("text/html"),
	TXT("text/plain");

	private final String name;

	ContentType(String name) {
		this.name = name;
	}

	private static final Map<String, ContentType> CONTENT_TYPE_BY_NAME;

	static {
		Map<String, ContentType> map = new HashMap<>();
		for (ContentType type : ContentType.values()) {
			map.put(type.name, type);
		}
		CONTENT_TYPE_BY_NAME = map;
	}

	public static ContentType get(String name) {
		return CONTENT_TYPE_BY_NAME.get(name);
	}
}
