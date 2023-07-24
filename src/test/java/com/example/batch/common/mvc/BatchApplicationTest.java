package com.example.batch.common.mvc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.example.batch.common.util.ClassPath;

class BatchApplicationTest {

	@Test
	void test() {
		String packageName = "com.example.batch.hoge";
		Set<Class<?>> allClasses = new HashSet<>();
		try {
			allClasses = ClassPath.getClasses(packageName);
		} catch(Exception e) {
			System.out.println("failed -> " + e);
		}

		System.out.println("target package [" + packageName + "] class num. ->" + allClasses.size());
	}

}
