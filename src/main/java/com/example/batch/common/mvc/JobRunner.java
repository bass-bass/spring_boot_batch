package com.example.batch.common.mvc;

public interface JobRunner {

	public JobStatus run(Class<? extends JobController> clazz, JobForm form);

}