<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경</title>
<style>
	#a{width:300px}
	#high{margin-top: 15%}
</style>
<script type="text/javascript">
	function pwNull(){
		if(!document.pwcheck.pw.value){
			alert("새로 설정할 비밀번호를 입력해주세요.");
		} 
	}
</script>
</head>
<body align="center" id=high>
	<form action="updatePW.do" method="post" name="pwcheck" onsubmit="pwNull()">
		새로 이용할 비밀번호를 입력해주세요.<br>
		다음 로그인시 새로 설정한 비밀번호로 로그인할 수 있습니다.<br><br><br>
		새로 이용할 비밀번호 :<br>
		<input id=a type="password" name="pw" placeholder="특수문자를 포함한 6-20자 영문 대소문자" pattern="(?=.*\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-z])(?=.*[A-Z]).{6,20}"
				title="특수문자를 포함한 6-20자 영문 대소문자로 설정하세요"><br><br>
		<!-- 만약 위의 조건이 맞을 시 아래의 버튼 수행하고 아닐시,window.close() 취소 여기 아직 안했뜸 -->
		
		<input type="submit" value="확인">
	</form>
</body>
</html>