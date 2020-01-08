<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>注册用户</title>
    <script type="text/javascript" charset="UTF-8">
        function checkPwd() {
            var pwd1 = document.addForm.userPwd.value;
            var pwd2 = document.addForm.testPwd.value;
            if(pwd1 == ""){
                alert("请输入密码");
                document.addForm.userPwd.focus();
                return false;
            }
            if (pwd1 != pwd2){
                document.getElementById("msg").innerHTML="两次输入密码不一致"
            }
        }
        function getTime() {
            var date = new Date();

            document.getElementById("curr_date").innerHTML= date.getDate();
            document.getElementById("curr_time").innerHTML= date.getTime();
        }
    </script>
    <div id="msg" style="color:red;"></div>
</head>
<body>
<form name="addForm" action="${pageContext.request.contextPath}/addUser" accept-charset="UTF-8" method="post">
    <table bgcolor="#6495ed" align="center" width="500">
        <tr align="center">
            <td align="center">用户注册表</td>
        </tr>
        <tr>
            <td>客户编号</td>
            <td><input type="text" name="userId" /></td>
        </tr>
        <tr>
            <td>客户名字</td>
            <td><input type="text" name="userName"></td>
        </tr>
        <tr>
            <td>客户类别</td>
            <td>
                <input type="radio" name="userType" value="0"/>管理员
                <input type="radio" name="userType" value="1" checked/>普通用户
                <input type="radio" name="userType" value="2"/>VIP用户
            </td>
        </tr>
        <tr>
            <td>用户状态</td>
            <td><input type="hidden" name="userStatus" value="0" placeholder="正常"/></td>
        </tr>
        <tr>
            <td>密码</td>
            <td><input type="text" name="userPwd" placeholder="6位到12位，字母+数字+(特殊符号)"/></td>
        </tr>
        <tr>
            <td>确认密码</td>
            <td><input type="text" name="testPwd" placeholder="6位到12位，字母+数字+(特殊符号)" onchange="checkpwd()"/></td>
        </tr>

        <tr>
            <td>注册日期</td>
            <td><div id="curr_date" align="center"></div></td>
        </tr>
        <tr>
            <td>注册时间</td>
            <td><div id="curr_time" align="center"></div></td>
        </tr>
        <tr>
            <td>备注</td>
            <td><input type="text" name="remark"/></td>
        </tr>
        <tr>
            <td>登录标志</td>
            <td><input type="hidden" name="loginFlag" value="0" placeholder="正常登录"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="确认"/></td>
        </tr>
    </table>
</form>
</body>
</html>
