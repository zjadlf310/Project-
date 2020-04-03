<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%  response.setStatus(HttpServletResponse.SC_OK); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오류발생</title>
</head>
<body>
	<center><h1>에러가 발생했습니다.</h1></center>
	<c:if test="${requestScope['javax.servlet.error.status_code'] == 400}">
        <center><p>400 : 클라이언트의 잘못된 요청으로 인해 처리할 수 없습니다.</p></center>
    </c:if>
    <c:if test="${requestScope['javax.servlet.error.status_code'] == 403}">
        <center><p>403 : 접근이 거부된 문서를 요청했습니다.</p></center>
    </c:if>
    <c:if test="${requestScope['javax.servlet.error.status_code'] == 404}">
        <center><p>404 : 문서를 찾을 수 없습니다.</p></center>
    </c:if>
    <c:if test="${requestScope['javax.servlet.error.status_code'] == 405}">
        <center><p>405 : 리소스를 허용할 수 없습니다.</p></center>
    </c:if>
    <c:if test="${requestScope['javax.servlet.error.status_code'] == 500}">
        <center><p>500 : 내부 서버에서 오류가 발생했습니다.</p></center>
    </c:if>
    <c:if test="${requestScope['javax.servlet.error.status_code'] == 503}">
        <center><p>503 : 외부 서비스가 끊어졌거나 현재 멈춘 상태 입니다.</p></center>
    </c:if>
</body>
</html>