package kr.co.adonce.sbp.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;

public class ConvertUtils {

	/**
	 * Array to Json 문자열로 변환하는 함수
	 * 
	 * @param arr
	 *            String Array 객체
	 * @return
	 *
	 */
	public static String convertArrToJson(List<String> arr) {

		String convertStr = null;

		String[] convertStrArr = new String[arr.size()];
		convertStrArr = arr.toArray(convertStrArr);

		JSONArray jsonArr = new JSONArray(Arrays.asList(convertStrArr));
		convertStr = jsonArr.toString();

		return convertStr;
	}

	/**
	 * Json to Array 리스트로 변환하는 함수
	 * 
	 * @param jsonStr
	 *            json 문자열
	 * @return
	 * @throws JSONException
	 *
	 */
	public static List<String> convertJsonToArr(String jsonStr) throws JSONException {

		JSONArray jsona = new JSONArray(jsonStr);
		List<String> convertList = new ArrayList<>();

		for (int i = 0; i < jsona.length(); i++) {
			convertList.add(jsona.getString(i));
		}

		return convertList;
	}

}
