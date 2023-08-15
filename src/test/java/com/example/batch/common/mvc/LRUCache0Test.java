package com.example.batch.common.mvc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.batch.common.util.LRUCache0;

public class LRUCache0Test {
	@Test
	public void test0() {
		try {
			LRUCache0 lru = new LRUCache0(2);
			lru.put(1, 10);
			lru.put(2, 20);
			Assertions.assertEquals(10, lru.get(1));
			Assertions.assertEquals(20, lru.get(2));
			Assertions.assertEquals(0, lru.get(3));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test1() {
		try {
			LRUCache0 lru = new LRUCache0(2);
			lru.put(1, 10);
			lru.put(2, 20);
			lru.put(3, 30);
			Assertions.assertEquals(0, lru.get(1));
			Assertions.assertEquals(20, lru.get(2));
			Assertions.assertEquals(30, lru.get(3));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test2() {
		try {
			LRUCache0 lru = new LRUCache0(2);
			lru.put(1, 10);
			lru.show();
			lru.put(2, 20);
			lru.show();
			lru.get(1);
			lru.show();
			lru.put(3, 30);
			lru.show();
			Assertions.assertEquals(10, lru.get(1));
			Assertions.assertEquals(0, lru.get(2));
			Assertions.assertEquals(30, lru.get(3));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test3() {
		try {
			LRUCache0 lru = new LRUCache0(3);
			lru.put(1, 10);
			lru.show();
			lru.put(2, 20);
			lru.show();
			lru.get(1);
			lru.show();
			lru.put(1, 5);
			lru.show();
			lru.put(3, 30);
			lru.show();
			lru.put(4, 40);
			lru.show();
			lru.put(1, 50);
			lru.show();// 3 4 1
			lru.get(3);
			lru.show();// 4 1 3
			Assertions.assertEquals(50, lru.get(1));
			Assertions.assertEquals(0, lru.get(2));
			Assertions.assertEquals(30, lru.get(3));
			Assertions.assertEquals(40, lru.get(4));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
