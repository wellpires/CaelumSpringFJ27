<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Listagem de produtos</title>
<style>
table {
	border-collapse: collapse;
}

table, td, th {
	border: 1px solid black;
}
</style>
<script>
	window.onload = function() {
		var message = document.getElementById('message').value;

		if (message !== null && message !== undefined && message !== '') {
			alert(message);
		}
	};
</script>
</head>
<body>
	<sec:authorize access="isAuthenticated()">
		<sec:authentication property="principal" var="user" />
		<div>Olá ${user.name}</div>
	</sec:authorize>

	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<c:url value="/products/form" var="formLink" />
		<a href="${formLink}">
			Cadastrar novo produto
		</a>

	</sec:authorize>


	<input type="hidden" value="${msg}" id="message">
	<table>
		<tr>
			<th>Título</th>
			<th>Descrição</th>
			<th>Preço</th>
		</tr>

		<c:forEach items="${products}" var="product">
			<tr>
				<td>${product.title}</td>
				<td>${product.description}</td>
				<td><c:forEach items="${product.prices}" var="price">
						${price.bookType}: ${price.value}
					</c:forEach></td>
				<td><c:url value="/products/${product.id}" var="linkDetalhar" />
					<a href="${linkDetalhar}">Detalhar</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>