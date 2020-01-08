<%@ page language="java" import="com.hundsun.xone.ssm.entity.User" pageEncoding="utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<meta content="text/javascript" charset="UTF-8">
<head>
    <title>个人详细信息查询</title>
</head>
<body>
<form action="listUserInfo" accept-charset="UTF-8">
    <table title="userInfo" bgcolor="#faebd7" align="center" width="500">
        <tr align="center">
            <td align="center">个人详细信息表</td>
        </tr>
        <%
            ServletContext context = request.getServletContext();
            User user = (User) context.getAttribute("user");%>
        <% if (user != null) {%>
        <tr>
            <td>客户编号</td>
            <td><%=user.getUserId()%>
            </td>
        </tr>
        <tr>
            <td>客户名字</td>
            <td><%=user.getUserName()%>
            </td>
        </tr>
        <tr>
            <td>客户类别</td>
            <td><%=user.getUserType()%>
            </td>
        </tr>
        <tr>
            <td>用户状态</td>
            <td><%=user.getUserStatus()%>
            </td>
        </tr>
        <tr>
            <td>认证密码</td>
            <td><%=user.getUserPwd()%>
            </td>
        </tr>
        <tr>
            <td>上次更新日期</td>
            <td><%=user.getLastUpdateDate()%>
            </td>
        </tr>
        <tr>
            <td>上次更新时间</td>
            <td><%=user.getLastUpdateTime()%>
            </td>
        </tr>
        <tr>
            <td>备注</td>
            <td><%=user.getRemark()%>
            </td>
        </tr>
        <tr>
            <td>登录标志</td>
            <td><%=user.getLoginFlag()%>
            </td>
        </tr>
        <%} else {%>
        <tr>无</tr>
        <%}%>
    </table>
</form>
</body>
</html>