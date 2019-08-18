<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
           uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<html>

<head>
    <title>Company Home Page</title>
</head>

<body>
<h2>Company Home Page</h2>
<hr>

Welcome to the company home page!
<p><%--
    <c:out value="${pageContext.request.contextPath}"></c:out>--%>
    User:
    <security:authentication property="principal.username" />
    <br> <br> Role(s):
    <security:authentication property="principal.authorities" />
</p>
<a href="${pageContext.request.contextPath}/leaders">
    Customers
</a>
<br>
<a href="${pageContext.request.contextPath}/systems">
    Admin
</a>
<form:form action="${pageContext.request.contextPath}/logout"
           method="POST">

    <input type="submit" value="Logout" />

</form:form></body>

</html>