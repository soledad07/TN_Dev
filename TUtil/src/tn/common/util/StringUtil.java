package tn.common.util;

public class StringUtil {
	/**
	 * 문자열 왼쪽에 해당 자리수 만큼 문자 추가
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
	 * 숫자 왼쪽에 해당 자리수 만큼 숫자 추가
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
