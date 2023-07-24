package com.example.batch.hoge;

import com.example.batch.common.mvc.JobForm;
import com.example.batch.common.util.Option;
import com.example.batch.common.util.OptionHandler;

import lombok.Getter;

public class MyForm extends JobForm {

	@Getter
	@Option("message")
	private String message;

	@Getter
	private final MyProperties properties;
	public MyForm(JobForm form, MyProperties properties) {
		super(form.getArgs());
		this.properties = properties;
		OptionHandler.set(this, form);
	}
}
