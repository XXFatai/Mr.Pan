<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>主页</title>
<script type="text/javascript">
function toLogin(){
	console.log("登陆");
	window.location.href="${pageContext.request.contextPath}/login";
}
</script>
</head>
<body>
sessionId = ${sessionId }<br>
reqestedSessionId = ${requestScope.requestedSessionId }
<br/>
编码为 = ${encoding }
<br/>
<button onclick="toLogin()">登陆</button>
<br/>
</body>
</html>