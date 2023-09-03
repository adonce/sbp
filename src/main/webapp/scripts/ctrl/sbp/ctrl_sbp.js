/*
 * This file is generated under this project, "kr.co.adonce.sbp".
 *
 * @author jaehwankim
 * @copyright:
 * @package:
 * @license:
 * @url:
 * @require:
 * @since: 2023. 07. 27.
 */
RouteContent = function(content) {
	this.content = content.root;
	this.display = content.display;
	this.url = content.root;
	this.role = content.role;
	this.menus = content.menus;
	this.selected = ""; // 현재 선택된 RooteContentdp 에 사용되는
};

RouteContent.prototype.toString = function() {
	return JSON.stringify(this, null, "\t");
};

RouteContent.prototype.getCss = function() {
	return this.selected ? "rollover" : "";
}

RouteContent.prototype.getUrl = function(standalone) {
	return standalone ? this.url : "#/" + this.url;
};

Authentication = function(auth) {
	this.name = auth.name;
	this.grade = auth.grade;
	this.phone = auth.phone;
	this.deptName = auth.deptName;
	this.compName = auth.compName;
	this.compAddress = auth.compAddress;
	this.birth = auth.birth;
	this.rank = auth.rank;
	this.task = auth.task;
	this.userGrade = auth.userGradeObj;	
};

var ngFilter = new NgFilter();
sbp.filter("startFrom", ngFilter.startFrom);

var sbpFactory = new SbpFactory();
sbp.factory("SbpFactory", sbpFactory.session);

var ngDirective = new NgDirective();
sbp.directive("ngConfirmClick", ngDirective.ngConfirmClick);
sbp.directive("dlEnterKey", ngDirective.dlEnterKey);
sbp.directive("dlEnterKey", ngDirective.dlEnterKey);
sbp.directive("modaldrag", function() {
	return {
		restrict : "A",
		link : function(scope, elem, attr) {
			elem = angular.element(document.getElementsByClassName("modal-content"));
			jQuery(elem).draggable({
				handle : ".modal-drag"
			});
		}
	}
});

/**
 * 
 */
