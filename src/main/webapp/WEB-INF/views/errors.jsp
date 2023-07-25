<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<meta http-equiv="X-UA-Compatible" content="IE=11" />
<html ng-app="sbp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> (주)신호 입찰 프로젝트 [ADONCE]</title>
<%@ include file="/WEB-INF/views/taglibs.jsp"%>

</head>
<body ng-controller="PclCtrl" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow: hidden;">

  <!-- Header :S -->
  <div id="header">
    <div class="layout">
      <h1>
        <a href="${ctx}/#/"><img src="${ctx}/images/logo.png" alt="소방활동융합정보플랫폼 [ETRI]" border="0"></a>
      </h1>
    </div>
  </div>
  <!-- end header -->
  <!-- START: error -->
  <div style="display: block; height: 100%; width: 100%;">
    <div id="contents_error" style="top: 50%; margin-top: -180px; text-align: center;">
      <ul>
        <li class="err_code">${code}</li>
        <li class="err_status">${status}</li>
        <li class="err_desc">${desc}</li>
      </ul>
      <div>
        <b>[Service Error Message]</b><br>${cause}
      </div>

      <li><a class="btn error-btn" href="javascript:history.go(-1)"> ← 뒤로가기</a></li>
    </div>
  </div>
  <!-- END display error -->
</body>
</html>