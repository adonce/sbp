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
				console.log("[Controller] Here comes a 소방융합활동정보 at", new Date());
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
			// new Content(ContentRouteConfig.getContents(ContentRouteConfig.APPLICATION)),
			new Content(ContentRouteConfig.getContents(ContentRouteConfig.DATA)),
				new Content(ContentRouteConfig.getContents(ContentRouteConfig.FRM)),
					new Content(ContentRouteConfig.getContents(ContentRouteConfig.SERVICE)),
					new Content(ContentRouteConfig.getContents(ContentRouteConfig.SUBSCRIPTION)),
					new Content(ContentRouteConfig.getContents(ContentRouteConfig.PERMISSION)),
					new Content(ContentRouteConfig.getContents(ContentRouteConfig.MANAGEMENT)) ];

			// FACI 그룹 목록
			var faciGroup = [];
			// 실시간 모니터링 그룹 목록
			var frmroup = [];

			/**
			 * FACI 하위 메뉴 목록 제공
			 */
			var getTableGroupAndTable = function() {

				SbpManager.getTableGroupAndTable({
					param1 : "faci",
					param2 : "group"
				},
				// on-success
				function(response, header) {

					console.debug("FACI 하위 메뉴 목록 제공 Response: ", response);

					faciGroup = response.data;

					faciGroup.forEach(function(group) {

						group.collapse = true;

					});
					// 그룹에 대한 테이블 목록 순서대로 정렬
					faciGroup.forEach(function(group) {
						group.tables.sort(function(a, b) {
							return a.name < b.name ? -1 : a.name > b.name ? 1 : 0; 
						});
					});

					addFaciMenu();
				},
				// on-error
				function(response) {

					alert("FACI 하위 메뉴 목록 제공 실패!");
				});
			};

			getTableGroupAndTable();

			/**
			 * 실시간 모니터링 하위 메뉴 목록 제공
			 */
			var getFrmTableGroupAndTable = function() {
				
				SbpManager.getFrmTableGroupAndTable({
					param1 : "frm",
					param2 : "group"
				},
				// on-success
				function(response, header) {

					console.debug("실시간 모니터링 하위 메뉴 목록 제공 Response: ", response);

					frmGroup = response.data;

					frmGroup.forEach(function(group) {

						group.collapse = true;

					});
					// 그룹에 대한 테이블 목록 순서대로 정렬
					frmGroup.forEach(function(group) {
						group.tables.sort(function(a, b) {
							return a.name < b.name ? -1 : a.name > b.name ? 1 : 0; 
						});
					});

					addFrmMenu();
				},
				// on-error
				function(response) {
					alert("실시간 모니터링 하위 메뉴 목록 제공 실패!");
				});
				
			};
			
			getFrmTableGroupAndTable();
			
			// FACI 하위 메뉴 추가
			var addFaciMenu = function() {

				var contentMenu = null;
				var content = null;

				$scope.routeContents.forEach(function(routeContent) {

					if (routeContent.content == "faci") {

						faciGroup.forEach(function(group) {

							contentMenu = new ContentMenu(group.id, ctx + "/templates/sbp/data/tpl_content_data.html"//
							, group.name, group.name//
							, [ //
							ContentRouteConfig.ROLE_ADMIN, //
							ContentRouteConfig.ROLE_USER //
							]//
							, "", false, false) //

							group.tables.forEach(function(table) {

								content = new Content({
									id : table.id,//
									content : table.id,//
									display : table.name,//
									role : [ // 
									ContentRouteConfig.ROLE_ADMIN, //
									ContentRouteConfig.ROLE_USER //
									],//
									menus : [//
									new ContentMenu("", ctx + "/templates/sbp/faci/tpl_content_faci.html" //
									, "소방융합정보를 관리합니다.", "소방융합정보를 관리합니다." //
									, [ ContentRouteConfig.ROLE_ADMIN //
									, ContentRouteConfig.ROLE_USER //
									] //
									, "", true, false) //
									],
									icon : ""
								});

								contentMenu.addSubMenu(content);
//								routeProviderReference.when("/", ContentRouteConfig.loadRouteExt(content));
//								routeProviderReference.when("/faci", ContentRouteConfig.loadRouteExt(content));
								routeProviderReference.when("/faci/:table_group", ContentRouteConfig.loadRouteExt(content));
								routeProviderReference.when("/faci/:table_group/:table", ContentRouteConfig.loadRouteExt(content));
							});

							routeContent.menus.put(group.id, contentMenu);
						});
					}

				});

//				$rootScope.load_menu.path = [];
//				$rootScope.load_menu.path.push("FACI");
//				$rootScope.load_menu.path.push("공간");
//				$rootScope.load_menu.path.push("POI 정보");

				$location.url("/");
				
				$scope.routeContents.forEach(function(routeContent) {

					if (routeContent.content != "faci") {
						return;
					}

					routeContent.menus.values().forEach(function(group) {
						group.collapse = true;
					});
				});

			};
			
			// 실시간 모니터링 하위 메뉴 추가
			var addFrmMenu = function() {

				var contentMenu = null;
				var content = null;

				$scope.routeContents.forEach(function(routeContent) {

					if (routeContent.content == "frm") {

						frmGroup.forEach(function(group) {

							contentMenu = new ContentMenu(group.id, ctx + "/templates/sbp/frm/tpl_content_sensing_equip_info.html"//
							, group.name, group.name//
							, [ //
							ContentRouteConfig.ROLE_ADMIN, //
							ContentRouteConfig.ROLE_USER //
							]//
							, "", false, false) //

							group.tables.forEach(function(table) {
								
								content = new Content({
									id : table.id,//
									content : table.id,//
									display : table.name,//
									role : [ // 
									ContentRouteConfig.ROLE_ADMIN, //
									ContentRouteConfig.ROLE_USER //
									],//
									menus : [//
									new ContentMenu(table.id, ctx + "/templates/sbp/frm/tpl_content_" + table.id + ".html" //
									, table.name + " 관리합니다.", table.name + " 관리합니다." //
									, [ ContentRouteConfig.ROLE_ADMIN //
									, ContentRouteConfig.ROLE_USER //
									] //
									, "", true, false) //
									],
									icon : ""
								});

								contentMenu.addSubMenu(content);
//								routeProviderReference.when("/frm", ContentRouteConfig.loadRouteExt2(content));
//								routeProviderReference.when("/frm/:table_group", ContentRouteConfig.loadRouteExt2(content));
//								routeProviderReference.when("/frm/:table_group/:table", ContentRouteConfig.loadRouteExt2(content));
								routeProviderReference.when("/frm", ContentRouteConfig.loadRouteExt2(content));
								routeProviderReference.when("/frm/".concat(group.id, "/", table.id), ContentRouteConfig.loadRouteExt2(content));
								
							});
							routeContent.menus.put(group.id, contentMenu);
							
						});
					}

				});

				$location.url("/");
				
				$scope.routeContents.forEach(function(routeContent) {

					if (routeContent.content != "frm") {
						return;
					}

					routeContent.menus.values().forEach(function(group) {
						group.collapse = true;
					});
				});

			};
			
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
				
				// 모든 메뉴 항목에 대해 selected false 할당
				if ((routeContent.content != "faci" && routeContent.content != "frm") || tbMenu != undefined){
					$scope.routeContents.forEach(function(routeContent) {
						routeContent.menus.values().forEach(function(m) {
							m.selected = false;
						});
					});
				}
				// 모든 테이블 항목에 대해 selected false 할당
				if ((routeContent.content == "faci" || routeContent.content == "frm") && tbMenu != undefined) {
					setSelectedFalseTable();
				}
				
				$rootScope.load_menu.path = [];

				$rootScope.load_menu.path.push(routeContent.display);

				if (menu == undefined) {
					// #1. 해당 routeContent의 첫번째 메뉴에 selected true를 할당

					setSelectedFalseTable();
					
					if (routeContent.content == "frm") {
						routeContent.selected = true;
						routeContent.menus.values()[0].menus[0].selected = true;
						// Realtime Monitoring 메뉴 클릭 시 실시간 모니터링 -> 센싱장비정보 테이블 선택되도록 하기 위한 설정
						routeContent.menus.values()[0].collapse = false;
					} else {
						routeContent.menus.values()[0].selected = true;
					}
					
					// 메인 메뉴 선택 시 메뉴 명 표시되도록 설정 
					switch(routeContent.display) {
					case "Service":
						$rootScope.load_menu.path.push("Service");
						break;
					case "Subscription":
						$rootScope.load_menu.path.push("Application");
						break;
					case "Permission":
						$rootScope.load_menu.path.push("Authorization");
						break;
					case "Management":
						$rootScope.load_menu.path.push("API");
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
					if (routeContent.content != "faci" && routeContent.content != "frm") {
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
				
				if(type == 'faci' || type == 'frm'){
					return;
				}
				
				$location.url(url)
			}
			
			// FACI / 실시간 모니터링 하위 메뉴 그룹 Collapse & Expand
			$scope.collapseGroup = function(targetGroup) {

				$scope.routeContents.forEach(function(routeContent) {

					if (routeContent.content != "faci" && routeContent.content != "frm") {
						return;
					}

					routeContent.menus.values().forEach(function(group) {

						if (targetGroup.key == group.key) {
							group.collapse = !group.collapse;
						}

					});
				});

			};
			
			// FACI 메뉴 전체 Expand & Collapse
			$scope.allExpand = function(value){
				
				$scope.routeContents.forEach(function(routeContent) {

					if (routeContent.content != "faci") {
						return;
					}

					routeContent.menus.values().forEach(function(group) {

						group.collapse = !value;

					});
				});
			};
			
		});


