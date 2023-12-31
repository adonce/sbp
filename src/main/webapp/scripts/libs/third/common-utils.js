/*
 * This file is generated under this project 
 *
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * @copyright: 
 * @package: 
 * @license: 
 * @url: 
 * @require: 
 * @since: 2015. 8. 25. 오전 11:10:37
 */

/**
 * 
 */
CommonUtils = function() {
};

/**
 * 
 * @param string
 * @returns {Boolean}
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * @since 2015. 8. 25.
 */
CommonUtils.availableString = function(string) {
	return Object.available(string) && string.trim().length > 0;
}

/**
 * 빈값인지 확인한다.
 */
CommonUtils.isEmpty = function(val) {
	return (val == undefined || val == null || val == "") ? true : false;
};

/**
 * 비밀번호 요구조건 만족 여부를 반환한다.
 * 
 * @param password
 * @returns {Boolean}
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * @since 2015. 8. 25.
 */
CommonUtils.availablePassword = function(password) {

	if (!Object.available(password)) {
		return false;
	}

	password = password.trim();

	if (!/.{8,32}/g.test(password)) {
		return false;
	}

	if (CommonUtils.containsWhiteSpace(password)) {
		return false;
	}

	if (!CommonUtils.containsLowercase(password)) {
		return false;
	}

// if (!CommonUtils.containsUppercase(password)) {
// return false;
// }

	if (!CommonUtils.containsDigit(password)) {
		return false;
	}

	if (!CommonUtils.containsSpecialCharacter(password)) {
		return false;
	}

	return true;
}

/**
 * 00시 00분 00초로 초기화 해서 milliseconds 형태로 반환
 */
CommonUtils.setDateInitStart = function(date) {
	
	let milliTime = null;
	
  date.setHours(0);
  date.setMinutes(0);
  date.setSeconds(0);
  date.setMilliseconds(0);
  
  milliTime = date.getTime();
  
	return milliTime;
};

/**
 * 23시 59분 59초로 초기화 해서 milliseconds 형태로 반환
 */
CommonUtils.setDateInitEnd = function(date) {
	
	let milliTime = null;
	
	date.setHours(23);
	date.setMinutes(59);
	date.setSeconds(59);
	date.setMilliseconds(999);
	
	milliTime = date.getTime();
	
	return milliTime;
};

/**
 * 비밀번호 요구조건 만족 여부를 반환한다.(확장)
 * 
 * @return 결과와 메시지 객체
 */
CommonUtils.availablePasswordResult = function(password) {
	var resObj = {
			result : true,
			message : null
	}
	
	if (!Object.available(password)) {
		resObj.result = false;
		resObj.message = "";
		return resObj;
	}
	
	password = password.trim();
	
	// if (!/.{8,16}/g.test(password)) {
	if (!/.{8}/g.test(password)) {
		resObj.result = false;
		resObj.message = "최소8자리 이상으로 입력하세요.";
		return resObj;
	}
	
	if (CommonUtils.containsWhiteSpace(password)) {
		resObj.result = false;
		resObj.message = "공백을 제거해주세요.";
		return resObj;
	}
	
	if (!CommonUtils.containsLowercase(password)) {
		resObj.result = false;
		resObj.message = "소문자를 사용해주세요.";
		return resObj;
	}
	
// if (!CommonUtils.containsUppercase(password)) {
// return false;
// }
	
	if (!CommonUtils.containsDigit(password)) {
		resObj.result = false;
		resObj.message = "숫자를 사용하세요.";
		return resObj;
	}
	
	if (!CommonUtils.containsSpecialCharacter(password)) {
		resObj.result = false;
		resObj.message = "특수문자를 사용하세요.";
		return resObj;
	}
	
	return resObj;
}

/**
 * 알파벳 소문자 포함여부를 반환한다.
 * 
 * @param str
 * @returns {Boolean}
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * @since 2015. 8. 25.
 */
CommonUtils.containsLowercase = function(str) {
	return /[a-z]+/g.test(str);
}

/**
 * 알파벳 대문자 포함여부를 반환한다.
 * 
 * @param str
 * @returns {Boolean}
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * @since 2015. 8. 25.
 */
