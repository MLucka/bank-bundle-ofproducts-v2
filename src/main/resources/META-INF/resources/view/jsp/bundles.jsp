<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
	<table><tr><td><a href="<%=request.getContextPath() %>/welcome">Beginning</a></td>
	<td>|</td>
	<td><a href="<%=request.getContextPath() %>/list-products">List of products</a></td>
	<td>|</td>
<td><a href="<%=request.getContextPath() %>/choose-a-bundle">Choose a bundle</a></td>
	</tr></table>
	<h3>List of bundles of products</h3>
	<table border="1">
		<tr><th>id</th><th>bundle</th><th>products included</th><th>rules</th><th>value</th></tr>
		<c:forEach items="${listOfBundles}" var="bundle">
		    <tr>
		    	<td>${bundle.id}</td>
		    	<td><a href="<%=request.getContextPath() %>/bundle/${bundle.id}">${bundle.name}</a></td>
		    	<td>
		    		<c:forEach items="${bundle.products}" var="product">
		        		${product.name}<br>
		    		</c:forEach>
		    	</td>
		    	<td>
			        <c:forEach items="${bundle.rules}" var="rule">
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
		        <td>${bundle.priority}</td>
		    </tr>
		</c:forEach>	    
	</table>
	<p>JSON: ${jsonOfBundles}</p>
</html>