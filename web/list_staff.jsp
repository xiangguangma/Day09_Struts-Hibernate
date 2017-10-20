<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: dllo
  Date: 17/10/20
  Time: 上午10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>员工列表</title>
    <script src="jquery-3.2.1.js"></script>
</head>
<body>

<form action="findStaff.action" method="post">
    部门:
    <select name="department" id="department" onchange="showPost(this)">
        <option value="-1">---请选择---</option>
        <%--遍历部门集合--%>
        <s:iterator value="#attr.departments" var="department">
            <option value="${department.id}">${department.dname}</option>
        </s:iterator>
    </select>
    职务:
    <select name="post" id="post">
        <option value="-1">---请选择---</option>
    </select>

    <input type="button" value="查询" id="bt">
</form>

<table>
    <c:if test="${applicationScope.staffs.size > 0}">
        <tr>
            <th>id</th>
            <th>姓名</th>
            <th>部门</th>
            <th>职务</th>
        </tr>
        <s:iterator value="#application.staffs" var="staff">
            <tr>
                <th>${staff.id}</th>
                <th>${staff.sname}</th>
                <th>${staff.department.dname}</th>
                <th>${staff.post.pname}</th>
            </tr>
        </s:iterator>
    </c:if>
</table>

<script type="text/javascript">
    function createXMLHttpRequest() {
        try {
            return new XMLHttpRequest();
        } catch (e) {
            try {
                return new ActiveXObject("Msxml2.HTTP");
            } catch (e) {
                try {
                    return new ActiveXObject("Microsoft.HTTP");
                } catch (e) {
                    throw e;
                }
            }
        }
    }
    function showPost(obj) {
        var dId = obj.value;
        var xmlHttp = createXMLHttpRequest();
        var url = "${pageContext.request.contextPath}/showPost.action";
        xmlHttp.open("POST", url, true);
        xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xmlHttp.send("departId=" + dId);
        xmlHttp.onreadystatechange = function () {
            if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                var jsonData = eval("(" + xmlHttp.responseText + ")");
                var selectP = document.getElementById("post");
                selectP.innerHTML = "<option value='-1'>---请选择---</option>";
                for (var i = 0; i < jsonData.length; i++) {
                    var id = jsonData[i].id;
                    var name = jsonData[i].pname;
                    selectP.innerHTML += "<option value='" + id + "'>" + name + "</option>"
                }

            }
        }
    }
</script>
</body>
</html>
