<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<html>
<meta content="text/html" charset="UTF-8">
<head>
    <title>欢迎登录</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/loginCheck" method="post">
<table align="center" width="400" bgcolor="#6495ed">
    <tr align="center">
        <td>用户名：<input type="text" name="username"/></td>
        <td>密  码：<input type="text" name="password"/></td>
    </tr>
    <tr>
        <td align="center"><input type="submit" value="登录"></td>
    </tr>
</table>
</form>
</body>
</html>
