<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
           uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<html>

<head>
    <title>Company Page</title>
</head>

<body>
<h2>Admin Page</h2>
<hr>

Welcome to the admin page!
<p><%--
    <c:out value="${pageContext.request.contextPath}"></c:out>--%>
    User:
    <security:authentication property="principal.username" />
    <br> <br> Role(s):
    <security:authentication property="principal.authorities" />
</p>

<a href="${pageContext.request.contextPath}/">
    Home
</a>
<br>
<a href="${pageContext.request.contextPath}/leaders">
    Leaders
</a>
<form:form action="${pageContext.request.contextPath}/logout"
           method="POST">

    <input type="submit" value="Logout" />

</form:form></body>

</html>