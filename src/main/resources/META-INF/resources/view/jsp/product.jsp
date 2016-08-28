<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<table>
	<tr><td><a href="<%=request.getContextPath() %>/list-products">List of products</a></td>
	<td>|</td>
<td><a href="<%=request.getContextPath() %>/list-bundles">List of bundles of products</a></td>
</tr></table>
	<h3>List of products</h3>
	<table border="1">
		<tr><th>id</th><th>name</th><th>rules</th></tr>
	    <tr>      
	        <td>${product.id}</td>
	        <td>${product.name}</td> 
	        <td>
		        <c:forEach items="${product.rules}" var="rule">
		        	${rule.type.type} :
		        	<c:if test="${not empty rule.min}">
					    <c:if test="${rule.type!='STUDENT'}">from </c:if>${rule.min}
					</c:if>
					<c:if test="${not empty rule.max}">
					    <c:if test="${rule.type!='STUDENT'}">to </c:if>${rule.max}
					</c:if>
		        	 <br>
		        </c:forEach>
	        </td>
	    </tr>
	</table>
	<p>JSON: ${jsonOfProduct}</p>
</html>