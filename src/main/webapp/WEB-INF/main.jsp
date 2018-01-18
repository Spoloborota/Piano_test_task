<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.spoloborota.piano.Constants" %>

<html>
<head>
	<title>Queries to StackExchange</title>
</head>

<body>
<form name="QueryForm" method="get" action="/piano/query">
	Query to StackExchange: <input type="text" name="${Constants.QUERY_TEXT}" />
	<input type="submit" value="Run" />
</form>

<c:if test="${not empty requestScope[Constants.PREVIOUS_PAGE_NUMBER]}">
	<form id="prev_form" method="get" action="/piano/query" style="float: left">
		<input type="hidden" name="${Constants.PAGE_NUMBER}" value="${requestScope[Constants.PREVIOUS_PAGE_NUMBER]}"  />
		<input type="hidden" name="${Constants.QUERY_TEXT}" value="${requestScope[Constants.CURRENT_QUERY_TEXT]}"  />
	</form>
	<button type="submit" form="prev_form" value="Submit">Previous page</button>
</c:if>

<c:if test="${not empty requestScope[Constants.NEXT_PAGE_NUMBER]}">
	<form id="next_form" method="get" action="/piano/query" style="float: right">
		<input type="hidden" name="${Constants.PAGE_NUMBER}" value="${requestScope[Constants.NEXT_PAGE_NUMBER]}"  />
		<input type="hidden" name="${Constants.QUERY_TEXT}" value="${requestScope[Constants.CURRENT_QUERY_TEXT]}"  />
	</form>
	<button type="submit" form="next_form" value="Submit">Next page</button>
</c:if>

<c:if test="${not empty requestScope[Constants.RESULT_LIST]}">
	<br> <br>
	<table border="2" width="100%">
		<tr class="heading" align="center">
			<td>Title of question</td>
			<td>Creation date</td>
			<td>Author</td>
			<td>Question link</td>
		</tr>
		<c:forEach items="${requestScope[Constants.RESULT_LIST]}" var="item">

			<c:if test="${item.isAnswered}">
				<c:set value="answered" var="backstyle"/>
			</c:if>
			<c:if test="${not item.isAnswered}">
				<c:set value="unanswered" var="backstyle"/>
			</c:if>

			<tr class="${backstyle}">
				<td><c:out value="${item.title}" /></td>
				<td><c:out value="${item.creationDate}" /></td>
				<td><c:out value="${item.author}" /></td>
				<td><a href = "<c:url value = "${item.originalLink}"/>">link</a>
			</tr>

		</c:forEach>
	</table>
</c:if>

<style>
	.answered {
		background-color: greenyellow;
	}
	.unanswered {
		background-color: white;
	}
	.heading {
		font-size: large;
		font-weight: bold;
	}
</style>

</body>
</html>
