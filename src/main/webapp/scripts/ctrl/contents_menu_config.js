// ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
// ↓↓↓↓↓↓↓↓↓↓↓↓↓'Content Menu' JavaScript Object ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
// ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
/**
 * 
 * @param key
 *          {string}: menu key
 * @param templateUrl
 *          {string}: template file's path
 * 
 * @param display
 *          {string}: UI 에 보여질 내용.
 * @param desc
 *          {string}: 메뉴 설명
 * @param role
 *          {array of number}: 롤 등급 배열
 * @param standalone
 *          {boolean}: 메뉴가 별도 URL로 처리되는지 여부
 * @param shadow
 *          {boolean}: 서브메뉴로 노출되지 않는지 여부
 * 
 * @returns
 * 
 * @author jaehwankim
 * @since 2023. 07. 25
 */
ContentMenu = function(key, templateUrl, display, desc, role, icon, standalone, shadow) {
	this.key = key;
	this.templateUrl = templateUrl;
	this.display = display;
	this.desc = desc;
	this.role = role;

	// ng-view 영역이 아닌 별도의 창으로 열리는 메뉴
	this.standalone = Object.available(standalone) ? standalone : false;

	this.icon = icon;

	// 실제 하위메뉴가 아닌 경우 (메뉴 구조에 맞추기 위함)
	this.shadow = Object.available(shadow) ? shadow : false;
	this.selected = false;
	this.menus = [];
};

ContentMenu.prototype.hasDesc = function() {
	return this.desc != undefined && this.desc != null && !this.desc.isEmptyString();
}

ContentMenu.prototype.toString = function() {
	return JSON.stringify(this, null, "\t");
}

ContentMenu.prototype.addSubMenu = function(menu) {
	this.menus.push(menu);
}
// ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
// ↑↑↑↑↑↑↑↑↑↑↑↑↑'Content Menu' JavaScript Object ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
// ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

// ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
// ↓↓↓↓↓↓↓↓↓↓↓↓↓'Content Menu Container' JavaScript Object ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
// ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
Content = function(content, userGrade) {
	this.id = Object.getPropertyValue(content, "id", null); // Content ID
	this.content = Object.getPropertyValue(content, "content", null); // Content 고육 식별값
	this.display = Object.getPropertyValue(content, "display", null); // UI에 보여질 Content 정보
	this.role = Object.getPropertyValue(content, "role", null); // 현재 콘텐츠 Role ID 배열
	this.menus = new FIFOMap(); // ContentMenu

	if (Object.available(content) && Object.available(content.menus)) {
		// 사용자 등급에 따라 하위 메뉴 선택적 추가
		// 이유: 동일한 페이지더라도 사용자 등급에 따라서 다르게 구성해야 하는 경우에 개발자들의 편의를 위해서 적용
		if (Object.available(userGrade)) {
			content.menus.forEach(function(elem, index, array) {
				if (elem.role.contains(userGrade)) {
					this.menus.put(elem.key, elem);
				}
			}, this);
		} else {
			content.menus.forEach(function(elem, index, array) {
				this.menus.put(elem.key, elem);
			}, this);
		}
	}

	this.current = null; // 현재 선택된 ContentMenu
	this.icon = Object.getPropertyValue(content, "icon", null);
	this.selected = false;
	this.standalone = Object.getPropertyValue(content, "standalone", null);
};

/**
 * 메뉴를 생성한다.
 * 
 * @param content
 *          {object} 메뉴 콘텐츠 Anonymous object
 * 
 * @param userGrade
 *          {number} 사용자 등급
 * 
 * @author jaehwankim
 * @since 2023. 07. 25
 */
