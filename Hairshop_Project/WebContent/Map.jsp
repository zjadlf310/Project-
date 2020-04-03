<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>찾아오시는 길</title>

<style type="text/css">
	#footer {margin: auto; font-size: 12px;}
	#jido{margin: auto; width: 700px; height: 400px}
	hr {margin: 30px;}
</style>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=fe068da6a475011a976516a31b7df662">
</script>
<script>
	window.onload = function(){
		var joongang = new daum.maps.LatLng(37.570999, 126.992528);
		var mapDesign = {zoom:15, center:joongang, mapTypeId:daum.maps.MapTypeId.ROADMAP}
		var map = new daum.maps.Map(document.getElementById("jido"), mapDesign);
		
		var markerPosition = new kakao.maps.LatLng(37.570999, 126.992528);
		
		var marker = new kakao.maps.Marker({
			position: markerPosition
		});
		
		marker.setMap(map);
	}
</script>
</head>
</head>
<body align=center>
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




	<h1>헤어샵 위치</h1>
		<div id="jido"></div>
	<table border=0 width=250 cellspacing=0 cellpadding=0 align=center>
	<tr><td>
	<h2>찾아오는 법</h2>
	</td></tr>
	<tr><td align=left>
	<h4>● 주변 지하철역 : 종로 3가역</h4>
	</td></tr>
	<tr><td align=left>
	<h4>● 주변 버스 정류장 :<br>
	   &nbsp;&nbsp;&nbsp;&nbsp;종로 3가 1, 3, 4호선(01-550)<br>
	   &nbsp;&nbsp;&nbsp;&nbsp;종로 3가 (01-767,01-768)<br>
	   &nbsp;&nbsp;&nbsp;&nbsp;종묘, 세운상가 (01-914)
	</h4>
	</td></tr>
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