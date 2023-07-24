package com.example.batch.common.util;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.lang3.StringUtils;

public class ClassPath {

	public static Set<Class<?>> getClasses(String packageName) throws IOException, ClassNotFoundException {
		final String resourceName = packageName.replace('.', '/');
		final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		final URL root = classLoader.getResource(resourceName);

		Set<Class<?>> set = new HashSet<Class<?>>();

		if ("file".equals(root.getProtocol())) {
			File[] files = new File(root.getFile()).listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					if (name.endsWith(".class"))
						return true;
					return false;
				}
			});
			for (File file : files) {
				String name = file.getName();
				name = name.replaceAll(".class$", "");
				name = StringUtils.join(packageName + "." + name);
				Class<?> c = Class.forName(name);
				set.add(c);
			}
			return set;
		}
		if ("jar".equals(root.getProtocol())) {
			try (JarFile jarFile = ((JarURLConnection) root.openConnection()).getJarFile()) {
				Enumeration<JarEntry> e = jarFile.entries();
				while (e.hasMoreElements()) {
					JarEntry entry = e.nextElement();
					String name = entry.getName();
					if (!name.startsWith(resourceName)) continue;
					if (!name.endsWith(".class")) continue;
					name = name.replace('/', '.').replaceAll(".class$", "");
					set.add(classLoader.loadClass(name));
				}
			}
			return set;
		}
		return set;
	}
}
