<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<meta http-equiv="X-UA-Compatible" content="IE=11" />
<html ng-app="sbp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>(주)신호 입찰 프로젝트 [ADONCE]</title>
<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<script type="text/javascript">
	var AUTHENTICATION = angular.fromJson('${authentication}'); // ${authentication}의 값이 JSON 문자열이기 때문에 작은 따옴표로 문자열을 표기한다.
	var APP_NAME = "sbp";
	var pcl = angular.module(APP_NAME, [ "ui.bootstrap", "ngRoute", "ngResource", "ngAnimate", "ngSanitize", "ngFileUpload" ]);
	var __CSRF__ = {
		headerName : '${_csrf.headerName}',
		parameterName : '${_csrf.parameterName}',
		token : '${_csrf.token}'
	};
</script>
<script type="text/javascript" src="${ctx}/scripts/ctrl/ng-support.js"></script>
<script type="text/javascript" src="${ctx}/scripts/ctrl/contents_menu_config.js"></script>
<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/ctrl_content_menu_container.js"></script>

<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/ftr_pcl.js"></script>
<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/res_pcl.js"></script>
<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/ctrl_pcl.js"></script>



<!-- Main: S -->
<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/main/ctrl_main.js"></script>
<!-- Main: E -->

<!-- Application: S -->
<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/management/application/ftr_application.js"></script>
<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/management/application/ctrl_application.js"></script>
<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/management/application/dlg_regist_application.js"></script>
<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/management/application/dlg_modify_application.js"></script>
<!-- Application: E -->

<!-- FACI: S -->
<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/faci/ftr_faci.js"></script>
<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/faci/res_faci.js"></script>
<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/faci/ctrl_faci.js"></script>
<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/faci/dlg_view_detail_faci.js"></script>
<!-- FACI: E -->

<!-- FACI DATA: S -->
<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/faci/dlg_real_sensing_equip.js"></script>
<!-- FACI DATA: E -->

<!-- Subscription: S -->
<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/subscription/ftr_subscription.js"></script>
<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/subscription/res_subscription.js"></script>
<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/subscription/ctrl_subscription_app.js"></script>
<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/subscription/ctrl_subscription_data.js"></script>
<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/subscription/dlg_add_subscription.js"></script>
<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/subscription/dlg_subscription_data_detail.js"></script>
<!-- Subscription: E -->

<!-- Realtime Monitoring: S -->
<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/frm/ftr_frm.js"></script>
<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/frm/res_frm.js"></script>
<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/frm/ctrl_frm_sensing_equip_info.js"></script>
<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/frm/ctrl_frm_sensing_info.js"></script>
<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/frm/ctrl_frm_event_info.js"></script>
<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/frm/dlg_reg_sensing_equip_info.js"></script>
<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/frm/dlg_mod_sensing_equip_info.js"></script>
<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/frm/dlg_detail_info.js"></script>
<!-- Realtime Monitoring: E -->

<!-- Service: S -->
<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/service/res_service.js"></script>
<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/service/ctrl_service.js"></script>
<!-- Service: E -->

<!-- Permission: S -->
<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/permission/ftr_permission.js"></script>
<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/permission/res_permission.js"></script>
<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/permission/ctrl_permission.js"></script>
<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/permission/ctrl_permission_token.js"></script>
<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/permission/dlg_regist_token.js"></script>
<!-- Permission: E -->

<!-- Management: S -->

<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/management/ftr_api.js"></script>
<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/management/ctrl_management_api.js"></script>
<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/management/dlg_regist_api.js"></script>
<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/management/dlg_modify_api.js"></script>

<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/management/ftr_datasource.js"></script>
<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/management/ctrl_management_datasource.js"></script>
<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/management/dlg_regist_datasource.js"></script>
<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/management/dlg_modify_datasource.js"></script>
<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/management/dlg_view_subscr_column.js"></script>
<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/management/dlg_view_datasource.js"></script>

<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/management/user/ftr_user.js"></script>
<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/management/dlg_api_detail.js"></script>
<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/management/ctrl_management_permission.js"></script>
<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/management/user/ctrl_management_user.js"></script>
<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/management/user/ctrl_user_info.js"></script>
<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/management/user/dlg_modify_user.js"></script>
<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/management/res_management.js"></script>

<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/management/service/ftr_service.js"></script>
<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/management/service/ctrl_management_service.js"></script>
<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/management/service/dlg_regist_service.js"></script>
<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/management/service/dlg_modify_service.js"></script>

<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/management/system/ctrl_management_system.js"></script>
<script type="text/javascript" src="${ctx}/scripts/ctrl/pcl/management/system/res_system.js"></script>


<!-- Management: E -->

<style type="text/css">
.sub_menu_icon_collapse {
  background: url(../images/icon_menu_on.png) no-repeat 0px 5px;
  width:16px;
  display:inline-block;
  cursor: pointer;
  margin-left: 5px;
}
.sub_menu_icon_expand {
  background: url(../images/icon_menu_on.png) no-repeat 0px -25px;;
  width:16px;
  display:inline-block;
  cursor: pointer;
  margin-left: 5px;
}

