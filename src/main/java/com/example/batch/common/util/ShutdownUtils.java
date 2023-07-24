package com.example.batch.common.util;

import java.io.Closeable;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShutdownUtils {

	private static final Logger logger = LoggerFactory.getLogger(ShutdownUtils.class);

	public static void regist(final Closeable closeable) {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				try {
					closeable.close();
				} catch (IOException e) {
					logger.error("failed to close..", e);
				}
			}
		});
	}

	public static void destructor() {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				try {
					Destructor.destroyAll();
				} catch (Exception e) {
					logger.error("failed to destruct", e);
				}
			}
		});
	}
}
