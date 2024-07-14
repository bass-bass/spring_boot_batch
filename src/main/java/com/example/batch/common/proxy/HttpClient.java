package com.example.batch.common.proxy;

import java.io.Closeable;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClient implements Closeable {
	private final String userAgent;
	private final CloseableHttpClient client;

	public HttpClient(String userAgent) {
		this(HttpClients.createDefault(), userAgent);
	}

	public HttpClient(CloseableHttpClient client, String userAgent) {
		this.client = client;
		this.userAgent = userAgent;
	}

	public String request(String targetURL) throws IOException {
		HttpGet request = new HttpGet(targetURL);
		request.addHeader(HttpHeaders.USER_AGENT, userAgent);
		try (CloseableHttpResponse response = client.execute(request)) {
			int status = response.getStatusLine().getStatusCode();
			if (status == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				Header contentTypeHeader = entity.getContentType();
				String name = contentTypeHeader.getValue();
				ContentType contentType = ContentType.get(name.split(";")[0]);
				return EntityUtils.toString(entity, StandardCharsets.UTF_8);

			} else {
				throw new IOException("request error by status code : " + status);
			}
		}
	}

	@Override
	public void close() throws IOException {
		client.close();
	}

}