var routeProviderReference = null;

sbp.config(function($routeProvider, $locationProvider) {

	routeProviderReference = $routeProvider;

	var routeWhen = null;

	// routeWhen = ContentRouteConfig.loadRoute(ContentRouteConfig.APPLICATION);
	// $routeProvider.when("/", routeWhen);
	// $routeProvider.when("/application", routeWhen);
	// $routeProvider.when("/application/:content_id", routeWhen);

	routeWhen = ContentRouteConfig.loadRoute(ContentRouteConfig.MAIN);
	$routeProvider.when("/", routeWhen);
	$routeProvider.when("/faci", routeWhen);
	$routeProvider.when("/main", routeWhen);
	$routeProvider.when("/main/:content_id", routeWhen);
	
	routeWhen = ContentRouteConfig.loadRoute(ContentRouteConfig.DATA);
	// $routeProvider.when("/faci", routeWhen);
	// $routeProvider.when("/faci/:content_id", routeWhen);
	
	routeWhen = ContentRouteConfig.loadRoute(ContentRouteConfig.FRM);
//	$routeProvider.when("/frm", routeWhen);
//	$routeProvider.when("/frm/:content_id", routeWhen);

	routeWhen = ContentRouteConfig.loadRoute(ContentRouteConfig.SERVICE);
	$routeProvider.when("/service", routeWhen);
	$routeProvider.when("/service/:content_id", routeWhen);

	routeWhen = ContentRouteConfig.loadRoute(ContentRouteConfig.SUBSCRIPTION);
	$routeProvider.when("/subscription", routeWhen);
	$routeProvider.when("/subscription/:content_id", routeWhen);

	routeWhen = ContentRouteConfig.loadRoute(ContentRouteConfig.PERMISSION);
	$routeProvider.when("/permission", routeWhen);
	$routeProvider.when("/permission/:content_id", routeWhen);

	routeWhen = ContentRouteConfig.loadRoute(ContentRouteConfig.MANAGEMENT);
	$routeProvider.when("/management", routeWhen);
	$routeProvider.when("/management/:content_id", routeWhen);

	routeWhen = ContentRouteConfig.loadRoute(ContentRouteConfig.MYINFO);
	$routeProvider.when("/myinfo", routeWhen);
	$routeProvider.when("/myinfo/:content_id", routeWhen);
	
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