.faci_li {
  padding-left:10px !important;
  /* overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis; */
}

.menu_style:hover {
  background-color: #3b78ce;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  width:195px;
}

.menu_style:hover > div {
  color: white !important;
}

.menu_style:hover > a > div {
  color: white !important;
}

.faci_sub_menu_div {
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  /* width:180px; */
  display:inline-block;
  /* background: url(../images/icon_menu02_on.png) no-repeat 0px 1px; */
}

.faci_sub_menu_div:hover {
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  width:180px;
  display:inline-block;
  /* background: url(../images/icon_menu02_on.png) no-repeat 0px -29px; */
}

.faci_sub_menu_a {
  background: url(../images/icon_menu02_on.png) no-repeat 5px 4px !important;
}

.faci_sub_menu_a:hover {
  background: url(../images/icon_menu02_on.png) no-repeat 5px -25px !important;
}

.left_menu_faci {
  /* overflow-x: hidden;
  overflow-y: auto; */
  /* overflow: auto; */
  overflow: overlay;
}

/* .left_menu_faci::-webkit-scrollbar {
  display: none;
} */

.left_menu_hr {
  display: block;
  margin: 10px 0 10px;
}

::-webkit-scrollbar {
      width: 15px;
} 

::-webkit-scrollbar-track {
      background-color:#f1f1f1;
      /* background-color: #e6e6e6; */
} /* the new scrollbar will have a flat appearance with the set background color */
 
::-webkit-scrollbar-thumb {
      background-color: #fff;
      border:1px solid #ddd; 
      /* background-color: #3e4956; */ 
      /* background-color: rgba(0, 0, 0, 0.2); */ 
} /* this will style the thumb, ignoring the track */
 
::-webkit-scrollbar-button {
      background-color: rgba(242, 242, 242);
      /* background-color: #3e4956; */
} /* optionally, you can style the top and the bottom buttons (left and right for horizontal bars) */
 
::-webkit-scrollbar-corner {
      background-color: black;
}


.left_menu_div {
  display:inline-block; 
  border:0; 
  vertical-align:top; 
  color:#666 !important;
  font-size:13px;
  font-weight: 600;
  line-height:15px; 
  padding:3px 0 3px 17px;
}

.left_menu_div:hover {
  display:inline-block; 
  border:0; 
  vertical-align:top; 
  color:#3b78ce !important;
  background: transparent !important;
  font-size:13px;
  font-weight: 600;
  line-height:15px; 
  padding:3px 0 3px 17px;
  cursor:pointer;
}

.selected_menu {
  font-weight: bold !important;
}

</style>

