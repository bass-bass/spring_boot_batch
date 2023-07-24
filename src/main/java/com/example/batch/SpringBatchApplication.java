package com.example.batch;

import org.springframework.stereotype.Controller;

import com.example.batch.common.mvc.BatchApplication;
import com.example.batch.common.mvc.JobConf;
import com.example.batch.common.mvc.JobStatus;
import com.example.batch.common.util.Logger;

public class SpringBatchApplication {

	private static final String ROOT_PACKAGE = "com.example.batch";
	private static final Logger logger = Logger.getLogger(SpringBatchApplication.class.getSimpleName());

	public static void main(String[] args) throws Exception {
		JobConf conf = new JobConf();
		conf.setRootPackage(ROOT_PACKAGE);
		conf.setControllerAnnotationClass(Controller.class);
		conf.setControllerClass(SpringJobController.class);
		conf.setJobRunnerClass(SpringJobRunner.class);

		JobStatus status = null;
		try {
			long start = System.currentTimeMillis();
			status = BatchApplication.execute(args, conf);
			long end = System.currentTimeMillis();

			long diff = end - start;
			logger.info(diff + " ms");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("main batch application error", e);
		} finally {
			System.exit(status == JobStatus.SUCCESS ? 0 : 1);
		}
	}

}
