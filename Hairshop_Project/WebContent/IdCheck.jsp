<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 설정</title>
<style type="text/css">
	#a{margin-top: 50px;}
</style>
<script type="text/javascript">
	
	function cc(){
		if(!document.IdCheck.idcheck.value){
			alert("아이디를 입력해주세요.")
			document.IdCheck.idcheck.focus();
			
		}
		opener.document.Signup.join_id.value=document.IdCheck.idcheck.value;
	}
	
</script>
</head>
<body align="center">
	<form action="doubleIDcheck.do" id="a" name="IdCheck" onsubmit="cc()">
		사용하실 아이디를 입력해주세요.<br><br><br>
		사용할 아이디 :<br>
		<input type="text" name="idcheck"><br><br>
		<input type="submit" value="확인"  >
	</form>
</body>
</html>