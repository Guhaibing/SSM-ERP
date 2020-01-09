<%--
  Created by IntelliJ IDEA.
  User: haibing
  Date: 2020/1/4
  Time: 18:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<meta charset="UTF-8"/>
<head>
    <title>菜单页面</title>
</head>
<body>
<table align="center" bgcolor="aqua">
    <tr>
        <td>
            <form action="${pageContext.request.contextPath}/user/queryUserById" accept-charset="UTF-8" name="查询用户信息" method="post">
                用户编号：<input type="text" placeholder="user_id" name="userId"/>
                <input type="submit" name="submit" value="确定">
            </form>
        </td>
    </tr>
</table>
</body>
</html>
