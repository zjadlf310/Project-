<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<% response.setStatus(200); %>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
	hr {margin: 30px;}
	footer {font-size: 12px; margin-top: 30px; margin-bottom: 30px; position: absolute; bottom: 0; width: 100%}
	#e {width: 200px;}
	#a {margin-left: 300px;}
</style>
<meta charset="UTF-8">
<title>오류</title>
</head>
<body>
	<pre>
		다음과 같은 에러가 발생했습니다.
		계속 에러가 발생되면 상담원에게 전화주시기 바랍니다.
		전화번호는 02-5959-8081입니다.
	</pre>
	<hr>
	에러내용은<h3> <%=request.getAttribute("error") %> </h3><hr>
	
</body>
	<footer>
	<hr>
		<table border="0" id=a>
			<tr>
				<td id=e>
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
</html>