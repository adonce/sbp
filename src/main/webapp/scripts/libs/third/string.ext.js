/**
 * 
 * 
 * This file is generated under this project, "OpenCommons Js".
 *
 * Date  : 200X. XX. XX. 오후 XX:XX:XX
 *
 * Author: Park Jun-Hong (fafanmama@naver.com)
 * 
 */

//↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
//↓↓↓↓↓↓↓↓↓↓↓↓↓ 'String' Object's Extended JavaScript functions↓↓↓↓↓↓↓↓↓↓↓↓
//↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
/**
 * 현재 문자열에 주어진 문자열이 존재하는지 여부를 반환한다.
 * 
 * @param searchValue
 * @returns {Boolean}
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * @since 2015. 1. 29.
 */
String.prototype.contains = function(searchValue) {
	return this.indexOf(searchValue) > -1;
};

/**
 * 현재 문자열이 주어진 문자열로 종료되는지 여부를 반환한다.
 * 
 * @param searchValue
 * @return
 * 
 * @author 박준홍 (fafanmama_at_naver_com)
 * @since 2011. 12. 5. 오후 6:53:40
 */
String.prototype.endsWith = function(searchValue) {
	return this.lastIndexOf(searchValue) + searchValue.length == this.length;
};

/**
 * 현재 문자열과 주어진 문자열이 대/소문자에 관계없이 동일한지 여부를 반환한다.
 * 
 * @param anotherString
 * @return
 * 
 * @author 박준홍 (fafanmama_at_naver_com)
 * @since 2011. 12. 5. 오후 6:59:45
 */
String.prototype.equalsIgnoreCase = function(anotherString) {
	if (typeof anotherString == "undefined" || anotherString == null) {
		return false;
	} else {
		return this.toLowerCase() == anotherString.toLowerCase();
	}
};

/**
 * <font color="RED">변수를 받는 부분은 %s 로 설정한다.</font>
 * 
 * <pre>
 * 예제: String.format(&quot;hi, nice to meet %s, I'm %s&quot;, &quot;Suzi..&quot;, &quot;John&quot;);
 * </pre>
 * 
 * @return
 * 
 * @author 박준홍 (fafanmama_at_naver_com)
 * @since 2011. 12. 5. 오후 11:14:57
 */
String.prototype.format = function() {
	var format = this;
	var varargs = arguments;

	var matches = format.match(/%-?\d*s/gi);

	if (matches.length != varargs.length) {
		throw Error("IllegalArgumentIndex: Required: " + matches.length + ", Provided: " + varargs.length + " #[" + varargs.toString() + "]");
	}

	var regex = /^(.*)%(-?\d*)s(.*)$/gi;

	var index = varargs.length - 1;
	var _var = null;
	matches.forEach(function(m, idx) {
		var lenstr = format.replace(regex, "$2");

		_var = varargs[index--];
		if (!lenstr.isEmptyString()) {
			var len = new Number(lenstr);

			var varlen = ("" + _var).length;

			var diff = Math.abs(len) - varlen;
			if (diff) {
				__var = [];

				for (var i = 0; i < diff; i++) {
					__var.push(" ");
				}
				__var.push(_var);

				// 좌측 정렬
				if (len < 0) {
					__var.reverse();
				}

				_var = __var.join("");
			}
		}

		format = format.replace(regex, "$1" + _var + "$3");

	}, varargs);

	return format;
};
/**
 * 
 * @param pad
 *            {string}:
 * @param varargs
 *            {...}:
 * @returns {String}
 */
String.prototype.formatPadding = function(pad) {
	var format = this;

	var varargs = [];

	for (var i = 1; i < arguments.length; i++) {
		varargs.push(arguments[i]);
	}

	var matches = format.match(/%-?\d*s/gi);

	if (matches.length != varargs.length) {
		throw Error("IllegalArgumentIndex: Required: " + matches.length + ", Provided: " + varargs.length + " #[" + varargs.toString() + "]");
	}

	var regex = /^(.*)%(-?\d*)s(.*)$/gi;

	var index = varargs.length - 1;
	var _var = null;
	matches.forEach(function(m, idx) {
		var lenstr = format.replace(regex, "$2");

		_var = varargs[index--];
		if (!lenstr.isEmptyString()) {
			var len = new Number(lenstr);

			var varlen = ("" + _var).length;

			var diff = Math.abs(len) - varlen;
			if (diff) {
				__var = [];

				for (var i = 0; i < diff; i++) {
					__var.push(pad);
				}
				__var.push(_var);

				// 좌측 정렬
				if (len < 0) {
					__var.reverse();
				}

				_var = __var.join("");
			}
		}

		format = format.replace(regex, "$1" + _var + "$3");

	}, varargs);

	return format;
};

String.prototype.hashCode = function() {
	var hash = 0, i, chr, len;
	if (this.length == 0)
		return hash;
	for (i = 0, len = this.length; i < len; i++) {
		chr = this.charCodeAt(i);
		hash = ((hash << 5) - hash) + chr;
		hash |= 0; // Convert to 32bit integer
	}
	return hash;
};

/**
 * 현재 문자열이 빈 문자열인지 여부를 반환한다.
 * 
 * @return
 * 
 * @author 박준홍 (fafanmama_at_naver_com)
 * @since 2011. 12. 20. 오전 11:20:57
 */
String.prototype.isEmpty = function() {
	return this.length < 1;
};

