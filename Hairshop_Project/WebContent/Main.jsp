<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

	<style>
		#button{border: 2px dashed; font-size:20px; font-weight:bold; height:70px; width:100px;} /* 메뉴버튼 */
		#table{font-size:18px; font-weight:bold;} /* 전화번호, 영업시간 등 테이블 */	
		#footer {margin: auto; font-size: 12px;}
		hr {margin: 30px;}
	</style>

<head>
<meta charset="UTF-8">
<title>메인</title>
<script>
function share(aa){
	var check = prompt("ctrl+c로 복사하세요.",aa);
	
}
</script>
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


	
	<img src="./img/hairsalon.png"><br><br>
	<a href="logincheck.do">
		<input type="button" value="예약" id="button"></a>&nbsp;&nbsp;&nbsp;
	
	
	<a href="Map.jsp">
		<input type="button" value="위치" id="button"></a>&nbsp;&nbsp;&nbsp;
	
	<%-- <a href="Share.jsp?url=${pageContext.request.requestURL}" onclick="window.open(this.href, '_blank', 'width=500px, height=200px, toolbars=no, scrollbars=no'); return false;"> --%>
		<input type="button" value="공유" id="button" onclick="share('${pageContext.request.requestURL}')"><!-- </a> -->&nbsp;&nbsp;&nbsp;
	
	<a href="reviewoutput.do">
		<input type="button" value="리뷰" id="button"></a>&nbsp;&nbsp;&nbsp;
	
	<a href="Style.jsp">
		<input type="button" value="스타일" id="button"></a>&nbsp;&nbsp;&nbsp;
	<br><br>


	<table border=0 align="center" id="table">
	<tr><td align="left">
	전화번호 : 02)1234-5678 <br><br>
	
	영업시간 : 평일 10:00 - 21:00<br>
	&emsp;&emsp;&emsp;&emsp;&emsp;토,일 10:00 - 21:00<br>
	&emsp;&emsp;&emsp;&emsp;&emsp;공휴일 휴무<br><br>
	
	이벤트 : 재방문 시 3,000원 할인 제공<br>
	&emsp;&emsp;&emsp;&emsp;<span style="color:red">리뷰 작성 시 클리닉 제공</span><br><br>
	
	가격 정보 : 남성 기본컷 29,000&emsp;&emsp;여성 기본컷 39,000<br>
	&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;앞머리컷 5,000&emsp;&emsp;&emsp;&emsp;앞머리펌 20,000<br>
	&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;남성 일반펌 60,000&emsp;&emsp;여성 일반펌 80,000<br>     
	&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;전체염색 80,000&emsp;&emsp;&emsp;&nbsp;기본 클리닉 50,000<br>      
	</td>
	</tr>
	</table>
	
	
<hr>
</body>
	<footer>
		<table border=0 id="footer">
			<tr>
				<td width=200>
					전화 문의<br>
					02-5959-8081
				</td>
				<td rowspan=2>
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
</html>