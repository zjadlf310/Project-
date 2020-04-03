<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<style type="text/css">
		#button{border: 2px dashed; font-size:20px; font-weight:bold; height:60px; width:95px;}
		textarea{ resize: none; font-size:20px; width:700px; height:250px; }
		#result{ width:700px; text-align:left;}
		#ta {font-size: 20px; margin:auto; text-align: left; margin-bottom: 30px;}
		#submit{margin:center; font:50px; font-weight:bold; width:100px; height:50px;}
		#footer {margin: auto; font-size: 12px;} 
		hr {margin: 30px;}		
	</style>

<meta charset="UTF-8">
<title>리뷰</title>
<script>
function share(aa){
	var check = prompt("ctrl+c로 복사하세요.",aa);
	
}
</script>
</head>
<body align="center">
<br><br>
	<table align="center" border="0">
	<tr><td>&nbsp;</td>
	<td colspan=3>
	<table border=0 align=center>
<%if(session.getAttribute("session1")==null){%>	
	<tr><td>&nbsp;</td><td>&nbsp;</td><td width=200><a href=Login.jsp id=top><input type="button" value="로그인" id="topbutton"></a>&nbsp;&nbsp;<a href="Signup.jsp?id=null"><input type="button" value="회원가입" id="topbutton"></a><br></td>	
<%}
  else {%>
	<tr><td>&nbsp;</td><td>&nbsp;</td><td width=200><a href="Logout.do?re_di='${pageContext.request.requestURL}'" id=top><input type="button" value="로그아웃" id="topbutton1"></a><br></td>	
<%}%>
	</table>
	</td>
	<tr>
	<td>
	<a href="Main.jsp"><img alt="에러" src="./img/logo.png" id=a></a>
	</td>
	
	<td>
	&nbsp;&nbsp;&nbsp;
	<a href="Reservation.jsp">
		<input type="button" value="예약" id="button"></a>&nbsp;&nbsp;&nbsp;
	</td>
	
	<td>
	<a href="Map.jsp">
		<input type="button" value="위치" id="button"></a>&nbsp;&nbsp;&nbsp;
	</td>
	
	<td>
	
		<input type="button" value="공유" id="button" onclick="share('${pageContext.request.requestURL}')">
	
	</td>
	</tr>
	</table>
	
	
	
	
	
	
	
	<hr>
	<h1 align="center">리뷰보기</h1>
	<form action="check.review" method="post" >
		<textarea name="r_text" maxlength="500byte"></textarea>
		<br>
		<input id="submit" type="submit" value="리뷰 남기기">
	</form>
	<br><br>
	<table border="0" id="ta" width="700">
		<c:forEach var = "vo" items="${requestScope.reviewoutput}">
		<tr>
			<td>
				${vo.review_text }
			</td>
		</tr>
		<tr>
			<td>
				${vo.review_hair } - ${vo.review_style }
			</td>
		</tr>
		<tr>
			<td>
				${vo.review_id }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${vo.review_date }
				<hr><br>
			</td>
		</tr>
		</c:forEach>
		
	</table>
	
	
		<hr>
	<footer>
		<table border="0"  id="footer">
			<tr>
				<td width=200>
					전화 문의<br>
					02-5959-8081
				</td>
				<td rowspan="2">
					법인명(상호) : (주)가즈아 헤어샵<br> 
					사업자등록번호 : 5959-8081-255<br>
					주소 : 서울특별시 종로구 돈화문로 26, 5층(묘동, 단성사)<br> 
					대표 : 김윤겸
				</td>
			</tr>
			<tr>
				<td>
					영업시간<br>
					평일 10:00 - 21:00<br>
					토, 일 10:00 - 21:00<br>
					공휴일 휴무
				</td>
			</tr>
		</table>
	</footer>
</body>
</html>