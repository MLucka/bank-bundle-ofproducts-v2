<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html lang="en">
<table>
	<tr><td><a href="<%=request.getContextPath() %>/welcome">Beginning</a></td>
	<td>|</td>
	<td><a href="<%=request.getContextPath() %>/list-products">List of products</a></td>
	<td>|</td>
<td><a href="<%=request.getContextPath() %>/list-bundles">List of bundles of products</a></td>
<td>|</td>
<td><a href="<%=request.getContextPath() %>/choose-a-bundle">Choose a bundle</a></td>
</tr></table>
<h3>Choose a bundle</h3>

<c:if test="${answer.answered==true}"><p style="color:red">${answer.errorMessage}</p></c:if>
<c:if test="${answer.isAllAnswersValid==false || answer.answered==false}"> 
<form action="<%=request.getContextPath() %>/answers-to-questions" method="POST">
<table>
<tr><td>What is your age?</td><td><input name="age" type="text" 
       value="${not empty answer.age ? answer.age : ''}" size="5"></td></tr>
<tr><td>Is you a student?</td>
<td><select name='isStudent'>
    <option value="no" selected>no</option>
    <option value="yes" ${not empty answer.isStudentAsBoolean && answer.isStudentAsBoolean ? 'selected' : ''}>yes</option>
 </select></td></tr>
<tr><td>What is your income?</td><td><input name="income" type="text" 
       value="${not empty answer.income ? answer.income : ''}" size="5"></td></tr>
   <tr><td colspan="2"><input name="answered" type="hidden" value="true" /></td></tr>
 <tr><td colspan="2"><input type="submit" value="Submit" /></td></tr>      
</table>
</form>

</c:if>
<c:if test="${answer.isAllAnswersValid==true && answer.answered==true}"> 
	<p>Answers:</p> 
	<ul>
		<li><b>age</b> ${answer.age}</li>
		<li><b>student</b> ${answer.isStudent}</li>
		<li><b>income</b> ${answer.income}</li>
	</ul>
	
	<h5>Recommended bundles</h5>
	<table border="1">
		<tr><th></th><th>bundle</th><th>products included</th><th>rules</th><th>value</th></tr>
		<c:forEach items="${choosenBundles}" var="bundle">
		    <tr>
		    	<td><a href="<%=request.getContextPath() %>/select-a-bundle/${bundle.id}">Select the bundle</a></td>
		    	<td>${bundle.name}</td>
		    	<td>
		    		<c:forEach items="${bundle.products}" var="product">
		        		<a href="<%=request.getContextPath() %>/remove-product-from-bundle/${bundle.id}/${product.id}">remove</a> ${product.name}<br>
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
	
	<c:if test="${not empty bundleId}">
		<h5>Add a product to the bundle</h5>
		<table border="1">
		<tr><th></th><th>name</th><th>rules</th></tr>
			<c:forEach items="${availableProducts}" var="product">
			    <tr>
			        <td><a href="<%=request.getContextPath() %>/add-product-to-bundle/${bundleId}/${product.id}">
			        	Add product to the bundle</a></td>
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
			</c:forEach>
		</table>
	</c:if>
</c:if>


</html>