CommonUtils.containsUppercase = function(str) {
	return /[A-Z]+/g.test(str);
}

/**
 * 숫자 포함여부를 반환한다.
 * 
 * @param str
 * @returns {Boolean}
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * @since 2015. 8. 25.
 */
CommonUtils.containsDigit = function(str) {
	return /[0-9]+/g.test(str);
}

/**
 * 특수문자 포함여부를 반환한다.<br>
 * 
 * <pre>
 * ! @ # \ $ % &circ; &amp; * ( ) _ + -
 * </pre>
 * 
 * @param str
 * @returns {Boolean}
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * @since 2015. 8. 25.
 */
CommonUtils.containsSpecialCharacter = function(str) {
	return /[!@#\$%\^&\*\(\)_+-]+/g.test(str);
}

/**
 * whitespace 포함여부를 반환한다.
 * 
 * @param str
 * @returns {Boolean}
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * @since 2015. 8. 25.
 */
CommonUtils.containsWhiteSpace = function(str) {
	return /[\s]+/g.test(str);
}

/**
 * 두 개의 비밀번호 조건 및 일치여부를 반환한다.
 * 
 * @param pwd1
 * @param pwd2
 * @returns {Boolean}
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * @since 2015. 8. 25.
 */
CommonUtils.matchPassword = function(pwd1, pwd2) {
	return CommonUtils.availablePassword(pwd1) //
			&& CommonUtils.availablePassword(pwd2) //
			&& pwd1 == pwd2;
}

/**
 * email 주소를 검증합니다.
 * 
 * @param email
 * @returns
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * @since 2015. 10. 5.
 */
CommonUtils.validateEmail = function(email){
	var regex = /^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$/i;
	return regex.test(email);
}




/**
 * IP를 Decimal 값으로 연산한다.
 */
CommonUtils.ipToDecimal = function (ip) {
    // a not-perfect regex for checking a valid ip address
	// It checks for (1) 4 numbers between 0 and 3 digits each separated by dots
	// (IPv4)
	// or (2) 6 numbers between 0 and 3 digits each separated by dots (IPv6)
	var ipAddressRegEx = /^(\d{0,3}\.){3}.(\d{0,3})$|^(\d{0,3}\.){5}.(\d{0,3})$/;
	var valid = ipAddressRegEx.test(ip);
	if (!valid) {
		return -1;
	}
	var dots = ip.split('.');
	// make sure each value is between 0 and 255
	for (var i = 0; i < dots.length; i++) {
		var dot = dots[i];
		if (dot > 255 || dot < 0) {
			return -1;
		}
	}
	if (dots.length == 4) {
		// IPv4
		return ((((((+dots[0])*256)+(+dots[1]))*256)+(+dots[2]))*256)+(+dots[3]);
	} else if (dots.length == 6) {
		// IPv6
		return ((((((((+dots[0])*256)+(+dots[1]))*256)+(+dots[2]))*256)+(+dots[3])*256)+(+dots[4])*256)+(+dots[5]);
	}
	return -1;
}

CommonUtils.cidrToDecimal = function (cidr) {
	
	if(!cidr){
		return -1;
	}
	var splitedCidr = cidr.split("/");
	if(splitedCidr.length < 2){
		return -1;
	}
	var ip = splitedCidr[0];
	var mask = splitedCidr[1];

	
	var ipAddressRegEx = /^(\d{0,3}\.){3}.(\d{0,3})$|^(\d{0,3}\.){5}.(\d{0,3})$/;
	var valid = ipAddressRegEx.test(ip);
	if (!valid) {
		return -1;
	}
	var dots = ip.split('.');
	// make sure each value is between 0 and 255
	for (var i = 0; i < dots.length; i++) {
		var dot = dots[i];
		if (dot > 255 || dot < 0) {
			return -1;
		}
	}
	if (dots.length == 4) {
		// IPv4
		return ((((((+dots[0])*256)+(+dots[1]))*256)+(+dots[2]))*256)+(+dots[3])+(mask*1);
	} else if (dots.length == 6) {
		// IPv6
		return ((((((((+dots[0])*256)+(+dots[1]))*256)+(+dots[2]))*256)+(+dots[3])*256)+(+dots[4])*256)+(+dots[5])+(mask*1);
	}
	return -1;
}

/**
 * 현재 날짜 반환
 * 
 * @param format
 * @returns
 */
CommonUtils.getCurrentTime = function(format) {
	var result = null;
	var date = new Date();
	
	var year = date.getFullYear().toString();
	var mon = (date.getMonth()+1).toString();
	var date_ = date.getDate().toString();
	var hour = date.getHours().toString();
	var min = date.getMinutes().toString();
	var sec = date.getSeconds().toString();
	
	if(format == "yyyyMMddHHmmss"){
		year = year[1] ? year : "0"+year[0];
		mon = mon[1] ? mon : "0"+mon[0];
		date_ = date_[1] ? date_ : "0"+date_[0];
		hour = hour[1] ? hour : "0"+hour[0];
		min = min[1] ? min : "0"+min[0];
		sec = sec[1] ? sec : "0"+sec[0];
		
		result = year + mon + date_ + hour + min + sec;
		
	}else if(format == "yyyy-MM-dd"){
		year = year[1] ? year : "0"+year[0];
		mon = mon[1] ? mon : "0"+mon[0];
		date_ = date_[1] ? date_ : "0"+date_[0];
		
		result = year + mon + date_;
	}
	
	return result;
};

function getTwoStr( num ){
	if(Number(num) < 10){
		return "0"+num;
	}
	return num;
}

/**
 * Date 포맷(yyyy-MM-dd HH:mm:ss)
 * 
 * @param date
 * @returns
 */
CommonUtils.getDateToString = function( input ) {
	
	if(input == null){
		return null;			
	}
	
	var date = new Date(input);
	
	var year = date.getFullYear();
	year = getTwoStr(year); 
	var mon = date.getMonth() + 1;
	mon = getTwoStr(mon); 
	var day = date.getDate();
	day = getTwoStr(day); 
	
	var hour = date.getHours();
	hour = getTwoStr(hour); 
	var min = date.getMinutes();
	min = getTwoStr(min); 
	var sec = date.getSeconds();
	sec = getTwoStr(sec); 
	
	return "%s-%s-%s %s:%s:%s".format(year, mon, day, hour, min, sec); 
// return "%s-%s-%s".format(year, mon, day);
}

/**
 * Date 포맷(yyyy-MM-dd)
 * 
 * @param date
 * @returns
 */	
CommonUtils.getDateToString = function( input ) {
	
	if(input == null){
		return null;			
	}
	
	var date = new Date(input);
	
	var year = date.getFullYear();
	year = getTwoStr(year); 
	var mon = date.getMonth() + 1;
	mon = getTwoStr(mon); 
	var day = date.getDate();
	day = getTwoStr(day); 
	
	return "%s-%s-%s".format(year, mon, day); 
}

/**
 * 파일명에서 확장자 추출
 */
CommonUtils.getExtensionOfFileName = function(fileName){
	
	var fileNameLength = fileName.length;
	
	var lastDot = fileName.lastIndexOf('.');
	var fileExtension = fileName.substring(lastDot, fileNameLength).toLowerCase();
	
	return fileExtension;
}

/**
 * Date 데이터를 세팅한다. 시작 날짜 또는 종료 날짜로 세팅 type - true : 시작날짜, false: 종료날짜
 */
// CommonUtils.setDate = function(date, type){
//	
// if(type){
// date.setHours(0);
// date.setMinutes(0);
// date.setSeconds(0);
// date.setMilliseconds(0);
// }else{
// date.setHours(23);
// date.setMinutes(59);
// date.setSeconds(59);
// date.setMilliseconds(59);
// }
//	
// return date.getTime();
// };

CommonUtils.toObject = function(arr) {
	  var rv = {};
	  for (var i = 0; i < arr.length; ++i){
		  if (arr[i] !== undefined){
			  rv[i] = arr[i];
		  } 
	  }
	  return rv;
}