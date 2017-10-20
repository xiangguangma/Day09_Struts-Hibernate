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
</head>
<body>

<form action="" method="post">
    部门:
    <select name="department" id="department">
        <option value="-1">---请选择---</option>
    </select>
    职务:
    <select name="post" id="post">
        <option value="-1">---请选择---</option>
    </select>

    <input type="submit" value="查询">
</form>

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
        xmlHttp.send("departmentId=" + dId);
        xmlHttp.onreadystatechange = function () {
            if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                var jsonData = eval("(" + xmlHttp.responseText + ")");
                var selectP = document.getElementById("post");
                selectP.innerHTML = "<option value='-1'>---请选择---</option>";
                for (var i = 0; i < jsonData.length; i++){
                    var id = jsonData[i].pId;
                    var name = jsonData[i].pName;
                    selectP.innerHTML += "<option value='"+id+"'>"+ name +"</option>"
                }

            }
        }
    }
</script>

</body>
</html>
