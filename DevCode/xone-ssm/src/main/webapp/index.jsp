<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" %>
<html>
<meta content="text/html" charset="UTF-8">
<head>
    <title>欢迎登录</title>
    <script type="text/javascript">
        function login() {
            document.form1.action="${pageContext.request.contextPath}/user/login";
            document.form1.submit();
        }
        function register() {
            document.form1.action="${pageContext.request.contextPath}/user/register";
            document.form1.submit();
        }
    </script>
</head>
<body>
<form name="form1" method="post">
    <table align="center" width="400" bgcolor="#6495ed">
        <tr align="center">
            <td>用户名：<input type="text" name="username"/></td>
            <td>密 码：<input type="text" name="password"/></td>
        </tr>
        <tr align="center">
            <td><input type="button" value="登录" onclick="login()"></td>
            <td><input type="button" value="注册" onclick="register()"/></td>
        </tr>
    </table>
</form>
</body>
</html>
