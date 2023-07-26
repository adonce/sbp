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
sbp.factory("SbpManager", function($resource) {
	return $resource(ctx + "/common/:param1/:param2/:param3/:param4/:param5", {
		"$" : function() {
			return Date.now();
		}
	}, {

		/*getAllApiByGroup : {
			desc : "그룹에 대한 모든 API 목록을 제공한다.",
			method : "GET"111111
		},

		downloadGuide : {
			desc : "가이드를 다운로드한다.",
			method : "GET"
		},

		getTableGroupAndTable : {
			desc : "FACI 하위 메뉴 목록 제공",
			method : "GET"
		},

		getFrmTableGroupAndTable : {
			desc : "실시간 모니터링 하위 메뉴 목록 제공",
			method : "GET"
		}*/

	});
});