package com.atsjp.webDemo.utils;

import java.util.UUID;

public class UUIDutils {
	/**
	 * ���ָ����Ŀ��UUID
	 * 
	 * @param number
	 *            int ��Ҫ��õ�UUID����
	 * @return String[] UUID����
	 */
	public static String[] getUUID(int number) {
		if (number < 1) {
			return null;
		}
		String[] retArray = new String[number];
		for (int i = 0; i < number; i++) {
			retArray[i] = getUUID();
		}
		return retArray;
	}

	/**
	 * ���һ��UUID
	 * 
	 * @return String UUID
	 */
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		// ȥ����-������
		// return uuid.replaceAll("-", "");
		return uuid;
	}
}