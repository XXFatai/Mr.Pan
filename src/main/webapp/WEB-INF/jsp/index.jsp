<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>��ҳ</title>
<script type="text/javascript">
function toLogin(){
	console.log("��½");
	window.location.href="${pageContext.request.contextPath}/login";
}
</script>
</head>
<body>
sessionId = ${sessionId }<br>
reqestedSessionId = ${requestScope.requestedSessionId }
<br/>
����Ϊ = ${encoding }
<br/>
<button onclick="toLogin()">��½</button>
<br/>
</body>
</html>