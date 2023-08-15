package com.example.batch.common.util;

import java.util.LinkedList;

public class LRUCache0 {
	static class Node0 {
		private final int key;
		private final int value;

		public Node0(int key, int value) {
			this.key = key;
			this.value = value;
		}

		public int getKey() {
			return key;
		}

		public int getValue() {
			return value;
		}
	}

	private final LinkedList<Node0> list;
	private final int MAX_SIZE;

	public LRUCache0(int maxSize) {
		this.MAX_SIZE = maxSize;
		this.list = new LinkedList<>();
	}

	/**
	 * key, valueによるnode追加
	 * 1. keyが存在する時 -> 上書きして最新に
	 * 2. サイズ上限に達している時 -> 最も古いものを削除して追加
	 * 3. それ以外 -> そのまま追加
	 * @param key
	 * @param value
	 */
	public void put(Integer key, Integer value) {
		Node0 node = new Node0(key, value);
		int index = getIndexByKey(key);
		// 1.
		if (index >= 0) {
			update(index, node);
			return;
		}
		// 2.
		if (isSizeMax()) {
			update(0, node);
			return;
		}
		// 3.
		list.add(node);
	}

	/**
	 * keyによるnode取得. 参照後には最新に更新
	 * @param key
	 * @return keyに対応するvalue(無ければ 0)
	 */
	public int get(Integer key) {
		for (int index = 0; index < list.size(); index++) {
			Node0 node = list.get(index);
			if (node.getKey() == key) {
				if (index != getLastIndex())
					update(index, node);
				return node.getValue();
			}
		}
		return 0;
	}

	/**
	 * 対象indexのnodeを削除、新規nodeの追加
	 * @param index 更新対象nodeのindex
	 * @param node 更新後のnode
	 */
	private void update(int index, Node0 node) {
		list.remove(index);
		list.add(node);
	}

	/**
	 * @return 最新のnodeのindex
	 */
	private int getLastIndex() {
		return list.size() - 1;
	}

	/**
	 * @param key
	 * @return 対象のkeyを持つnodeのindex(無ければ - 1)
	 */
	private int getIndexByKey(Integer key) {
		for (int index = 0; index < list.size(); index++) {
			if (key == list.get(index).key)
				return index;
		}
		return -1;
	}

	/**
	 * @return listのsizeがMax_Sizeに一致する場合にtrue
	 */
	private boolean isSizeMax() {
		return list.size() == MAX_SIZE;
	}

	// tmp
	public void show() {
		System.out.println("*** show ***");
		for (int i = 0; i < list.size(); i++) {
			Node0 n = list.get(i);
			System.out.println(i + " : " + n.getKey() + " - " + n.getValue());
		}
	}
}
