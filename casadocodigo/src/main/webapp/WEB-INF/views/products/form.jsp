<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="cdc"%>

<cdc:page title="Cadastro de livros - Casa do código">
	<sec:authorize access="isAuthenticated()">
		<sec:authentication property="principal" var="user" />
		<div>Olá ${user.name}</div>
	</sec:authorize>


	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	</head>
	<form:form action="${spring:mvcUrl('PC#save').build()}" method="POST"
		commandName="product" enctype="multipart/form-data">
		<div>
			<label for="title">Título</label>
			<form:input path="title" id="title" />
			<form:errors path="title" />
		</div>

		<div>
			<label for="description">Descrição</label>
			<form:textarea rows="10" cols="20" path="description"
				id="description" />
			<form:errors path="description" />
		</div>

		<div>
			<label for="numberOfPages">Número de páginas</label>
			<form:input type="text" path="numberOfPages" id="numberOfPages" />
			<form:errors path="numberOfPages" />
		</div>

		<div>
			<c:forEach items="${types}" var="bookType" varStatus="status">
				<div>
					<label for="price_${bookType}">${bookType}</label> <input
						type="text" name="prices[${status.index}].value"
						id="price_${bookType}"> <input type="hidden"
						name="prices[${status.index}].bookType" value="${bookType}">
				</div>
			</c:forEach>
		</div>

		<div>
			<label for="releaseDate">Data de lançamento</label>
			<form:input type="text" path="releaseDate" id="releaseDate" />
			<form:errors path="releaseDate" />
		</div>

		<div>
			<label for="summary">Sumário do livro</label> <input type="file"
				name="summary" id="summary" />
			<form:errors path="summaryPath" />
		</div>

		<div>
			<input type="submit" value="Enviar">
		</div>
	</form:form>
</cdc:page>