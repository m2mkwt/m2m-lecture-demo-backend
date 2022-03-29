package kr.co.m2m.framework.util;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecurityUtil {

	/* 마스크를 업무적으로 안할 경우 마스크 방지 여부를 체크한는데  사용하는 키 */
	private static final String KEY_PROTECT = "MASK.PROTECT";

	/* 개인정보권한이 있는 사용사 있는지 구분하는 키 */
	private static final String KEY_UNMASK_AUTH = "MASK.UNMASKAUTH";

	/* 마스킹 여부를 관리하는 Thread 변수를 저장하는 저장소 */
	private static final ThreadLocal<Map<String, Boolean>> TL = new ThreadLocal<Map<String, Boolean>>();

	/* 마스킹 Char */
	private static final char MASKCHAR = '*';

	/* Password 길이를 Check한다 */
	private static int checkPwdLength(char[] plainText) {
		int lowerChar = 0, upperChar = 0, numberChar = 0, specialChar = 0;
		int pwdChar = 0;

		// 문자 종류별 포함 갯수 산정
		for (char echeChar : plainText) {
			if (echeChar >= 'a' && echeChar <= 'z') {
				lowerChar = 1;
			} else if (echeChar >= 'A' && echeChar <= 'Z') {
				upperChar = 1;
			} else if (echeChar >= '0' && echeChar <= '9') {
				numberChar = 1;
			} else {
				specialChar = 1;
			}
		}

		pwdChar = lowerChar + upperChar + numberChar + specialChar;

		// 8자리 미만인 경우		
		if (plainText.length < 8) {
			return 1;
			// 10자리 미만이면서 문자 종류가 3개 이상이 아닌 경우
		} else if (plainText.length < 10 && pwdChar < 3) {
			return 2;

			// 10자리 이상이면서 문자 종료가 2개 이상이 아닌 경우	
		} else if (plainText.length >= 10 && pwdChar < 2) {
			return 3;

			// 정상
		} else {
			return 0;
		}
	}

	// 반복 문자를 Check 한다 
	private static int checkRepeatChar(char[] plainText) {
		for (int inx = 0; inx < plainText.length - 3; inx++) {
			boolean isContinu = true;

			for (int jnx = 1; jnx < 3; jnx++) {
				if (plainText[inx] != plainText[inx + jnx]) {
					isContinu = false;
					break;
				}
			}

			// 반복 문자인 경우
			if (isContinu) {
				return 4;
			}
		}

		return 0;
	}

	// 키보드 연속 문자를 Check 한다
	private static int checkContChar(String plainText) {
		String checkSeeds[] = { "1234567890", "qwertyuiop", "asdfghjkl", "zxcvbnm", "abcdefghijklmnopqrstuvwxyz", "1qaz2wsx3edc4rfv5tgb6yhn7ujm8ik9ol0p",
				"qazwsxedcrfvtgbyhnujmikolp" };

		int result = 0;

		for (String checkSeed : checkSeeds) {
			result = checkPwdWord(plainText, checkSeed, 4);
			if (result > 0) {

				return 4 + result;
			}
		}

		return 0;

	}

	/* 잘 알려진 문자를 Check 한다 */
	private static int checkWellKnwon(String plainText) {

		String checkSeeds[] = {};

		int result = 0;
		int checkSeedLen = 4;

		for (String checkSeed : checkSeeds) {
			if (checkSeed.length() < 4) {
				checkSeedLen = checkSeed.length();
			}
			result = checkPwdWord(checkSeed, plainText, checkSeedLen);
			if (result > 0) {

				return 6 + result;
			}
		}

		return 0;

	}

	/* 문자내 특정 문자가 포함되어 있는지 점검한다 */
	private final static int checkPwdWord(String plainText, String checkSeed, int checkLen) {

		// 만약 seed가 1234567890 이면  1234567890123 으로 만들어 순환 연속 문자를 체크
		String forCheckSeed = checkSeed + checkSeed.substring(0, checkLen - 1);

		// seed 리버스하여 역으로 check
		String revCheckSeed = reverseString(forCheckSeed);
		for (int inx = 0; inx <= plainText.length() - checkLen; inx++) {
			String checkWord = plainText.substring(inx, inx + checkLen);

			// 순방향 연속 이면
			if (forCheckSeed.indexOf(checkWord) >= 0) {
				return 1;
			}

			// 역방향 연속이면
			if (revCheckSeed.indexOf(checkWord) >= 0) {
				return 2;
			}
		}

		return 0;

	}

	/* 입력 문자열을 리버스 하여 리턴한다 */
	private static String reverseString(String plainText) {
		char[] tempChar = plainText.toCharArray();
		StringBuilder sb = new StringBuilder(tempChar.length);

		for (int inx = tempChar.length - 1; inx >= 0; inx--) {
			sb.append(tempChar[inx]);
		}

		return sb.toString();
	}

	/**
	 * 
	 * @param plainText
	 *            평문 패스워드
	 * @return int Check 결과
	 *         -1 : 인수가 null 인 경우
	 *         0 : 정상
	 *         1 : 패스워드 길이가 8자 미만이 경우
	 *         2 : 패스워드 길이가 8자 이상 10자 미만인 경우 문자set이 3종료 안되는 경우
	 *         3 : 패스워드 길이가 10자 이상인다 문자 set이 2종류 미만이 경우
	 *         4 : 반복문자를 3글자 사용한 경우
	 *         5 : 연속문자, 키보드 배열문자를 4자 이상 연속 사용한 경우
	 *         6 : 연속문자를 역순으로 사용한 경우
	 *         7 : 잘 알려진 단어를 포함한 경우
	 *         8 : 잘 알려진 단어를 연순으로 포함한 경우
	 */
	public static int checkPassword(String plainText) {

		int result = 0;

		if (plainText == null) {
			return -1;
		}

		char[] tempText = plainText.toCharArray();

		// 1. 패스워드 길이 Check
		result = checkPwdLength(tempText);
		if (result != 0) {
			return result;
		}

		// 2. 반복문자 Check
		result = checkRepeatChar(tempText);
		if (result != 0) {
			return result;
		}

		// 3. 연속문자 Check
		result = checkContChar(plainText);
		if (result != 0) {
			return result;
		}

		// 4. 잘알려진 단어 Check
		result = checkWellKnwon(plainText);
		if (result != 0) {
			return result;
		}

		return result;

	}

	/**
	 * 
	 * @param plainText
	 *            평문 패스워드
	 * @param plainText
	 *            평문 패스워드
	 * @return int Check 결과
	 *         -1 : 인수가 null 인 경우, 개인 정보 문자열이 4자리 미만인 경우
	 *         0 : 정상
	 *         1 : 패스워드에 개인정보가 4자리 이상 포함된 경우
	 *         2 : 패스워드에 개인정보가 4자리 이상 역순으로 포함된 경우
	 */
	public static int checkPassword(String plainText, String privateWord) {

		int result = 0;

		if (plainText == null || privateWord == null) {
			return -1;
		}

		// formatting 되어 있는 문자가 있을 경우 formatting 제거하고 검증
		String tempPrivateWord = privateWord.replace("-", "").replace("/", "");

		// 개인정보는 4글자 이상 포함시 오류 처리 함으로 개인정보가 4자리 이하이면 오류 처리 한다.
		if (tempPrivateWord.length() < 4) {
			return -1;
		}

		// 포함 문자가 있는지 검증
		result = checkPwdWord(plainText, tempPrivateWord, 4);

		return result;
	}
}
