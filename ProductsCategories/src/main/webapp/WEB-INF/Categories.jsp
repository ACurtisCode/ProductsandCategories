<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) -->
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<!-- change to match your file/naming structure -->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h1>"${ category.getName()}"</h1>
		<a href="/">Home</a>
		<h4>Products:</h4>
		<ul>
			<c:forEach var="product" items="${productsOwn}">
				<li>${ product.getName()}</li>
			</c:forEach>
		</ul>
		<h4>Add Category:</h4>
		<form:form action="/categories/${ category.getId() }"
			method="post">
			<select name="addProduct" id="addProduct" class="input">
				<c:forEach var="product" items="${ productsNot }">
					<option value="${ product.getId() }">${ product.getName() }</option>
				</c:forEach>
			</select>
			<input type="submit" value="Submit">
		</form:form>
	</div>
</body>
</html>