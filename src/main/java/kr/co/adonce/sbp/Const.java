package kr.co.adonce.sbp;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 
 *
 * @since 2023. 7. 17.
 * @author 김재환
 */
public class Const {

	public static final class HttpSession {
		public static final String ATTRNAME_HTTP_SESSION = "HTTP_SESSION";
		public static final String ATTRNAME_HTTP_JSESSIONID = "JSESSIONID";
	}

	public static final class Security {
		/**
		 * Following information is the result of
		 * {@link WebSocketSession#getAttributes()}.
		 * 
		 * <pre>
		 *   SPRING_SECURITY_CONTEXT = org.springframework.security.core.context.SecurityContextImpl@bada8631: Authentication: org.springframework.security.authentication.UsernamePasswordAuthenticationToken@bada8631: Principal: admin; Credentials: [PROTECTED]; Authenticated: true; Details: org.springframework.security.web.authentication.WebAuthenticationDetails@ffff4c9c: RemoteIpAddress: 39.119.118.159; SessionId: 5266C066720D4A592E7E6315DDD44327; Granted Authorities: ROLE_ADMIN
		 *               values Type = class org.springframework.security.core.context.SecurityContextImpl
		 * SPRING_SECURITY_SAVED_REQUEST = DefaultSavedRequest[http://39.119.118.159:8090/sdn/none]
		 *               values Type = class org.springframework.security.web.savedrequest.DefaultSavedRequest
		 *    extensions: []
		 * </pre>
		 */
		public static final String ATTRNAME_SPRING_SECURITY_CONTEXT = "SPRING_SECURITY_CONTEXT";

		/**
		 * Following information is the result of
		 * {@link WebSocketSession#getAttributes()}.
		 * 
		 * <pre>
		 *   SPRING_SECURITY_CONTEXT = org.springframework.security.core.context.SecurityContextImpl@bada8631: Authentication: org.springframework.security.authentication.UsernamePasswordAuthenticationToken@bada8631: Principal: admin; Credentials: [PROTECTED]; Authenticated: true; Details: org.springframework.security.web.authentication.WebAuthenticationDetails@ffff4c9c: RemoteIpAddress: 39.119.118.159; SessionId: 5266C066720D4A592E7E6315DDD44327; Granted Authorities: ROLE_ADMIN
		 *               values Type = class org.springframework.security.core.context.SecurityContextImpl
		 * SPRING_SECURITY_SAVED_REQUEST = DefaultSavedRequest[http://39.119.118.159:8090/sdn/none]
		 *               values Type = class org.springframework.security.web.savedrequest.DefaultSavedRequest
		 *    extensions: []
		 * </pre>
		 */
		public static final String ATTRNAME_SPRING_SECURITY_SAVED_REQUEST = "SPRING_SECURITY_SAVED_REQUEST";

		public static final String USER_TO_BE_REGISTER = "!@#$%^&*()_+____USER__TO_BE_REGISTER____!@#$%^&*()_+";
	}

	public static final class UserDataAccess {
		public static final String ILLEGAL_USER_DATA_ACCESS = "(Illegal User Data Access) 사용자 정보에 대한 접근권한이 없는 요청입니다.";
		public static final String USER_DATA_ACCESS_BY_SELF = "정보 요청자({req})와 대상자({target})가 동일합니다.";
		public static final String USER_DATA_ACCESS_BY_OTHER = "정보 요청자({req})와 대상자({target})가 동일하지 않습니다.";
		public static final String USER_DATA_ACCESS_BY_ADMIN = "관리자({admin})에 의한 사용자 정보 요청입니다.";
	}

	public static final class EventName {
		public static final String FIRE = "화재알람";
		public static final String ABNORMAL_STATE = "상태이상알람";
		public static final String FACILITY_OPERATION = "설비작동알람";
		public static final String FULL_RECOVERY = "전체복구";
		public static final String ETC = "기타";
	}

	public static final class Trigger {
		public static final String TRIGGER_CREATED = "created";
		public static final String TRIGGER_UPDATED = "updated";
		public static final String TRIGGER_DELETED = "deleted";
	}

	public static final class SubscriptionTarget {
		public static final String SUBS_TARGET_SENSING_INFO = "/frm/tb_frm_sensing_info/table";
		public static final String SUBS_TARGET_EVENT_INFO = "/frm/tb_frm_event_info/table";
	}

