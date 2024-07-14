package com.example.batch.common.proxy;

import java.io.IOException;

public class HttpClientTest {
	//@Test
	public void test() throws IOException {
		try (HttpClient client = new HttpClient("user-agent")) {
			String result = client.request("https://www.google.com");
			System.out.println(result);
		}
	}
}
