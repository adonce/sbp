/*
 * This file is generated under this project, "kr.co.adonce.sbp".
 *
 * @author jaehwankim
 * @copyright:
 * @package:
 * @license:
 * @url:
 * @require:
 * @since: 2023. 07. 27
*/
ServiceContentCtrl = function($scope, $http, $route, $routeParams, $location, $rootScope, content) {

	// ////////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////////
	// /// start - 디버깅 용도

//	if ($rootScope.logger.isDebuggable()) {
//		console.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//		console.debug("[Controller - Here comes 'Content Controller'.");
//		console.debug("content: ", content);
//		console.debug("content: ", content.content);
//	}
	// /// end - 디버깅 용도
	// ////////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////////

	$scope.templateUrlStyle = new Style();

	// 메뉴 선택 CSS 변경
	$scope.updateCurrentContent(content);

	// 메뉴 컨텐츠(서브메뉴) 선택
	var contentID = $routeParams["content_id"];

	if (contentID == undefined || contentID == null) {
		content.updateCurrent(0);
	} else {
		content.updateCurrent(contentID);
	}

	if (content.current == null) {

		if ($rootScope.logger.isDebuggable()) {
			console.log("(잘못된 URL 요청이 전달되었습니다) url: /" + content.content + "/" + contentID);
		}

		// 잘못된 URL 값을 $rootScope를 이용해서 공유
		$rootScope.invalidUrl = "/" + content.content + "/" + contentID;

		$location.path("/error_handle/invalid_url");
	} else {
		$scope.content = content;
	}

	// /// start - 디버깅 용도
//	if ($rootScope.logger.isDebuggable()) {
//		console.log("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
//	}
	// /// end - 디버깅 용도
};