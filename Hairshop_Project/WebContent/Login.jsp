<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
	#button {font-size:17px; font-weight:bold; height:50px; width:100px; }
	#center {font:30px; height:30px; width:200px; }
	#a {color: red;}
	
</style>
<meta charset="UTF-8" >
<title>로그인</title>
</head>
<script language="JavaScript">
 	function loginNullCheck(){
 		//아이디 Null체크
		if(!document.Login.id.value){
			alert("아이디를 입력해주세요.");
			document.Login.id.focus();
			return false;
		}
		
 		//비밀번호 Null체크
		if(!document.Login.pw.value){
			alert("비밀번호를 입력해주세요.");
			document.Login.pw.focus();
			return false;
		}
	} 
</script>
<body align="center">	
<br><br>
	
	<table border=0  height=20 align=center>

	<tr>
		<td>&nbsp;</td> 
		<td>&nbsp;</td>
		<td width=200 align=right><input type="button" value="스타일북" onclick="location='./Style.jsp'"></a></td>	
	</tr>
	<tr>
	<td colspan=3>
	&nbsp;&nbsp;&nbsp;&nbsp;<a href="Main.jsp"><img alt="에러" src="./img/logo.png"></a><br><br>
	</td></tr>
	</table>
<img src="./img/haircut.png"><br><br><br>
<form action="login.do" name="Login" method="post" onsubmit="return loginNullCheck()">
	<h3 align="center">아이디&emsp;<input type="text" name="id" id="center" onfocus="this.value=''; return true;"></h3>
	<h3 align="center">비밀번호&nbsp;<input type="password" name="pw" id="center" onfocus="this.value=''; return true;"></h3><br>

<!-- <a href="Main.jsp">	 -->		
	<input type="submit" value="로그인" id="button"><!-- </a> -->&emsp;&emsp;&emsp;

<a href="Signup.jsp?id=null">
	<input type="button" value="회원가입" id="button"><br>
</a>
	<br><br><br>
	<a id=a href="Search.jsp">아이디/비밀번호를 잊어버리셨나요?</a>
</form>
</body>
</html>