	public static final class SubscriptionType {
		public static final String TYPE_DATA = "data";
		public static final String TYPE_APP = "app";
	}

	/**
	 * 각 이벤트 코드에 대해서 UI에서 어느 정보까지 표시되는지에 따라 판단하는 기준 상세명 그룹이다. tb_frm_event_code의
	 * 상세이벤트명과 동일해야한다.
	 *
	 * @return
	 */
	public static final class DetailNameGroup {
		// ViewCode가 0인 상태이상 알람 코드 목록 (건물명 표시)
		private static String[] ABNORMAL_STATE_ARR_CODE0_LIST = { //
				"주경종 차단 알람 발생", "상용전원 경보 알람 발생", "예비전원 이상 알람 발생", //
				"저수위 경보 알람 발생", "펌프 작동 입력 알람 발생", "알람밸브 작동 입력 알람 발생", //
				"주경종 차단 알람 해제", "상용전원 경보 알람 해제", "예비전원 이상 알람 해제", //
				"저수위 경보 알람 해제", "펌프 작동 입력 알람 해제", "알람밸브 작동 입력 알람 해제" //
		};

		public static final List<String> ABNORMAL_STATE_ARR_CODE0 = Collections
				.unmodifiableList(Arrays.asList(ABNORMAL_STATE_ARR_CODE0_LIST));

		// ViewCode가 1인 상태이상 알람 코드 목록 (건물명, 층 표시)
		private static final String[] ABNORMAL_STATE_ARR_CODE1_LIST = { //
				"지구경종 차단 알람 발생", "지구경종 차단 알람 해제" //
		};

		public static final List<String> ABNORMAL_STATE_ARR_CODE1 = Collections
				.unmodifiableList(Arrays.asList(ABNORMAL_STATE_ARR_CODE1_LIST));

		// ViewCode가 2인 상태이상 알람 코드 목록 (건물명, 층, 경계 구역 표시)
		private static final String[] ABNORMAL_STATE_ARR_CODE2_LIST = { //
				"출력 고장 알람 발생", "중계기 통신 고장 알람 발생", "범용 고장 알람 발생", //
				"출력 고장 알람 해제", "중계기 통신 고장 알람 해제", "범용 고장 알람 해제" //
		};

		public static final List<String> ABNORMAL_STATE_ARR_CODE2 = Collections
				.unmodifiableList(Arrays.asList(ABNORMAL_STATE_ARR_CODE2_LIST));

		// ViewCode가 0인 설비작동 알람 코드 목록 (건물명 표시)
		private static final String[] FACILITY_OPERATION_ARR_CODE0_LIST = { //
				"사이렌 작동 알람 발생", "사이렌 작동 알람 해제", "펌프 작동 알람 발생", //
				"비상방송 작동 알람 발생", "설비경보 범용 알람 발생", "펌프 작동 알람 해제", //
				"비상방송 작동 알람 해제", "설비경보 범용 알람 해제" //
		};

		public static final List<String> FACILITY_OPERATION_ARR_CODE0 = Collections
				.unmodifiableList(Arrays.asList(FACILITY_OPERATION_ARR_CODE0_LIST));

		// ViewCode가 2인 설비작동 알람 코드 목록 (건물명, 층, 경계 구역 표시)
		private static final String[] FACILITY_OPERATION_ARR_CODE2_LIST = { //
				"방화셔터감지기 입력 알람 발생", "배연창감지기 입력 알람 발생", "댐퍼감지기 입력 알람 발생", //
				"제연커튼감지기 입력 알람 발생", "FAN확인 입력 알람 발생", "저압감지기 입력 알람 발생", //
				"방화셔터 작동 알람 발생", "배연창 작동 알람 발생", "댐퍼 작동 알람 발생", //
				"제연커튼 작동 알람 발생", "FAN 작동 알람 발생", //
				"방화셔터감지기 입력 알람 해제", "배연창감지기 입력 알람 해제", "댐퍼감지기 입력 알람 해제", //
				"제연커튼감지기 입력 알람 해제", "FAN확인 입력 알람 해제", "저압감지기 입력 알람 해제", //
				"방화셔터 작동 알람 해제", "배연창 작동 알람 해제", "댐퍼 작동 알람 해제", //
				"제연커튼 작동 알람 해제", "FAN 작동 알람 해제" //
		};

