<%--
  Created by IntelliJ IDEA.
  User: anikonova
  Date: 22.02.18
  Time: 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>All departments</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!--Bootstrap js -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
    <H1>All Departments</H1>

    <p style="color:darkslategrey;">${status}</p>
    <br/>
    <c:if test="${not empty departments}">
        <table class="table table-striped">
            <caption>Our departments</caption>
            <thead>
            <th>Name</th>
            <th>Info</th>
            <th>Actions</th>
            </thead>
            <tbody>
            <c:forEach items="${departments}" var="department">
                <tr>
                    <td>${department.name}</td>
                    <td>${department.info}</td>
                    <td>&nbsp;&nbsp;<a class="btn btn-danger"
                                       href="/task1/departments?action=delete&id=${department.id}">Delete</a></td>
                    <td>&nbsp;&nbsp;<a class="btn btn-warning"
                                       href="/task1/departments?action=edit&id=${department.id}">Edit</a></td>
                    <td>&nbsp;&nbsp;<a class="btn btn-primary"
                                       href="/task1/employees?action=list&department=${department.name}">Show
                        employees</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </c:if>


    <c:if test="${empty departments}">
        <caption> There are no any departments</caption>
    </c:if>

    <a class="btn btn-success" href="/task1/departments?action=edit">Add new department</a>


</div>

</body>
</html>
