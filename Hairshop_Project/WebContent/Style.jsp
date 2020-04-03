<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
	
	#footer {margin: auto; font-size: 12px;}
	hr {margin: 30px;}
	h3 {margin-bottom: 50px;}
	
</style>
<title>스타일 제안</title>
</head>
</head>
<body align="center">	
<br><br>
	
	<table border=0 align=center>
<%if(session.getAttribute("session1")==null){%>	
	<tr><td>&nbsp;</td><td>&nbsp;</td><td width=200 align=right><a href=Login.jsp id=top><input type="button" value="로그인" id="topbutton"></a>&nbsp;&nbsp;<a href="Signup.jsp?id=null"><input type="button" value="회원가입" id="topbutton"></a><br></td>	
<%}
  else {%>
	<tr><td>&nbsp;</td><td>&nbsp;</td><td width=200 align=right><a href="Logout.do?re_di='${pageContext.request.requestURL}'" id=top><input type="button" value="로그아웃" id="topbutton1"></a><br></td>	
<%}%>
	
	<tr><td colspan=3>
	&nbsp;&nbsp;&nbsp;&nbsp;<a href="Main.jsp"><img alt="에러" src="./img/logo.png"></a><br><br>
	</td></tr>
	</table>
	
	<h3 align="center">스타일 제안</h3>
	<table border="0" align="center" cellspacing="3" cellpadding="30">
		<tr>
			<td>
				<img alt="에러" src="./img/cutman2.PNG"><br>
				<h3 align="center">남성 기본컷</h3>
				<img alt="에러" src="./img/manperm.jpg"><br>
				<h3 align="center">남성 일반펌</h3>
			</td>
			<td>
				<img alt="에러" src="./img/womancut.jpg" ><br>
				<h3 align="center">여성 기본컷</h3>
			</td>
		</tr>
		<tr>
			<td>
				<img alt="에러" src="./img/mancut.jpg"><br>
				<h3 align="center">남성 스타일링</h3>
								<img alt="에러" src="./img/manshortcut.jpg"><br>
				<h3 align="center">남성 짧은 기장 컷</h3>
			</td>
			<td>
				<img alt="에러" src="./img/womanperm.jpg"><br>
				<h3 align="center">여성 일반펌</h3>
			</td>
		</tr>
		<tr>
			<td>
				<img alt="에러" src="./img/womanstyle.jpg"><br>
				<h3 align="center">여성 스타일링</h3>
				<img alt="에러" src="./img/manstyle.jpg"><br>
				<h3 align="center">남성 스타일링2</h3>
			</td>		
			<td>
				<img alt="에러" src="./img/womanstraight.jpg"><br>
				<h3 align="center">여성 매직</h3>
			</td>
		</tr>
	</table>
	<hr>
	
	
	<footer>
		<table border="0" id="footer">
			<tr>
				<td width="200">
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