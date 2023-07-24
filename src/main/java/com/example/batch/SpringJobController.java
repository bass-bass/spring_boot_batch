package com.example.batch;

import org.springframework.boot.CommandLineRunner;

import com.example.batch.common.mvc.JobController;
import com.example.batch.common.mvc.JobForm;

public abstract class SpringJobController implements JobController, CommandLineRunner{
	@Override
	public void run(String... args) throws Exception {
		JobForm form = new JobForm(args);
		dispatch(form.getAction(), form);
	}

	@Override
	public abstract void dispatch(String paramString, JobForm paramJobForm) throws Exception;
}
