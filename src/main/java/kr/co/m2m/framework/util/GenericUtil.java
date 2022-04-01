package kr.co.m2m.framework.util;

import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.regex.Pattern;

import org.apache.commons.lang3.RandomStringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GenericUtil {

	private static String default_delimiter = "_";
	private static String default_date_format = "yyMMdd";
	private static Integer default_random_length = 7;

	/**
	 * ID 생성 (문자+날자(yyMMdd)+임의수(7))
	 * 
	 * @param gbn
	 *            : 구분 문자
	 * @return
	 */
	public static String genericId(String gbn) {
		return genericId(gbn, default_date_format, default_random_length);
	}

	/**
	 * ID 생성 (문자+날짜+임의수(7))
	 * 
	 * @param dateFormat
	 *            : 날짜형식
	 * @param gbn
	 *            : 구분 문자
	 * @return
	 */
	public static String genericId(String gbn, String dateFormat) {
		return genericId(gbn, dateFormat, default_random_length);
	}

	/**
	 * <pre>
	 * 작성일 : 2017. 5. 29.
	 * 작성자 : KWT
	 * 설명   : TODO
	 * 
	 * 수정내역(수정일 수정자 - 수정내용)
	 * -------------------------------------------------------------------------
	 * 2017. 5. 29. KWT - 최초생성
	 * </pre>
	 *
	 * @param dateFormat
	 * @param randomLength
	 * @return
	 */
	public static String genericId(String dateFormat, int randomLength) {
		return genericId(null, dateFormat, randomLength);
	}

	/**
	 * ID 생성 (문자+날짜+임의수)
	 * 
	 * @param dateFormat
	 *            : 날짜형식
	 * @param gbn
	 *            : 구분 문자
	 * @param randomLength
	 *            : 임의숫자 자리수
	 * @return
	 */
	public static String genericId(String gbn, String dateFormat, int randomLength) {
		StringBuilder sbID = new StringBuilder();

		if (gbn != null) {
			sbID.append(gbn);
		}

		if (dateFormat != null) {
			DateFormat df = new SimpleDateFormat(dateFormat);

			Calendar calendar;
			calendar = Calendar.getInstance();
			String currentDay = df.format(calendar.getTime());

			sbID.append(currentDay);
		}
		sbID.append(generateNum(randomLength));
		return sbID.toString();
	}

	public static String genericTempId(String dateFormat, int randomLength) {
		StringBuilder sbID = new StringBuilder();
		if (dateFormat != null) {
			DateFormat df = new SimpleDateFormat(dateFormat);

			Calendar calendar;
			calendar = Calendar.getInstance();
			String currentDay = df.format(calendar.getTime());

			sbID.append(currentDay).append("_");
		}

		sbID.append(generateNum(randomLength));

		return sbID.toString();
	}

	/**
	 * ID 생성 (임의수)
	 * 
	 * @param randomLength
	 * @return
	 */
	public static Integer genericId(int randomLength) {
		Integer newId = null;

		newId = Integer.valueOf(generateNum(randomLength));

		return newId;
	}

	/**
	 * 지정한 길이의 랜덤한 숫자 생성
	 * 
	 * @param length
	 *            : 길이
	 * @return
	 */
	public static String generateNum(int length) {

		String newRanNum = "";
		Random r = new Random();
		SecureRandom secRandom = new SecureRandom();
		r.setSeed((new Date().getTime()) + secRandom.nextLong());
		while (newRanNum.length() < length) {
			newRanNum += r.nextInt(10);
		}

		return newRanNum;
	}

	/**
	 * ID 생성 (문자+날자(yyMMdd)+임의수(7))
	 * 
	 * @param gbn
	 *            : 구분 문자
	 * @return
	 */
	public static String genericDateId(String gbn) {
		return genericDateId(default_date_format, gbn, default_random_length);
	}

	/**
	 * ID 생성 (문자+날짜+임의수(7))
	 * 
	 * @param dateFormat
	 *            : 날짜형식
	 * @param gbn
	 *            : 구분 문자
	 * @return
	 */
	public static String genericDateId(String dateFormat, String gbn) {
		return genericDateId(dateFormat, gbn, default_random_length);
	}

	/**
	 * <pre>
	 * 작성일 : 2017. 5. 29.
	 * 작성자 : dmKim
	 * 설명   : TODO
	 * 
	 * 수정내역(수정일 수정자 - 수정내용)
	 * -------------------------------------------------------------------------
	 * 2017. 5. 29. dmKim - 최초생성
	 * </pre>
	 *
	 * @param dateFormat
	 * @param randomLength
	 * @return
	 */
	public static String genericDateId(String dateFormat, int randomLength) {
		return genericDateId(dateFormat, null, randomLength);
	}

	/**
	 * ID 생성 (날짜+문자+임의수)
	 * 
	 * @param dateFormat
	 *            : 날짜형식
	 * @param gbn
	 *            : 구분 문자
	 * @param randomLength
	 *            : 임의숫자 자리수
	 * @return
	 */
	public static String genericDateId(String dateFormat, String gbn, int randomLength) {
		StringBuilder sbID = new StringBuilder();

		if (dateFormat != null) {
			DateFormat df = new SimpleDateFormat(dateFormat);

			Calendar calendar;
			calendar = Calendar.getInstance();
			String currentDay = df.format(calendar.getTime());

			sbID.append(currentDay).append(default_delimiter);
		}

		if (gbn != null) {
			sbID.append(gbn);
		}

		sbID.append(generateNum(randomLength));
		return sbID.toString();
	}

	/**
	 * ID 생성 (임의수)
	 * 
	 * @param randomLength
	 * @return
	 */
	public static Integer genericDateId(int randomLength) {
		return genericId(randomLength);
	}

	/**
	 * <pre>
	 * 설명     : 입력받은 숫자 자리수만큼의 알파벳소문자, 숫자 랜덤 문자열을 리턴한다.
	 * &#64;param length 랜덤으로 생성할 문자열 길이
	 * &#64;return 입력받은 파라메터 길이만큼 랜덤한 알파벳 소문자, 숫자의 문자열
	 * </pre>
	 */
	public static String genericRandomAlphanumeric(int length) {
		// 알파벳 소문자, 숫자가 반드시 하나씩은 포함
		Pattern pattern = Pattern.compile("(?=.*\\d)(?=.*[a-z])");
		String str = RandomStringUtils.randomAlphanumeric(length).toLowerCase();
		while (!pattern.matcher(str).find()) {
			str = RandomStringUtils.randomAlphanumeric(length).toLowerCase();
		}
		return str;
	}

}
