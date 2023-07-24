package com.example.batch.common.mvc;

import java.util.HashMap;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class JobForm extends HashMap<String, String> {
	private String[] args;
	private String control;
	private String action;

	public JobForm(String[] args) {
		this.args = args;
		this.control = args[0];
		if (args.length < 2)
			throw new IllegalArgumentException("action is not set in args");
		this.action = args[1];

		int cnt = 0;
		for (int i = 0; i < args.length; i++) {
			String arg = args[i];
			if (arg.length() == 0) continue;

			if (arg.charAt(0) != '-') {
				put(Integer.toString(++cnt), arg);
				continue;
			}

			// key: -message → "message"
			String key = arg.substring(1).toLowerCase();

			// value: -message hello → "hello"
			String value = "";
			if (i + 1 < args.length) {
				if (args[i + 1].length() > 0 && args[i + 1].charAt(0) != '-') {
					value = args[i + 1];
					i++;
				}
			}

			put(key, value);
		}
	}
}