		public static final List<String> FACILITY_OPERATION_ARR_CODE2 = Collections
				.unmodifiableList(Arrays.asList(FACILITY_OPERATION_ARR_CODE2_LIST));

		// ViewCode가 0인 전체복구 코드 목록
		private static final String[] FULL_RECOVERY_ARR_CODE0_LIST = { "복구 발생", "복구 해제" };

		public static final List<String> FULL_RECOVERY_ARR_CODE0 = Collections
				.unmodifiableList(Arrays.asList(FULL_RECOVERY_ARR_CODE0_LIST));

		// ViewCode가 0인 기타 코드 목록
		private static final String[] ETC_ARR_CODE0_LIST = {};

		public static final List<String> ETC_ARR_CODE0 = Collections
				.unmodifiableList(Arrays.asList(ETC_ARR_CODE0_LIST));

		// ViewCode가 2인 기타 코드 목록
		private static final String[] ETC_ARR_CODE2_LIST = { "가스 알람 발생", "가스 알람 해제", "축적 설정 발생", "축적 설정 해제" };

		public static final List<String> ETC_ARR_CODE2 = Collections
				.unmodifiableList(Arrays.asList(ETC_ARR_CODE2_LIST));
	}

	/**
	 * 
	 * @since 2018. 3. 14.
	 * @author jhlee
	 *
	 */
	public static final class WfUiModelProperty {
		/** Engine 설정 정보 */
		public static final String ENGINES_JSON = "enginesJson";
		/** Engine 위치 정보 */
		public static final String ENGINES_LOCATION = "enginesLocation";
		/** Engine 사이의 연결 정보 */
		public static final String LINKS_LOCATION = "linksLocation";
	}

	public static class Table {
		/** begin: 공간정보 */
		/** 층 */
		public static final String SPATIAL_LEVEL_TB_NM = "tb_spatial_level";
		/** 벽체의 타입 및 재질 정보 */
		public static final String SPATIAL_COMPONENT_TB_NM = "tb_spatial_component";
		/** 문 */
		public static final String SPATIAL_DOOR_TB_NM = "tb_spatial_door";
		/** 벽체 선형 정보 */
		public static final String SPATIAL_GRID_TB_NM = "tb_spatial_grid";
		/** POI */
		public static final String SPATIAL_POI_TB_NM = "tb_spatial_poi";
		/** POI 타입 */
		public static final String SPATIAL_POITYPE_TB_NM = "tb_spatial_poitype";
		/** 소방시설 배선 */
		public static final String SPATIAL_POI_WIRE_TB_NM = "tb_spatial_poi_wire";
		/** 건물 정보 */
		public static final String SPATIAL_PROJECT_TB_NM = "tb_spatial_project";
		/** 공간 정보 */
		public static final String SPATIAL_SPACE_TB_NM = "tb_spatial_space";
		/** 벽체를 둘러싸고 있는 벽체의 정보 */
		public static final String SPATIAL_SPACEWALLLINK_TB_NM = "tb_spatial_spacewalllink";
		/** 토폴로지 */
		public static final String SPATIAL_TOPOLOGY_TB_NM = "tb_spatial_topology";
		/** 토폴로지 노드 정보 */
		public static final String SPATIAL_TOPOLOGYNODE_TB_NM = "tb_spatial_topologynode";
		/** 토폴로지 노드 연결 정보 */
		public static final String SPATIAL_TOPOLOGYNODELINK_TB_NM = "tb_spatial_topologynodelink";
		/** 벽 */
		public static final String SPATIAL_WALL_TB_NM = "tb_spatial_wall";
		/** 창문 */
		public static final String SPATIAL_WINDOW_TB_NM = "tb_spatial_window";
		/** end: 공간정보 */

