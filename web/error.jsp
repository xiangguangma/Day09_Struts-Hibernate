<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: dllo
  Date: 17/10/20
  Time: 上午10:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>失败页面</title>
</head>
<body>
不好意思, 出错了~~~
<%--显示错误信息--%>
<s:fielderror/>
<s:actionerror/>
</body>
</html>
