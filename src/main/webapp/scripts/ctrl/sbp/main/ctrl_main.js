/**
 * 
 */
sbp.controller("MainCtrl", function($scope, $http, $rootScope, ManagementSystemManager) {

	if ($rootScope.logger.isDebuggable()) {
		console.debug("[Controller] Here comes a MainCtrl at", new Date());
	}

	var mainImageId = "main.image";
	var mainTextId = "main.text.id";
	var mainTextFontSize = "main.text.fontsize";
	var mainImageWidth = "main.image.width"
	
	//////  s: 메인 메뉴 들어왔을 경우 모든 메뉴 및 하위 메뉴(테이블) 선택 해제 //////////////////////
	$scope.routeContents.forEach(function(routeContent) {
		routeContent.menus.values().forEach(function(m) {
			m.selected = false;
		});
	});
	
	$scope.routeContents[0].menus.values().forEach(function(menu) {
		menu.menus.forEach(function(m) {
			m.selected = false;
		});
	});
	////// e: 메인 메뉴 들어왔을 경우 모든 메뉴 및 하위 메뉴(테이블) 선택 해제 //////////////////////

	// 메인 개요 텍스트
	$scope.mainText = null;

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
	 * 메인 이미지 binary 파일 가져오기
	 */
	$scope.getImage = function() {
		return ctx + "/common/files/" + mainImageId;
	}

	/**
	 * 메인 개요 텍스트 가져오기
	 */
	var getMainText = function() {
		// 마스킹 설정
		$scope.masking.loadingList.forceFreeMasking();
		$scope.masking.loadingList.setMasking();

		ManagementSystemManager.getSystemPropertyByName({
			param1 : mainTextId
		},
		// on-success
		function(response, header) {
			console.debug("Main 개요 텍스트 제공 Response: ", response);

			$scope.mainText = response.data.value;

			$scope.masking.loadingList.freeMasking();
		},
		// on-error
		function(response) {
			$scope.masking.loadingList.forceFreeMasking();
			// 세션 종료됐을 시 로그인 페이지 이동 처리
			if (response.status == -1 || (response.status == 500 && response.data.code == -1)) {
				alert("Session이 만료되었습니다. 로그인 페이지로 이동됩니다.");
				PclFactory.moveLoginPage($scope, function() {

				});
			} else {
				alert("Main 개요 텍스트 제공 실패!");
			}
		});
	};

	/* 메인 텍스트 글씨 크기 가져오기 * */
	var getTextFontSize = function() {
		// 마스킹 설정
		$scope.masking.loadingList.forceFreeMasking();
		$scope.masking.loadingList.setMasking();

		ManagementSystemManager.getSystemPropertyByName({
			param1 : mainTextFontSize
		},
		// on-success
		function(response, header) {
			console.debug("Main 개요 텍스트 글씨 크기 제공 Response: ", response);

			$scope.mainTextFontSize = response.data.value;

			$scope.masking.loadingList.freeMasking();
		},
		// on-error
		function(response) {
			$scope.masking.loadingList.forceFreeMasking();
			// 세션 종료됐을 시 로그인 페이지 이동 처리
			if (response.status == -1 || (response.status == 500 && response.data.code == -1)) {
				alert("Session이 만료되었습니다. 로그인 페이지로 이동됩니다.");
				PclFactory.moveLoginPage($scope, function() {

				});
			} else {
				alert("Main 개요 텍스트 글씨 크기 제공 실패!");
			}
		});
	};
	
	/* 메인 텍스트 글씨 크기 가져오기 * */
	var getMainImageWidth = function() {
		// 마스킹 설정
		$scope.masking.loadingList.forceFreeMasking();
		$scope.masking.loadingList.setMasking();
		
		ManagementSystemManager.getSystemPropertyByName({
			param1 : mainImageWidth
		},
		// on-success
		function(response, header) {
			console.debug("Main 이미지 Width 제공 Response: ", response);
			
			$scope.mainImageWidth = response.data.value;
			
			$scope.masking.loadingList.freeMasking();
		},
		// on-error
		function(response) {
			$scope.masking.loadingList.forceFreeMasking();
			// 세션 종료됐을 시 로그인 페이지 이동 처리
			if (response.status == -1 || (response.status == 500 && response.data.code == -1)) {
				alert("Session이 만료되었습니다. 로그인 페이지로 이동됩니다.");
				PclFactory.moveLoginPage($scope, function() {
					
				});
			} else {
				alert("Main 이미지 Width 제공 실패!");
			}
		});
	};

	/* 메인 개요 텍스트 가져오기 * */
	getMainText();
	/* 메인 이미지 가로 % 가져오기 * */
	getMainImageWidth();
	/* 메인 개요 텍스트 가져오기 * */
	getTextFontSize();
});