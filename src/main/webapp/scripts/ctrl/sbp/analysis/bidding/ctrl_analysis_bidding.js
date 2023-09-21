/**
 * System Property
 */

var propertyModel = function(property) {
	this.name = property.name;
	this.value = property.value;
	this.descr = property.descr;
};
var sbpFactory = new SbpFactory();
sbp.factory("SbpFactory", sbpFactory.session);

sbp.controller("AnalysisCtrl",
		function($scope, $http, $rootScope, SbpFactory) {

			if ($rootScope.logger.isDebuggable()) {
				console.log("[Controller] Here comes a SystemManagementCtrl at", new Date());
			}

			/* *** s: 마스킹 관련 ********** */
			// 마스킹
			$scope.masking = {
				loadingList : {
					count : 0,
					setMasking : function() {
						$scope.masking.loadingList.count++;
					},
					freeMasking : function() {
						if ($scope.masking.loadingList.count > 0) {
							$scope.masking.loadingList.count--;
						}
					},
					forceFreeMasking : function() {
						$scope.masking.loadingList.count = 0;
					},
					getFinished : function() {
						return $scope.masking.loadingList.count == 0 ? true : false;
					}
				}
			};
			/* *** e: 마스킹 관련 ********** */


			/**
			 * Property 조회
			 */
			var getSystemProperties = function() {
				// 마스킹 설정
				$scope.masking.loadingList.forceFreeMasking();
				$scope.masking.loadingList.setMasking();

				ManagementSystemManager.getSystemProperties({
					pageNum : $scope.pagination.pageNum,
					itemCountPerPage : $scope.pagination.itemCountPerPage,
					orderColumn : "name",
					isDesc : true
				},
				// on-success
				function(response, header) {
					console.debug("시스템 프로퍼티 목록 제공 Response: ", response);

					$scope.systemProperties = [];

					response.data.forEach(function(property) {
						this.push(new propertyModel(property));
					}, $scope.systemProperties);

					// 페이지네이션 정보 셋팅
					// $scope.pagination.pageNum = response.data.pageNum;
					$scope.pagination.itemTotalCount = response.data.length;
					$scope.masking.loadingList.freeMasking();
				},
				// on-error
				function(response) {
					console.debug("시스템 프로퍼티 목록 제공 실패 Response: ", response);
					$scope.masking.loadingList.forceFreeMasking();
					// 세션 종료됐을 시 로그인 페이지 이동 처리
					if (response.status == -1 || (response.status == 500 && response.data.code == -1)) {
						alert("Session이 만료되었습니다. 로그인 페이지로 이동됩니다.");
						PclFactory.moveLoginPage($scope, function() {

						});
					} else {
						alert("시스템 프로퍼티 목록 제공 실패!");
					}
				});
			};

			/** ******** S: Pagination ******************* */

			// 페이지네이션 한 페이지 당 아이템 수
			$scope.itemCountPerPage = [ 5, 10, 15, 20, 25 ];

			// 페이지네이션 초기 값
			$scope.pagination = {
				pageNum : 1,
				pageMaxSize : 10,
				itemCountPerPage : $scope.itemCountPerPage[2],
				itemTotalCount : 0
			};

			// 페이지 보기 옵션
			$scope.pageOptions = [ {
				value : 5,
				name : "5개씩 보기"
			}, {
				value : 10,
				name : "10개씩 보기"
			}, {				value : 15,
				name : "15개씩 보기"
			}, {
				value : 20,
				name : "20개씩 보기"
			} ];

			/**
			 * 페이지네이션 클릭 시 토큰 목록 가져오기
			 */
			$scope.pagingProperties = function() {
				getSystemProperties();
			};
			/** ******** E: Pagination ******************* */

			// getSystemProperties();

			// Null 체크 함수
			var isEmpty = function(val) {
				return (val == undefined || val == null || val == "") ? true : false;
			};
		});