Content.prototype.loadContents = function(content, userGrade) {
	this.id = Object.getPropertyValue(content, "id", null); // Content ID
	this.content = Object.getPropertyValue(content, "content", null); // Content 고육 식별값
	this.display = Object.getPropertyValue(content, "display", null); // UI에 보여질 Content 정보
	this.role = Object.getPropertyValue(content, "role", null); // 현재 콘텐츠 Role ID 배열
	this.menus = new FIFOMap(); // ContentMenu

	if (Object.available(content) && Object.available(content.menus)) {
		// 사용자 등급에 따라 하위 메뉴 선택적 추가
		// 이유: 동일한 페이지더라도 사용자 등급에 따라서 다르게 구성해야 하는 경우에 개발자들의 편의를 위해서 적용
		if (Object.available(userGrade)) {
			content.menus.forEach(function(elem, index, array) {
				if (elem.role.contains(userGrade)) {
					this.menus.put(elem.key, elem);
				}
			}, this);
		} else {
			content.menus.forEach(function(elem, index, array) {
				this.menus.put(elem.key, elem);
			}, this);
		}
	}

	this.current = null; // 현재 선택된 ContentMenu

	this.selected = false;
};

/**
 * 메뉴의 개수를 반환한다.
 * 
 * @returns
 * 
 * @author jaehwankim
 * @since 2023. 07. 25.
 */
Content.prototype.count = function() {
	return this.menus.size();
};

/**
 * 메뉴의 개수를 반환한다.
 * 
 * @returns {Number}
 * 
 * @author jaehwankim
 * @since 2023. 07. 25.
 */
Content.prototype.countOfMenus = function() {

	var count = 0;

	if (Object.available(this.menus)) {

		this.menus.values().forEach(function(elem, idx) {
			count += (elem.shadow ? 0 : 1);
		});
	}

	return count;
}

Content.prototype.getContentName = function(upperCase) {
	return upperCase ? this.content.toUpperCaseCharAt(0) : this.content;
};

Content.prototype.getCss = function() {

	var hasSub = this.countOfMenus() > 0 ? "has-sub " : "";

	return this.selected ? hasSub + "active" : hasSub;
}

Content.prototype.getUrl = function() {
	return "#/" + this.content;
};

Content.prototype.toString = function() {
	return JSON.stringify(this, null, "\t");
};

Content.prototype.updateCurrent = function(key) {
	if (this.menus.size() < 1) {
		return;
	}

	if (key == undefined || key == null) {
		this.current = this.menus.values()[0];
	} else if (typeof key == "number" && this.count() > key) {
		this.current = this.menus.values()[key];
	} else if (typeof key == "string") {
		this.current = this.menus.get(key);
	}

	// 현재 선택된 메뉴에 대해서 class="" 적용
	if (this.current != undefined && this.current != null) {
		this.current.selected = "active";
	}

};

// ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
// ↑↑↑↑↑↑↑↑↑↑↑↑↑'Content Menu Container' JavaScript Object ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
// ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

// ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
// ↓↓↓↓↓↓↓↓↓↓↓↓↓'Object for $routeProvider.when(url, route) function' JavaScript Object ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
// ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
/**
 * 
 * @param templateUrl
 * @param controller
 *          {string}: a name of a controller
 * @param controllerAs
 *          {string}: alias of controller
 * @param content
 *          {object}: see {@link ContentRouteConfig#getContents()}
 * @returns
 * 
 * @author jaehwankim
 * @since 2023. 07. 25.
 */
RouteWhen = function(templateUrl, controller, controllerAs, content) {
	this.templateUrl = templateUrl;
	this.controller = controller;
	this.controllerAs = controllerAs;
	this.resolve = {
		content : content,
	};
	this.redirectTo = null;
};
// ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
// ↑↑↑↑↑↑↑↑↑↑↑↑↑'Object for $routeProvider.when() function' JavaScript Object ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
// ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

//// 수정예정
// 왼쪽 메뉴 구성 템플릿
var tplSbpContent = ctx + "/templates/sbp/tpl_content_menu_container.html";

ContentRouteConfig = function() {

};

////////////
////////////
//////////// 수정예정
////////////
////////////
ContentRouteConfig.INVALID_CONTENT = -0x01;
// 어플리케이션
// ContentRouteConfig.APPLICATION = 0x00;
// 데이터
ContentRouteConfig.DATA = 0x00;
// 소방시설 실시간 모니터링
ContentRouteConfig.FRM = 0x01;
// 서비스
ContentRouteConfig.SERVICE = 0x02;
// 구독
ContentRouteConfig.SUBSCRIPTION = 0x03;
// 권한
ContentRouteConfig.PERMISSION = 0x04;
// 관리
ContentRouteConfig.MANAGEMENT = 0x05;
// 사용자 정보 수정
ContentRouteConfig.MYINFO = 0x06;
// 메인 화면
ContentRouteConfig.MAIN = 0x07;


