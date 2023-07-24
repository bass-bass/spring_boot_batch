package com.example.batch.common.mvc;

public interface JobController {
	public void dispatch(String action, JobForm form) throws Exception;
}
