package com.example.batch;

import com.example.batch.common.mvc.JobController;
import com.example.batch.common.mvc.JobForm;
import com.example.batch.common.mvc.JobRunner;
import com.example.batch.common.mvc.JobStatus;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Getter;

public class SpringJobRunner implements JobRunner {

	public static class ExitCodes implements ExitCodeGenerator {

		@Getter
		private JobStatus status;

		@Override
		public int getExitCode() {
			return status.getStatusCode();
		}

		public void setExitCode(JobStatus status) {
			this.status = status;
		}
	}

	private static final Logger logger = LoggerFactory.getLogger(SpringJobRunner.class);

	@Override
	public JobStatus run(Class<? extends JobController> kickClass, JobForm form) {
		ExitCodes exitCode = new ExitCodes();
		try {
			SpringApplication.run(kickClass, form.getArgs());
			exitCode.setExitCode(JobStatus.SUCCESS);
			logger.info("SpringApplication.run end. exit code: " + exitCode.getExitCode());
		} catch (Exception e) {
			exitCode.setExitCode(JobStatus.ERROR);
			logger.error("", e);
		}
		return exitCode.getStatus();
	}

}