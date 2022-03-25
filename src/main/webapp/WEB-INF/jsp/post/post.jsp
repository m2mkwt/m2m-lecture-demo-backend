<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:forEach items="${postList}" var="post">
	<table border=1>
		<tr>
           <td><c:out value="${post.idx}"/></td>
           <td><c:out value="${post.text}"/></td>
        </tr>
	</table>
     </c:forEach>
     <button><a href="/post/uploadJsp">업로드</a></button>
</body>
</html>