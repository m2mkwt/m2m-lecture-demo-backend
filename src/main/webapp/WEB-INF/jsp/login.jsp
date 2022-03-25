<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 화면 페이지</title>
</head>
<body>
	로그인 화면 페이지
	<form action="${path}/upload?${_csrf.parameterName}=${_csrf.token}" method="post" enctype="multipart/form-data">
		<input type="file" name="file">
		<button>업로드</button>
	</form>
</body>
</html>