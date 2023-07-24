package com.example.batch.hoge;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.batch.common.util.Logger;

@Service
public class MyService {

	private  static final Logger logger = Logger.getLogger(MyService.class.getSimpleName(),true);
	@Autowired
	private MyLogic logic;
	public void execute(MyForm form) throws Exception {
		logger.info("*** service execute ***");
		logic.execute(form);
	}
}