		/** begin: 소방활동정보 */
		/** 소방활동정보카드 */
		public static final String ACTIVITY_INFO_CARD_TB_NM = "tb_activity_info_card";
		/** 특별용도 */
		public static final String ACTIVITY_BUILDING_SPECIAL_TB_NM = "tb_activity_building_special";
		/** 화재보험가입 */
		public static final String ACTIVITY_BUILDING_INSURANCE_TB_NM = "tb_activity_building_insurance";
		/** 건물정보 */
		public static final String ACTIVITY_BUILDING_INFO_TB_NM = "tb_activity_building_info";
		/** 건물구조 */
		public static final String ACTIVITY_BUILDING_STRUCTURE_TB_NM = "tb_activity_building_structure";
		/** 관계자 */
		public static final String ACTIVITY_BUILDING_CONCERN_TB_NM = "tb_activity_building_concern";
		/** 안전관리자 */
		public static final String ACTIVITY_BUILDING_SAFETY_TB_NM = "tb_activity_building_safety";
		/** 층별현황 */
		public static final String ACTIVITY_BUILDING_FLOOR_TB_NM = "tb_activity_building_floor";
		/** 소방시설공사/정비 */
		public static final String ACTIVITY_CONSTRUCTION_TB_NM = "tb_activity_construction";
		/** 연혁 */
		public static final String ACTIVITY_HISTORY_TB_NM = "tb_activity_history";
		/** 소방활동계획 */
		public static final String ACTIVITY_PLAN_TB_NM = "tb_activity_plan";
		/** 출동사항 */
		public static final String ACTIVITY_MOBILIZE_TB_NM = "tb_activity_mobilize";
		/** 인근수리 */
		public static final String ACTIVITY_IRRIGATION_TB_NM = "tb_activity_irrigation";
		/** 작성자 */
		public static final String ACTIVITY_INFO_WRITE_TB_NM = "tb_activity_info_write";
		/** 소방활동계획도 */
		public static final String ACTIVITY_LOCATION_TB_NM = "tb_activity_location";
		/** 소방검사결과조치 */
		public static final String ACTIVITY_RESULT_TB_NM = "tb_activity_result";
		/** 소방훈련 */
		public static final String ACTIVITY_TRAINING_TB_NM = "tb_activity_training";
		/** 화재발생현황 */
		public static final String ACTIVITY_CONDITION_TB_NM = "tb_activity_condition";
		/** 소방시설상세 */
		public static final String ACTIVITY_EQUIPMENT_TB_NM = "tb_activity_equipment";
		/** 위험물허가신고현황 */
		public static final String ACTIVITY_HAZARD_TB_NM = "tb_activity_hazard";
		/** 일반현황 */
		public static final String ACTIVITY_RESEARCH_GENERAL_TB_NM = "tb_activity_research_general";
		/** 소유자 */
		public static final String ACTIVITY_RESEARCH_CONCERN_TB_NM = "tb_activity_research_concern";
		/** 소방활동 자료 조사서 */
		public static final String ACTIVITY_RESEARCH_CARD_TB_NM = "tb_activity_research_card";
		/** 소방용수시설 */
		public static final String ACTIVITY_RESEARCH_IRRIGATION_TB_NM = "tb_activity_research_irrigation";
		/** - */
		public static final String ACTIVITY_RESEARCH_SURVEY_TB_NM = "tb_activity_research_survey";
		/** end: 소방활동정보 */

