/**
 * 
 */
SbpFactory = function() {
	
	this.session = function($log) {
		
		var __rtn__  = {
				moveLoginPage: function($scope, $http, callback) {
					location.href = ctx + "/j_spring_security_logout";
				}
		};
		
		return __rtn__;
	};
	
	this.error = function() {
		
		var __rtn__ = {
			
				handleError : function(response){
					
					if(response.message != undefined){
						
						window.alert(response.message);
						
						return;
						
					}else if(response.data.message != undefined){
						window.alert(response.data.message);
						
						return;
					}
					
					if(response.status == 403){
						alert("로그아웃되었습니다.");
						location.href = ctx + "/j_spring_security_logout";
					}
					
				}
		};
		
		return __rtn__;
	};
};