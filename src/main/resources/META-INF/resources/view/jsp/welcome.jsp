<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">

<body>
	<table><tr><td><a href="<%=request.getContextPath() %>/list-products">List of products</a></td>
	</tr><tr>
<td><a href="<%=request.getContextPath() %>/list-bundles">List of bundles of products</a></td>
	</tr><tr>
<td><a href="<%=request.getContextPath() %>/choose-a-bundle">Choose a bundle</a></td>
	</tr></table>
</body>

</html>