// 관리자
ContentRouteConfig.ROLE_ADMIN = 0x00;
// 사용자
ContentRouteConfig.ROLE_USER = 0x01;


/**
 * 
 * @param id
 * @returns
 * 
 * <pre>
 * 	{
 * 		content: content url
 * 		, display: display text 
 * 		, role: user role
 * 		, menus: 'ContentMenu' list
 * 	}
 * </pre>
 * 
 * @author jaehwankim
 * @since 2023. 07. 25.
 */
ContentRouteConfig.getContents = function(id) {

	switch (id) {
	case ContentRouteConfig.INVALID_CONTENT: // 
		return {
			id : ContentRouteConfig.INVALID_CONTENT,//
			content : "error_handle",//
			display : "잘못된 URL 처리",//
			role : [ ContentRouteConfig.ROLE_ADMIN, //
			ContentRouteConfig.ROLE_USER //
			], //
			menus : [ //
			new ContentMenu("invalid_url", ctx + "/templates/invalid/tpl_content_invalid_url.html"//
			, "Warning", "잘못된 URL 요청입니다."//
			, [// 
			ContentRouteConfig.ROLE_ADMIN, //
			ContentRouteConfig.ROLE_USER //
			]//
			, "glyphicon-alert", false) //
			],//
		};

		// 어플리케이션
		// case ContentRouteConfig.APPLICATION:
		// return {
		// id : ContentRouteConfig.APPLICATION,//
		// content : "application",//
		// display : "Application",//
		// role : [ //
		// ContentRouteConfig.ROLE_ADMIN, //
		// ContentRouteConfig.ROLE_USER //
		// ],//
		// menus : [//
		// new ContentMenu("", ctx + "/templates/pcl/application/tpl_content_application.html"//
		// , "Application", "어플리케이션을 관리합니다."//
		// , [ //
		// ContentRouteConfig.ROLE_ADMIN, //
		// ContentRouteConfig.ROLE_USER //
		// ]//
		// , "", false, true) //
		// ],
		// icon : ""
		// };

		// 메인 화면
	case ContentRouteConfig.MAIN:
		return {
		id : ContentRouteConfig.MAIN,//
		content : "main",//
		display : "MAIN",//
		role : [ // 
			ContentRouteConfig.ROLE_ADMIN, //
			ContentRouteConfig.ROLE_USER //
			],//
			menus : [//
				new ContentMenu("", ctx + "/templates/pcl/main/tpl_content_main.html"//
				, "Main", "메인 화면을 표시합니다."//
				, [ //
				ContentRouteConfig.ROLE_ADMIN, //
				ContentRouteConfig.ROLE_USER //
				]//
				, "", false, false) //
			],
				icon : ""
	};
		// 데이터
	case ContentRouteConfig.DATA:
		return {
			id : ContentRouteConfig.DATA,//
			content : "faci",//
			display : "FACI",//
			role : [ // 
			ContentRouteConfig.ROLE_ADMIN, //
			ContentRouteConfig.ROLE_USER //
			],//
			menus : [//
//			new ContentMenu("spatial", ctx + "/templates/pcl/data/tpl_content_data.html"//
//			, "공간 정보", "공간 정보"//
//			, [ //
//				ContentRouteConfig.ROLE_ADMIN, //
//				ContentRouteConfig.ROLE_USER //
//				]//
//			, "", false, false) //			
			],
			icon : ""
		};
		
		// 소방시설 실시간 모니터링
	case ContentRouteConfig.FRM:
		return {
		id : ContentRouteConfig.FRM,//
		content : "frm",//
		display : "Realtime Monitoring",//
		role : [ //
			ContentRouteConfig.ROLE_ADMIN, //
			ContentRouteConfig.ROLE_USER //
			],//
			menus : [//
//				new ContentMenu("", ctx + "/templates/pcl/frm/tpl_content_realtime_monitoring.html"//
//						, "실시간 모니터링", "실시간 모니터링 데이터를 관리합니다."//
//						, [ //
//							ContentRouteConfig.ROLE_ADMIN, //
//							ContentRouteConfig.ROLE_USER //
//							]//
//				, "", false, false) //
				],
				icon : ""
	};

		// 서비스
	case ContentRouteConfig.SERVICE:
		return {
			id : ContentRouteConfig.SERVICE,//
			content : "service",//
			display : "Service",//
			role : [ //
			ContentRouteConfig.ROLE_ADMIN, //
			ContentRouteConfig.ROLE_USER //
			],//
			menus : [//
				new ContentMenu("", ctx + "/templates/pcl/service/tpl_content_service.html"//
				, "Service", "서비스를 관리합니다."//
				, [ //
				ContentRouteConfig.ROLE_ADMIN, //
				ContentRouteConfig.ROLE_USER //
				]//
				, "", false, false) //
			],
			icon : ""
		};

		// 구독
	case ContentRouteConfig.SUBSCRIPTION:
		return {
			id : ContentRouteConfig.SUBSCRIPTION,//
			content : "subscription",//
			display : "Subscription",//
			role : [ // 
			ContentRouteConfig.ROLE_ADMIN, //
			ContentRouteConfig.ROLE_USER //
			],//
			menus : [//
			new ContentMenu("app", ctx + "/templates/pcl/subscription/tpl_content_subscription_app.html"//
			, "Application", "App 구독정보를 관리합니다."//
			, [ // 
			ContentRouteConfig.ROLE_ADMIN, //
			ContentRouteConfig.ROLE_USER //
			]//
			, "", false, false), //
			new ContentMenu("data", ctx + "/templates/pcl/subscription/tpl_content_subscription_data.html"//
			, "Data", "Data 구독정보를 관리합니다."//
			, [ // 
			ContentRouteConfig.ROLE_ADMIN, //
			ContentRouteConfig.ROLE_USER //
			]//
			, "", false, false) ],
			icon : ""
		};

		// 권한
	case ContentRouteConfig.PERMISSION:
		return {
			id : ContentRouteConfig.PERMISSION,//
			content : "permission",//
			display : "Permission",//
			role : [ // 
			ContentRouteConfig.ROLE_ADMIN, //
			ContentRouteConfig.ROLE_USER //
			],//
			menus : [//
			new ContentMenu("authorization", ctx + "/templates/pcl/permission/tpl_content_permission.html"//
			, "Authorization", "권한 신청을 및 확인을 합니다."//
			, [ // 
			ContentRouteConfig.ROLE_ADMIN, //
			ContentRouteConfig.ROLE_USER //
			]//
			, "", false, false), //
//			new ContentMenu("service", ctx + "/templates/pcl/permission/tpl_content_service.html"//
//			, "Service", "서비스 권한 신청 및 확인을 합니다."//
//			, [ // 
//			ContentRouteConfig.ROLE_ADMIN, //
//			ContentRouteConfig.ROLE_USER //
//			]//
//			, "", false, false), //
			new ContentMenu("token", ctx + "/templates/pcl/permission/tpl_content_permission_token.html"//
			, "Token", "권한에 대한 토큰 신청 및 확인을 합니다."//
			, [ // 
			ContentRouteConfig.ROLE_ADMIN, //
			ContentRouteConfig.ROLE_USER //
			]//
			, "", false, false) ],
			icon : ""
		};

		// 관리
	case ContentRouteConfig.MANAGEMENT:
		return {
			id : ContentRouteConfig.MANAGEMENT,//
			content : "management",//
			display : "Management",//
			role : [ // 
			ContentRouteConfig.ROLE_ADMIN //
			],//
			menus : [//
					new ContentMenu("api", ctx + "/templates/pcl/management/tpl_content_management_api.html"//
					, "API", "API를 매핑, 관리합니다."//
					, [ // 
					ContentRouteConfig.ROLE_ADMIN //
					]//
					, "", false, false), //
					new ContentMenu("application", ctx
							+ "/templates/pcl/management/application/tpl_content_management_application.html"//
					, "Application", "어플리케이션을 관리합니다."//
					, [ // 
					ContentRouteConfig.ROLE_ADMIN, //
					]//
					, "", false, false), //
					new ContentMenu("datasource", ctx + "/templates/pcl/management/tpl_content_management_datasource.html" //
					, "Data Source", "Data Source를 매핑, 관리합니다." //
					, [ ContentRouteConfig.ROLE_ADMIN //
					] //
					, "", false, false), //
//					new ContentMenu("service", ctx + "/templates/pcl/management/service/tpl_content_management_service.html" //
//					, "Service", "Service를 매핑, 관리합니다." //
//					, [ ContentRouteConfig.ROLE_ADMIN //
//					] //
//					, "", false, false), //
					new ContentMenu("system", ctx + "/templates/pcl/management/system/tpl_content_management_system.html" //
					, "System", "시스템 설정 정보에 대한 관리를 합니다." //
					, [ ContentRouteConfig.ROLE_ADMIN //
					] //
					, "", false, false),
					new ContentMenu("permission", ctx + "/templates/pcl/management/tpl_content_management_permission.html" //
					, "Permission", "Permission 요청에 대한 관리를 합니다." //
					, [ ContentRouteConfig.ROLE_ADMIN //
					] //
					, "", false, false), //
					new ContentMenu("user", ctx + "/templates/pcl/management/user/tpl_content_management_user.html" //
					, "User", "사용자 관리를 합니다." //
					, [ ContentRouteConfig.ROLE_ADMIN //
					] //
					, "", false, false) //
			],
			icon : ""
		};
		
	// 사용자 정보 수정
	case ContentRouteConfig.MYINFO:
		return {
			id : ContentRouteConfig.MYINFO,
			content : "myinfo",
			display : "사용자 정보 수정",
			role : [ ContentRouteConfig.ROLE_ADMIN, ContentRouteConfig.ROLE_USER, ContentRouteConfig.ROLE_OPERATOR ],
			menus : [ new ContentMenu("", ctx + "/templates/pcl/management/user/tpl_content_user_info.html"//
			, "사용자 정보 수정 ", "사용자 정보를 수정합니다."//
			, [ ContentRouteConfig.ROLE_ADMIN, ContentRouteConfig.ROLE_USER, ContentRouteConfig.ROLE_OPERATOR ],
					"glyphicon glyphicon-cog", false, false) //

			],
			icon : ""
		};
	}
	;

};

