<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html lang="ko">
<head>
<title>소방활동융합정보플랫폼 [ETRI]</title>
<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="title" content="소방활동융합정보플랫폼" />
<meta name="keywords" content="소방활동융합정보플랫폼" />
<meta name="description" content="API - Management" />
<meta name="copyright" content="Copyright@2019 소방활동융합정보플랫폼. All Rights Reserved." />
<!-- <link rel="apple-touch-icon" href="images/images/favicon.png" /> -->
<script language='JavaScript' type="text/javascript">
	function microService(url, n, w, h, s) {
		var winl = (screen.width - w) / 2;
		var wint = (screen.height - h) / 2;
		winprops = 'height=' + h + ',width=' + w + ',top=' + wint + ',left=' + winl + ',scrollbars=' + s + ',resizable'
		win = window.open(url, n, winprops)
		if (parseInt(navigator.appVersion) >= 4) {
			win.window.focus();
		}
	}

	/****************** s: 로그인 시 ID 저장  **********************/
	jQuery(document).ready(function() {
		// 저장된 쿠키값을 가져와서 ID 칸에 넣어준다. 없으면 공백으로 들어감.
		var userInputId = getCookie("userInputId");
		jQuery("input[name='username']").val(userInputId);

		if (jQuery("input[name='username']").val() != "") { // 그 전에 ID를 저장해서 처음 페이지 로딩 시, 입력 칸에 저장된 ID가 표시된 상태라면,
			jQuery("#idSaveCheck").attr("checked", true); // ID 저장하기를 체크 상태로 두기.
		}

		jQuery("#idSaveCheck").change(function() { // 체크박스에 변화가 있다면,
			if (jQuery("#idSaveCheck").is(":checked")) { // ID 저장하기 체크했을 때,
				var userInputId = $("input[name='username']").val();
				setCookie("userInputId", userInputId, 7); // 7일 동안 쿠키 보관
			} else { // ID 저장하기 체크 해제 시,
				deleteCookie("userInputId");
			}
		});

		// ID 저장하기를 체크한 상태에서 ID를 입력하는 경우, 이럴 때도 쿠키 저장.
		jQuery("input[name='username']").keyup(function() { // ID 입력 칸에 ID를 입력할 때,
			if ($("#idSaveCheck").is(":checked")) { // ID 저장하기를 체크한 상태라면,
				var userInputId = jQuery("input[name='username']").val();
				setCookie("userInputId", userInputId, 7); // 7일 동안 쿠키 보관
			}
		});
	});

	function setCookie(cookieName, value, exdays) {
		var exdate = new Date();
		exdate.setDate(exdate.getDate() + exdays);
		var cookieValue = escape(value) + ((exdays == null) ? "" : "; expires=" + exdate.toGMTString());
		document.cookie = cookieName + "=" + cookieValue;
	}

	function deleteCookie(cookieName) {
		var expireDate = new Date();
		expireDate.setDate(expireDate.getDate() - 1);
		document.cookie = cookieName + "= " + "; expires=" + expireDate.toGMTString();
	}

	function getCookie(cookieName) {
		cookieName = cookieName + '=';
		var cookieData = document.cookie;
		var start = cookieData.indexOf(cookieName);
		var cookieValue = '';
		if (start != -1) {
			start += cookieName.length;
			var end = cookieData.indexOf(';', start);
			if (end == -1)
				end = cookieData.length;
			cookieValue = cookieData.substring(start, end);
		}
		return unescape(cookieValue);
	}
	/****************** e: 로그인 시 ID 저장  **********************/
</script>
</head>
<body topmargin="0" leftmargin="0" marginheight="0" marginwidth="0">
  <div id="wrap_login">
    <form action="${pageContext.request.contextPath}/j_security_check" method="post">
      <!-- contents_login -->
      <div id="contents_login">

        <div class="login_area">
          <h1>
            <img class="main_title" src="images/bg_shinho_title.png">
          </h1>
          <!--div class="l_intro">환영합니다.</div-->
          <ul>
            <li><input name="username" class="id" type="text" placeholder="User ID"></li>
            <li><input name="password" class="pw" type="password" placeholder="Password"></li>
            <li><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"></li>
            <li><button type="submit" class="btn_login">로그인</button></li>
            <!--             <li><a href="app_step01.html" class="btn_login">Login</a></li> -->
            <li class="login_check">
              <span><input type="checkbox" name="group01_son" id="idSaveCheck" class="dsi"><label for="idSaveCheck"> ID 저장</label></span> 
              <span><a onclick="location.href='/signup'">회원가입</a></span>
              <span><a onclick="location.href='/init-password'">비밀번호 초기화</a></span>
            </li>
              
          </ul>
        </div>

        <div class="l_copy_area">Copyright ⓒ [ADONCE] (주)신호]. All Rights Reserved.</div>

      </div>
      <!--end contents Area -->
    </form>
    <!--end contents_login -->
  </div>
  <div id="snackbar">${message}</div>
  <!--end login -->
  <script>
			/**
			 * 로그인 화면 toast 띄우기
			 */
			var toastFunc = function() {
				var x = document.getElementById("snackbar");
				x.className = "show";
				setTimeout(function() {
					x.className = x.className.replace("show", "");
				}, 3000);
			};
			toastFunc();
  </script>
</body>
</html>
<!-- end 컨텐츠 영역 -->
</body>
</html>
