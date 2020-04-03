<%@ page info="diary calendar" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.sql.*,java.io.*,java.net.*,java.util.*" %>


<%	
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	
	String S_HAIR=request.getParameter("S_HAIR");
	String strYear = request.getParameter("year");
	String strMonth = request.getParameter("month");
	String query = new String();
	String url = new String();
	String selected = new String();
	String image1=request.getParameter("image1");
	String tea1=request.getParameter("tea1");
	String teatext1=request.getParameter("teatext1");
	String teatext11=request.getParameter("teatext11");
	Integer sw = (Integer)request.getAttribute("sw");
	String [] str=(String [])request.getAttribute("str");
	
	//달력객체 생성
	Calendar myCalendar = Calendar.getInstance();
	
	int year = 0;
	int month = 0;
	int previous_year = 0;
	int previous_month = 0;
	int next_year = 0;
	int next_month = 0;
	int day = 0;
	
	int t_year = 0;
	int t_month = 0;
	int t_day = 0;
	
	
		
	t_year = myCalendar.get(myCalendar.YEAR);
	t_month = myCalendar.get(myCalendar.MONTH)+1;
	t_day = myCalendar.get(myCalendar.DAY_OF_MONTH);

	
	if (strYear == null) {
		year = myCalendar.get(myCalendar.YEAR);
	} else {
		year = Integer.parseInt(strYear);
	}
	
	if(strMonth == null) {
		month = myCalendar.get(myCalendar.MONTH);
	} else {
		month = Integer.parseInt(strMonth) -1;
	}
	
	myCalendar.set(myCalendar.YEAR, year);
	myCalendar.set(myCalendar.MONTH, month);
	month = month + 1; //0부터 시작하는구나
	
	previous_year = myCalendar.get(myCalendar.YEAR) - 1;
	previous_month = myCalendar.get(myCalendar.MONTH);
	
	if (previous_month < 1) {
		previous_month = 12;
	} else if(previous_month > 12) {
		previous_month = 1;
	}
	
	next_year = myCalendar.get(myCalendar.YEAR) + 1;
	next_month = myCalendar.get(myCalendar.MONTH) + 2;
	
	if (next_month < 1) {
		next_month = 12;
	} else if (next_month > 12) {
		next_month = 1;
	}
	
	%>

<%--HTML 코드 시작--%><%-- <jsp:useBean id = "dbconn" class="MyBeans.DBConnect" scope="request" />--%>
<html>
<head>
<meta charset="UTF-8">
<title>예약</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-2.1.0.min.js"></script>
<script type="text/javascript">
$(function() {
    $("#file1").on('change', function(){
        readURL(this);
    });
});

function readURL(input) {
    if (input.files && input.files[0]) {
    var reader = new FileReader();

    reader.onload = function (e) {
            $('#image1').attr('src', e.target.result);
        }

      reader.readAsDataURL(input.files[0]);
    }
}

function changeSelect(){
	result.style.display='block'
}

function p_price(){//시술 선택될 때 발생 이벤트
	
	 var st1 =document.getElementById("style1");//콤보박스 선택된 값
	 var rt1=document.getElementById("#1");//결과출력 hair id
	 var rt2=document.getElementById("#2");//결과출력 price id
	 //[st1.selectedIndex]
	 var stv = st1.options[st1.selectedIndex].value;

	 if(stv=="no"){
		 rt1.value="시술내역입력";
		 rt2.value="0";
	 }
	 else if(stv=="남성 기본 컷"){
		rt1.value="남성 기본 컷";
		rt2.value="29000원";		 	 
	 }
	 else if(stv=="여성 기본 컷"){
		rt1.value="여성 기본 컷";
		rt2.value="39000원";		 	 
	 }
	 else if(stv=="앞머리컷"){
		rt1.value="앞머리컷";
		rt2.value="5000원";		 	 
	 }
	 else if(stv=="앞머리펌"){
		rt1.value="앞머리펌";
		rt2.value="20000원";		 	 
	 }
	 else if(stv=="남성 일반펌"){
		rt1.value="남성 일반펌";
		rt2.value="60000원";		 	 
	 }
	 else if(stv=="여성 일반펌"){
		rt1.value="여성 일반펌";
		rt2.value="80000원";		 	 
	 }
	 else if(stv=="전체염색"){
		rt1.value="전체염색";
		rt2.value="80000원";		 	 
	 }
	 else if(stv=="기본 클리닉"){
		rt1.value="기본 클리닉";
		rt2.value="50000원";		 	 
	 } 
	 else{
		rt1.text="에러"; 
	 }
	 
}//p_price-end
function getTime(){
	var aa = document.getElementsByName("time");
	return aa;
}

