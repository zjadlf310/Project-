<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디/비밀번호 찾기</title>
<style type="text/css">
	hr {width: 800px}
	table {font-size: 20px; margin: auto;}
	#topbutton{font-size: 9px;}
	#searchtable{font-size: 20px;}
	#searchbutton{font-size: 15px; align:center; }

</style>
</head>
<script type="text/javascript">	
	//아이디 찾기 Null체크
	function idNullCheck(){
		
		if(!document.idCheck.S_name.value){
			alert("이름을 입력해주세요.");
			document.idCheck.S_name.focus();
			
		} else if(!document.idCheck.S_pnum.value) {
			alert("휴대전화 번호를 입력해주세요.");
			document.idCheck.S_pnum.focus();
			
		} 
	}
	//비밀번호 찾기 Null체크
	function pwNullCheck(){
		
		if(!document.pwCheck.S_id.value){
			alert("아이디를 입력해주세요.");
			document.pwCheck.S_id.focus();
			
		} else if(!document.pwCheck.S_name.value){
			alert("이름을 입력해주세요.");
			document.pwCheck.S_name.focus();
			
		} else if(!document.pwCheck.S_pnum.value){
			alert("휴대전화 번호를 입력해주세요.");
			document.pwCheck.S_pnum.focus();
			
		}
		
	}
</script>
<body align="center">	
<br><br>	
	<table border=0 align=center cellpadding=0 cellspacing=0>
<%if(session.getAttribute("session1")==null){%>	
	<tr><td>&nbsp;</td><td>&nbsp;</td><td width=200 align=right><a href=Login.jsp id=top><input type="button" value="로그인" id="topbutton"></a>&nbsp;&nbsp;<a href="Signup.jsp?id=null"><input type="button" value="회원가입" id="topbutton"></a><br></td>	
<%}
  else {%>
	<tr><td>&nbsp;</td><td>&nbsp;</td><td width=200><a href="Logout.do?re_di='${pageContext.request.requestURL}'" id=top><input type="button" value="로그아웃" id="topbutton1"></a><br></td>	
<%}%>
	
	<tr><td colspan=3>
	&nbsp;&nbsp;&nbsp;&nbsp;<a href="Main.jsp"><img alt="에러" src="./img/logo.png"></a><br><br>
	</td></tr>
	</table>
	
	<h3>아이디 찾기</h3>
	<hr>
	<form action="searchID.do" method="post" name="idCheck" onsubmit="return idNullCheck()">
		<table border="0" cellpadding="15">
			<tr>
				<td>	
					이름
				</td>
				<td>
					<input type="text" name="S_name" id=searchtable>
				</td>
			</tr>
			<tr>
				<td>
					휴대전화
				</td>
				<td>
					<input type="text" name="S_pnum" id=searchtable>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="확인" id=searchbutton>
				</td>
			</tr>
		</table>
	</form>
	<h3>비밀번호 찾기</h3>
	<hr>
	<form action="checkID.do" method="post" name=pwCheck onsubmit="return pwNullCheck()">
		<table border="0" cellpadding="15">
			<tr>
				<td>	
					아이디
				</td>
				<td>
					<input type="text" name="S_id" id=searchtable>
				</td>
			</tr>
			<tr>
				<td>
					이름
				</td>
				<td>
					<input type="text" name="S_name" id=searchtable>
				</td>
			</tr>
			<tr>
				<td>
					휴대전화
				</td>
				<td>
					<input type="text" name="S_pnum" id=searchtable>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="확인" id=searchbutton>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>