<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="cdc"%>

<cdc:page title="Listagem de Produtos">
	<sec:authorize access="isAuthenticated()">
		<sec:authentication property="principal" var="user" />
		<div>Olá ${user.name}</div>
	</sec:authorize>

	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<c:url value="/products/form" var="formLink" />
		<a href="${formLink}"> Cadastrar novo produto </a>
	</sec:authorize>

	<head>
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
</cdc:page>