<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: dllo
  Date: 17/10/20
  Time: 下午7:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="jquery-3.2.1.js"></script>
    <style>
        #table,th,tr,td{
            border: 1px solid black;
        }
    </style>
</head>
<body>
<form action="showStaff.action" method="post">
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

    <input type="submit" value="查询" id="bt">

</form>

<table id="table">
    <tr>
        <th>id</th>
        <th>姓名</th>
        <th>部门</th>
        <th>职务</th>
    </tr>
    <s:iterator var="staff" value="#attr.staffList">
        <tr>
            <th>${staff.id}</th>
            <th>${staff.sname}</th>
            <th>${staff.department.dname}</th>
            <th>${staff.post.pname}</th>
        </tr>
    </s:iterator>
</table>

<script>
    $(function () {
        $.post("${pageContext.request.contextPath}/showDepartment.action",
                null,
                function (data) {
                    var _html = "<option value='-1'>---请选择---</option>";
                    $.each(data, function (index, per) {
                        _html += '<option value="' + per.id + '">' + per.dname + '</option>';
                    });
                    $("#department").html(_html);
                }, "json"
        );

        $("#department").change(function () {
            $.post("${pageContext.request.contextPath}/showPost.action",
                    {departId:$("#department").val()},
                    function (data) {
                        var _html = "<option value='-1'>---请选择---</option>";
                        $.each(data, function (index, per) {
                            _html += '<option value="' + per.id + '">' + per.pname + '</option>';
                        });
                        $("#post").html(_html);
                    }, "json"
            );
        });


        <%--$("#bt").click(function () {--%>
            <%--$.post("${pageContext.request.contextPath}/showStaff.action",--%>
                    <%--{department: $("#department").val(), post: $("#post").val()},--%>
                    <%--function (date) {--%>
                        <%--var _html = "";--%>
                        <%--$.each(data, function (index, per) {--%>
                            <%--_html += "<tr><th>"+per.id+"</th><th>"+per.sname+"</th><th>"+per.department.dname+"</th><th>"+per.post.pname+"</th></tr>";--%>
                        <%--});--%>
                        <%--alert(date);--%>
                        <%--$("#table").html(_html);--%>
                    <%--},--%>
                    <%--"json"--%>

            <%--)--%>
        <%--})--%>
    })
</script>
</body>
</html>
