package kr.co.m2m.framework.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StringUtil {

	/**
	 * strTarget이 null 이거나 화이트스페이스 일 경우 strDest을 반환한다.
	 *
	 * @param strTarget
	 *            대상 문자열
	 * @param strDest
	 *            대체 문자열
	 * @return strTarget이 null 이거나 화이트스페이스 일 경우 strDest 문자열로 반환
	 */
	public static String nvl(String strTarget, String strDest) {
		String retValue = null;

		if (strTarget == null || "".equals(strTarget)) {
			retValue = strDest;
		} else {
			retValue = strTarget;
		}
		return retValue;
	}

	/**
	 * strTarget이 null 이거나 화이트스페이스 일 경우 화이트스페이스로 반환한다.
	 *
	 * @param strTarget
	 *            대상문자열
	 * @return strTarget이 null 이거나 화이트스페이스 일 경우 화이트스페이스로 반환
	 */
	public static String nvl(String strTarget) {
		return nvl(strTarget, "");
	}

	/**
	 * 대상문자열이 null 인지 여부 확인하기
	 *
	 * @paramstrTarget 대상 문자열
	 * @return null 여부
	 */
	public static boolean isNull(String strTarget) {
		boolean retValue = false;

		if (strTarget == null)
			retValue = true;
		else
			retValue = false;

		return retValue;
	}

	/**
	 * 대상 문자열이 지정한 길이보다 길 경우 지정한 길이만큼 잘라낸 문자열 반환하기
	 *
	 * @param strTarget
	 *            대상 문자열
	 * @param nLimit
	 *            길이
	 * @param bDot
	 *            잘린 문자열이 존재할 경우 ... 표시 여부
	 * @return 대상 문자열이 지정한 길이보다 길 경우 지정한 길이만큼 잘라낸 문자열
	 */
	public static String cutText(String strTarget, Integer limit) {

		// log.debug("###################### strTarget : {} ################################ ", strTarget);

		if (strTarget == null || strTarget.equals("")) {
			return strTarget;
		}

		String retValue = null;

		int nLimit = limit.intValue();
		int nLen = strTarget.length();
		int nTotal = 0;
		int nHex = 0;

		String strDot = "...";

		for (int i = 0; i < nLen; i++) {
			nHex = strTarget.charAt(i);
			nTotal += Integer.toHexString(nHex).length() / 2;

			if (nTotal > nLimit) {
				retValue = strTarget.substring(0, i) + strDot;
				break;
			} else if (nTotal == nLimit) {
				if (i == (nLen - 1)) {
					retValue = strTarget.substring(0, i - 1) + strDot;
					break;
				}
				retValue = strTarget.substring(0, i + 1) + strDot;
				break;
			} else {
				retValue = strTarget;
			}
		}

		// log.debug("###################### retValue  : {} ################################ ", retValue);

		return retValue;
	}

	/**
	 * 대상 문자열이 지정한 길이보다 길 경우 지정한 길이만큼 잘라낸 문자열 반환하기
	 *
	 * @param strTarget
	 *            대상 문자열
	 * @param nLimit
	 *            길이
	 * @param bDot
	 *            잘린 문자열이 존재할 경우 ... 표시 여부
	 * @return 대상 문자열이 지정한 길이보다 길 경우 지정한 길이만큼 잘라낸 문자열
	 */
	public static String cutText(String strTarget, int nLimit, boolean bDot) {

		log.debug("###################### strTarget : {} ################################ ", strTarget);

		if (strTarget == null || strTarget.equals("")) {
			return strTarget;
		}

		String retValue = null;

		int nLen = strTarget.length();
		int nTotal = 0;
		int nHex = 0;

		String strDot = "";

		if (bDot)
			strDot = "...";

		for (int i = 0; i < nLen; i++) {
			nHex = strTarget.charAt(i);
			nTotal += Integer.toHexString(nHex).length() / 2;

			if (nTotal > nLimit) {
				retValue = strTarget.substring(0, i) + strDot;
				break;
			} else if (nTotal == nLimit) {
				if (i == (nLen - 1)) {
					retValue = strTarget.substring(0, i - 1) + strDot;
					break;
				}
				retValue = strTarget.substring(0, i + 1) + strDot;
				break;
			} else {
				retValue = strTarget;
			}
		}

		log.debug("###################### retValue  : {} ################################ ", retValue);

		return retValue;
	}

	/**
	 * 대상문자열에 지정한 문자가 위치한 위치 값을 반환하기(대소문자 무시)
	 *
	 * @param strTarget
	 *            대상 문자열
	 * @param strDest
	 *            찾고자 하는 문자열
	 * @param nPos
	 *            시작 위치
	 * @return 대상문자열에 지정한 문자가 위치한 위치 값을 반환
	 */
	public static int indexOfIgnore(String strTarget, String strDest, int nPos) {
		if (strTarget == null || strTarget.equals(""))
			return -1;

		strTarget = strTarget.toLowerCase();
		strDest = strDest.toLowerCase();

		return strTarget.indexOf(strDest, nPos);
	}

	/**
	 * 대상문자열에 지정한 문자가 위치한 위치 값을 반환하기(대소문자 무시)
	 *
	 * @param strTarget
	 *            대상 문자열
	 * @param strDest
	 *            찾고자 하는 문자열
	 * @return 대상문자열에 지정한 문자가 위치한 위치 값을 반환
	 */
	public static int indexOfIgnore(String strTarget, String strDest) {
		return indexOfIgnore(strTarget, strDest, 0);
	}

	/**
	 * 대상 문자열 치환하기
	 *
	 * @param strTarget
	 *            대상문자열
	 * @param strOld
	 *            찾고자 하는 문자열
	 * @param strNew
	 *            치환할 문자열
	 * @param bIgnoreCase
	 *            대소문자 구분 여부
	 * @param bOnlyFirst
	 *            한 번만 치환할지 여부
	 * @return 치환한 문자열
	 */
	public static String replace(String strTarget, String strOld, String strNew, boolean bIgnoreCase, boolean bOnlyFirst) {
		if (strTarget == null || strTarget.equals(""))
			return strTarget;

		StringBuilder objDest = new StringBuilder("");
		int nLen = strOld.length();
		int strTargetLen = strTarget.length();
		int nPos = 0;
		int nPosOld = 0;

		if (bIgnoreCase == true) { // 대소문자 구분하지 않을 경우
			while ((nPos = indexOfIgnore(strTarget, strOld, nPosOld)) >= 0) {
				objDest.append(strTarget.substring(nPosOld, nPos));
				objDest.append(strNew);
				nPosOld = nPos + nLen;
				if (bOnlyFirst == true) // 한번만 치환할시
					break;
			}
		} else { // 대소문자 구분하는 경우
			while ((nPos = strTarget.indexOf(strOld, nPosOld)) >= 0) {
				objDest.append(strTarget.substring(nPosOld, nPos));
				objDest.append(strNew);
				nPosOld = nPos + nLen;
				if (bOnlyFirst == true)
					break;
			}
		}

		if (nPosOld < strTargetLen)
			objDest.append(strTarget.substring(nPosOld, strTargetLen));

		return objDest.toString();
	}

	/**
	 * 대상 문자열 치환하기
	 *
	 * @param strTarget
	 *            대상문자열
	 * @param strOld
	 *            찾고자 하는 문자열
	 * @param strNew
	 *            치환할 문자열
	 * @return 치환된 문자열
	 */
	public static String replaceAll(String strTarget, String strOld, String strNew) {
		return replace(strTarget, strOld, strNew, false, false);
	}

	/**
	 * 각종 구분자 제거하기
	 *
	 * @param strTarget
	 *            대상문자열
	 * @return String 구분자가 제거된 문자열
	 */
	public static String removeFormat(String strTarget) {
		if (strTarget == null || strTarget.equals(""))
			return strTarget;

		return strTarget.replaceAll("[$|^|*|+|?|/|:|\\-|,|.|\\s]", "");
	}

	/**
	 * 콤마 제거하기
	 *
	 * @param strTarget
	 *            대상 문자열
	 * @return String 콤마가 제거된 문자열
	 */
	public static String removeComma(String strTarget) {
		if (strTarget == null || strTarget.equals(""))
			return strTarget;

		return strTarget.replaceAll("[,|\\s]", "");
	}

	/**
	 * 값 채우기
	 *
	 * @param strTarget
	 *            대상 문자열
	 * @param strDest
	 *            채워질 문자열
	 * @param nSize
	 *            총 문자열 길이
	 * @param bLeft
	 *            채워질 문자의 방향이 좌측인지 여부
	 * @return 지정한 길이만큼 채워진 문자열
	 */
	public static String padValue(String strTarget, String strDest, int nSize, boolean bLeft) {
		if (strTarget == null)
			return strTarget;

		String retValue = null;

		StringBuilder objSB = new StringBuilder();

		int nLen = strTarget.length();
		int nDiffLen = nSize - nLen;
		for (int i = 0; i < nDiffLen; i++) {
			objSB.append(strDest);
		}

		if (bLeft == true) // 채워질 문자열의 방향이 좌측일 경우
			retValue = objSB.toString() + strTarget;
		else
			// 채워질 문자열의 방향이 우측일 경우
			retValue = strTarget + objSB.toString();

		return retValue;
	}

	/**
	 * 좌측으로 값 채우기
	 *
	 * @param strTarget
	 *            대상 문자열
	 * @param strDest
	 *            채워질 문자열
	 * @param nSize
	 *            총 문자열 길이
	 * @return 채워진 문자열
	 */
	public static String padLeft(String strTarget, String strDest, int nSize) {
		return padValue(strTarget, strDest, nSize, true);
	}

	/**
	 * 좌측에 공백 채우기
	 *
	 * @param strTarget
	 *            대상 문자열
	 * @param nSize
	 *            총 문자열 길이
	 * @return 채워진 문자열 길이
	 */
	public static String padLeft(String strTarget, int nSize) {
		return padValue(strTarget, " ", nSize, true);
	}

	/**
	 * 우측으로 값 채우기
	 *
	 * @param strTarget
	 *            대상문자열
	 * @param strDest
	 *            채워질 문자열
	 * @param nSize
	 *            총 문자열 길이
	 * @return 채워진 문자열 길이
	 */
	public static String padRight(String strTarget, String strDest, int nSize) {
		return padValue(strTarget, strDest, nSize, false);
	}

	/**
	 * 우측으로 공백 채우기
	 *
	 * @param strTarget
	 *            대상문자열
	 * @param nSize
	 *            총 문자열 길이
	 * @return 채워진 문자열
	 */
	public static String padRight(String strTarget, int nSize) {
		return padValue(strTarget, " ", nSize, false);
	}

	/**
	 * 대상 문자열을 금액형 문자열로 변환하기
	 *
	 * @param strTarget
	 *            대상 문자열
	 * @return 금액형 문자열
	 */
	@SuppressWarnings("null")
	public static String formatMoney(String strTarget) {
		if (strTarget == null && strTarget.trim().length() == 0)
			return "0";

		strTarget = removeComma(strTarget);

		String strSign = strTarget.substring(0, 1);
		if (strSign.equals("+") || strSign.equals("-")) { // 부호가 존재할 경우
			strSign = strTarget.substring(0, 1);
			strTarget = strTarget.substring(1);
		} else {
			strSign = "";
		}

		String strDot = "";
		if (strTarget.indexOf(".") != -1) { // 소숫점이 존재할 경우
			int nPosDot = strTarget.indexOf(".");
			strDot = strTarget.substring(nPosDot, strTarget.length());
			strTarget = strTarget.substring(0, nPosDot);
		}

		StringBuilder objSB = new StringBuilder(strTarget);
		int nLen = strTarget.length();
		for (int i = nLen; 0 < i; i -= 3) { // Comma 단위
			objSB.insert(i, ",");
		}
		return strSign + objSB.substring(0, objSB.length() - 1) + strDot;
	}

	/**
	 * 대상문자열의 소숫점 설정하기
	 *
	 * @param strTarget
	 *            대상문자열
	 * @param nDotSize
	 *            소숫점 길이
	 * @return
	 */
	public static String round(String strTarget, int nDotSize) {
		if (strTarget == null || strTarget.trim().length() == 0)
			return strTarget;

		String strDot = null; // 占쌀쇽옙a; 占쏙옙占쏙옙占쏙옙 占쌀쇽옙a占쏙옙占쏙옙 占쏙옙

		int nPosDot = strTarget.indexOf(".");
		if (nPosDot == -1) { // 소숫점이 존재하지 않을 경우
			strDot = (nDotSize == 0) ? padValue("", "0", nDotSize, false) : "." + padValue("", "0", nDotSize, false);
		} else { // 소숫점이 존재할 경우

			String strDotValue = strTarget.substring(nPosDot + 1); // 소숫점 이하 값
			strTarget = strTarget.substring(0, nPosDot); // 정수 값

			if (strDotValue.length() >= nDotSize) { // 실제 소숫점 길이가 지정한 길이보다 크다면
														// 지정한 소숫점 길이 만큼 잘라내기
				strDot = "." + strDotValue.substring(0, nDotSize);
			} else { // 실제 소숫점길이가 지정한 길이보다 작다면 지정한 길이만큼 채우기
				strDot = "." + padValue(strDotValue, "0", nDotSize, false);
			}
		}
		return strTarget + strDot;
	}

	/**
	 * 대상 문자열을 날짜 포멧형 문자열로 변환하기
	 *
	 * @param strTarget
	 *            대상 문자열
	 * @return 날짜 포멧 문자열로 변환하기
	 */
	public static String formatDate(String strTarget) {
		String strValue = removeFormat(strTarget);

		if (strValue.length() != 8)
			return strTarget;

		StringBuilder objSB = new StringBuilder(strValue);
		objSB.insert(4, "-");
		objSB.insert(7, "-");

		return objSB.toString();
	}

	/**
	 * 대상 문자열을 주민등록번호 포멧 문자열로 변환하기
	 *
	 * @param strTarget
	 *            대상 문자열
	 * @return 주민등록번호 포멧 문자열
	 */
	public static String formatJuminID(String strTarget) {
		String strValue = removeFormat(strTarget);

		if (strValue.length() != 8)
			return strTarget;

		StringBuilder objSB = new StringBuilder(strValue);
		objSB.insert(4, "-");
		objSB.insert(7, "-");

		return objSB.toString();
	}

	/**
	 * 대상 문자열을 전화번호 포멧 문자열로 변환하기
	 *
	 * @param strTarget
	 *            대상문자열
	 * @return 전화번호 포멧 문자열
	 */
	public static String formatPhone(String strTarget) {
		String strValue = removeFormat(strTarget);
		int nLength = strValue.length();

		if (nLength < 9 || nLength > 12) // 9 ~ 12 占쏙옙' 占쏙옙占쏙옙 占쏙옙占�
			return strTarget;

		StringBuilder objSB = new StringBuilder(strValue);

		if (strValue.startsWith("02") == true) { // 서울지역일 경우
			if (nLength == 9) {
				objSB.insert(2, "-");
				objSB.insert(6, "-");
			} else {
				objSB.insert(2, "-");
				objSB.insert(7, "-");
			}
		} else { // 서울외 지역 또는 휴대폰 일 경우
			if (nLength == 10) {
				objSB.insert(3, "-");
				objSB.insert(7, "-");
			} else { // 내선번호등과 같은 특수 번호일 경우
				objSB.insert(3, "-");
				objSB.insert(8, "-");
			}
		}
		return objSB.toString();
	}

	/**
	 * 대상문자열을 우편번호 포멧형식으로 변환하기
	 *
	 * @param strTarget
	 *            대상문자열
	 * @return 우편번호 포멧형 문자열
	 */
	public static String formatPost(String strTarget) {
		String strValue = removeFormat(strTarget);

		if (strValue.length() != 6)
			return strTarget;

		StringBuilder objSB = new StringBuilder(strValue);
		objSB.insert(3, "-");

		return objSB.toString();
	}

	/**
	 * 문자열 Byte Length 구하기
	 *
	 * @param str
	 * @return
	 */
	public static int getByteLength(String str) {

		int strLength = 0;

		char tempChar[] = new char[str.length()];

		for (int i = 0; i < tempChar.length; i++) {
			tempChar[i] = str.charAt(i);

			if (tempChar[i] < 128) {
				strLength++;
			} else {
				strLength += 2;
			}
		}

		return strLength;
	}

	/**
	 * HTML 태그 제거하기
	 *
	 * @param strTarget
	 *            대상문자열
	 * @return 태그가 제거된 문자열
	 */
	public static String removeHTML(String strTarget) {
		if (strTarget == null || strTarget.equals(""))
			return strTarget;

		return strTarget.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
	}

	/**
	 * HTML을 캐리지 리턴 값으로 변환하기
	 *
	 * @param strTarget
	 *            대상 문자열
	 * @return HTML을 캐리지 리턴값으로 반환한 문자열
	 */
	public static String encodingHTML(String strTarget) {
		if (strTarget == null || strTarget.equals(""))
			return strTarget;

		strTarget = strTarget.replaceAll("<br>", "\r\n");
		strTarget = strTarget.replaceAll("<q>", "'");
		strTarget = strTarget.replaceAll("&quot;", "\"");
		return strTarget;
	}

	/**
	 * 캐리지리턴값을 HTML 태그로 변환하기
	 *
	 * @param strTarget
	 *            대상 문자열
	 * @return 캐리지 리턴값을 HTML 태그로 변환한 문자열
	 */
	public static String decodingHTML(String strTarget) {
		if (strTarget == null || strTarget.equals(""))
			return strTarget;

		strTarget = strTarget.replaceAll("\r\n", "<br/>");
		strTarget = strTarget.replaceAll("\u0020", "&nbsp;");
		strTarget = strTarget.replaceAll("'", "<q>");
		strTarget = strTarget.replaceAll("\"", "&quot;");
		return strTarget;
	}

	/**
	 * <p>
	 * 문자열(String)을 카멜표기법으로 표현한다.
	 * </p>
	 *
	 * <pre>
	 * StringUtils.toUnCamelCase("ItemCode") = "ITEM_CODE"
	 * </pre>
	 *
	 * @param str
	 *            문자열
	 * @return 카멜표기법을 일반 디비 문자열
	 */
	public static String toUnCamelCase(String str) {
		String regex = "([a-z])([A-Z])";
		String replacement = "$1_$2";
		String value = "";
		value = str.replaceAll(regex, replacement).toUpperCase();
		return value;
	}

	/**
	 * <p>
	 * 문자열(String)을 카멜표기법으로 표현한다.
	 * </p>
	 *
	 * <pre>
	 * StringUtils.toCamelCase("ITEM_CODE", true)  = "ItemCode"
	 * StringUtils.toCamelCase("ITEM_CODE", false) = "itemCode"
	 * </pre>
	 *
	 * @param str
	 *            문자열
	 * @param firstCharacterUppercase
	 *            첫문자열을 대문자로 할지 여부
	 * @return 카멜표기법으로 표현환 문자열
	 */
	public static String toCamelCase(String str, boolean firstCharacterUppercase) {
		if (str == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder();

		boolean nextUpperCase = false;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);

			if (c == '_') {
				if (sb.length() > 0) {
					nextUpperCase = true;
				}
			} else {
				if (nextUpperCase) {
					sb.append(Character.toUpperCase(c));
					nextUpperCase = false;
				} else {
					sb.append(Character.toLowerCase(c));
				}
			}
		}

		if (firstCharacterUppercase) {
			sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		}
		return sb.toString();
	}

	/**
	 * <p>
	 * 문자열(String)을 카멜표기법으로 표현한다.
	 * </p>
	 *
	 * <pre>
	 * StringUtils.toCamelCase("ITEM_CODE") = "itemCode"
	 * </pre>
	 *
	 * @param str
	 *            문자열
	 * @return 카멜표기법으로 표현환 문자열
	 */
	public static String toCamelCase(String str) {
		return toCamelCase(str, false);
	}

	/**
	 * 대상문자열이 null 인지 여부 확인하기
	 *
	 * @paramstrTarget 대상 문자열
	 * @return null 여부
	 */
	public static boolean isEmpty(Object str) {
		return ((str == null) || ("".equals(str)));
	}

	public static boolean isEmpty(String str) {
		return StringUtils.isEmpty(str);
	}

	public static boolean isNotEmpty(String str) {
		return StringUtils.isNotEmpty(str);
	}

	public static boolean isBlank(String str) {
		return StringUtils.isBlank(str);
	}

	public static boolean isNotBlank(String str) {
		return StringUtils.isNotBlank(str);
	}

	public static boolean isNumeric(String str) {
		return StringUtils.isNumeric(str);
	}

	public static boolean isNumericSpace(String str) {
		return StringUtils.isNumericSpace(str);
	}

	public static String[] splitEnter(String str) {
		return StringUtils.split(str, "\r\n");
	}

	public static String[] split(String str, String separatorChars) {
		return StringUtils.split(str, separatorChars);
	}

	/**
	 * <pre>
	 * 설명     : 입력받은 숫자 자리수만큼의 숫자를 리턴한다.
	 * &#64;param length 랜덤으로 생성할 문자열 길이
	 * &#64;return 입력받은 파라메터 길이만큼 랜덤한 0-9까지의 문자열
	 * </pre>
	 */
	public static String randomNumeric(int length) {
		return RandomStringUtils.randomNumeric(length);
	}

	/**
	 * <pre>
	 * 설명     : 128bit Random UiqueID를 생성한다.
	 * &#64;return
	 * </pre>
	 */
	public static String getUniqueId() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public static <T> boolean isEmpty(List<T> list) {
		if (list == null || list.size() == 0)
			return true;
		return false;
	}

	public static String phoneNumber(String s) {
		if (isEmpty(s)) {
			return "";
		} else {
			String result = "";
			if (s.length() == 9) {
				result = s.substring(0, 2) + "-" + s.substring(2, 5) + "-" + s.substring(5);
			} else if (s.length() == 10) {
				if ("02".equals(s.substring(0, 2))) {
					result = s.substring(0, 2) + "-" + s.substring(2, 6) + "-" + s.substring(6);
				} else {
					result = s.substring(0, 3) + "-" + s.substring(3, 6) + "-" + s.substring(6);
				}
			} else {
				result = s.substring(0, 3) + "-" + s.substring(3, 7) + "-" + s.substring(7);
			}

			return result;
		}
	}

	public static String bizNo(String s) {
		if (isEmpty(s)) {
			return "";
		} else {
			String result = "";
			if (s.length() == 10) {
				result = s.substring(0, 3) + "-" + s.substring(3, 5) + "-" + s.substring(5);
			}
			return result;
		}
	}

	public static String filterText(String sText, String regx) {
		Pattern p = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(sText);

		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, maskWord(m.group()));
		}
		m.appendTail(sb);

		return sb.toString();
	}

	public static String maskWord(String word) {
		StringBuilder buff = new StringBuilder();
		char[] ch = word.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			// if (i < 1) {
			// buff.append(ch[i]);
			// } else {
			buff.append("*");
			// }
		}
		return buff.toString();
	}

	public static String maskWord(Object word, int length, String maskingChar) {
		if (word == null || "".equals(word.toString().trim())) {
			return "";
		} else {
			StringBuilder buff = new StringBuilder();
			char[] ch = word.toString().toCharArray();

			for (int i = 0; i < ch.length; i++) {
				if (i < length) {
					buff.append(ch[i]);
				} else {
					buff.append("*");
				}
			}
			return buff.toString();
		}
	}

	/**
	 * <p>
	 * 문자열(String- 클래스명등)의 첫자를 대문자로 변환
	 * </p>
	 *
	 * <pre>
	 * StringUtils.firstCharUpperCase("itemCode") = "ItemCode"
	 * </pre>
	 *
	 * @param str
	 *            문자열
	 * @return 문자열(String- 클래스명등)의 첫자를 대문자로 변환한 문자열
	 */
	public static String firstCharUpperCase(String str) {
		if (str == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(str);
		sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		return sb.toString();
	}

	/**
	 * <p>
	 * 문자열(String- 클래스명등)의 첫자를 소문자로 변환
	 * </p>
	 *
	 * <pre>
	 * StringUtils.firstCharLowerCase("ItemCode") = "itemCode"
	 * </pre>
	 *
	 * @param str
	 *            문자열
	 * @return 문자열(String- 클래스명등)의 첫자를 소문자로 변환한 문자열
	 */
	public static String firstCharLowerCase(String str) {
		if (str == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(str);
		sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
		return sb.toString();
	}

	public static String encode(String str, String charset) {
		StringBuilder sb = new StringBuilder();
		try {
			byte[] key_source = str.getBytes(charset);
			for (byte b : key_source) {
				String hex = String.format("%02x", b).toUpperCase();
				sb.append("%");
				sb.append(hex);
			}
		} catch (UnsupportedEncodingException e) {
			log.debug("UnsupportedEncodingException", e);
		} // Exception
		return sb.toString();
	}

	public static String decode(String hex, String charset) {
		byte[] bytes = new byte[hex.length() / 3];
		int len = hex.length();
		for (int i = 0; i < len;) {
			int pos = hex.substring(i).indexOf("%");
			if (pos == 0) {
				String hex_code = hex.substring(i + 1, i + 3);
				bytes[i / 3] = (byte) Integer.parseInt(hex_code, 16);
				i += 3;
			} else {
				i += pos;
			}
		}
		try {
			return new String(bytes, charset);
		} catch (UnsupportedEncodingException e) {
			log.debug("UnsupportedEncodingException", e);
		} // Exception
		return "";
	}

	public static String changeCharset(String str, String charset) {
		try {
			byte[] bytes = str.getBytes(charset);
			return new String(bytes, charset);
		} catch (UnsupportedEncodingException e) {
			log.debug("UnsupportedEncodingException", e);
		} // Exception
		return "";
	}

	public static String utf8TOeuckr(String value) {
		String result = null;
		try {
			// String temp = new String(value.getBytes("utf-8"), "utf-8");
			result = new String(value.getBytes("utf-8"), "euc-kr");
		} catch (UnsupportedEncodingException e) {
			log.debug("인코딩변환중 에러 UTF-8 => EUC-KR [{}]", value);
			log.error("utf8TOeuckr", e);
		}
		return result;
	}

	public static String euckrTOutf8(String value) {
		String result = null;
		try {
			// String temp = new String(value.getBytes("euc-kr"), "euc-kr");
			result = new String(value.getBytes("euc-kr"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			log.debug("인코딩변환중 에러 EUC-KR => UTF-8 [{}]", value);
			log.error("euckrTOutf8", e);
		}
		return result;
	}

	/**
	 * trim 처리 NullPointerException 없이 처리
	 * strTarget이 null 이거나 화이트스페이스 일 경우 strTarget을 반환한다.
	 *
	 * @param strTarget
	 *            대상 문자열
	 * @return strTarget이 null 이거나 화이트스페이스 일 경우 strTarget 문자열을 그대로 반환
	 */
	public static String trim(String strTarget) {
		String retValue = null;

		if (strTarget == null || "".equals(strTarget)) {
			retValue = strTarget;
		} else {
			retValue = strTarget.trim();
		}
		return retValue;
	}

	/**
	 * 이름 마스킹(masking) 처리
	 * strTarget이 null 이거나 화이트스페이스 일 경우 strTarget을 반환한다.
	 *
	 * @param strTarget
	 *            대상 문자열
	 * @return 첫 문자를 제외한 나머지 문자를 *로 치환하여 반환한다.
	 */
	public static String maskingName(String strTarget) {
		String retValue = null;

		if (strTarget == null || "".equals(strTarget)) {
			retValue = strTarget;
		} else {
			retValue = strTarget.replaceAll("(?<=.{1}).", "*");
		}

		return retValue;
	}

	/**
	 * 전화번호 문자열을 배열형태로 변환하여 리턴한다.
	 * str 이 null이거나 자리수가 모자라도 size 값 만큼의 배열을 리턴한다.
	 *
	 * @param str
	 *            대상 문자열
	 * @param size
	 *            배열리턴 size
	 */
	public static String[] splitPhone(String str, int size) {
		if (size < 1) {
			throw new IllegalArgumentException("배열선언 사이즈는 1보다 커야합니다.");
		}

		String strtemp = StringUtil.nvl(str);

		String strreturn[] = new String[size];

		/* DB에 -를 붙인 형태로 저장 */
		// String strtelformat = StringUtil.formatPhone(strtemp);

		String strsplit[] = StringUtil.split(strtemp, "-");
		for (int i = 0; i < size; i++) {
			if (i < strsplit.length) {
				strreturn[i] = strsplit[i];
			} else {
				strreturn[i] = "";
			}
		}
		return strreturn;
	}

	public static String convert(String str, String encoding) throws IOException {
		ByteArrayOutputStream requestOutputStream = new ByteArrayOutputStream();
		requestOutputStream.write(str.getBytes(encoding));
		return requestOutputStream.toString(encoding);
	}

	/**
	 * <pre>
	 * 작성일 : 2016. 8. 9.
	 * 작성자 : wtKim
	 * 설명   : 문자열의 캐릭터셋을 변경한다.
	 *
	 * 수정내역(수정일 수정자 - 수정내용)
	 * -------------------------------------------------------------------------
	 * 2016. 8. 9. wtKim - 최초생성
	 * </pre>
	 *
	 * @param s
	 *            변경할 문자열
	 * @param from
	 *            소스 캐릭터셋
	 * @param to
	 *            타겟 캐릭터셋
	 * @return
	 * @throws IOException
	 */
	public static String convertCharset(String s, String from, String to) throws IOException {
		byte[] fromStringBuilder = s.getBytes(Charset.forName(from));
		log.debug("{} - length : {}", from, fromStringBuilder.length);
		String sourceStr = new String(fromStringBuilder, from);
		log.debug("String from {} : {}", from, sourceStr);

		// String 을 타겟 캐릭터셋으로 인코딩.
		byte[] toStringBuilder = sourceStr.getBytes(to);

		log.debug("{} - length : {}", to, toStringBuilder.length);
		String targetStr = new String(toStringBuilder, to);
		log.debug("String from {} : {}", to, targetStr);

		return targetStr;
	}

	/**
	 * <p>
	 * 문자열 Bean 메소드 형식으로 변환
	 * 첫번째 소문자 두번째 대문자의 String의 경우 빈메소드 형식으로
	 * 첫번째 두번째를 모두 대문자로 처리한다.
	 * </p>
	 *
	 * <pre>
	 * StringUtils.convertBeanName("itemCode") = "itemCode"
	 * StringUtils.convertBeanName("iTemCode") = "ITemCode"
	 * </pre>
	 *
	 * @param str
	 *            문자열
	 * @return 문자열 Bean 메소드 형식으로 변환
	 */
	public static String convertBeanName(String str) {
		if (str == null) {
			return null;
		}
		if (str.length() < 2) {
			return str;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(str);
		//첫번째 문자 소문자, 두번째 문자 대문자일 경우 첫번재 문자를 대문자로 바꾼다.
		if (Character.isLowerCase(sb.charAt(0)) && Character.isUpperCase(sb.charAt(1))) {
			sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		}
		return sb.toString();
	}
}
