/**
 * 
 */
sbp.factory("ManagementSystemManager", function($resource) {
	return $resource(ctx + "/management/system/:param1/:param2/:param3/:param4", {
		"$" : function() {
			return Date.now();
		}
	}, {
		
		getSystemProperties : {
			desc : "시스템 프로퍼티 조회",
			method : "GET"
		},
		
		getSystemPropertyByName : {
			desc : "프로퍼티명에 대한 시스템 프로퍼티 조회",
			method : "GET"
		},
		
		getUploadProperties : {
			desc : "업로드 관련 프로퍼티 조회",
			method : "GET"
		},
		
		registSystemProperty : {
			desc : "시스템 프로퍼티 등록",
			method : "PUT"
		},
		
		modifySystemProperty : {
			desc : "시스템 프로퍼티 수정",
			method : "PATCH"
		},
		
		modifyUploadProperty : {
			desc : "업로드 관련 프로퍼티 수정",
			method : "POST"
		},
		
		deleteSystemProperty : {
			desc : "시스템 프로퍼티 삭제",
			method : "DELETE"
		}
	});
});