// ////////////////////////////////////////////////////////////////////////////
// ////////////////////////////////////////////////////////////////////////////
// /// start - FCP-PCL 서비스

/**
 * 메뉴별 Route 설정을 반환한다.
 * 
 * @param contentId
 *          {number} 메뉴 콘텐트 ID
 * @param userGrade
 *          {number} 사용자 등급
 */
ContentRouteConfig.loadRoute = function(contentId, userGrade) {
	var menuconfig = function() {
		return new Content(ContentRouteConfig.getContents(contentId), userGrade);

	};

	return new RouteWhen(tplPclContent, ServiceContentCtrl, "menuContainer", menuconfig);
}

/**
 * 메뉴별 Route 설정을 반환한다. (확장)
 * 
 * @param contentId
 *          {number} 메뉴 콘텐트 ID
 * @param userGrade
 *          {number} 사용자 등급
 */
ContentRouteConfig.loadRouteExt = function(content, userGrade) {

	var menuconfig = function() {
		return content;
	};
	
	return new RouteWhen(tplPclContent, ServiceContentCtrl, "menuContainer", menuconfig);
}

ContentRouteConfig.loadRouteExt2 = function(content, userGrade) {

	var menuconfig = function() {
		return content;
	};
	
	return new RouteWhen(tplPclContent, ServiceContentCtrl, "menuContainer", menuconfig);
}

// /// end - FCP-PCL 서비스
// ////////////////////////////////////////////////////////////////////////////
// ////////////////////////////////////////////////////////////////////////////

/**
 * 잘못된 URL 요청 처리를 위한 설정을 반환한다.
 * 
 * @returns {RouteWhen}
 * 
 * @author jaehwankim
 * @since 2023. 07. 25.
 */
ContentRouteConfig.loadInvalidUrlRoute = function() {
	var menuconfig = function() {
		var cmc = new Content();
		cmc.loadContents(ContentRouteConfig.getContents(ContentRouteConfig.INVALID_CONTENT));

		return cmc;
	};

	return new RouteWhen(tplPclContent, ServiceContentCtrl, "menuContainer", menuconfig);
};