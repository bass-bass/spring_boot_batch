package com.example.batch.common.mvc;

import java.lang.annotation.Annotation;

import lombok.Data;

@Data
public class JobConf {

	private String rootPackage;
	private Class<? extends Annotation> controllerAnnotationClass;
	private Class<? extends JobController> controllerClass;
	private Class<? extends JobRunner> jobRunnerClass;

	private final boolean displayInfo = true;

	public JobRunner getRunner() {
		try {
			JobRunner runner = (JobRunner) Class.forName(jobRunnerClass.getCanonicalName())
					.getDeclaredConstructor()
					.newInstance();
			return runner;
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

}
