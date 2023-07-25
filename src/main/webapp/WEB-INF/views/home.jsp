<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<script type="text/javascript">
  var __CSRF__ = {
    headerName: '${_csrf.headerName}',
    parameterName: '${_csrf.parameterName}',
    token: '${_csrf.token}'
  };
</script>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
</body>
</html>
