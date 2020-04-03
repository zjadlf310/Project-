package com.hairshop.contorller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hairshop.dao.HairDAO;
import com.hairshop.vo.HairVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class controller
 */
@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String uri;    
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String c = request.getRequestURI().substring(request.getContextPath().length());
		
		switch(c) {
		case "/reviewoutput.do":
			
			ArrayList<HairVO> ar1 = null;
			try {
				ar1 = new HairDAO().ReviewOutput();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				request.setAttribute("error", e.getMessage());
				uri ="Error.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(uri);
				rd.forward(request, response);
			}
			
			uri ="Review.jsp";
			request.setAttribute("reviewoutput", ar1);
			break;
			
		case "/reviewinput.do":
			
			HairVO in = (HairVO) request.getAttribute("hvo");
			Date date = new Date();
			String text = (String)request.getAttribute("text");
			Boolean input = false;
			try {
				input = new HairDAO().ReviewInput(in.getReservation_id(), date, in.getReservation_hair(), in.getReservation_style(), text);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				request.setAttribute("error", e1.getMessage());
				uri ="Error.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(uri);
				rd.forward(request, response);
			}
			if(input) {
				request.setAttribute("errorcheck", "review00");
				uri ="Errorcheck.jsp";
			}else {
				request.setAttribute("errorcheck", "review03");
				uri ="Errorcheck.jsp";
			}
			break;
			
		
		case "/login.do":
			
			String id2 = request.getParameter("id");
			String pw2 = request.getParameter("pw");
			boolean b1 = false;
			try {
				b1 = new HairDAO().login(id2, pw2);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				request.setAttribute("error", e.getMessage());
				uri ="Error.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(uri);
				rd.forward(request, response);
			}
			
			if(b1) {
				HttpSession ses = request.getSession();
				HairVO session = null;
				try {
					session = new HairDAO().session(id2);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					request.setAttribute("error", e.getMessage());
					uri ="Error.jsp";
					RequestDispatcher rd = request.getRequestDispatcher(uri);
					rd.forward(request, response);
				}
				ses.setAttribute("session1", session);
				uri = "Main.jsp";
			}else {
				request.setAttribute("errorcheck", "login02");
				uri = "Errorcheck.jsp";
			}
			break;
			
		case "/logincheck.do":
			HttpSession ses = request.getSession(false);
			if(ses == null || ses.getAttribute("session1") == null) {
				request.setAttribute("errorcheck", "login01");
				uri = "Errorcheck.jsp";
			}else {
				uri = "Reservation.jsp";
			}
			
		
			break;
			
		case "/doubleIDcheck.do":
			String join_id = request.getParameter("idcheck");
			
			boolean b2 = false;
			try {
				b2 = new HairDAO().doubleIDcheck(join_id);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				request.setAttribute("error", e.getMessage());
				uri ="Error.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(uri);
				rd.forward(request, response);
			}
			
			if(b2) {
				
				request.setAttribute("errorcheck", "idcheck01");
				uri = "Errorcheck.jsp";
			}else {
				request.setAttribute("errorcheck", "idcheck02");
				uri = "Errorcheck.jsp";
			}

			
			break;
			
		case "/newID.do":
			
			
			String name3 = request.getParameter("join_name");
			String id3 = request.getParameter("join_id");
			String pw3 = request.getParameter("join_pw");
			String tel3 = request.getParameter("join_tel");
			String mail3 = request.getParameter("join_mail");
			HairVO newid = new HairVO(name3,id3,pw3,tel3,mail3);
			boolean b3 = false;
			try {
				b3 = new HairDAO().newID(newid);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				request.setAttribute("error", e.getMessage());
				uri ="Error.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(uri);
				rd.forward(request, response);
			}
			
			if(b3) {
				request.setAttribute("errorcheck", "signup01");
				uri = "Errorcheck.jsp";
			}else {
				request.setAttribute("errorcheck", "signup02");
				uri = "Errorcheck.jsp";
			}
			break;
			
		case "/searchID.do":

			String name = request.getParameter("S_name");
			String tel = request.getParameter("S_pnum");
			String sname = null;
			try {
				sname = new HairDAO().SearchID(name, tel);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				request.setAttribute("error", e.getMessage());
				uri ="Error.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(uri);
				rd.forward(request, response);
			}
			
			if(sname!=null) {
				request.setAttribute("sname", sname);
				request.setAttribute("errorcheck", "searchid01");
				uri = "Errorcheck.jsp";
			}else {
				
				request.setAttribute("errorcheck", "searchid02");
				uri = "Errorcheck.jsp";
			}
			
			break;
			
		case "/checkID.do":
			
			String name2 = request.getParameter("S_name");
			String ID = request.getParameter("S_id");
			String tel2 = request.getParameter("S_pnum");
			
			boolean b4 = false;
			try {
				b4 = new HairDAO().checkID(ID, name2, tel2);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				request.setAttribute("error", e.getMessage());
				uri ="Error.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(uri);
				rd.forward(request, response);
			}
			if(b4) {
				HttpSession ses2 = request.getSession();
				ses2.setAttribute("id", ID);
				request.setAttribute("errorcheck", "checkid01");
				uri = "Errorcheck.jsp";
			}else {
				request.setAttribute("errorcheck", "checkid02");
				uri = "Errorcheck.jsp";
			}
			break;
			
		case "/updatePW.do":
			
			String id = (String) request.getSession().getAttribute("id");
			String pw = request.getParameter("pw");
			
			boolean b5 = false;
			try {
				b5 = new HairDAO().updatePW(pw,id);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				request.setAttribute("error", e.getMessage());
				uri ="Error.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(uri);
				rd.forward(request, response);
			}
			
			if(b5) {
				request.setAttribute("errorcheck", "password01");
				uri = "Errorcheck.jsp";
				
			}else {
				request.setAttribute("errorcheck", "password02");
				uri = "Errorcheck.jsp";
			}
			break;
			
		case"/Reservation.do"://선생님들 화면에서 개인 선생님으로 넘길 때 
			String S_HAIR=request.getParameter("S_HAIR");
			String image1=null;
			String  tea1=null;
			String error=null;
			String teatext1 = null;
			String teatext11 = null;
		
			if (S_HAIR.equals("Cat")) {
				image1="img/cat.png";
				tea1="고양이 선생님";
				teatext1="- 컬러리스트 자격증 보유";
				teatext11=" - 염색, 탈색 전문";
			}
			else if(S_HAIR.equals("Dog")) {
				image1="img/dog.png";
				tea1="강아지 선생님";
				teatext1="- 복구펌 마스터  ";
				teatext11 = "- 여성 숏컷 전문";
			}
			else if(S_HAIR.equals("Elephant")) {
				image1="img/elephant.png";
				tea1="코끼리 선생님";
				teatext1="- 커트의 본고장 호주 유학파";
				teatext11="- 여성 컷 전문";
			}
			else if(S_HAIR.equals("Frog")) {
				image1="img/frog.png";
				tea1="개구리 선생님";
				teatext1="- 조선,프라자,르네상스 호텔 이발사 출신";
				teatext11="- 남성 컷 전문";
			}
			else if(S_HAIR.equals("Giraffe")) {
				image1="img/giraffe.png";
				tea1="기린 선생님";
				teatext1="- 가즈아예술대학교 미용학부 외래교수";
				teatext11="- 스타일링 문의 대환영";
			}
			else if(S_HAIR.equals("Hippo")) {
				image1="img/hippo.png";
				tea1="하마 선생님";
				teatext1="- 가즈아 헤어 아카데미 교육강사";
				teatext11="- 열펌 전문";
			}
			else if(S_HAIR.equals("Sheep")) {
				image1="img/sheep.png";
				tea1="양 선생님";
				teatext1="- 양쌤한테서만! 첫 방문 40% 행사!";
				teatext11="- 뿌리염색 전문";
			}
			else if(S_HAIR.equals("Eagle")) {
				image1="img/eagle.png";
				tea1="독수리 선생님";
				teatext1="- 수년간의 노하우를 가진 열펌 전문가!";
				teatext11="- 탈색 전문가 교육 이수";
			}
			else if(S_HAIR.equals("Tiger")){
				image1="img/tiger.png";
				tea1="호랑이 선생님";
				teatext1="- 마법의 손으로 컷트를 완성해드립니다";
				teatext11="- 앞머리컷 문의 환영";
			}
			
			int [] soo1 = null;
			
			request.setAttribute("sw", 0);	
			request.setAttribute("soo", soo1);	
			request.setAttribute("image1", image1);
			request.setAttribute("tea1", tea1);
			request.setAttribute("error", error);
			request.setAttribute("S_HAIR", S_HAIR );
			request.setAttribute("teatext1", teatext1);
			request.setAttribute("teatext11", teatext11 );
			uri="Reservation2.jsp";
			break;
			
		case"/Reservation1_1.do"://달력 만질 때
			String S_HAIR1_1=request.getParameter("S_HAIR");
			String image1_1=null;
			String tea1_1=null;
			String error1_1=null;
			String teatext1_1 = null;
			String teatext11_1 = null;
			String year1_1=request.getParameter("year");
			String month1_1=request.getParameter("month");
			
			if (S_HAIR1_1.equals("Cat")) {
				image1_1="img/cat.png";
				tea1_1="고양이 선생님";
				teatext1_1="- 컬러리스트 자격증 보유";
				teatext11_1=" - 염색, 탈색 전문";
			}
			else if(S_HAIR1_1.equals("Dog")) {
				image1_1="img/dog.png";
				tea1_1="강아지 선생님";
				teatext1_1="- 복구펌 마스터  ";
				teatext11_1= "- 여성 숏컷 전문";
			}
			else if(S_HAIR1_1.equals("Elephant")) {
				image1_1="img/elephant.png";
				tea1_1="코끼리 선생님";
				teatext1_1="- 커트의 본고장 호주 유학파";
				teatext11_1="- 여성 컷 전문";
			}
			else if(S_HAIR1_1.equals("Frog")) {
				image1_1="img/frog.png";
				tea1_1="개구리 선생님";
				teatext1_1="- 조선,프라자,르네상스 호텔 이발사 출신";
				teatext11_1="- 남성 컷 전문";
			}
			else if(S_HAIR1_1.equals("Giraffe")) {
				image1_1="img/giraffe.png";
				tea1_1="기린 선생님";
				teatext1_1="- 가즈아예술대학교 미용학부 외래교수";
				teatext11_1="- 스타일링 문의 대환영";
			}
			else if(S_HAIR1_1.equals("Hippo")) {
				image1_1="img/hippo.png";
				tea1_1="하마 선생님";
				teatext1_1="- 가즈아 헤어 아카데미 교육강사";
				teatext11_1="- 열펌 전문";
			}
			else if(S_HAIR1_1.equals("Sheep")) {
				image1_1="img/sheep.png";
				tea1_1="양 선생님";
				teatext1_1="- 양쌤한테서만! 첫 방문 40% 행사!";
				teatext11_1="- 뿌리염색 전문";		
			}
			else if(S_HAIR1_1.equals("Eagle")) {
				image1_1="img/eagle.png";
				tea1_1="독수리 선생님";
				teatext1_1="- 수년간의 노하우를 가진 열펌 전문가!";
				teatext11_1="- 탈색 전문가 교육 이수";		
			}
			else {
				image1_1="img/tiger.png";
				tea1_1="호랑이 선생님";
				teatext1_1="- 마법의 손으로 컷트를 완성해드립니다";
				teatext11_1="- 앞머리컷 문의 환영";
			}	
			
			int [] soo1_1 = null;
			
			request.setAttribute("sw", 0);	
			request.setAttribute("soo", soo1_1);	
			request.setAttribute("image1", image1_1);
			request.setAttribute("tea1", tea1_1);
			request.setAttribute("error", error1_1);
			request.setAttribute("S_HAIR", S_HAIR1_1 );
			request.setAttribute("year", year1_1 );
			request.setAttribute("month", month1_1 );
			request.setAttribute("teatext1", teatext1_1);
			request.setAttribute("teatext11", teatext11_1 );
			uri="Reservation2.jsp";
			break;
			

		case"/reservation_only.do": //날짜 넘어갈 때
			
			String error1=null;
			HairDAO Hdao1 = null;
			Hdao1 = new HairDAO();
				
			int year= Integer.parseInt(request.getParameter("year"));
			int month=Integer.parseInt(request.getParameter("month"));			
			int day=Integer.parseInt(request.getParameter("day"));				
			String S_HAIR1=request.getParameter("S_HAIR");
			String re_day=null;
			String teatext1_2 = null;
			String teatext11_2 = null;
			if(month<10) {
				re_day=year+"-"+"0"+month;
			}else {
				re_day=year+"-"+month;
			}			
			if(day<10) {
				re_day += "-0"+day;
			}else {
				re_day += "-"+day;
			}
			
					
			ArrayList<HairVO> alist1 = null;
			try {
				alist1=Hdao1.Schedule_info(S_HAIR1,re_day);
			} catch (SQLException | ClassNotFoundException e) {
				request.setAttribute("error", e.getMessage());
				uri ="Error.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(uri);
				rd.forward(request, response);
			} 
			
			//s_time 집합
			int [] soo = new int[alist1.size()];
			if (alist1 != null) {
				for(int i=0; i<alist1.size();i++) {
					soo[i]=alist1.get(i).getS_TIME();
				}
			}
			
			String image11=null;
			String  tea11=null;
			if (S_HAIR1.equals("Cat")) {
				image11="img/cat.png";
				tea11="고양이 선생님";
				teatext1_2 = "- 컬러리스트 자격증 보유";
				teatext11_2 = " - 염색, 탈색 전문";
			}
			else if(S_HAIR1.equals("Dog")) {
				image11="img/dog.png";
				tea11="강아지 선생님";
				teatext1_2 = "- 복구펌 마스터  ";
				teatext11_2 = "- 여성 숏컷 전문";
			}
			else if(S_HAIR1.equals("Elephant")) {
				image11="img/elephant.png";
				tea11="코끼리 선생님";
				teatext1_2 = "- 커트의 본고장 호주 유학파";
				teatext11_2 = "- 여성 컷 전문";
			}
			else if(S_HAIR1.equals("Frog")) {
				image11="img/frog.png";
				tea11="개구리 선생님";
				teatext1_2 = "- 조선,프라자,르네상스 호텔 이발사 출신";
				teatext11_2 = "- 남성 컷 전문";
			}
			else if(S_HAIR1.equals("Giraffe")) {
				image11="img/giraffe.png";
				tea11="기린 선생님";
				teatext1_2 = "- 가즈아예술대학교 미용학부 외래교수";
				teatext11_2 = "- 스타일링 문의 대환영";
			}
			else if(S_HAIR1.equals("Hippo")) {
				image11="img/hippo.png";
				tea11="하마 선생님";
				teatext1_2 = "- 가즈아 헤어 아카데미 교육강사";
				teatext11_2 = "- 열펌 전문";
			}
			else if(S_HAIR1.equals("Sheep")) {
				image11="img/sheep.png";
				tea11="양 선생님";
				teatext1_2 = "- 양쌤한테서만! 첫 방문 40% 행사!";
				teatext11_2 = "- 뿌리염색 전문";	
			}
			else if(S_HAIR1.equals("Eagle")) {
				image11="img/eagle.png";
				tea11="독수리 선생님";
				teatext1_2 = "- 수년간의 노하우를 가진 열펌 전문가!";
				teatext11_2 = "- 탈색 전문가 교육 이수";	
			}
			else {
				image11="img/tiger.png";
				tea11="호랑이 선생님";
				teatext1_2 = "- 마법의 손으로 컷트를 완성해드립니다";
				teatext11_2 = "- 앞머리컷 문의 환영";
			}	
			Calendar cal=Calendar.getInstance();
			int toyear=cal.get(Calendar.YEAR);
			int tomonth=cal.get(Calendar.MONTH)+1;
			int today=cal.get(Calendar.DAY_OF_MONTH);
			int tohour=cal.get(Calendar.HOUR_OF_DAY);
			
			//어차피 숫자합으로 계산해도 차이는 없음.
			int todayo=toyear+tomonth+today;//오늘
			int reday=year+month+day;//예약 당일 
			
			String [] str={"true","true","true","true","true","true","true","true","true","true","true"};
		
		if(todayo == reday) {//예약할 날짜가 오늘로 선택 되었다면
			//한번 돌리고
			
			
			if(tohour>=20) {
				str[10]="disabled";
			}if(tohour>=19) {
				str[9]="disabled";
			}if(tohour>=18) {
				str[8]="disabled";
			}if(tohour>=17) {
				str[7]="disabled";
			}if(tohour>=16) {
				str[6]="disabled";
			}if(tohour>=15) {
				str[5]="disabled";
			}if(tohour>=14) {
				str[4]="disabled";
			}if(tohour>=13) {
				str[3]="disabled";
			}if(tohour>=12) {
				str[2]="disabled";
			}if(tohour>=11) {
				str[1]="disabled";
			}if(tohour>=10)
				str[0]="disabled";
			
			
			//오늘 예약된 일정이 있으면 또 한번 있는거 색출해내고 아웃
			for(int i=0;i<soo.length;i++){	
				if (soo[i]==10 ) {
					str[0]="disabled";
				}else if(soo[i]==11){
					str[1]="disabled";
				}else if(soo[i]==12){
					str[2]="disabled";
				}else if(soo[i]==13){
					str[3]="disabled";
				}else if(soo[i]==14){
					str[4]="disabled";
				}else if(soo[i]==15){
					str[5]="disabled";
				}else if(soo[i]==16){
					str[6]="disabled";
				}else if(soo[i]==17){
					str[7]="disabled";
				}else if(soo[i]==18){
					str[8]="disabled";
				}else if(soo[i]==19){
					str[9]="disabled";
				}else if(soo[i]==20){
					str[10]="disabled";
				}				
			}
			
		}else {//오늘 선택 안됬을 때 이전 날들은 태그 다 때 버려서 이후 날들만 나옴.
			for(int i=0;i<soo.length;i++){	
				if (soo[i]==10 ) {
					str[0]="disabled";
				}else if(soo[i]==11){
					str[1]="disabled";
				}else if(soo[i]==12){
					str[2]="disabled";
				}else if(soo[i]==13){
					str[3]="disabled";
				}else if(soo[i]==14){
					str[4]="disabled";
				}else if(soo[i]==15){
					str[5]="disabled";
				}else if(soo[i]==16){
					str[6]="disabled";
				}else if(soo[i]==17){
					str[7]="disabled";
				}else if(soo[i]==18){
					str[8]="disabled";
				}else if(soo[i]==19){
					str[9]="disabled";
				}else if(soo[i]==20){
					str[10]="disabled";
				}				
			}
		}
			request.setAttribute("sw", 1);
			request.setAttribute("S_HAIR", S_HAIR1);
			request.setAttribute("image1", image11);
			request.setAttribute("tea1", tea11);
			request.setAttribute("date", re_day);//string 날짜
			request.setAttribute("error",error1);//s_time 할꺼야
			request.setAttribute("str",str);//s_time 할꺼야
			request.setAttribute("teatext1", teatext1_2);
			request.setAttribute("teatext11", teatext11_2 );
			uri="Reservation2.jsp";
					
			break;			
			
		case"/reservation2.do": //예약2 확인버튼 클릭 시
		
			//예약할 선생님 이름/시술할 정보/시술 날짜 /시술 시간/시술 사진
	//파일 받기
			String username = System.getProperty("user.name");
			String bang1="c:/Users/"+username+"/Downloads/";
		    String encType1 = "UTF-8";
		    int maxFilesize1 = 100 * 1024 * 1024;
 
			// MultipartRequest(request, 저장경로[, 최대허용크기, 인코딩케릭터셋, 동일한 파일명 보호 여부])
		    MultipartRequest mr1 = new MultipartRequest(request, bang1, maxFilesize1,
		            encType1, new DefaultFileRenamePolicy());
	
		    File file11 = mr1.getFile("file1");
		    
			
		//받은내용
		String S_HAIR2=mr1.getParameter("S_HAIR3");
		String S_STYLE=mr1.getParameter("S_style");
		String S_date=mr1.getParameter("S_DATE3"); //시술 날짜 only.do부터 이어받아온 것
		String S_TIME=mr1.getParameter("time");//시술 시간
		String price=mr1.getParameter("price3");// 거기에 맞는 가격
		System.out.println(S_TIME);
	    request.setAttribute("S_HAIR",S_HAIR2);
		request.setAttribute("S_STYLE",S_STYLE);
		request.setAttribute("S_DATE", S_date);
		request.setAttribute("S_TIME",S_TIME);
		request.setAttribute("photo", price);
		request.setAttribute("price", price);
		
		//check
		uri="Reservation3.jsp";
		
		break;
			
	case"/reservation3.do":	//예약 3번에서 결제하기 눌렀을 때
		//총 10개
		String name1=request.getParameter("W_name");//예약 고객 이름
		//session하고 중복
		String tel1=request.getParameter("W_tel");//예약 고객 연락처 <<이거 바뀔 수 있음 주의!
	    //이건 바뀔수 있어야 하지 않나? 택배를 다른 번호로 붙이듯이
		String mail=request.getParameter("W_mail"); //예약 고객 메일 보내기용
	    //session하고 중복
		String text1=request.getParameter("W_text");//용량제한 걸려있을꺼 같구.
	    
		/*String text2=request.getParameter("W_text2");//최종 선택 내역 밑 가격 확인란 이것도 메일 보내기용
	    //선택내역은 굳이 안받아도 됨.*/
		
	    //hidden
		//이것들도 계속 끌고옴 아직 결제 안 끝낫음.
	    String S_HAIR21=request.getParameter("S_HAIR");
		String S_STYLE1=request.getParameter("S_STYLE");
		String sday11=request.getParameter("S_DATE");
		String S_TIME1=request.getParameter("S_TIME");
		String photo1=request.getParameter("photo");
		String price1=request.getParameter("price");
			

		request.setAttribute("W_name",name1);//예약 고객 이름
		request.setAttribute("W_tel",tel1);//예약 고객 연락처 <<이거 바뀔 수 있음 주의!
	    request.setAttribute("W_mail",mail); //예약 고객 메일 보내기용
	    request.setAttribute("W_text",text1);//용량제한 걸려있을꺼 같구.
	    /*request.setAttribute("W_text2",text2);//최종 선택 내역 밑 가격 확인란 이것도 메일 보내기용
		*/
	    request.setAttribute("S_HAIR",S_HAIR21);
		request.setAttribute("S_STYLE",S_STYLE1);
		request.setAttribute("S_DATE",sday11);
		request.setAttribute("S_TIME",S_TIME1);
		request.setAttribute("photo",photo1);
		request.setAttribute("price",price1);
		
		
		uri="Payment.jsp";
		break;
			
	case"/Payment.do":  //결제창에서 결제 완료 눌럿을 때

		
		HairVO	login11=(HairVO) request.getSession().getAttribute("session1");
		String rename=login11.getName();
		String reid=login11.getId();
		String mail1=login11.getMail();
		
		//예약 고객 이름 중복 String name1=request.getParameter("W_name");
		String tel11=request.getParameter("W_tel");//예약 고객 연락처 <<이거 바뀔 수 있음 주의!
	    String text11=request.getParameter("W_text");//용량제한 걸려있을꺼 같구.
	     
	    //이것들도 계속 끌고옴 아직 결제 안 끝낫음.
	    String S_HAIR211=request.getParameter("S_HAIR");
		String S_STYLE11=request.getParameter("S_STYLE");
		String sday111=request.getParameter("S_DATE");
		int S_TIME11=Integer.parseInt(request.getParameter("S_TIME"));
		String S_photo11=request.getParameter("photo");
		String price11=request.getParameter("price");
		
			
		HairDAO Hdao11 = null;
			Hdao11 = new HairDAO();
		//선생님들 스케줄표에 insert하기
		try {
			Hdao11.S_insert(S_HAIR211, S_STYLE11, sday111, S_TIME11);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			request.setAttribute("error", e.getMessage());
			uri ="Error.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(uri);
			rd.forward(request, response);
		}
			
		//예약 테이블에 예약 자료 넣기
		try {
			Hdao11.RE_insert(rename,reid,tel11,sday111,S_HAIR211,S_STYLE11,S_photo11);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			request.setAttribute("error", e.getMessage());
			uri ="Error.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(uri);
			rd.forward(request, response);
		}
		
		//메일 보내기 필요
		/*request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");*/
        try {
/*            String mail_from =  m_name + "<" + m_email + ">";
            String mail_to =    "admin<admin@83rpm.com>";
            String title =      "hosting.83rpm.com 요청사항 :: " + m_title;
            String contents =   "보낸 사람 :: " + m_name + "&lt;" + m_email + "&gt;<br><br>" + m_title + "<br><br>" + m_text;
 */
            String mail_from 	= "김윤겸<ep2596@gmail.com>";
            String mail_to 		= mail1;
            String title		= rename+" 님, 가즈아 헤어 살롱에"+sday111+" 일 일정이 예약되었습니다.";
            String contents =   "보낸 사람 :: < kazzza hair salon > &lt;<br><br>" + S_STYLE11 +"-"+ price11 + "&gt;<br><br>" +sday111+ S_TIME11 + "시 <br><br> 요청사항 ::" + 
            					text11+"<br><br> 저희 'kazzza hair salon'을 이용해주셔서 감사합니다.";

            
            mail_from = new String(mail_from.getBytes("UTF-8"), "UTF-8");
            mail_to = new String(mail_to.getBytes("UTF-8"), "UTF-8");
 
            Properties props = new Properties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.fallback", "false");
            props.put("mail.smtp.auth", "true");
 
            Authenticator auth = new SMTPAuthenticator();
 
            Session sess = Session.getDefaultInstance(props, auth);
 
            MimeMessage msg = new MimeMessage(sess);
 
            msg.setFrom(new InternetAddress(mail_from));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(mail_to));
            msg.setSubject(title, "UTF-8");
            msg.setContent(contents, "text/html; charset=UTF-8");
            msg.setHeader("Content-type", "text/html; charset=UTF-8");
 
            Transport.send(msg);
            
            uri="Main.jsp";
        } catch (Exception e) {
        	request.setAttribute("error", e.getMessage());
			uri ="Error.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(uri);
			rd.forward(request, response);
        } 
        	break;
        	
			case"/Logout.do":
				HttpSession sess = request.getSession();
				sess.invalidate();//세션데이터 초기화
				uri="Main.jsp";
				break;
		
		}
		RequestDispatcher rd = request.getRequestDispatcher(uri);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
