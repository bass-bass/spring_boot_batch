package com.example.batch.hoge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;

import com.example.batch.SpringJobController;
import com.example.batch.common.mvc.JobForm;
import com.example.batch.common.util.Logger;

@Controller
@ComponentScan
public class MyController extends SpringJobController {
	private static final Logger logger = Logger.getLogger(MyController.class.getSimpleName());

	@Autowired
	private MyService myService;

	@Autowired
	private MyProperties myProperties;

	@Override
	public void dispatch(String action, JobForm jobForm) throws Exception {
		MyForm form = new MyForm(jobForm, myProperties);

		logger.info("*** " + action + " start ***");

		switch (action) {
			case "huga":
				myService.execute(form);
				return;

			default:
				throw new IllegalArgumentException("unsupported action -> " + action);

		}
	}
}
