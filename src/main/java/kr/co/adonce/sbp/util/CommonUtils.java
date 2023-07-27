package kr.co.adonce.sbp.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import kr.co.adonce.sbp.pagination.PaginationResult;

public class CommonUtils {

	/**
	 * 
	 * 목록을 페이지네이션하여 해당하는 목록을 추출한다.
	 * 
	 * @param items            페이지네이션할 목록
	 * @param pageNum          페이지 번호
	 * @param itemCountPerPage 한 페이지당 보여줄 item 개수
	 * @return
	 * @throws NullPointerException If items is null.
	 */
	public static <T> PaginationResult<T> paginate(List<T> items, int pageNum, int itemCountPerPage)
			throws NullPointerException {

		PaginationResult<T> pagination = new PaginationResult<T>();

		List<T> paginatedItems = new ArrayList<>();

		for (int i = (pageNum - 1) * itemCountPerPage; i < items.size() && i <= pageNum * itemCountPerPage - 1; i++) {
			paginatedItems.add(items.get(i));
		}

		pagination.setTotalCount(items.size());
		pagination.setItems(paginatedItems);
		pagination.setItemCountPerPage(itemCountPerPage);
		pagination.setPageNum(pageNum);

		return pagination;
	}

	/** 전자우편 주소 패턴 */
	private static final Pattern emailPattern = Pattern.compile(
			"^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$",
			Pattern.CASE_INSENSITIVE);

	/** 전화번호 패턴 */
	private static final Pattern MOBILE_PATTERN1 = Pattern.compile("\\d{11}");
	private static final Pattern MOBILE_PATTERN2 = Pattern.compile("\\+\\d{11}");
	private static final Pattern SECURITY_USER_PHONE_NUMBER_23_4_4 = Pattern
			.compile("\\b\\(?([0-9]{2,3})\\)?[-.\\s]?([0-9]{4})[-.\\s]?([0-9]{4}).*");

	public CommonUtils() {
	}

	/**
	 * Long 데이터를 Date 형태로 변환후 String으로 리턴한다.
	 * 
	 * @param timestamp
	 * @return
	 */
	public static String formatTime(Long timestamp) {

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String time = df.format(timestamp);

		return time;
	}

	/**
	 * 사용할 ID를 생성 한다.
	 * 
	 * 
	 * @param userId
	 * @return
	 */
	public static String createUuid() {

		UUID uuid = UUID.randomUUID();

		return uuid.toString();
	}

	/**
	 * 
	 * <br>
	 * 
	 * <pre>
	 * [개정이력]
	 *      날짜    	| 작성자	|	내용
	 * ------------------------------------------
	 * 2018. 3. 16.		  jhlee		최초 작성
	 * </pre>
	 * 
	 * @param milliseconds
	 * @return
	 */
	public static String getDateFormat(long milliseconds, String format) {

		SimpleDateFormat sfd = new SimpleDateFormat(format);

		Date date = new Date(milliseconds);

		return sfd.format(date);
	};

	/**
	 * URL을 디코딩한다.
	 * 
	 * @param str request 파라미터
	 * @param enc 인코딩
	 * @return
	 * @throws UnsupportedEncodingException
	 * 
	 * @see {@link URLDecoder}
	 */
	public static String decodeUrl(String str, String enc) throws UnsupportedEncodingException {
		return str != null ? URLDecoder.decode(str, enc) : null;
	}

	/**
	 * URL을 UTF-8로 디코딩한다.
	 * 
	 * @param str request 파라미터
	 * @return
	 * @throws UnsupportedEncodingException
	 * 
	 * @see {@link URLDecoder}
	 */
	public static String decodeUrlAsUTF8(String str) throws UnsupportedEncodingException {
		return decodeUrl(str, "UTF-8");
	}

	/**
	 * 현재 시간을 보다 작은 가장 가까운 5분 단위 시간으로 변환하여 제공한다. <br>
	 * 
	 * <pre>
	 * [개정이력]
	 *      날짜    	| 작성자	|	내용
	 * ------------------------------------------
	 * 2018. 5. 16.		박준홍			최초 작성
	 * </pre>
	 *
	 * @return
	 *
	 * @since 2018. 5. 16.
	 */
	public static long getTimestampBy5min() {

		Calendar cal = GregorianCalendar.getInstance();

		int min = cal.get(Calendar.MINUTE);

		min -= (min % 5);

		cal.set(Calendar.MINUTE, min);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTimeInMillis();
	}

	/**
	 * 전자우편 주소에 양식을 검증한다.
	 * 
	 * @param email
	 * @return
	 *
	 */
	public static boolean validateEmail(String email) {
		return emailPattern.matcher(email).matches();
	}

	/**
	 * 전화번호 양식을 검증한다.
	 * 
	 * @param input
	 * @return
	 *
	 */
	public static boolean isValidationPhoneNumber(String input) {
		if (input == null)
			return false;
		else if (CommonUtils.MOBILE_PATTERN1.matcher(input).matches())
			return true;
		else if (CommonUtils.MOBILE_PATTERN2.matcher(input).matches())
			return true;
		// 추가
		else if (CommonUtils.SECURITY_USER_PHONE_NUMBER_23_4_4.matcher(input).matches())
			return true;
		else
			return false;
	}

	/**
	 * 잘못된 비밀번호 이유
	 * 
	 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
	 */
	public enum PwdInvalidReason {
		/** 특수문자 없음 */
		NO_SPECIAL_CHARACTER(0, "비밀번호에 특수문자가 포함되어 있지 않음."),
		/** 숫자 없음 */
		NO_DIGIT(1, "숫자가 포함되어 있지 않음."),
		/** 알파벳 소문자 없음 */
		NO_LOWERCASE(2, "알파벳 소문자가 포함되어 있지 않음."),
		/** 알파벳 대문자 없음 */
		NO_UPPERCASE(3, "알파벳 대문자가 포함되어 있지 않음."),
		/** 너무 짧음 */
		TOO_SHORT(4, "비밀번호 길이가 8자보다 짧음."),
		/** 너무 긴 */
		TOO_LONG(5, "비밀번호 길이가 16자보다 김."),
		/** 2개의 비밀번호 불일치 */
		NOT_EQUAL(6, "2개의 비밀번호가 일치하지 않음.");

		private int feature;
		private String msg;

		private PwdInvalidReason(int feature) {
			this.feature = feature;
		}

		private PwdInvalidReason(int feature, String msg) {
			this.feature = feature;
			this.msg = msg;
		}

		public String getMessage() {
			return this.msg;
		}

		/**
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return name() + "[feature: " + this.feature + (this.msg != null ? ", message: " + msg : "") + "]";
		}

		public static PwdInvalidReason getReason(int feature) {
			try {
				switch (feature) {
				case 0:
					return NO_SPECIAL_CHARACTER;
				case 1:
					return NO_DIGIT;
				case 2:
					return NO_LOWERCASE;
				case 3:
					return NO_UPPERCASE;
				case 5:
					return TOO_SHORT;
				case 6:
					return TOO_LONG;
				default:
					throw new IllegalArgumentException("잘못된 비밀번호 요구사항입니다. 적용된 요구사항: "
							+ (Arrays.toString(PwdInvalidReason.values())) + ", feature: " + feature);
				}
			} catch (Exception e) {
				throw e;
			}
		}
	}
}
