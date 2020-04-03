<%@page import="com.hairshop.vo.HairVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
  HairVO login=(HairVO) session.getAttribute("session1");
  String name=login.getName();
  String mail=login.getMail();
  String tel=login.getTel();
  
  String photo=(String)request.getAttribute("photo");
  String S_HAIR=(String)request.getAttribute("S_HAIR");
  String S_STYLE=(String)request.getAttribute("S_STYLE");
  String price=(String)request.getAttribute("price");
  String S_DATE=(String)request.getAttribute("S_DATE");
  String S_TIME=(String)request.getAttribute("S_TIME");
  
  %>  
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
	textarea {resize: none;}
	#submit {margin-top: 50px; font:40px; height:50px; width:100px; margin-bottom: 25px;}
	hr {margin: 30px;}
	#b {padding-left: 50px; font:40px; width:100px; font-size:15px; font-weight:bold;}
	#c {width: 380px}
	#d {width: 100%}
	#font {font:40px; font-size:15px; font-weight:bold;}
	#footer {margin: auto; font-size: 12px;}
	
</style>
<meta charset="UTF-8">
<title>예약확인</title>
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
	<h1 align="center">예약 확인</h1>
	<h3 align="center">예약하실 시술과 정보를 확인해주세요.</h3>
	<form action="reservation3.do" method="post">
		<table border="0" align="center">
			<tr>
				<td><h2>예약자 정보</h2>
				</td>
				
				<td id="b">
					<h2 align="left">선택 내역&nbsp;&nbsp;&nbsp;
					<input type="text" readonly="readonly" id="c" value="${requestScope.S_STYLE}+${requestScope.price}"></h2>
				</td>
			</tr>

			<tr>
				<td id="font">
					예약자 <input type="text" name="W_name" value="<%=name %>" readonly="readonly">
				</td>
				<td id="b" rowspan="4">
					판매자 정보<br>
					<textarea cols="70" rows="5" readonly="readonly" >
상호		가즈아 헤어샵
대표자명	김윤겸
소재지		서울특별시 종로구 돈화문로 26, 5층(묘동, 단성사)
사업자번호	5959-8081-255
연락처		02-5959-8081
					</textarea>
				</td>	
			</tr>
			<tr>
				<td id="font">
					연락처 <input type="text" name="W_tel" value="<%=tel %>" readonly="readonly">
				</td>
			</tr>
			<tr >
				<td id="font">
					이메일 <input type="text" name="W_mail" value="<%=mail %>" readonly="readonly">
				</td>
			</tr>
			<tr>
				<td id="font">
					요청 사항 <input type="text" name="W_text" id="d" placeholder="요청하실 내용을 적어주세요">
				</td>
			</tr>
		</table>
		
			<input id="submit" type="submit" value="결제">
		
				<input type="hidden" name="S_HAIR" value="${requestScope.S_HAIR}" >
				<input type="hidden" name="S_STYLE" value="${requestScope.S_STYLE}" >
				<input type="hidden" name="price" value="${requestScope.price}" >
				<input type="hidden" name="S_DATE" value="${requestScope.S_DATE}" >
				<input type="hidden" name="S_TIME" value="${requestScope.S_TIME}" >
				<input type="hidden" name="photo" value="${requestScope.photo}" >
	</form>
		<hr>
	<footer>
		<table border="0" id="footer">
			<tr>
				<td width=200px>
					전화 문의<br>
					02-5959-8081
				</td>
				<td rowspan="2">
					법인명(상호) : (주)가즈아 헤어샵<br> 
					사업자등록번호 : 5959-8081-255<br>
					주소 : 서울특별시 종
					로구 돈화문로 26, 5층(묘동, 단성사)<br> 
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