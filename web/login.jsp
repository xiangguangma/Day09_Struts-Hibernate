<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: dllo
  Date: 17/10/20
  Time: 下午2:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
<form action="login.action">
    username:
    <input type="text" name="sname"><br>
    password:
    <input type="password" name="password"><br>
    验证码:
    <input name="code" size="4">
    <img id="img" src="${pageContext.request.contextPath}/codeImg.action"><br>
    <input type="submit" value="Login">
</form>
<s:actionerror/>
<s:fielderror/>
<script>
    var img = document.getElementById("img");
    img.onclick = function () {
        var i = Math.random();
        img.src = "${pageContext.request.contextPath}/codeImg.action}/codeImg.action?random="+i;
    }
</script>
</body>
</html>