sbp.controller("SbpCtrl",
		function($scope, $http, $route, $rootScope, $uibModal, $log, $location, $timeout, SbpManager, SbpFactory) {

			$rootScope.logger = new NgLogger("debug");

			if ($rootScope.logger.isDebuggable()) {
				console.log("[Controller] Here comes a  (주)신호 입찰 프로젝트 at", new Date());
			}

			$scope.authentication = new Authentication(AUTHENTICATION);

			console.log($scope.authentication);

			// Context-Path
			$scope.image = {
				firstPage : ctx + "/images/paginate_first.gif",
				prevPage : ctx + "/images/paginate_prev.gif",
				nextPage : ctx + "/images/paginate_next.gif",
				lastPage : ctx + "/images/paginate_last.gif"
			};

			// CSRF
//			$http.defaults.headers.common[__CSRF__.headerName] = __CSRF__.token;

			$scope.routeContents = [
					new Content(ContentRouteConfig.getContents(ContentRouteConfig.ANALYSIS)),
					new Content(ContentRouteConfig.getContents(ContentRouteConfig.NEWMENU)),
					new Content(ContentRouteConfig.getContents(ContentRouteConfig.MANAGEMENT)) ];


			// 메뉴 노출
			$scope.isAllowedContent = function(content) {
				if ($rootScope.logger.isTraceable()) {
					console.debug("[Is Allowed Content", content.content);
				}

				return content.role.contains($scope.authentication.grade);
			}

			// 서브 메뉴 노출
			$scope.isAllowedSubmenu = function(menu) {
				if ($rootScope.logger.isTraceable()) {
					console.debug("[Is Allowed Submenu", menu.key);
				}

				return menu.role.contains($scope.authentication.grade);
			}

			// 메뉴 갱신
			$scope.updateCurrentContent = function(content) {
				var url = "#/" + content.content;
				var _content_ = null;

				for (var i = 0; i < $scope.routeContents.length; i++) {
					_content_ = $scope.routeContents[i];
					_content_.selected = url == _content_.getUrl();
				}

				$scope.content = content;

				if ($rootScope.logger.isDebuggable()) {
					console.debug("(Current Content)", $scope.content);
				}

				// 페이지 변경 시, Alert 삭제
				$rootScope.alerts = [];
			};

			$scope.logout = function() {
				document.getElementById("logout_form").submit();
			};

			// Menu 경로 데이터
			$rootScope.load_menu = {
				path : []
			}

			// 메뉴 클릭 시 해당 정보 저장
			$scope.clickMenu = function(routeContent, menu, tbMenu) {
				
				//////////////////////////////////////////////////////////////////////////////////////////////////
				// 모든 메뉴 항목에 대해 selected false 할당
				if ((routeContent.content != "analysis") || tbMenu != undefined){
					$scope.routeContents.forEach(function(routeContent) {
						routeContent.menus.values().forEach(function(m) {
							m.selected = false;
						});
					});
				}
				// 모든 테이블 항목에 대해 selected false 할당
				/*if ((routeContent.content == "analysis" ) && tbMenu != undefined) {
					setSelectedFalseTable();
				}*/
				
				$rootScope.load_menu.path = [];

				$rootScope.load_menu.path.push(routeContent.display);

				if (menu == undefined) {
					// #1. 해당 routeContent의 첫번째 메뉴에 selected true를 할당

					setSelectedFalseTable();
					
					
					// TODO: 수정예정
					if (routeContent.content == "analysis") {
						routeContent.selected = true;
						routeContent.menus.values()[0].menus[0].selected = true;
						// Realtime Monitoring 메뉴 클릭 시 실시간 모니터링 -> 센싱장비정보 테이블 선택되도록 하기 위한 설정
						routeContent.menus.values()[0].collapse = false;
					} else {
						routeContent.menus.values()[0].selected = true;
					}
					
					// 메인 메뉴 선택 시 메뉴 명 표시되도록 설정 
					switch(routeContent.display) {
					case "Analysis":
						$rootScope.load_menu.path.push("Analysis");
						break;
					case "NewMenu":
						$rootScope.load_menu.path.push("NewMenu");
						break;
					case "Management":
						$rootScope.load_menu.path.push("System");
						break;
					}
					
					return;
				}
				
				// 선택한 메뉴 selected true 할당
				menu.selected = true;

				if (menu.display != undefined) {
					$rootScope.load_menu.path.push(menu.display);
				} 

				if (tbMenu == undefined) {
					if (routeContent.content != "analysis") {
						setSelectedFalseTable();
					}
					return;
				}
				
				// 선택한 테이블에 selected true 할당
				tbMenu.selected = true;
				
				if (tbMenu.display != undefined) {
					$rootScope.load_menu.path.push(tbMenu.display);
				}

			}
				//////////////////////////////////////////////////////////////////////////////////////////////////
			
			// 모든 테이블 항목에 대해 selected false 설정 
			var setSelectedFalseTable = function() {
				// FACI 데이터 테이블에 대한 false 설정
				$scope.routeContents[0].menus.values().forEach(function(menu) {
					menu.menus.forEach(function(m) {
						m.selected = false;
					});
				});
				// 실시간 모니터링 테이블에 대한 false 설정
				$scope.routeContents[1].menus.values().forEach(function(menu) {
					menu.menus.forEach(function(m) {
						m.selected = false;
					});
				});
			};
			
			// URL 이동
			$scope.moveLocation = function(type, url){
//				if(type == 'faci' || type == 'frm'){
				/*if(type == 'analysis'){
					return;
				}*/
				
				$location.url(url)
			}
			
			// ANALYSIS 메뉴 그룹 Collapse & Expand
			/*$scope.collapseGroup = function(targetGroup) {

				$scope.routeContents.forEach(function(routeContent) {

					if (routeContent.content != "analysis") {
						return;
					}

					routeContent.menus.values().forEach(function(group) {

						if (targetGroup.key == group.key) {
							group.collapse = !group.collapse;
						}

					});
				});

			};
			*/
			// ANALYSIS 메뉴 전체 Expand & Collapse
			/*$scope.allExpand = function(value){
				
				$scope.routeContents.forEach(function(routeContent) {

					if (routeContent.content != "analysis") {
						return;
					}

					routeContent.menus.values().forEach(function(group) {

						group.collapse = !value;

					});
				});
			};*/
			
		});


var routeProviderReference = null;

sbp.config(function($routeProvider, $locationProvider) {

	routeProviderReference = $routeProvider;

	var routeWhen = null;

	routeWhen = ContentRouteConfig.loadRoute(ContentRouteConfig.MAIN);
	$routeProvider.when("/", routeWhen);
	$routeProvider.when("/sbp", routeWhen);
	$routeProvider.when("/main", routeWhen);
	$routeProvider.when("/main/:content_id", routeWhen);
	
	routeWhen = ContentRouteConfig.loadRoute(ContentRouteConfig.ANALYSIS);
	$routeProvider.when("/analysis", routeWhen);
	$routeProvider.when("/analysis/:content_id", routeWhen);
	
	routeWhen = ContentRouteConfig.loadRoute(ContentRouteConfig.NEWMENU);
	$routeProvider.when("/newmenu", routeWhen);
	$routeProvider.when("/newmenu/:content_id", routeWhen);

	routeWhen = ContentRouteConfig.loadRoute(ContentRouteConfig.MANAGEMENT);
	$routeProvider.when("/management", routeWhen);
	$routeProvider.when("/management/:content_id", routeWhen);

	// ////////////////////////////////////////////////////////////////////////////
	// start - Route for 'Unknown URL Request' : 2016. 2. 24., Park_Jun_Hong_(fafanmama_at_naver_com)
	routeWhen = ContentRouteConfig.loadInvalidUrlRoute();
	$routeProvider.when("/error_handle", routeWhen);
	$routeProvider.when("/error_handle/:content_id", routeWhen);
	// end - Route for 'Unknown URL Request' : 2016. 2. 24.
	// ////////////////////////////////////////////////////////////////////////////

	$routeProvider.otherwise({
		redirectTo : "/error_handle"
	});
});

sbp.config([ '$locationProvider', function($locationProvider) {
	$locationProvider.hashPrefix('');
} ]);