function time_check(){
	var abc=document.getElementsByName("time");	
	var chk=false;
	
	for(var i=0;i<abc.length;i++){
		if(abc[i].checked==true){
			chk=true;
			break;
		}	
	}

	if(chk==false){
		alert("예약시간을 선택해주세요");
		return false;
	}
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
<%--달력의 FORM 태그 시작--%>


<div>
	<table align="center" border="0">
	
	<tr><td rowspan=2><img src="${image1}"><br><h4 align="center">${tea1}</h4><h5 align="center">${teatext1}<br>${teatext11}</h5></td>
	<td>			
		<table border="0" cellspacing="0" cellpadding="0" align="center" width="350" height="30">
			<tr>
				<td width="60" height="30" align="center">
					<a href="Reservation1_1.do?year=<%= previous_year %>&month=<%= month %>&S_HAIR=<%=S_HAIR %>"><img src="img/lastyear.png" border="0" align="absmiddle"></a>
					<a href="Reservation1_1.do?year=<%= previous_month == 12 ? previous_year : year %>&month=<%= previous_month %>&S_HAIR=<%=S_HAIR%>"><img src="img/lastmonth.png" border="0" align="absmiddle"></a>
				</td>
				<td width="150" align="center" class="title">
				<form method="post" action="Reservation1_1.do?">
					<select name="year"class="title">				
				<%
					for(int i = 0; i<5; i++){
						int temp_year = year + i;
						if (year == temp_year) {
							selected = "selected";
						} else{
							selected = "";
						}
				%>
						<option value = "<%= temp_year %>" <%= selected %>><%= temp_year %></option>
				<%
					}
				%>
					</select>년
					<select name="month" class="title">
				<%
					for(int i = 1; i<=12; i++) {
						if (month == i) {
							selected = "selected";
						} else {
							selected = "";
						}
				%>
						<option value="<%= i %>" <%= selected %>><%= i %></option>
				<%
					}
				%>
					</select>월	
					<input type="hidden" name="S_HAIR" value="<%=S_HAIR%>">		
					<input type="submit" value="이동" style="border-width:1; color:#FFFFFF; background-color:#44A5E9;" class="title">
					</form>
				</td>
				
				<td width="60" height="30" align="center">
					<a href="Reservation1_1.do?year=<%= next_month == 1 ? next_year : year%>&month=<%= next_month %>&S_HAIR=<%=S_HAIR %>"><img src="img/nextmonth.png" border="0" align="absmiddle"></a>
					<a href="Reservation1_1.do?year=<%= next_year %>&month=<%= month %>&S_HAIR=<%=S_HAIR %>"><img src="img/nextyear.png" border="0" align="absmiddle"></a>
				</td>
			</tr>
		</table>
	</td>
	</tr>
	<tr>
	<td>
		<table border="1" cellspacing="0" cellpadding="0" align="center" width="240" height="250">
			
			<tr align="center" valing="middle">
				<td width="30" height="20" class="title"><font color="red">일</font></td>
				<td width="30" height="20" class="title">월</td>
				<td width="30" height="20" class="title">화</td>
				<td width="30" height="20" class="title">수</td>
				<td width="30" height="20" class="title">목</td>
				<td width="30" height="20" class="title">금</td>
				<td width="30" height="20" class="title"><font color="blue">토</font></td>
			</tr>
		<%
			while(true){
				day++;
				
				//날짜를 day 값으로 세팅
				myCalendar.set(myCalendar.DAY_OF_MONTH, day);
				
				//달력의 날짜가 다음달로 넘어가면 day 값이랑 달라짐
				if (day != myCalendar.get(myCalendar.DAY_OF_MONTH)) {
					break;
				}
				
				if (day == 1) {
					out.println("<tr valign='top'>");
					
					//요일 수만큼 빈칸 만들기
					for (int j = 1; j < myCalendar.get(myCalendar.DAY_OF_WEEK); j++) {
						out.print("<td width='30' height='20'>&nbsp;</td>");
					}
				}
				
	//a태그 달기			
				if(t_year < year){//2020	2021
					url ="reservation_only.do?year="+year+"&month="+month+"&day="+day+"&S_HAIR="+S_HAIR;////////////////////////////////////////////////////////수정
					out.println("<td width='30' height='20'><a href="+url+">");	
				}
				else if(t_year == year & t_month < month){//같은년도 3	4
					url ="reservation_only.do?year="+year+"&month="+month+"&day="+day+"&S_HAIR="+S_HAIR;////////////////////////////////////////////////////////수정
					out.println("<td width='30' height='20'><a href="+url+">");	
				}
				else if(t_year == year & t_month == month & t_day <= day){//같은년도 같은 달 1101
					url ="reservation_only.do?year="+year+"&month="+month+"&day="+day+"&S_HAIR="+S_HAIR;////////////////////////////////////////////////////////수정
					out.println("<td width='30' height='20'><a href="+url+">");	
				}
				else{
					out.println("<td width='30' height='20'>");
				}
				
				//요일이 일요일이면 빨간색으로 표시
				if (myCalendar.get(myCalendar.DAY_OF_WEEK) == myCalendar.SUNDAY) {
					out.println("<font color=red>");
					out.println(myCalendar.get(myCalendar.DAY_OF_MONTH));
					out.println("</font>");
					
				} else if (myCalendar.get(myCalendar.DAY_OF_WEEK) == myCalendar.SATURDAY) {
					//요일이 토요일이면 파란색으로 표시					
					out.println("<font color=blue>");
					out.println(myCalendar.get(myCalendar.DAY_OF_MONTH));
					out.println("</font>");
					
				} else if (t_year == year & t_month == month & t_day == day) {
					//오늘일 경우 폰트는 크고 색깔은 검은색으로				
					out.println("<font color=#EE7EAD><b>");
					out.println(myCalendar.get(myCalendar.DAY_OF_MONTH));
					out.println("</b></font>");
				
				} else {
					out.println(myCalendar.get(myCalendar.DAY_OF_MONTH));
				}
		 		
/* -------------- out.println("</a></td>"); */
				if(t_year < year){//2020	2021
					out.println("</a></td>");
				}
				else if(t_year == year & t_month < month){//같은년도 3	4
					out.println("</a></td>");
				}
				else if(t_year == year & t_month == month & t_day <= day){//같은년도 같은 달 1101
					out.println("</a></td>");
				}
				else{
					out.println("</td>");
				}				


				//요일이 토요일이면 다음줄로 내림
				if (myCalendar.get(myCalendar.DAY_OF_WEEK) == myCalendar.SATURDAY) {
					out.println("</tr>");
				}
			}
			
			//마지막이 토요일로 끝나지 않았으면 줄을 닫아줌
			if (myCalendar.get(myCalendar.DAY_OF_WEEK) != myCalendar.SATURDAY){
				out.println("</tr>");
			}
	
		%>
		</table>
	</td></tr>
	</table>

	<form id="#7" action="reservation2.do" enctype="multipart/form-data" method="post" onsubmit="return time_check()"><br>	
	
	<table border=0 width="550" height="70" cellspacing="0" cellpadding="0" align="center">
		<tr>
		<td width="220">&nbsp;&nbsp;</td>
		<td align="left"  width="330" >
<% if(sw==1){%>	
		
			&nbsp;&nbsp;<input type="radio" name="time" value="10" <%=str[0]%> OnClick="hair.style.display='block';""> 10:00
			&nbsp;&nbsp;<input type="radio" name="time" value="11" <%=str[1]%> OnClick="hair.style.display='block';"> 11:00	
			&nbsp;&nbsp;<input type="radio" name="time" value="12" <%=str[2]%> OnClick="hair.style.display='block';"> 12:00
			&nbsp;&nbsp;<input type="radio" name="time" value="13" <%=str[3]%> OnClick="hair.style.display='block';"> 13:00<br>
			&nbsp;&nbsp;<input type="radio" name="time" value="14" <%=str[4]%> OnClick="hair.style.display='block';"> 14:00
			&nbsp;&nbsp;<input type="radio" name="time" value="15" <%=str[5]%> OnClick="hair.style.display='block';"> 15:00
			&nbsp;&nbsp;<input type="radio" name="time" value="16" <%=str[6]%> OnClick="hair.style.display='block';"> 16:00
			&nbsp;&nbsp;<input type="radio" name="time" value="17" <%=str[7]%> OnClick="hair.style.display='block';"> 17:00<br>
			&nbsp;&nbsp;<input type="radio" name="time" value="18" <%=str[8]%> OnClick="hair.style.display='block';"> 18:00
			&nbsp;&nbsp;<input type="radio" name="time" value="19" <%=str[9]%> OnClick="hair.style.display='block';"> 19:00
			&nbsp;&nbsp;<input type="radio" name="time" value="20" <%=str[10]%> OnClick="hair.style.display='block';"> 20:00
	<%}%>
		
			</td>
		</tr>
	</table>
	
	<div id="hair" style="display:none"> <!-- style="display:none" -->
	<br>
	<table border="0" width="330" cellspacing="0" cellpadding="0" align="center">		
		<tr>
		<td align="center"><select id="style1" name="S_style" onchange="p_price();changeSelect()" > 	
				<option value="no" selected>희망 시술 선택</option>
				<option value="남성 기본 컷">남성 기본 컷</option>
				<option value="여성 기본 컷">여성 기본 컷</option>
				<option value="앞머리컷">앞머리컷</option>
				<option value="앞머리펌">앞머리펌</option>
				<option value="남성 일반펌">남성 일반펌</option>
				<option value="여성 일반펌">여성 일반펌</option>
				<option value="전체염색">전체염색</option>
				<option value="기본 클리닉">기본 클리닉</option>
				</select>
		</td>
		</tr>
	</table><br><br>
	
	<table border="0" width="500" cellspacing="0" cellpadding="0" align="center">	
		<tr><td align="center">희망 스타일 업로드<br>
		<br><br>
			<input type="file"id="file1" accept=".gif, .jpg, .png">&nbsp;&nbsp;
			<input type="reset" value="취소">
	
		</td>
		<td>
		<img id="image1" alt="미리보기" width=150 height=200>
		</td>
		</tr>
	</table><br><br>
	</div>

	<div id="result" style="display:none">
	
		<table border=0 width="100" cellspacing="0" cellpadding="0" align="center">
			<tr><td>
			선택내역
			</td>
			<td>
			&nbsp;
			</tr>
			<tr><td>
			<input type="text" id="#1" name="select_style"  readonly="readonly" value="시술선택내역">
			</td>
			<td>
			<input type="submit" value="확인" >
			</td>
			</tr>
			<tr><td>
			<input type="text" id="#2" name="price3" readonly="readonly" value="">
			 <td>
			<input type="reset" value="취소"> 
			</td>
			</tr>		
		</table>
	<input type="hidden" name="S_DATE3" value="${requestScope.date}">
	<input type="hidden" name="S_HAIR3" value="${requestScope.S_HAIR}">
	</div>
	</form>
	
	
	
</div>

</body>
</html>