		/** begin: 공통정보 */
		/** - */
		public static final String COMMON_ADM_TB_NM = "tc_adm";
		/** 법정동 코드 */
		public static final String COMMON_LAW_TB_NM = "tc_law";
		/** - */
		public static final String COMMON_GRP_TB_NM = "type_grp";
		/** - */
		public static final String COMMON_SYS_TB_NM = "type_sys";
		/** 코드 그룹 */
		public static final String COMMON_CODE_GRP_TB_NM = "tc_code_grp";
		/** 코드 상세 */
		public static final String COMMON_CODE_SYS_TB_NM = "tc_code_sys";
		/** 소방시설분류_대 */
		public static final String COMMON_EQUIP_TYPE_LARGE_TB_NM = "tc_equip_type_large";
		/** 소방시설분류_중 */
		public static final String COMMON_EQUIP_TYPE_MEDIUM_TB_NM = "tc_equip_type_medium";
		/** 소방시설분류_소 */
		public static final String COMMON_EQUIP_TYPE_SMALL_TB_NM = "tc_equip_type_small";
		/** 소방시설분류_상세 */
		public static final String COMMON_EQUIP_TYPE_DETAIL_TB_NM = "tc_equip_type_detail";
		/** 점검 고유속성, 분류별 고유속성 */
		public static final String COMMON_INSPECTION_CHARACTER_TB_NM = "tc_inspection_character";
		/** 파일상세정보 */
		public static final String COMMON_EQUIP_TYPE_FILE_TB_NM = "tc_equip_type_file";
		/** - */
		public static final String COMMON_ATTACH_FILE_TB_NM = "tb_attach_file";
		/** 소방시설 점검 매핑 */
		public static final String COMMON_INSPECTION_CHECK_MAPPING_TB_NM = "tc_inspection_check_mapping";
		/** 소방시설분류_대 */
		public static final String COMMON_INSPECTION_CHECK_L_TB_NM = "tc_inspection_check_l";
		/** 소방시설분류_중 */
		public static final String COMMON_INSPECTION_CHECK_M_TB_NM = "tc_inspection_check_m";
		/** 소방시설분류_소 */
		public static final String COMMON_INSPECTION_CHECK_S_TB_NM = "tc_inspection_check_s";
		/** 소방시설분류_상세 */
		public static final String COMMON_INSPECTION_CHECK_D_TB_NM = "tc_inspection_check_d";
		/** 위험물(대) */
		public static final String COMMON_HAZARD_LARGE_TB_NM = "tc_hazard_large";
		/** 위험물(중) */
		public static final String COMMON_HAZARD_MEDIUM_TB_NM = "tc_hazard_medium";
		/** 위험물(소) */
		public static final String COMMON_HAZARD_SMALL_TB_NM = "tc_hazard_small";
		/** end: 공통정보 */

		/** begin: 소방점검정보 */
		/** 대상물 */
		public static final String INSPECTION_BUILDING_TB_NM = "tb_inspection_building";
		/** 시설별 고유 속성 */
		public static final String INSPECTION_CHARACTER_RESULT_TB_NM = "tb_inspection_character_result";
		/** 점검결과 */
		public static final String INSPECTION_CHECK_RESULT_TB_NM = "tb_inspection_check_result";
		/** 단말기 */
		public static final String INSPECTION_DEVICE_TB_NM = "tb_inspection_device";
		/** 소방시설 */
		public static final String INSPECTION_EQUIP_TB_NM = "tb_inspection_equip";
		/** 층별 */
		public static final String INSPECTION_FLOOR_TB_NM = "tb_inspection_floor";
		/** 지적내용 */
		public static final String INSPECTION_INDICATION_TB_NM = "tb_inspection_indication";
		/** 점검자 */
		public static final String INSPECTION_INSPECOTR_TB_NM = "tb_inspection_inspector";
		/** 자격증 */
		public static final String INSPECTION_LICENSE_TB_NM = "tb_inspection_license";
		/** 실시간 센싱 장비 */
		public static final String INSPECTION_REALTIME_SENSING_EQUIP_TB_NM = "tb_inspection_realtime_sensing_equip";
		/** 보고서 */
		public static final String INSPECTION_REPORT_TB_NM = "tb_inspection_report";
		/** 건물구조 */
		public static final String INSPECTION_STRUCTURE_TB_NM = "tb_inspection_structure";
		/** end: 소방점검정보 */

		/** begin: 융합정보 */
		/** 건물맵핑 */
		public static final String CONVERGENCE_BUILDING_MAPPING_TB_NM = "tb_building_mapping";
		/** - */
		public static final String CONVERGENCE_EQUIPMENT_TB_NM = "tb_convergence_equipment";
		/** - */
		public static final String CONVERGENCE_BUILDING_TB_NM = "tb_convergence_building";
		/** 건물 주소 정보 */
		public static final String CONVERGENCE_ADDR_NAVI_BUILD_TB_NM = "tb_addr_navi_build";
		/** 지번정보 */
		public static final String CONVERGENCE_ADDR_NAVI_JIBUN_TB_NM = "tb_addr_navi_jibun";
		/** 도로명주소 */
		public static final String CONVERGENCE_ADDR_ROAD_TB_NM = "tb_addr_road";
		/** 건축물대장 층별개요 */
		public static final String CONVERGENCE_BR_FLR_OULN_INFO_TB_NM = "tb_br_flr_ouln_info";
		/** 건축물대장 표제부 */
		public static final String CONVERGENCE_BR_TITLE_INFO_TB_NM = "tb_br_title_info";
		/** end: 융합정보 */
	}

}
