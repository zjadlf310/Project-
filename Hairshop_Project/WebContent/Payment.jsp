<%@page import="com.hairshop.vo.HairVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	HairVO vo = (HairVO)session.getAttribute("session1");
	String mail = vo.getMail();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제</title>
<style>
		#card_num {	width: 40px;}
		#card_date { width: 20px;}
		#card_cvs {	width: 30px;}
		#footer {margin:auto; font-size: 12px;}
		hr {margin: 30px;}
		#submit {margin:auto; height:50px; width:100px; font-size: 20px;}
	</style>
<script type="text/javascript">
	function show() {
		alert("결제를 완료했습니다.\n확인을 클릭할시 메인화면으로 넘어갑니다.");
	} 
/*  	function paymentNullCheck(){
		if(!document.payment.P_num1.value){
			alert("결제정보를 입력해주세요");
			document.payment.P_num1.focus();
			return false;
		} */

</script>
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
	</table><br><br>
	<form name="payment" action="Payment.do" method="post" id="form" ><!-- onsubmit="return paymentNullCheck()" -->
	<table border="0" align="center" width="900">
			<tr>
				<td rowspan="6" width="400">
					<b>결제 방식 선택</b>
					<input type="radio" name="pay" value="shop" id="paycash" OnClick="cash.style.display='block'; card.style.display='none'; account.style.display='none'; submit.style.display='block'">현장 결제
					<input type="radio" name="pay" value="card" id="paycard" Onclick="card.style.display='block'; cash.style.display='none'; account.style.display='none'; submit.style.display='block'">카드 결제
					<input type="radio" name="pay" value="cash" id="payaccount" Onclick="account.style.display='block'; cash.style.display='none'; card.style.display='none'; submit.style.display='block'">계좌 이체
					<br><br><br>
				</td>
			</tr>
			<tr>
				<td>
				<br>
				</td>
			</tr>
			<tr>
				<td>
					<div id="cash" style="display:none" > <!-- style="display:none" --> 
					<br><br><b>
					총 결제 금액은 <input type="text" name="P_total" readonly="readonly" value="${requestScope.price}"> 입니다.<br>
					예약 정보가 <input type="text" name="P_mail" readonly="readonly" value="<%=mail%>"> 로 발송됩니다.</b>
					</div>
					<br><br>
				</td>
			</tr>
			<tr>
				<td>
					<div name="card" id="card" style="display:none"><b>
					카드 정보 입력란<br>
					<input id="card_num" type="text" name="P_num1" maxlength="4">-
					<input id="card_num" type="text" name="P_num2" maxlength="4">-
					<input id="card_num" type="text" name="P_num3" maxlength="4">-
					<input id="card_num" type="text" name="P_num4" maxlength="4"><br>
					<input id="card_date" type="text" name="P_ydate" maxlength="2">(YY)/
					<input id="card_date" type="text" name="P_mdate" maxlength="2">(MM)    
					<input id="card_cvs" type="text" name="P_ydate" maxlength="3">(3자리)</b>
					</div>
					<br><br>
				</td>
			</tr>
			<tr>
				<td>
					<div id="account" style="display:none"><b>
					입금 계좌 번호<br>
					391-910***-***** 하나은행 (주)가즈아헤어샵</b>
					</div>
					<br><br>
				</td>
			</tr>
			</table>
			<br><br><br><br><br>
			<input type="hidden" name="S_HAIR" value="${requestScope.S_HAIR}">
			<input type="hidden" name="S_STYLE" value="${requestScope.S_STYLE}">
			<input type="hidden" name="price" value="${requestScope.price}">
			<input type="hidden" name="S_DATE" value="${requestScope.S_DATE}">
			<input type="hidden" name="S_TIME" value="${requestScope.S_TIME}">
			<input type="hidden" name="photo" value="${requestScope.photo}">
			<input type="hidden" name="W_text" value="${requestScope.W_text}">
			<input type="hidden" name="W_tel" value="${requestScope.W_tel}">
			
			<input type="submit" id=submit value="결제" style="display:none;" onclick="show();">
			
			
	</form>

	<footer>
			<br><br>
				<hr>
			<br><br>
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