package com.example.batch.common.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class OptionHandler {

	public static void set(Object argsBeanClass, Map<String, String> form) throws IllegalArgumentException {
		Class<?> classObject = argsBeanClass.getClass();
		List<Field> fields = getAllFields(new ArrayList<>(), classObject);

		for (Field field : fields) {
			int mod = field.getModifiers();
			if (Modifier.isStatic(mod)
					&& Modifier.isFinal(mod)) {
				continue;
			}

			field.setAccessible(true);
			Class<?> types = field.getType();
			Annotation[] annotations = field.getAnnotations();
			for (Annotation annotation : annotations) {
				if (annotation instanceof Option) {
					String attributeName = ((Option) annotation).value();
					String formValue = form.get(attributeName);

					if (!form.containsKey(attributeName) || StringUtils.isEmpty(formValue)) continue;

					set0(argsBeanClass, field, formValue, types);
				}
			}
		}

	}

	private static List<Field> getAllFields(List<Field> fields, Class<?> type) {
		fields.addAll(Arrays.asList(type.getDeclaredFields()));

		if (type.getSuperclass() != null) {
			getAllFields(fields, type.getSuperclass());
		}

		return fields;
	}

	private static void set0(Object argsBeanClass, Field field, String formValue, Class<?> types) throws IllegalArgumentException {
		try {
			if (types == String.class) {
				field.set(argsBeanClass, formValue);
			} else if (types == Integer.class || types == int.class) {
				field.set(argsBeanClass, Integer.parseInt(formValue));
			} else if (types == Boolean.class || types == boolean.class) {
				if ("1".equals(formValue))
					field.set(argsBeanClass, true);
				else
					field.set(argsBeanClass, Boolean.parseBoolean(formValue));
			} else if (types == String[].class) {
				field.set(argsBeanClass, formValue.split(","));
			} else if (types == int[].class) {
				String[] values = formValue.split(",");
				int[] parsedValues = new int[values.length];

				for (int i = 0; i < values.length; i++) {
					parsedValues[i] = Integer.parseInt(values[i]);
				}

				field.set(argsBeanClass, parsedValues);
			}
		} catch (NumberFormatException | IllegalAccessException e) {
			throw new IllegalArgumentException(e);
		}
	}

}