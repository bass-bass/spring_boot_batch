package com.example.batch.common.mvc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class SampleStaticTest {
	static class Sample {
		static String greet(String name) {
			return "hello " + name;
		}
	}

	@Test
	public void test() {
		Assertions.assertEquals("hello test", Sample.greet("test"));

		try (MockedStatic<Sample> sample = Mockito.mockStatic(Sample.class)) {
			Mockito.when(Sample.greet("test")).thenReturn("hello world");
			Assertions.assertEquals("hello world", Sample.greet("test"));
		}
	}
}
