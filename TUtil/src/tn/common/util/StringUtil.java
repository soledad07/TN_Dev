package tn.common.util;

public class StringUtil {
	/**
	 * ���ڿ� ���ʿ� �ش� �ڸ��� ��ŭ ���� �߰�
	 * 
	 * @param str
	 * @param len
	 * @param addStr
	 * @return
	 */
	public String lPad(String str, int len, char addStr) {
		if(str == null){
			return str;
		}
		
		String result = str;

		int templen = len - result.length();

		for (int i = 0; i < templen; i++) {
			result = addStr + result;
		}

		return result;
	}

	/**
	 * ���� ���ʿ� �ش� �ڸ��� ��ŭ ���� �߰�
	 * 
	 * @param num
	 * @param len
	 * @param addNum
	 * @return
	 */
	public String lPad(int num, int len, int addNum) {
		return String.format("%" + addNum + String.valueOf(len) + "d", num);
	}
}
