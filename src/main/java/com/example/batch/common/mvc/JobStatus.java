package com.example.batch.common.mvc;

public enum JobStatus {
	SUCCESS(0),
	ERROR(1);
	final int statusCode;

	private JobStatus(int statusCode) {
		this.statusCode = statusCode;
	}

	public int getStatusCode() {
		return statusCode;
	}
}
