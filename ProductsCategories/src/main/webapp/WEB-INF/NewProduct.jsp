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
		<h1>New Product</h1>
		<a href="/">Home</a>
		<form:form action="/products/add" method="post"
			modelAttribute="newProduct">
			<div class="form-group form-inline ">
				<form:label path="name">Name</form:label>
				<form:errors path="name" class="text-danger" />
				<form:input path="name" />
			</div>
			<div class="form-group form-inline ">
				<form:label path="description" >Description </form:label>
				<form:errors path="description" class="text-danger" />
				<form:textarea rows="3" cols="50" path="description" />
			</div>
			<div class="form-group form-inline ">
				<form:label path="price">Price </form:label>
				<form:errors path="price" class="text-danger" />
				<form:input type="number" step="0.01" path="price" />
			</div>
			<input type="submit" value="Submit">
		</form:form>
	</div>
</body>
</html>