package tn.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
	/**
	 * 현재 날짜를 pattern 으로 변환하여 리턴 
	 * (yyyyMMdd/yyyy-MM-dd ...)
	 * @param pattern
	 * @return 현재 날짜
	 */
	public String getFormatToDay(String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.KOREA);

		String dateString = formatter.format(new Date());

		return dateString;
	}

	/**
	 * 날짜 형식 확인
	 * @param date
	 * @return
	 */
	public boolean isDate(String date) {
		boolean dateValidity = true;

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd", Locale.KOREA); // 20041102101244
		df.setLenient(false); // false 로 설정해야 엄밀한 해석을 함.

		try {
			df.parse(date);
		} catch (ParseException pe) {
			dateValidity = false;
		} catch (IllegalArgumentException ae) {
			dateValidity = false;
		}

		return dateValidity;
	}

	/**
	 * 두 날짜 사이의 차이
	 * @param startDate	시작 날짜
	 * @param endDate	종료 날짜
	 * @return long		날짜 차이
	 */
	public long getDayDistance(String startDate, String endDate) throws Exception {
		return getDayDistance(startDate, endDate, null);
	}

	/**
	 * 두 날짜 사이의 차이
	 * @param startDate	시작 날짜
	 * @param endDate	종료 날짜
	 * @param format	날짜 형식
	 * @return long		날짜 차이
	 */
	private long getDayDistance(String startDate, String endDate, String format) throws Exception {
		if (format == null)
			format = "yyyyMMdd";
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		
		Date sDate;
		Date eDate;
		
		long day2day = 0;
		
		try {
			sDate = sdf.parse(startDate);
			eDate = sdf.parse(endDate);
			
			day2day = (eDate.getTime() - sDate.getTime()) / (1000 * 60 * 60 * 24);
		} catch (Exception e) {
			throw new Exception("wrong format string");
		}
		
		return Math.abs(day2day);
	}
	
	/**
	 * 경과시간을 계산하여 반환
	 * @param sTime
	 * @param eTime
	 * @return
	 */
	public String getPeriodTimeString(String sTime, String eTime){
		String hh = "00";
		String mm = "00";
		String ss = "00";

		if (sTime != "" && eTime != "") {
			int st = Integer.parseInt(sTime.substring(0, 2))*60*60 + Integer.parseInt(sTime.substring(2, 4))*60 + Integer.parseInt(sTime.substring(4, 6));
			int et = Integer.parseInt(eTime.substring(0, 2))*60*60 + Integer.parseInt(eTime.substring(2, 4))*60 + Integer.parseInt(eTime.substring(4, 6));
			
			int sec = et - st;

			if (sec > 0) {
				hh = String.valueOf(sec / (60 * 60));
				sec = sec % (60 * 60);
				
				mm = String.valueOf(sec / 60);
				sec = sec % 60;
				
				ss = String.valueOf(sec);

				if (Integer.parseInt(hh) < 10) hh = "0" + hh;
				if (Integer.parseInt(mm) < 10) mm = "0" + mm;
				if (Integer.parseInt(ss) < 10) ss = "0" + ss;
			}
		}
		
		return hh + mm + ss;
	}
}
