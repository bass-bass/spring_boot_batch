package com.example.batch.common.mvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import com.example.batch.common.util.ClassPath;
import com.example.batch.common.util.Destructor;
import com.example.batch.common.util.RuntimeInfo;

public class BatchApplication {

	private static final Logger logger = LoggerFactory.getLogger(BatchApplication.class);

	public static JobStatus execute(String[] args, JobConf conf) throws Exception {
		boolean infoDisplayed = conf.isDisplayInfo();

		JobForm form = new JobForm(args);

		String controller = form.getControl();

		String packageName = StringUtils.join(conf.getRootPackage(), ".", controller);
		Set<Class<?>> allClasses = ClassPath.getClasses(packageName);

		List<Class<?>> contrllerClass = new ArrayList<>();
		logger.debug("target package [" + packageName + "] class num. ->" + allClasses.size());

		// controller を取り出す.
		for (Class<?> clazz : allClasses) {
			// JobController の判定
			if (!JobController.class.isAssignableFrom(clazz)) continue;
			// 指定された Controller アノテーションがあるか？
			if (clazz.getAnnotation(conf.getControllerAnnotationClass()) == null) continue;
			contrllerClass.add(clazz);
		}

		if (contrllerClass.size() == 0) {
			logger.error("package in [" + packageName + "] do not have controller class.");
			return JobStatus.ERROR;
		}

		if (contrllerClass.size() > 1) {
			logger.error("package in [" + packageName + "] have multi controller class.");
			return JobStatus.ERROR;
		}

		Class<?> candidateControllerClass = contrllerClass.get(0);
		Class<? extends JobController> controllerClass = conf.getControllerClass();

		// cast check
		if (!controllerClass.isAssignableFrom(candidateControllerClass)) {
			logger.error("package in [" + packageName + "] do not have any JobController Object.");
			return JobStatus.ERROR;
		}

		@SuppressWarnings("unchecked")
		Class<? extends JobController> kickedClass = (Class<? extends JobController>) contrllerClass.get(0);
		logger.info("Batch Controller -> " + kickedClass.getSimpleName() + " start.", infoDisplayed);
		logger.info(RuntimeInfo.get().toString(), infoDisplayed);

		JobRunner runner = conf.getRunner();
		JobStatus status = null;
		try {
			long start = System.currentTimeMillis();
			status = runner.run(kickedClass, form);
			long end = System.currentTimeMillis();
			long diff = end - start;
			logger.info("job execute time -> " + diff + " ms", infoDisplayed);
			return status;
		} finally {
			Destructor.destroyAll();
		}
	}

}
