package com.example.batch.hoge;

import org.springframework.stereotype.Component;

import com.example.batch.common.util.Logger;

@Component
public class MyLogic {
	private static final Logger logger = Logger.getLogger(MyLogic.class.getSimpleName());
	public void execute(MyForm form) throws Exception{
		logger.info("*** logic execute ***");
		logger.info("message -> " + form.getMessage());
	}
}