</head>
<body ng-controller="PclCtrl" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">

  <!-- header :S -->
  <div id="header">
    <div class="layer">
      <h1>
        <a href="${ctx}/#/"><img src="${ctx}/images/logo.png" alt="소방활동융합정보플랫폼 [ETRI]" border="0"></a>
      </h1>
      <!-- header_btn : S -->
      <div class="topmenu">
        <ul>
          <li><a href="${ctx}/#/myinfo "><span class="myinfo">My Info</span></a></li>
          <li><a href="" ng-click="logout()" ng-confirm-click="서비스에서 나가시겠습니까?"> Logout </a></li>
        </ul>
      </div>
      <!-- header_btn : E -->
    </div>
  </div>
  <!-- end header -->

  <!-- container-->
  <div class="container" id="container" ng-style="container.size">
    <div class="con_layer">
      <!-- left_menu :S -->
      <div id="left_menu" style="overflow:hidden;">
        <ul>
          <li class="submenu" ng-repeat="routeContent in routeContents" ng-if="isAllowedContent(routeContent)">
            <a ng-if="!routeContent.standalone" href="{{routeContent.getUrl(false)}}" ng-click="clickMenu(routeContent)"> 
              {{routeContent.display}}
            </a> 
            <span ng-if="routeContent.content == 'faci'" style="cursor:pointer;">
              <i class="fa fa-plus-square clickable" ng-click="allExpand(true)" title="전체 열기"></i>
              <i class="fa fa-minus-square clickable" ng-click="allExpand(false)" title="전체 닫기"></i>
            </span> 
            <a ng-if="routeContent.standalone" href="" ng-click="handleStandalone(routeContent.content, routeContent.menus.values()[0].key, routeContent.menus.values()[0].templateUrl)">
                {{routeContent.display}}
            </a>
            <ul id="{{routeContent.content}}" class="bubble left_menu_faci" ng-if="routeContent.countOfMenus() > 0" style="max-height: calc(100% - 450px);">
              <div class="pointer"></div>
              <li ng-repeat="menu in routeContent.menus.values()" ng-if="!menu.shadow && isAllowedSubmenu(menu)">
                <div class="menu_style clickable" style="width: 195px;"ng-click="collapseGroup(menu);moveLocation(routeContent.content, '/' + routeContent.content + '/' + menu.key);clickMenu(routeContent, menu);">
                  <div class="sub_menu_icon_expand" ng-class="menu.selected ? 'selected_menu' : ''" ng-if="!menu.collapse && routeContent.content == 'faci'">&nbsp</div>
                  <div class="sub_menu_icon_collapse" ng-class="menu.selected ? 'selected_menu' : ''" ng-if="menu.collapse || routeContent.content != 'faci'">&nbsp</div>
                  <!-- <div class="sub_menu_icon_collapse" ng-class="menu.selected ? 'selected_menu' : ''" ng-if="menu.collapse || routeContent.content != 'faci' && !menu.selected">&nbsp</div>
                  <div class="sub_menu_icon_expand" ng-class="menu.selected ? 'selected_menu' : ''" ng-if="menu.selected && routeContent.content != 'faci'">&nbsp</div> -->
                  <!-- <div class="left_menu_div" ng-if="!menu.standalone" style="padding-left: 2px;">{{menu.display}}</div> -->
                  <div class="left_menu_div" ng-class="menu.selected ? 'selected_menu' : ''" ng-if="!menu.standalone && (menu.display != '공통' && menu.display != '융합')" style="padding-left: 2px;">{{menu.display}}</div>
                  <div class="left_menu_div" ng-class="menu.selected ? 'selected_menu' : ''" ng-if="!menu.standalone && menu.display == '공통'" style="color: #e25a5a !important; padding-left: 2px;">{{menu.display}}</div>
                  <div class="left_menu_div" ng-class="menu.selected ? 'selected_menu' : ''" ng-if="!menu.standalone && menu.display == '융합'" style="color: #20bb20 !important; padding-left: 2px;">{{menu.display}}</div>
                </div>
                <!-- <a ng-if="routeContent.content != 'faci' && !menu.standalone" href="{{routeContent.getUrl(menu.standalone)}}/{{menu.key}}" ng-click="clickMenu(routeContent, menu)" style="padding-left: 2px;">{{menu.display}}</a> -->
                <a ng-if="menu.standalone" href=""
                ng-click="handleStandalone(routeContent.content, menu.key, menu.templateUrl)">{{menu.display}}</a>
                
                <!-- FACI > Data > 각 Table -->
                <ul ng-if="!menu.collapse && routeContent.content == 'faci'">
                  <li class="submenu faci_li" ng-repeat="tbMenu in menu.menus track by $index" ng-if="isAllowedContent(tbMenu)" >
                    <div class="menu_style clickable">
                      <!-- <img alt="" src="../images/faci_sub_meu_icon.png" style="width:15px;height:15px;top: 0;position: relative;margin-left: 5px;"/> -->
                      <a class="faci_sub_menu_a" style="width:100%;" ng-if="!tbMenu.standalone" href="{{routeContent.getUrl(menu.standalone)}}/{{menu.key}}/{{tbMenu.id}}" ng-click="clickMenu(routeContent, menu, tbMenu)">
                        <div class="faci_sub_menu_div" title="{{tbMenu.display}}" ng-class="tbMenu.selected ? 'selected_menu' : ''" style="padding-left:20px;">
                          {{tbMenu.display}}
                        </div>
                      </a> 
                      <a ng-if="tbMenu.standalone" href="" ng-click="handleStandalone(tbMenu.content, tbMenu.menus.values()[0].key, tbMenu.menus.values()[0].templateUrl)">{{tbMenu.display}} </a>
                    </div>
                  </li>
                </ul>
                <!-- 실시간 모니터링 > Data > 각 Table -->
                <ul ng-if="!menu.collapse && routeContent.content == 'frm'">
                  <li class="submenu faci_li" ng-repeat="tbMenu in menu.menus track by $index" ng-if="isAllowedContent(tbMenu)" >
                    <div class="menu_style clickable">
                      <a class="faci_sub_menu_a" style="width:100%;" ng-if="!tbMenu.standalone" href="{{routeContent.getUrl(menu.standalone)}}/{{menu.key}}/{{tbMenu.id}}" ng-click="clickMenu(routeContent, menu, tbMenu)">
                        <div class="faci_sub_menu_div" title="{{tbMenu.display}}" ng-class="tbMenu.selected ? 'selected_menu' : ''" style="padding-left:20px;">
                          {{tbMenu.display}}
                        </div>
                      </a> 
                      <a ng-if="tbMenu.standalone" href="" ng-click="handleStandalone(tbMenu.content, tbMenu.menus.values()[0].key, tbMenu.menus.values()[0].templateUrl)">{{tbMenu.display}} </a>
                    </div>
                  </li>
                </ul>
              </li>
            </ul>
            <hr class="left_menu_hr" size="10px;">
          </li>
        </ul>
      </div>
      <!-- left_menu :E -->

      <div ng-view></div>
    </div>
  </div>
  <!-- end container-->

  <!--START: footer
  <div class="footer footer_violet">
    <div class="footer_cont">Copyright ⓒ [ETRI] 소방활동융합정보플랫폼. All Rights Reserved.</div>
  </div>
   END: footer-->

  <form id="logout_form" action="${ctx}/j_spring_security_logout" method="POST">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
  </form>
</body>
</html>