<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: anikonova
  Date: 22.02.18
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>

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
    <H1>Employees at ${param.department}</H1>

    <p style="color:darkslategrey;">${status}</p>
    <br/>
    <c:if test="${not empty employees}">
        <table class="table table-striped">
            <thead>
            <th>Email</th>
            <th>Name</th>
            <th>Birthday</th>
            <th>Room</th>
            <th>Actions</th>
            </thead>
            <tbody>
            <c:forEach items="${employees}" var="employee">
                <tr>
                    <td>${employee.email}</td>
                    <td>${employee.name}</td>
                    <td>${employee.birthday}</td>
                    <td>${employee.room}</td>
                    <td>&nbsp;&nbsp;<a class="btn btn-danger"
                                       href="/task1/employees?action=delete&id=${employee.id}&department=${param.department}">Delete</a>
                    </td>
                    <td>&nbsp;&nbsp;<a class="btn btn-warning"
                                       href="/task1/employees?action=edit&id=${employee.id}&department=${param.department}">Edit</a>
                    </td>

                </tr>
            </c:forEach>
            </tbody>
        </table>

    </c:if>


    <c:if test="${empty employees}">
        <caption> There are no any employees</caption>
    </c:if>

    <a class="btn btn-success" href="/task1/employees?action=edit&department=${param.department}">Add new employee</a>


</div>

</body>
</html>