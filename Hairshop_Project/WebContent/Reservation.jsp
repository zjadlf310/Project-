<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>시술 선생님 선택</title>
<style>
	#footer {margin: auto; font-size: 12px;}
	hr {margin: 30px;}
</style>
</head>
<body align="center">
<br><br>
	
	<table border=0 align=center>
<%if(session.getAttribute("session1")==null){%>	
	<tr><td>&nbsp;</td><td>&nbsp;</td><td width=200><a href=Login.jsp id=top><input type="button" value="로그인" id="topbutton"></a>&nbsp;&nbsp;<a href="Signup.jsp?id=null"><input type="button" value="회원가입" id="topbutton"></a><br></td>	
<%}
  else {%>
	<tr><td>&nbsp;</td><td>&nbsp;</td><td width=200 align=right><a href="Logout.do?re_di='${pageContext.request.requestURL}'" id=top><input type="button" value="로그아웃" id="topbutton1"></a><br></td>	
<%}%>
	
	<tr><td colspan=3>
	&nbsp;&nbsp;&nbsp;&nbsp;<a href="Main.jsp"><img alt="에러" src="./img/logo.png"></a><br><br>
	</td></tr>
	</table>

		<table border=0 align=center>
			<tr>
			<td width=220 height=230><a href="Reservation.do?S_HAIR=Cat"><img src="img/cat.png"><br>고양이 선생님</a></td>
			<!-- 넘기는 값 hidden 으로 만들어줄까 말까? -->
			<td width=220 height=230><a href="Reservation.do?S_HAIR=Dog"><img src="img/dog.png"><br>강아지 선생님</a></td>
			<td width=220 height=230><a href="Reservation.do?S_HAIR=Eagle"><img src="img/eagle.png"><br>독수리 선생님</a></td>
			</tr>
			
			<tr>
			<td width=220 height=230><a href="Reservation.do?S_HAIR=Elephant"><img src="img/elephant.png"><br>코끼리 선생님</a></td>
			<td width=220 height=230><a href="Reservation.do?S_HAIR=Frog"><img src="img/frog.png"><br>개구리 선생님</a></td>
			<td width=220 height=230><a href="Reservation.do?S_HAIR=Giraffe"><img src="img/giraffe.png"><br>기린 선생님</a></td>
			</tr>
							
			<tr>
			<td width=220 height=230><a href="Reservation.do?S_HAIR=Hippo"><img src="img/hippo.png"><br>하마 선생님</a></td>
			<td width=220 height=230><a href="Reservation.do?S_HAIR=Sheep"><img src="img/sheep.png"><br>양 선생님</a></td>
			<td width=220 height=230><a href="Reservation.do?S_HAIR=Tiger"><img src="img/tiger.png"><br>호랑이 선생님</a></td>
			</tr>
		</table>
	<hr>
	<footer>
		<table border=0 id=footer>
			<tr>
				<td width=200px>
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