<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var="datePattern"><fmt:message key="date.format"/></c:set>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<script type="text/javascript">
// context for 'Global Use'
var ctx = "${ctx}";
</script>

<!-- begin: angularjs -->
<script src="${ctx}/scripts/libs/angularjs/angular.js" type="text/javascript"></script>
<script src="${ctx}/scripts/libs/angularjs/angular-animate.js" type="text/javascript"></script>
<script src="${ctx}/scripts/libs/angularjs/angular-resource.js" type="text/javascript"></script>
<script src="${ctx}/scripts/libs/angularjs/angular-route.js" type="text/javascript"></script>
<script src="${ctx}/scripts/libs/angularjs/angular-sanitize.js" type="text/javascript"></script>
<script src="${ctx}/scripts/libs/angularjs/ui-bootstrap-tpls-2.5.0.js" type="text/javascript"></script>
<script src="${ctx}/scripts/libs/angularjs/ng-file-upload-shim.js" type="text/javascript" ></script>
<script src="${ctx}/scripts/libs/angularjs/ng-file-upload.js" type="text/javascript" ></script>
<!-- end: angularjs -->

<!-- begin: jQuery -->
<script src="${ctx}/scripts/libs/jquery/jquery-3.4.1.js" type="text/javascript"></script>
<script src="${ctx}/scripts/libs/jquery/ui/jquery-ui-1.12.1/jquery-ui.js" type="text/javascript"></script>
<link rel="StyleSheet" href="${ctx}/scripts/libs/jquery/ui/jquery-ui-1.12.1/jquery-ui.css" type="text/css">
<!-- end: jQuery -->

<!-- begin: Common Utilities -->
<script src="${ctx}/scripts/libs/third/browser-utils.js" type="text/javascript"></script>
<script src="${ctx}/scripts/libs/third/collections.ext.js" type="text/javascript"></script>
<script src="${ctx}/scripts/libs/third/common-utils.js" type="text/javascript"></script>
<script src="${ctx}/scripts/libs/third/ng-utils.js" type="text/javascript"></script>
<script src="${ctx}/scripts/libs/third/object.ext.js" type="text/javascript"></script>
<script src="${ctx}/scripts/libs/third/string.ext.js" type="text/javascript"></script>
<!-- end: Common Utilities -->


<!-- begin: css  -->
<link rel="stylesheet" type="text/css" href="${ctx}/css/common.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/layout.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/font-awesome.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/fontawesome_v5.css" />
<!-- end: css  -->

<!-- begin: favicon icon  -->
<link rel="shortcut icon" type="image/x-icon" href="${ctx}/images/favicon.ico" />
<link rel="apple-touch-icon" href="${ctx}/images/favicon.png" />
<!-- end: favicon icon  -->