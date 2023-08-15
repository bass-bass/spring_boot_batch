package com.example.batch.common.mvc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.batch.common.util.LRUCache;

public class LRUCacheTest {

	@Test
	public void test0() {
		try {
			System.out.println("test0 start");
			LRUCache lru = new LRUCache(2);
			System.out.println("init");
			lru.put(1, 10);
			System.out.println("put 1");
			lru.put(2, 20);
			System.out.println("put 2");
			Assertions.assertEquals(10, lru.get(1));
			System.out.println("assert get 1");
			Assertions.assertEquals(20, lru.get(2));
			System.out.println("assert get 2");
			Assertions.assertEquals(0, lru.get(3));
			System.out.println("assert get 3");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test1() {
		try {
			System.out.println("test1 start");
			LRUCache lru = new LRUCache(2);
			System.out.println("init");
			lru.put(1, 10);
			System.out.println("put 1");
			lru.put(2, 20);
			System.out.println("put 2");
			lru.put(3, 30);
			System.out.println("put 3");
			Assertions.assertEquals(0, lru.get(1));
			System.out.println("assert get 1");
			Assertions.assertEquals(20, lru.get(2));
			System.out.println("assert get 2");
			Assertions.assertEquals(30, lru.get(3));
			System.out.println("assert get 3");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test2() {
		try {
			System.out.println("test2 start");
			LRUCache lru = new LRUCache(2);
			System.out.println("init");
			lru.put(1, 10);
			System.out.println("put 1");
			//lru.show();
			lru.put(2, 20);
			System.out.println("put 2");
			//lru.show();
			lru.get(1);
			System.out.println("get 1");
			//lru.show();
			lru.put(3, 30);
			System.out.println("put 3");
			//lru.show();
			Assertions.assertEquals(10, lru.get(1));
			System.out.println("assert get 1");
			Assertions.assertEquals(0, lru.get(2));
			System.out.println("assert get 2");
			Assertions.assertEquals(30, lru.get(3));
			System.out.println("assert get 3");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//@Test
	public void test3() {
		try {
			System.out.println("test3 start");
			LRUCache lru = new LRUCache(2);
			System.out.println("put 1");
			lru.put(1, 10);
			System.out.println("get 1");
			lru.get(1);
			System.out.println("put 2");
			lru.put(2, 20);
			System.out.println("put 3");
			lru.put(3, 30);
			System.out.println("put 4");
			lru.put(4, 40);
			System.out.println("put 5");
			lru.put(5, 50);
			System.out.println("get 3");
			lru.get(3);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test4() {
		try {
			System.out.println("test4 start");
			LRUCache lru = new LRUCache(3);
			lru.put(1, 10);
			lru.show();
			lru.put(2, 20);
			lru.show();
			lru.get(1);
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
