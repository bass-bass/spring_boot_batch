package com.example.batch.common.mvc;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.example.batch.common.util.ClassPath;

public class BatchApplicationTest {

	@Test
	void test() {
		String packageName = "com.example.batch.hoge";
		Set<Class<?>> allClasses = new HashSet<>();
		try {
			allClasses = ClassPath.getClasses(packageName);
		} catch (Exception e) {
			System.out.println("failed -> " + e);
		}

		System.out.println("target package [" + packageName + "] class num. ->" + allClasses.size());
	}

	//@Test
	void mainTest() throws Exception{
		String[] _args = new String[] {
				"hoge", "huga",
				"-message", "hello"
		};

		JobForm form = new JobForm(_args);
		System.out.println("control -> "+form.getControl());
		System.out.println("action -> "+form.getAction());
		//SpringBatchApplication.main(_args);
	}

}
