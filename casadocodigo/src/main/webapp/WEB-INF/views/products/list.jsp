<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
</head>
<body>
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
			</tr>
		</c:forEach>
	</table>
</body>
</html>