<%--
  Created by IntelliJ IDEA.
  User: anikonova
  Date: 22.02.18
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Add | Edit employee</title>
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
    Enter employee info:
    <form method="post" action="employees">
        <fieldset class="form-group">
            <label>Email</label>
            <input name="email" type="text" class="form-control" value="${dto.email}"/>
            <br/>
        </fieldset>
        <fieldset class="form-group">
            <label>Name</label>
            <input name="name" type="text" class="form-control" value="${dto.name}"/>
            <br/>
        </fieldset>
        <fieldset class="form-group">
            <label>Birthday</label>
            <input name="birthday" type="text" class="form-control" value="${dto.birthday}"/>
            <br/>
        </fieldset>

        <fieldset class="form-group">
            <label>Room</label>
            <input name="room" type="text" class="form-control" value="${dto.room}"/> <br/>
        </fieldset>

        <fieldset class="form-group">
            <label>Department</label>
            <input name="departmentName" type="text" class="form-control"
                   <c:if test="${not empty department}">value="${department}"/></c:if>
            <c:if test="${empty department}"> value="${dto.departmentName}"</c:if>

            <br/>
        </fieldset>

        <input type="hidden" name="id" value="${param.id}"/>

        <input name="action" type="submit" class="btn btn-success" value="submit"/>


    </form>


    <p style="color: brown">${warning}</p>

    <c:forEach items="${errors}" var="entry">
        <p style="color:red;"><strong>${entry.key}: </strong>${entry.value}</p>
    </c:forEach>


</div>

</body>
</html>

