package com.example.batch.common.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache extends LinkedHashMap<Integer, Integer> {
	private final int maxSize;

	public LRUCache(int maxSize) {
		this(maxSize, 16);
	}

	public LRUCache(int maxSize, int initialCapacity) {
		this(maxSize, initialCapacity, 0.75F);
	}

	public LRUCache(int maxSize, int initialCapacity, float loadFactor) {
		super(initialCapacity, loadFactor, true);
		this.maxSize = maxSize;
	}

	/**
	 * put時に呼ばれる. trueの場合に最も古いentryをremove
	 * @param eldest the least recently accessed　entry.
	 *           This is the entry that will be removed it this　method returns {@code true}.
	 * @return put時にmaxSizeを超えた場合にtrue
	 */
	@Override
	protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
		System.out.println("remove -> " + eldest.getKey());
		return size() > maxSize;
	}

	/**
	 * keyに対応するvalueのget
	 * @param key
	 * @return value (keyが存在しない場合は0)
	 */
	public Integer get(Integer key) {
		Integer value = super.get(key);
		if (value == null)
			return 0;
		return value;
	}

	public void show() {
		System.out.println("*** show ***");
		this.entrySet().forEach(e -> {
			System.out.println(e.getKey() + " - " + e.getValue());
		});
	}
}
