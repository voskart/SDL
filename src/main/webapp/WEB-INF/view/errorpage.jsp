<%--
  Created by IntelliJ IDEA.
  User: voskart
  Date: 11.06.15
  Time: 23:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Error</title>
</head>
<body>
A user with the username <c:out value="${username}"/> and hash <c:out value="${password}"/> is not known! Wrong password or not registered?
</body>
</html>
