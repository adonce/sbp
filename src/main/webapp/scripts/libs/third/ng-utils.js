/*
 * This file is generated under this project, "com.lguplus.stams". 
 *
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * @copyright: 
 * @package: 
 * @license: 
 * @url: 
 * @require: 
 * @since: 2015. 8. 5. 오후 2:48:59
 */

NgRouteUtil = function() {
};

/**
 * Angular JS $routeProvider 를 통해서 전달하는 데이터를 ng-controller에서 $route를 통해서 획득하는 것을 지원.
 * 
 * @param route
 *            angular $route module.
 * @param name
 *            property name in $route.resolve property
 * @param params
 *            use for a property if a properly is function.
 * @returns
 * 
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * @since 2014. 8. 25.
 */
NgRouteUtil.resolve = function(route, name, params) {
	var property = route.current.$$route.resolve[name];

	return typeof property == "function" ? property.call(this, params) : property;
}

NgResourceUtil = function() {
};
NgResourceUtil.errorToStr = function(response) {
	return JSON.stringify(response, function(name, value) {
		if (name == "") {
			value.data = "{{discarded}}";
		}
		return value;
	}, "\t");
};

// ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
// ↓↓↓↓↓↓↓↓↓↓↓↓↓'for "ng-style" directive' JavaScript Object ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
// ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
Style = function() {
	this.map = new Map();
};

Style.prototype.apply = function(key, value, unit) {
	if (unit) {
		value = value + "" + unit;
	}

	this.map.put(key, value);

	return this;
};

Style.prototype.get = function(key) {
	return this.map.get(key);
};

Style.prototype.clear = function() {
	this.map.clear();
};

Style.prototype.toCSS = function() {
	var style = [];

	var entrySet = this.map.entrySet();
	var entry = null;
	for (var i = 0; i < entrySet.length; i++) {
		entry = entrySet[i];
		style.push(entry.key + ":" + entry.value);
	}

	return style.join(";");
};
// ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
// ↑↑↑↑↑↑↑↑↑↑↑↑↑'for "ng-style" directive' JavaScript Object ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
// ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

NgLogger = function(level) {
	this.level = Object.available(level) ? level : "debug";
};
NgLogger.prototype.setLevel = function(level) {
	if (Object.available(level)) {

		switch (level.toLowerCase()) {
		case "trace":
		case "debug":
		case "info":
		case "critical":
		case "error":
			this.level = level;
			break;
		default:
			break;
		}
	}
};
NgLogger.prototype.getLevel = function(level) {
	return this.level;
};
NgLogger.prototype.isTraceable = function() {
	return this.level.equalsIgnoreCase("trace");
};
NgLogger.prototype.isDebuggable = function() {
	return this.isTraceable() || this.level.equalsIgnoreCase("debug");
};
NgLogger.prototype.isInfoable = function() {
	return this.isDebuggable() || this.level.equalsIgnoreCase("info");
};
NgLogger.prototype.isCritical = function() {
	return this.isInfoable() || this.level.equalsIgnoreCase("critical");
};
NgLogger.prototype.isErrorable = function() {
	return this.isCritical() || this.level.equalsIgnoreCase("error");
};


//Date.prototype.getWeekOfMonth = function(exact) {
//    var month = this.getMonth()
//        , year = this.getFullYear()
//        , firstWeekday = new Date(year, month, 1).getDay()
//        , lastDateOfMonth = new Date(year, month + 1, 0).getDate()
//        , offsetDate = this.getDate() + firstWeekday - 1
//        , index = 1 // start index at 0 or 1, your choice
//        , weeksInMonth = index + Math.ceil((lastDateOfMonth + firstWeekday - 7) / 7)
//        , week = index + Math.floor(offsetDate / 7)
//    ;
//    if (exact || week < 2 + index) return week;
//    return week === weeksInMonth ? index + 5 : week;
//};

Date.prototype.getWeekOfMonth = function () {
    var dayOfMonth = this.getDay();
    var month = this.getMonth();
    var year = this.getFullYear();
    var checkDate = new Date(year, month, this.getDate());
    var checkDateTime = checkDate.getTime();
    var currentWeek = 0;

    for (var i = 1; i < 32; i++) {
        var loopDate = new Date(year, month, i);

        if (loopDate.getDay() == dayOfMonth) {
            currentWeek++;
        }

        if (loopDate.getTime() == checkDateTime) {
            return currentWeek;
        }
    }
};


var thousandDotted = function(x){
	
	  var parts = x.toString().split(".");
	  parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	  
	  return parts.join(".");
}

var tenThousandFormatter = function(number, toFixed){

	if( number < 500 && number > -500) {
		return number.toFixed(toFixed);
	}else{
		return thousandDotted((number / 10000).toFixed(toFixed)) + " 만";
	}
	
//	if( number < 1000 && number > -1000) {
//		return number.toFixed(toFixed);
//	}else if ( number < 10000 && number > -10000) {
//		return thousandDotted(number.toFixed(toFixed));
//	}else{
//		return thousandDotted((number / 10000).toFixed(toFixed)) + " 만";
//	}
}

Date.prototype.yyyymmdd = function() {
  var mm = this.getMonth() + 1; // getMonth() is zero-based
  var dd = this.getDate();

  return [this.getFullYear(),
          (mm>9 ? '' : '0') + mm,
          (dd>9 ? '' : '0') + dd
         ].join('');
};


var Utf8ArrayToStr = function(array) {
    var out, i, len, c;
    var char2, char3;

    out = "";
    len = array.length;
    i = 0;
    while(i < len) {
	    c = array[i++];
	    switch(c >> 4){ 
	      case 0: 
		  case 1:
		  case 2: 
		  case 3:
		  case 4: 
		  case 5: 
		  case 6:
		  case 7:
	        // 0xxxxxxx
	        out += String.fromCharCode(c);
	        break;
	      case 12: case 13:
	        // 110x xxxx   10xx xxxx
	        char2 = array[i++];
	        out += String.fromCharCode(((c & 0x1F) << 6) | (char2 & 0x3F));
	        break;
	      case 14:
	        // 1110 xxxx  10xx xxxx  10xx xxxx
	        char2 = array[i++];
	        char3 = array[i++];
	        out += String.fromCharCode(((c & 0x0F) << 12) |
	                       ((char2 & 0x3F) << 6) |
	                       ((char3 & 0x3F) << 0));
	        break;
	    }
    }

    return out;
}