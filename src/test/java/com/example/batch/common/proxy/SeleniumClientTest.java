package com.example.batch.common.proxy;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumClientTest {
	//@Test
	public void test() throws Exception {
		WebDriverManager.chromedriver().setup();
		try (SeleniumClient client = new SeleniumClient("chrome")) {
			String result = client.request("");
			System.out.println(result);
		}
	}
}
