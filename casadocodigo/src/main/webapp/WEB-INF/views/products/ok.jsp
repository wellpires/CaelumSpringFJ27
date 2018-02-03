<%@taglib uri="/WEB-INF/tags" prefix="cdc"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="cdc"%>

<cdc:page title="Cadastro de livros">
	<sec:authorize access="isAuthenticated()">
		<sec:authentication property="principal" var="user" />
		<div>Olá ${user.name}</div>
	</sec:authorize>
	<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	</head>
	<p>Produto cadastrado com sucesso!</p>
</cdc:page>