/**
 * 현재 문자열을 trim() 한 후 빈 문자열인지 여부를 반환한다.
 * 
 * @return
 * 
 * @author 박준홍 (fafanmama_at_naver_com)
 * @since 2011. 12. 20. 오전 11:22:44
 */
String.prototype.isEmptyString = function() {
	return this.trim().length < 1;
};

/**
 * {0}, {1} 패턴 지원
 * 
 * 사용예:
 * 
 * <pre>
 * var template = &quot;{0}는 {1}와 관계가 있다.&quot;;
 * var string = template.format(&quot;나&quot;, &quot;너&quot;);
 * 
 * console.log(string)
 * 
 * [result]나는 너와 관계가 있다.
 * </pre>
 * 
 * @returns
 */
String.prototype.messageformat = function() {
	var args = arguments;
	return this.replace(/{(\d+)}/g, function(match, number) {
		return typeof args[number] != 'undefined' ? args[number] : match;
	});
};

/**
 * {0}, {1} 패턴 지원
 * 
 * 사용예:
 * 
 * <pre>
 * var template = &quot;{0}는 {1}와 관계가 있다.&quot;;
 * var string = template.formatArray([ &quot;나&quot;, &quot;너&quot; ]);
 * 
 * console.log(string)
 * 
 * [result]나는 너와 관계가 있다.
 * </pre>
 * 
 * @param args
 * @returns
 */
String.prototype.messageformatArray = function(args) {
	return this.replace(/{(\d+)}/g, function(match, number) {
		return typeof args[number] != 'undefined' ? args[number] : match;
	});
};

/**
 * 
 * @param str
 * @param src
 * @param tgt
 * @returns
 */
String.prototype.replaceAll = function(oldStr, newStr) {
	return this.replace(new RegExp(oldStr, "g"), newStr);
};
/**
 * 현재 문자열이 주어진 문자열로 시작되는지 여부를 반환한다.
 * 
 * @param searchValue
 * @return
 * 
 * @author 박준홍 (fafanmama_at_naver_com)
 * @since 2011. 11. 21. 오후 3:55:04
 */
String.prototype.startsWith = function(searchValue) {
	return this.indexOf(searchValue, 0) == 0;
};
/**
 * 현재 문자열이 주어진 문자열로 시작되는지 여부를 반환한다.
 * 
 * @param searchValue
 * @return
 * 
 * @author 박준홍 (fafanmama_at_naver_com)
 * @since 2017. 10. 24.
 */
String.prototype.startsWith = function(searchValue, position) {
	if(!position){
		return this.indexOf(searchValue, 0) == 0;
	}else {
		return this.indexOf(searchValue, position) == position;
	}
};

/**
 * 현재 문자열의 왼쪽 whitespace를 제거한다.
 * 
 * @param string
 * @return
 * 
 * @author 박준홍 (fafanmama_at_naver_com)
 * @since 2011. 8. 30. 오전 8:25:46
 */
String.prototype.trimL = function() {
	return this.toString().replace(/^\s*/g, "");
},
/**
 * 현재 문자열의 오른쪽 whitespace를 제거한다.
 * 
 * @param string
 * @return
 * 
 * @author 박준홍 (fafanmama_at_naver_com)
 * @since 2011. 8. 30. 오전 8:25:50
 */
String.prototype.trimR = function() {
	return this.toString().replace(/\s*$/g, "");
},
/**
 * 현재 문자열의 좌/우 whitespace를 제거한다.
 * 
 * @param string
 * @return
 * 
 * @author 박준홍 (fafanmama_at_naver_com)
 * @since 2011. 8. 30. 오전 8:25:55
 */
String.prototype.trim = function() {
	return this.toString().replace(/(^\s*)|(\s*$)/g, "");
};

/**
 * 
 * @param separator
 * @returns {Array}
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * @since 2015. 1. 28.
 */
String.prototype.splitAsIntArray = function(separator) {
	var intArray = [];

	var strArray = this.split(separator);
	var v;
	for (var i = 0; i < strArray.length; i++) {
		if (!isNaN(v = parseInt(strArray[i]))) {
			intArray.push(v);
		}
	}

	return intArray;
};

/**
 * 
 * @param separator
 *            구분자
 * @returns {Array}
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * @since 2015. 1. 28.
 */
String.prototype.splitAndTrim = function(separator) {
	var strArray = this.split(separator);

	strArray.forEach(function(str, i, arr) {
		arr[i] = str.trim();
	});

	return strArray;
};

/**
 * 
 * @param index
 * @returns
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * @since 2014. 8. 25.
 */
String.prototype.toUpperCaseCharAt = function(index) {

	if (index == undefined || index < 0 || index > this.length) {
		return this.toUpperCase();
	} else {
		switch (index) {
		case 0:
			return this.charAt(0).toUpperCase() + this.slice(1);
		default:
			return this.slice(0, index) + this.charAt(index) + this.slice(index);
		}
	}
}


String.prototype.endsWith = function(searchString, position) {
	var subjectString = this.toString();
	if (typeof position !== 'number' || !isFinite(position) || Math.floor(position) !== position || position > subjectString.length) {
		position = subjectString.length;
	}
	position -= searchString.length;
	var lastIndex = subjectString.indexOf(searchString, position);
	return lastIndex !== -1 && lastIndex === position;
};

// ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
// ↑↑↑↑↑↑↑↑↑↑↑↑↑ 'String' Object's Extended JavaScript functions↑↑↑↑↑↑↑↑↑↑↑↑
// ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
