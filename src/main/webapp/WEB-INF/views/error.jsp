<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: anikonova
  Date: 13.02.18
  Time: 18:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<h3>Sorry, an error happened</h3>
<h4>${error}</h4>
<h5>See stacktrace:</h5>
<c:forEach items="${stackTrace}" var="item">
    <p>${item}</p>
</c:forEach>
<a href="departments?action=list">Go back</a>
</body>
</html>
