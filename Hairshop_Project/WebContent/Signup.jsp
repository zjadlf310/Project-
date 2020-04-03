<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style>
	.submit{font-size:14px;}
	.title{font-weight:bold;}
	.error{color:red;}
	.a{font:30px; height:30px; width:300px;}
	#a{font:30px; height:30px; width:300px;}
	#checkbtn{height: 35px; width:70px}
</style>
</head>
<script language="JavaScript">
	//회원가입 성공시 안내 메세지
	
	function signUpNullCheck(){		
		//이름 Null체크
		if(!document.Signup.join_name.value){
			alert("이름을 입력해주세요.");
			document.Signup.join_name.focus();
			return false;
		}
		
		//아이디 Null체크
		else if(!document.Signup.join_id.value){
			alert("아이디를 입력해주세요.");
			document.Signup.join_id.focus();
			return false;
		}
		
		//비밀번호 Null체크
		else if(!document.Signup.join_pw.value){
			alert("비밀번호를 입력해주세요.\n특수문자를 포함한 6-20자 영문 대소문자로 설정할 수 있습니다.");
			document.Signup.join_pw.focus();
			return false;
		}
		
		//전화번호 Null체크
		else if(!document.Signup.join_tel.value){
			alert("전화번호를 입력해주세요.");
			document.Signup.join_tel.focus();
			return false;
		}
		
		//메일 Null체크
		else if(!document.Signup.join_mail.value){
			alert("메일을 입력해주세요.");
			document.Signup.join_mail.focus();
			return false;
		
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

	<form action="newID.do" name="Signup" onsubmit="return signUpNullCheck()">
		
		<table border="0" style="margin-left:auto; margin-right:auto;">
			<tr><td align="left" class="title">이름</td>
			<tr><td align="left"><input type="text" name="join_name" placeholder="한글 이름을 입력해주세요" 
			id="a" autofocus></td>
			<tr><td>&nbsp;</td>
			
			<tr><td align="left" class="title">아이디</td>
			<tr><td align="left"><input type="text" name="join_id" id="jid" class="a"  readonly="readonly"></td>
			<td>
			
			<input type="button" value="중복 확인" id="checkbtn" name="check" onclick="window.open('IdCheck.jsp', '_blank', 'width=500px, height=300px')"></td>
			<tr><td>&nbsp;</td>
			
			<tr><td align="left" class="title">비밀번호</td>
			<tr><td align="left"><input type="password" name="join_pw" 
			placeholder="특수문자와 숫자를 포함한 6-20자 영문 대소문자" pattern="(?=.*\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-z])(?=.*[A-Z]).{6,20}" title="특수문자와 숫자를 포함한 6-20자 영문 대소문자로 설정하세요" id=a></td>
			<tr><td>&nbsp;</td>
	
			<tr><td align="left" class="title">전화번호</td>
			<tr><td align="left"><input type="text" name="join_tel" pattern="[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}" 
			placeholder="XXX-XXXX-XXXX" id="a"></td>
			<tr><td>&nbsp;</td>
			
			<tr><td align="left" class="title">메일</td>
			<tr><td align="left"><input type="email" name="join_mail" 
			placeholder="example@email.com" id="a"></td>
			<tr><td>&nbsp;</td></tr>
			</table>

		<input type="submit" value="회원가입하기" style="height:50px; width:100px;" class="submit" onclick="Signup_ok()">
	</form>
</body>
</html>