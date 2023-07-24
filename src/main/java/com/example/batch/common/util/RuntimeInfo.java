package com.example.batch.common.util;

public class RuntimeInfo {

	private static final RuntimeInfo r = new RuntimeInfo();

	public static RuntimeInfo get() {
		return r;
	}

	private RuntimeInfo() {}

	@Override
	public String toString() {
		Runtime r = Runtime.getRuntime();

		int vCoreNum = r.availableProcessors();
		MemoryInfo m = new MemoryInfo().setMega();

		StringBuilder sb = new StringBuilder()
				.append("vCore -> ").append(vCoreNum).append(", ")
				.append("memoryInfo -> ").append(m);

		return sb.toString();
	}

}